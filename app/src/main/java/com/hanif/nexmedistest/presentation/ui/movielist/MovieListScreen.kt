package com.hanif.nexmedistest.presentation.ui.movielist

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults.assistChipColors
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.hanif.nexmedistest.R
import com.hanif.nexmedistest.data.pref.PrefManager
import com.hanif.nexmedistest.domain.model.MovieCategoryView
import com.hanif.nexmedistest.domain.model.MovieListView
import com.hanif.nexmedistest.presentation.ui.components.ReusableComponents
import com.hanif.nexmedistest.presentation.ui.main.viewmodel.MainViewModel
import com.hanif.nexmedistest.presentation.ui.movielist.viewmodel.MovieListViewModel
import kotlinx.coroutines.delay

@Composable
fun MovieListScreen(viewModel: MovieListViewModel, prefManager: PrefManager, mainVM: MainViewModel) {

    LaunchedEffect(Unit) {
        mainVM.getBaseConfiguration()
        delay(2000)
        viewModel.getMovieCategory()
        viewModel.getPopularMovie()
    }

    val movieList by viewModel.observeMovies().observeAsState()
    val categoryList by viewModel.observeMovieCategory().observeAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 8.dp)
    ) {
        ReusableComponents().SearchBar(viewModel.searchQuery.value, onQueryChange = {
            viewModel.searchMovies(it)
        })
        MovieCategoryList(
            categories = categoryList?.data?: listOf(),
            selectedCategory = viewModel.selectedCategory.value,
            onCategorySelected = {
                viewModel.selectedCategory.value = it
                if (it.id == -999) {
                    viewModel.getPopularMovie()
                } else {
                    viewModel.discoverMovieByGenre(it.id)
                }
            }
        )

        MovieList(
            movieList = movieList?.data?: listOf(),
            prefManager = prefManager,
            viewModel = viewModel
        )
    }
}

@Composable
fun MovieCategoryList(categories: List<MovieCategoryView>, selectedCategory: MovieCategoryView? = null, onCategorySelected: (MovieCategoryView) -> Unit) {
    LazyRow (
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        contentPadding = PaddingValues(horizontal = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(categories.size) { index ->
            ChipItem(
                text = categories[index].name,
                selectedCategory = categories[index],
                isSelected = categories[index] == selectedCategory,
                onClick = { onCategorySelected(categories[index]) }
            )
        }
    }
}

@Composable
fun ChipItem(
    text: String,
    selectedCategory: MovieCategoryView,
    isSelected: Boolean,
    onClick: (MovieCategoryView) -> Unit
) {
    Surface(
        modifier = Modifier
            .clickable { onClick(selectedCategory) },
        shape = RoundedCornerShape(16.dp),
        color = if (isSelected) Color.Blue else Color.LightGray,
        border = if (isSelected) null else BorderStroke(1.dp, MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f))
    ) {
        Text(
            text = text,
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp),
            color = if (isSelected) Color.White else Color.Gray
        )
    }
}

@Composable
fun MovieList(
    movieList: List<MovieListView>,
    prefManager: PrefManager,
    viewModel: MovieListViewModel
) {
    val state = rememberLazyGridState()
    LazyVerticalGrid (
        state = state,
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        columns = GridCells.Adaptive(128.dp)
    ) {
        Log.d("MOVIE_LIST", "MovieList: ${movieList.size}")
        items(movieList.size) { index ->
            MovieListItem(movie = movieList[index], prefManager, onClickListener = {
                viewModel.setMovieAsFavorite(it.id)
            })
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MovieListItem(movie: MovieListView, prefManager: PrefManager, onClickListener: (MovieListView) -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .fillMaxHeight()
            .clickable { onClickListener.invoke(movie) }
    ) {
        Surface(
            color = Color.LightGray,
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f))
        ) {
            Column {
                GlideImage(
                    model = "${prefManager.imageBaseUrl}${prefManager.imagePosterSize}${movie.poster_path}",
                    contentDescription = movie.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                ) {
                    it
                        .centerCrop()
                        .placeholder(R.drawable.ic_broken_img)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                }

                Text(
                    text = movie.title,
                    modifier = Modifier.padding(8.dp),
                    color = Color.Black,
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }
    }
}
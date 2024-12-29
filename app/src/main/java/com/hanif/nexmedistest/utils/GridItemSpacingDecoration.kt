package com.hanif.nexmedistest.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 *Created by Ahmad Hanif S on 07/05/22
 */
class GridSpacingItemDecoration(
    private val spanCount: Int,
    private val spacing: Int,
    private val includeEdge: Boolean,
    private val headerNum: Int,
    private val isReverse: Boolean,
    private val lastSpacing: Int = 0
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view) - headerNum
        val totalItemCount = state.itemCount
        if (position >= 0) {
            val column = position % spanCount

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount
                outRect.right = (column + 1) * spacing / spanCount

                if (position < spanCount) {
                    if (isReverse)
                        outRect.bottom = spacing else
                        outRect.top = spacing
                }

                if (isReverse)
                    outRect.top = spacing else
                    outRect.bottom = spacing
            } else {
                outRect.left = column * spacing / spanCount
                outRect.right = spacing - (column + 1) * spacing / spanCount

                if (position >= spanCount) {
                    if (isReverse)
                        outRect.bottom = spacing else
                        outRect.top = spacing
                }
            }
        } else {
            outRect.left = 0
            outRect.right = 0
            outRect.top = 0
            outRect.bottom = 0
        }

        val isLastRow = position >= totalItemCount - spanCount

        if (isLastRow) {
            outRect.bottom = lastSpacing
        } else {
            outRect.bottom = 0
        }
    }

}
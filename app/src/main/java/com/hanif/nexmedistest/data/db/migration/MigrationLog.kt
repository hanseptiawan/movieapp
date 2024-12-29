package com.hanif.nexmedistest.data.db.migration

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

object MigrationLog {

    val MIGRATION_LOG_1_2 = object : Migration(1, 2) {
        override fun migrate(db: SupportSQLiteDatabase) {
            db.execSQL("ALTER TABLE tblmovielist ADD COLUMN movieCost FLOAT NOT NULL DEFAULT 0.0")
        }
    }

}
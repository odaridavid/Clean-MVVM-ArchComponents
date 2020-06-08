/**
 *
 * Copyright 2020 David Odari
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *          http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 **/
package com.github.odaridavid.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//TODO Use one to many for film to characters on next migration
@Entity(tableName = "films")
data class FilmEntity(
    val title: String,
    @ColumnInfo(name = "opening_crawl") val openingCrawl: String,
    @ColumnInfo(name = "fav_id") val favoriteId: Long = 0L,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)
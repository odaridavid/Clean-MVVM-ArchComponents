/**
 *
 * Copyright 2020 David Odari
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *            http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 **/
package com.github.odaridavid.data.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.github.odaridavid.data.local.dao.FavoritesDao
import org.junit.After
import org.junit.Before
import java.io.IOException


open class BaseTest {

    protected lateinit var db: CharactersDatabase
    protected lateinit var favoritesDao: FavoritesDao

    @Before
    open fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, CharactersDatabase::class.java).build()
        favoritesDao = db.favoritesDao()
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        db.close()
    }
}
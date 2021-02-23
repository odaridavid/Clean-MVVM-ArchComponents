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
package com.k0d4black.theforce

import android.app.Application
import com.k0d4black.theforce.feature.main.featureModules
import com.k0d4black.theforce.remote.remoteModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

//TODO Cleanup gradle files
//TODO Setup Unit testing coverage
//TODO Setup Github action + Bitrise with ktlint code check and detekt and deploy to app store
//TODO Create a nice UI
//TODO Write Unit tests and UI tests
//TODO Use Result class
//TODO Better Error Handling and logging of errors
//TODO Try out fast lane
//TODO Update Project Readme
//TODO Animation and navigation transitions
internal class TheForceApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@TheForceApplication)
            modules(
                featureModules + remoteModules
            )
        }
    }
}

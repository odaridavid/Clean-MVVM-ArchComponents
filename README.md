<p align="center">
<img src="art/app_icon.png" alt="home" width="100"/>
</p>
<p align="center">
<img  src="https://travis-ci.com/odaridavid/Clean-MVVM-ArchComponents.svg?branch=develop">&nbsp;
<a href="https://codecov.io/gh/odaridavid/Clean-MVVM-ArchComponents">
  <img src="https://codecov.io/gh/odaridavid/Clean-MVVM-ArchComponents/branch/develop/graph/badge.svg" />
</a>&nbsp;
<a href="https://www.codacy.com/manual/odaridavid/Clean-MVVM-ArchComponents?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=odaridavid/Clean-MVVM-ArchComponents&amp;utm_campaign=Badge_Grade"><img src="https://app.codacy.com/project/badge/Grade/e9536c1df39b482b863f152654e14fa5"/></a>
</p>

# The-Force

An Android app consuming [a Star Wars API](https://swapi.dev/) to display movie characters,show your
recent searches and save some of your favorite characters.

Min Api Level : 21 [Supports Over 87% Devices ](https://developer.android.com/about/dashboards)

Build System : [Gradle](https://gradle.org/)

## Table of Contents

- [Prerequisite](#prerequisite)
- [Architecture](#architecture)
- [Testing](#testing)
- [Libraries](#libraries)
- [Contributors](#contributors)
- [Related Posts](#related-posts)
- [Demo](#demo)

## Prerequisite

Android Studio 4.0 +

## Architecture

TODO

## Testing

TODO

## Libraries

Libraries used in the whole application are:

- [Jetpack](https://developer.android.com/jetpack)🚀
  - [Viewmodel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Manage UI related data in a lifecycle conscious way 
  and act as a channel between use cases and ui
  - [Data Binding](https://developer.android.com/topic/libraries/data-binding) - support library that allows binding of UI components in  layouts to data sources,binds character details and search results to UI
  - [Room](https://developer.android.com/training/data-storage/room) - Provides abstraction layer over SQLite
- [Retrofit](https://square.github.io/retrofit/) - type safe http client 
and supports coroutines out of the box.  
- [Moshi](https://github.com/square/moshi) - JSON Parser,used to parse 
requests on the data layer for Entities and understands Kotlin non-nullable 
and default parameters
- [okhttp-logging-interceptor](https://github.com/square/okhttp/blob/master/okhttp-logging-interceptor/README.md) - logs HTTP request and response data.
- [kotlinx.coroutines](https://github.com/Kotlin/kotlinx.coroutines) - Library Support for coroutines,provides `runBlocking` coroutine builder used in tests
- [Truth](https://truth.dev/) - Assertions Library,provides readability as far as assertions are concerned
- [MockWebServer](https://github.com/square/okhttp/tree/master/mockwebserver) - web server for testing HTTP clients ,verify requests and responses on the star wars api with the retrofit client.
- [Leak Canary](https://square.github.io/leakcanary/) - Leak Detection Library
- [Material Design](https://material.io/develop/android/docs/getting-started/) - build awesome beautiful UIs.🔥🔥
- [Firebase](https://firebase.google.com/) - Backend As A Service for faster mobile development.
  - [Crashylitics](https://firebase.google.com/docs/crashlytics) - Provide Realtime crash reports from users end.
- [Koin](https://github.com/InsertKoinIO/koin) - A pragmatic lightweight dependency injection framework for Kotlin
- [Robolectric](http://robolectric.org/) - Unit test on android framework.
- [Espresso](https://developer.android.com/training/testing/espresso) - Test framework to write UI Tests
- [recyclerview-animators](https://github.com/wasabeef/recyclerview-animators) - Recycler View Animations
- [AboutLibraries](https://github.com/mikepenz/AboutLibraries) -provide info on used open source libraries.

## Contributors

- Thanks to [Zafer Celaloglu](https://github.com/zfrc) for the Dagger to Koin Refactor

Feel free to contribute in any way to the project from typos in docs to code review are all welcome.

If its a major refactor open up an issue first and lets chat first about it :)

## Demo

The codebase in most cases will be ahead of whats on the store.

|<img src="art/sh1.png" width=200/>|<img src="art/sh2.png" width=200/>|<img src="art/sh3.png" width=200/>|<img src="art/sh4.png" width=200/>|
|:----:|:----:|:----:|:----:|

|<img src="art/app.gif" width=200/>|
|:----:|

<a href='https://play.google.com/store/apps/details?id=com.k0d4black.theforce&pcampaignid=pcampaignidMKT-Other-global-all-co-prtnr-py-PartBadge-Mar2515-1'><img alt='Get it on Google Play' src='https://play.google.com/intl/en_us/badges/static/images/badges/en_badge_web_generic.png' width='170'/></a>

Google Play and the Google Play logo are trademarks of Google LLC.

## Copyright Notice

Star Wars and all associated names are copyright Lucasfilm ltd.

## Related Posts

[Handling Dynamic Urls](https://davidodari.hashnode.dev/retrofit-handling-dynamic-urls-ck9zygtw700x0ans1tm5spma4)

## License

 ```
   Copyright 2019 David Odari
   
   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 ```

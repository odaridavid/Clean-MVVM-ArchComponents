# The-Force

An Android app consuming [a Star Wars API](https://swapi.co/documentation) to display Movie Characters
it has been built with Clean Architecture, Repository Pattern and MVVM
pattern as well as Architecture Components.

Its Minimum API level is currently at 21 and supports over 87% of 
Android Devices.

## Table of Contents

- [Architecture](#architecture)
- [Persistence](#persistence)
- [Code Analysis](#code-analysis)
- [Testing](#testing)
- [Design](#design)
- [Libraries](#libraries)
- [Extras](#extras)
- [Screenshots](#screenshots)

## Architecture

The Application is split into a three layer architecture:
- Presentation
- Domain
- Data

This provides better abstractions between concrete framework implementations 
and the underlying business logic.

The 3 layered architectural approach is majorly guided by clean architecture which provides
a clear separation of concerns with its Abstraction Principle.

The `domain` and `data` layers are java module libraries as the business 
logic does not rely on the Android frameworks concrete implementations.
This will also help with build performance with a smaller Task Dependency Graph.

#### Presentation

The application presentation layer contains the Activity,Fragments and 
Viewmodels and is represented by the `app` module.

It utilises the navigation architecture components and has a Single 
Activity Architecture serving as the applications entry point and
[NavHost](https://developer.android.com/guide/navigation/navigation-getting-started) 
The fragments are mapped to the available use cases.

This makes it easier to test fragments in isolation, simple provision of 
transition animations to destinations and also removes the need of
working with fragment transactions as its been abstracted with the 
NavController ,saving dev time.

#### Domain

Contains Business Logic Abstractions which consitutes models representative of 
searched character and character details.

#### Data

The data layer contains abstract definitions for the data sources and 
contains abstract definitions of repositories,which will come in handy
during testing when creating test doubles as well as their concrete
implementation.
 
Using the repository pattern we will be able to provide data to the
defined use cases which in this case is searching for characters and
viewing details of selected characters.

This provides a more decoupled system,as it is isolated from changes to the 
db by abstracting low level implementation details of data sources and
changes to the UI.

The repository classes provide means of data access to this use cases by 
delegating to the data source of interest either local data source or a 
remote data source.

## Persistence

 ```TODO```
 
## Code Analysis

 ```TODO```

## Testing

 ```TODO```

## Design

 ```TODO```

## Libraries

Libraries used in the whole application are:
- [Jetpack](https://developer.android.com/jetpack)
  - [Navigation](https://developer.android.com/guide/navigation/) - 
  Caters for in app navigation with the NavController

## Extras

#### Gradle Dependencies

Centralized versioning of gradle dependencies in a global file,
```dependencies.gradle```, visible to all available modules.This helps 
maintain dependency versioning for different modules as well as improve
dependency organisation and readability by providing a clear separation
of which dependencies go where.

## Screenshots

 ```TODO```


## License

 ```TODO```



package com.k0d4black.theforce.sample

val characterDetails = """
    {
      "birth_year": "19 BBY",
      "eye_color": "Blue",
      "films": [
        "https://swapi.co/api/films/1/"
      ],
      "gender": "Male",
      "hair_color": "Blond",
      "height": "172",
      "homeworld": "https://swapi.co/api/planets/1/",
      "mass": "77",
      "name": "Luke Skywalker",
      "skin_color": "Fair",
      "created": "2014-12-09T13:50:51.644000Z",
      "edited": "2014-12-10T13:52:43.172000Z",
      "species": [
        "https://swapi.co/api/species/1/"
      ],
      "starships": [
        "https://swapi.co/api/starships/12/"
      ],
      "url": "https://swapi.co/api/people/1/",
      "vehicles": [
        "https://swapi.co/api/vehicles/14/"
      ]
    }
""".trimIndent()

val characterFilms = """
    {
      "characters": [
        "https://swapi.co/api/people/1/"
      ],
      "created": "2014-12-10T14:23:31.880000Z",
      "director": "George Lucas",
      "edited": "2014-12-12T11:24:39.858000Z",
      "episode_id": 4,
      "opening_crawl": "It is a period of civil war.\n\nRebel spaceships, striking\n\nfrom a hidden base, have won\n\ntheir first victory against\n\nthe evil Galactic Empire.\n\n\n\nDuring the battle, Rebel\n\nspies managed to steal secret\r\nplans to the Empire's\n\nultimate weapon, the DEATH\n\nSTAR, an armored space\n\nstation with enough power\n\nto destroy an entire planet.\n\n\n\nPursued by the Empire's\n\nsinister agents, Princess\n\nLeia races home aboard her\n\nstarship, custodian of the\n\nstolen plans that can save her\n\npeople and restore\n\nfreedom to the galaxy....",
      "planets": [
        "https://swapi.co/api/planets/1/"
      ],
      "producer": "Gary Kurtz, Rick McCallum",
      "release_date": "1977-05-25",
      "species": [
        "https://swapi.co/api/species/1/"
      ],
      "starships": [
        "https://swapi.co/api/starships/2/"
      ],
      "title": "A New Hope",
      "url": "https://swapi.co/api/films/1/",
      "vehicles": [
        "https://swapi.co/api/vehicles/4/"
      ]
    }
""".trimIndent()

val characterPlanet = """
    {
      "climate": "Arid",
      "created": "2014-12-09T13:50:49.641000Z",
      "diameter": "10465",
      "edited": "2014-12-15T13:48:16.167217Z",
      "films": [
        "https://swapi.co/api/films/1/"
      ],
      "gravity": "1",
      "name": "Tatooine",
      "orbital_period": "304",
      "population": "120000",
      "residents": [
        "https://swapi.co/api/people/1/"
      ],
      "rotation_period": "23",
      "surface_water": "1",
      "terrain": "Dessert",
      "url": "https://swapi.co/api/planets/1/"
    }
""".trimIndent()

val characterSpecies = """
    {
      "average_height": "2.1",
      "average_lifespan": "400",
      "classification": "Mammal",
      "created": "2014-12-10T16:44:31.486000Z",
      "designation": "Sentient",
      "edited": "2014-12-10T16:44:31.486000Z",
      "eye_colors": "blue, green, yellow, brown, golden, red",
      "hair_colors": "black, brown",
      "homeworld": "https://swapi.co/api/planets/14/",
      "language": "Shyriiwook",
      "name": "Wookie",
      "people": [
        "https://swapi.co/api/people/13/"
      ],
      "films": [
        "https://swapi.co/api/films/1/"
      ],
      "skin_colors": "gray",
      "url": "https://swapi.co/api/species/3/"
    }
""".trimIndent()

val searchNoMatch = """
    {
      "count": 0,
      "next": null,
      "previous": null,
      "results": []
    }
""".trimIndent()

val searchSuccess = """
    {
      "count": 1,
      "next": null,
      "previous": null,
      "results": [
        {
          "name": "Darth Vader",
          "height": "202",
          "mass": "136",
          "hair_color": "none",
          "skin_color": "white",
          "eye_color": "yellow",
          "birth_year": "41.9BBY",
          "gender": "male",
          "homeworld": "https://swapi.co/api/planets/1/",
          "films": [
            "https://swapi.co/api/films/1/"
          ],
          "species": [
            "https://swapi.co/api/species/1/"
          ],
          "vehicles": [],
          "starships": [
            "https://swapi.co/api/starships/13/"
          ],
          "created": "2014-12-10T15:18:20.704000Z",
          "edited": "2014-12-20T21:17:50.313000Z",
          "url": "https://swapi.co/api/people/1/"
        }
      ]
    }
""".trimIndent()

val notFound = """
    {
      "detail": "Not found"
    }
""".trimIndent()
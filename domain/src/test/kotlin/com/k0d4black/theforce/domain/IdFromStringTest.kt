package com.k0d4black.theforce.domain

import com.k0d4black.theforce.domain.utils.id
import org.junit.Test

class IdFromStringTest {

    @Test
    fun `should extract numbers when called on string`() {

        val urlString = "https://swapi.co/api/species/3/"

        assert(urlString.id == 3) {
            println("Expected value was 3 got ${urlString.id}")
        }

    }

    @Test
    fun `should return -1 when called on empty string`() {
        val emptyString = ""

        assert(emptyString.id == -1) {
            println("Expected value was -1 got ${emptyString.id}")
        }
    }

    @Test
    fun `should return -1 when called on string with no numbers`() {
        val emptyString = "dddd"

        assert(emptyString.id == -1) {
            println("Expected value was -1 got ${emptyString.id}")
        }
    }
}
package com.botob.supernetwork.sdk.http

import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class SuperClientTest {

    @Before
    fun setUp() {

    }

    @After
    fun cleanUp() {

    }

    @Test
    fun get() {
        runBlocking {
            val response = SuperClient().get("https://google.com")
            Assert.assertEquals(200, response.code)
        }
    }

    @Test
    fun post() {
    }
}
package com.botob.supernetwork.sdk.http

import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import java.net.HttpURLConnection

class SuperClientTest {
    companion object {
        private const val GET_URL = "https://www.wikipedia.org"

        // Source https://stackoverflow.com/a/9770981
        private const val POST_URL = "https://httpbin.org/post"
        private const val POST_BODY = "Hello World"
    }

    @Test
    fun get() = runBlocking {
        val response = SuperClient().get(GET_URL)

        Assert.assertEquals(HttpURLConnection.HTTP_OK, response.code)
    }

    @Test
    fun post() = runBlocking {
        val response = SuperClient().post(
            POST_URL,
            headers = mapOf("Content-Type" to "text/plain"),
            body = POST_BODY
        )

        Assert.assertEquals(HttpURLConnection.HTTP_OK, response.code)
        val expected = "\"data\": \"$POST_BODY\""
        Assert.assertTrue(response.payload.contains(expected))
    }
}
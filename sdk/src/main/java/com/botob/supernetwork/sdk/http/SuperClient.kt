package com.botob.supernetwork.sdk.http

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.DataOutputStream
import java.net.HttpURLConnection
import java.net.URL

/**
 * [SuperClient] is the class wrapping [HttpURLConnection] and offering easy HTTP request methods.
 */
class SuperClient {
    companion object {
        /**
         * The code returned when an exception happened at the client level.
         */
        const val EXCEPTION_CODE = -1;
    }

    /**
     * Executes a GET request.
     *
     * @param url the URL of the resource to fetch.
     * @param headers the optional headers and the associated values to attach.
     * @return the resulting [SuperResponse]. Note that [EXCEPTION_CODE] is returned if an exception happens on the client.
     */
    suspend fun get(
        url: String,
        headers: Map<String, String> = mapOf()
    ): SuperResponse {
        return request(HttpMethod.GET, url, headers)
    }

    /**
     * Executes a POST request.
     *
     * @param url the URL of the resource to fetch.
     * @param headers the optional headers and the associated values to attach.
     * @param body the data to send.
     * @return the resulting [SuperResponse]. Note that [EXCEPTION_CODE] is returned if an exception happens on the client.
     */
    suspend fun post(
        url: String,
        headers: Map<String, String> = mapOf(),
        body: String = ""
    ): SuperResponse {
        return request(HttpMethod.POST, url, headers, body)
    }

    /**
     * Executes an HTTP request.
     *
     * @param method the [HttpMethod] to use.
     * @param url the URL of the resource to fetch.
     * @param headers the optional headers and the associated values to attach.
     * @param body the data to send.
     * @return the resulting [SuperResponse]. Note that [EXCEPTION_CODE] is returned if an exception happens on the client.
     */
    suspend fun request(
        method: HttpMethod,
        url: String,
        headers: Map<String, String> = mapOf(),
        body: String = ""
    ): SuperResponse {
        return request(SuperRequest(method, url, headers, body))
    }

    /**
     * Executes an HTTP request.
     *
     * @param request the [SuperRequest] encapsulating the request metadata and data.
     * @return the resulting [SuperResponse]. Note that [EXCEPTION_CODE] is returned if an exception happens on the client.
     */
    suspend fun request(request: SuperRequest): SuperResponse {
        return withContext(Dispatchers.IO) {
            val connection = (URL(request.url).openConnection() as HttpURLConnection).apply {
                requestMethod = request.formattedMethod
                doOutput = request.body.isNotEmpty()
                request.headers.forEach { (header, value) -> setRequestProperty(header, value) }
            }

            try {
                if (connection.doOutput) {
                    DataOutputStream(connection.outputStream).use {
                        it.writeBytes(request.body)
                    }
                }
                val data = connection.inputStream.bufferedReader().use { it.readText() }
                return@withContext SuperResponse(request, connection.responseCode, data)
            } catch (exception: Exception) {
                // TODO: improve exception handling by either throwing a dedicated exception or
                // refining this current behavior.
                return@withContext SuperResponse(request, EXCEPTION_CODE, exception.toString())
            } finally {
                connection.disconnect()
            }
        }
    }
}
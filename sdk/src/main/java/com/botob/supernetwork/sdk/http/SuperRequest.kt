package com.botob.supernetwork.sdk.http

/**
 * [SuperRequest] is a data class wrapping the essential data used to send an HTTP request.
 */
data class SuperRequest(
    val method: HttpMethod,
    val url: String,
    val headers: Map<String, String> = mapOf(),
    val body: String = ""
) {
    val formattedMethod = method.name
}
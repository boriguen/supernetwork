package com.botob.supernetwork.sdk.http

/**
 * [SuperResponse] is the data class encapsulating HTTP response data.
 */
data class SuperResponse(val request: SuperRequest, val code: Int, val payload: String)

package com.botob.supernetwork.sdk.logging

import android.util.Log
import com.botob.supernetwork.sdk.http.SuperRequest
import com.botob.supernetwork.sdk.http.SuperResponse
import com.botob.supernetwork.sdk.persistence.SuperNetworkDao
import com.botob.supernetwork.sdk.persistence.entities.NetworkEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * [SuperLogger] is the class offering logging for specific network metadata.
 *
 * @property superNetworkDao the DAO to store and get related network data.
 */
class SuperLogger(private val superNetworkDao: SuperNetworkDao) {
    companion object {
        /**
         * The tag for logging.
         */
        private val TAG = SuperLogger::class.java.simpleName
    }

    /**
     * Logs targeted metadata contained within the given [request] and [response].
     *
     * @param request the request from which to log some targeted metadata like [SuperRequest].
     * @param response the response from which to log some targeted metadata like code.
     * @return the created [NetworkEvent].
     */
    suspend fun logNetworkEvent(request: SuperRequest, response: SuperResponse) =
        CoroutineScope(Dispatchers.IO).launch {
            val networkEvent = NetworkEvent(requestUrl = request.url, responseCode = response.code)
            val id = superNetworkDao.putNetworkEvent(networkEvent)

            Log.d(TAG, "logNetworkEvent: $networkEvent has now id=$id.")
        }
}
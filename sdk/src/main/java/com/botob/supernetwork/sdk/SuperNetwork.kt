package com.botob.supernetwork.sdk

import android.content.Context
import com.botob.supernetwork.sdk.http.SuperClient
import com.botob.supernetwork.sdk.logging.SuperLogger
import com.botob.supernetwork.sdk.persistence.SuperNetworkDatabase

/**
 * [SuperNetwork] is the static class providing helpers to encapsulate initialization of key objects.
 */
object SuperNetwork {
    /**
     * Creates a [SuperClient] object with the given context.
     *
     * @param context the context used to initialize the local storage that will contain logs.
     * @return the resulting [SuperClient] object, ready to use.
     */
    fun createClient(context: Context): SuperClient {
        return SuperClient(SuperLogger(SuperNetworkDatabase.getDatabase(context).superNetworkDao()))
    }
}
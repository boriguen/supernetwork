package com.botob.supernetwork.sdk

import android.content.Context
import com.botob.supernetwork.sdk.http.SuperClient
import com.botob.supernetwork.sdk.logging.SuperLogger
import com.botob.supernetwork.sdk.persistence.SuperNetworkDao
import com.botob.supernetwork.sdk.persistence.SuperNetworkDatabase
import com.botob.supernetwork.sdk.persistence.entities.NetworkEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

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
        return SuperClient(SuperLogger(getDao(context)))
    }


    /**
     * Gets the stored network events.
     *
     * @param context the context to access the database.
     * @return the resulting list of [NetworkEvent].
     */
    suspend fun getNetworkEvents(context: Context): List<NetworkEvent> {
        return withContext(Dispatchers.IO) {
            getDao(context).getNetworkEvents()
        }
    }

    /**
     * Gets the DAO.
     */
    private fun getDao(context: Context): SuperNetworkDao {
        return SuperNetworkDatabase.getDatabase(context).superNetworkDao()
    }
}
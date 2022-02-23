package com.botob.supernetwork.sdk.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.botob.supernetwork.sdk.persistence.entities.NetworkEvent

/**
 * [SuperNetworkDao] is the Data Access Object interface offering CRUD capabilities related to local storage.
 */
@Dao
interface SuperNetworkDao {
    /**
     * Inserts the given [NetworkEvent] into the local database.
     *
     * @param networkEvent the event to store.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun putNetworkEvent(networkEvent: NetworkEvent): Long

    /**
     * Gets the [NetworkEvent] of given [id].
     *
     * @param id the ID of the [NetworkEvent] to get.
     * @return the matching [NetworkEvent] if found; otherwise, null.
     */
    @Query("SELECT * FROM NetworkEvent WHERE networkEventId = :id")
    fun getNetworkEvent(id: String): NetworkEvent?

    /**
     * Gets all stored [NetworkEvent] objects by descending order on [NetworkEvent.createdAt].
     *
     * @return the stored [NetworkEvent] objects.
     */
    @Query("SELECT * FROM NetworkEvent ORDER BY createdAt DESC")
    fun getNetworkEvents(): List<NetworkEvent>


}
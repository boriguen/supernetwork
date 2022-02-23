package com.botob.supernetwork.sdk.persistence.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * [NetworkEvent] is the entity allowing to cache network request and response metadata.
 */
@Entity
data class NetworkEvent(
    @PrimaryKey(autoGenerate = true) val networkEventId: Long = 0,
    val createdAt: Long = System.currentTimeMillis(),
    val requestUrl: String,
    val responseCode: Int
)

package com.botob.supernetwork.sdk.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.botob.supernetwork.sdk.persistence.entities.NetworkEvent

/**
 * [SuperNetworkDatabase] is the class allowing to store data locally.
 */
@Database(entities = [NetworkEvent::class], version = 1)
abstract class SuperNetworkDatabase : RoomDatabase() {
    abstract fun superNetworkDao(): SuperNetworkDao

    companion object {
        @Volatile
        private var INSTANCE: SuperNetworkDatabase? = null

        /**
         * Gets the database instance.
         */
        fun getDatabase(context: Context): SuperNetworkDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SuperNetworkDatabase::class.java,
                    "supernetwork_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
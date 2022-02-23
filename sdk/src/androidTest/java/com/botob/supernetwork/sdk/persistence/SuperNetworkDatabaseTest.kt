package com.botob.supernetwork.sdk.persistence

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.botob.supernetwork.sdk.persistence.entities.NetworkEvent
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class SuperNetworkDatabaseTest {
    companion object {
        private const val EXPECTED_URL = "https://www.wikipedia.org/"
        private const val EXPECTED_CODE = 200
    }

    private lateinit var dao: SuperNetworkDao
    private lateinit var database: SuperNetworkDatabase

    @Before
    fun createDatabase() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(
            context, SuperNetworkDatabase::class.java
        ).build()
        dao = database.superNetworkDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDatabase() {
        database.close()
    }

    @Test
    @Throws(Exception::class)
    fun putNetworkEvent() = runBlocking {
        dao.putNetworkEvent(NetworkEvent(requestUrl = EXPECTED_URL, responseCode = EXPECTED_CODE))
        Assert.assertTrue(dao.getNetworkEvents().size == 1)
    }
}

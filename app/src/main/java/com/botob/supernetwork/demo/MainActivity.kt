package com.botob.supernetwork.demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.botob.supernetwork.demo.ui.theme.SuperNetworkDemoTheme
import com.botob.supernetwork.sdk.SuperNetwork
import com.botob.supernetwork.sdk.http.SuperClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    companion object {
        /**
         * The static GET URL to use.
         */
        private const val GET_URL = "https://www.wikipedia.org"
    }

    private lateinit var superClient: SuperClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        superClient = SuperNetwork.createClient(applicationContext)

        lifecycleScope.launch {
            val response = superClient.get(GET_URL)
            assert(SuperNetwork.getNetworkEvents(applicationContext).isNotEmpty())

            launch(Dispatchers.Main) {
                setContent {
                    SuperNetworkDemoTheme {
                        // A surface container using the 'background' color from the theme
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = MaterialTheme.colors.background
                        ) {
                            SampleGet(GET_URL, response.code)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SampleGet(url: String, code: Int) {
    Text(text = "$url -> $code")
}
package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.myapplication.ui.theme.MyApplicationTheme
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {

    private val service: CharacterService by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CharacterService::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                var uiState by remember { mutableStateOf<UiState>(UiState.Loading) }
                var currentPage by remember { mutableIntStateOf(1) }
                var retryTrigger by remember { mutableIntStateOf(0) }

                LaunchedEffect(currentPage, retryTrigger) {
                    uiState = UiState.Loading
                    uiState = try {
                        val response = service.getCharacters(currentPage)
                        UiState.Success(response.results, response.info)
                    } catch (e: Exception) {
                        UiState.Error(e.message ?: "Fallo desconocido")
                    }
                }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainView(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        state = uiState,
                        onPageChange = { currentPage = it },
                        onRetry = { retryTrigger++ }
                    )
                }
            }
        }
    }
}
package com.example.myapplication

import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterService {
    @GET(Constants.CHARACTERS_PATH)
    suspend fun getCharacters(@Query("page") page: Int = 1): CharacterResponse
}
package com.example.myapplication

data class CharacterResponse(
    val info: Info,
    val results: List<Character>
)

data class Info(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)

data class Character(
    val id: Long,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val image: String,
    val origin: Origin,
    val location: Origin,
    val episode: List<String> = emptyList()
)

data class Origin(
    val name: String
)
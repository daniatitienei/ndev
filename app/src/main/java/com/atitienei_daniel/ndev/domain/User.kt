package com.atitienei_daniel.ndev.domain

data class User(
    val name: String = "",
    val username: String = "",
    val imageUrl: String = "",
    val latitude: Long = 0,
    val longitude: Long = 0
)

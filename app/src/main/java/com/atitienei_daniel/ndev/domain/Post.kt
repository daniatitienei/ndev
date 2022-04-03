package com.atitienei_daniel.ndev.domain

data class Post(
    val title: String = "",
    val user: User = User(),
    val likeCounter: Long = 0,
    val commentCount: Long = 0,
    /* TODO Add timestamp from firebase */
)


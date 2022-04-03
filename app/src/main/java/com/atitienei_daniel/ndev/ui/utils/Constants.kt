package com.atitienei_daniel.ndev.ui.utils

import com.atitienei_daniel.ndev.domain.Post
import com.atitienei_daniel.ndev.domain.User

object Constants {
    val post = Post(
        title = "Lorem ipsum",
        user = User(name = "Atitienei Daniel", username = "a.dani"),
        likeCounter = 10,
        commentCount = 5
    )
}
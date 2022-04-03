package com.atitienei_daniel.ndev.ui.screens.code_feed

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.ListItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.atitienei_daniel.ndev.R
import com.atitienei_daniel.ndev.domain.Post
import com.atitienei_daniel.ndev.domain.User
import com.atitienei_daniel.ndev.ui.theme.NDEVTheme
import com.atitienei_daniel.ndev.ui.utils.Constants

@ExperimentalMaterialApi
@ExperimentalMaterial3Api
@Composable
fun CodeFeedScreen() {
    Scaffold { innerPadding ->
        LazyColumn {
            item {
                Text(text = stringResource(id = R.string.newest_posts))
            }
            items(5) {
                CodePost(
                    post = Constants.post
                )
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun CodePost(post: Post) {
    Column {
        ListItem(
            text = {
                Text(text = post.title)
            },
            overlineText = {
                Text(text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(post.user.name)
                    }
                    append(" - ${post.user.username}")
                })
            },
            icon = {
                Box(
                    modifier = Modifier
                        .size(size = 48.dp)
                        .clip(CircleShape)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.poza),
                        contentDescription = null,
                        contentScale = ContentScale.Fit
                    )
                }
            }
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            IconButtonBeforeText(
                text = "(${post.likeCounter})",
                icon = Icons.Rounded.FavoriteBorder,
                modifier = Modifier.weight(1f)
            )

            IconButtonBeforeText(
                text = "(${post.commentCount})",
                icon = Icons.Rounded.ChatBubbleOutline,
                modifier = Modifier.weight(1f)
            )
            Box(
                modifier = Modifier.weight(1f)
            ) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Rounded.NotInterested,
                        contentDescription = null
                    )
                }
            }
            Box(
                modifier = Modifier.weight(1f)
            ) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Rounded.Share,
                        contentDescription = null
                    )
                }
            }


        }
    }
}

@Composable
fun IconButtonBeforeText(
    text: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = icon,
                contentDescription = contentDescription
            )
        }
        Text(text = text)
    }
}

@ExperimentalMaterialApi
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun CodePostPreview() {
    NDEVTheme {
        CodePost(
            post = Post(
                title = "Lorem ipsum",
                user = User(name = "Atitienei Daniel", username = "a.dani")
            )
        )
    }
}

@ExperimentalMaterial3Api
@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
private fun CodeFeedPreview() {
    NDEVTheme {
        CodeFeedScreen()
    }
}
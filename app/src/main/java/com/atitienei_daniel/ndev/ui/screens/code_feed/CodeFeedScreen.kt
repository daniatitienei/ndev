package com.atitienei_daniel.ndev.ui.screens.code_feed

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.ListItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
    var newPostTextValue by remember {
        mutableStateOf("")
    }

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(15.dp),
        contentPadding = PaddingValues(vertical = 15.dp)
    ) {
        item {
            Text(
                text = stringResource(id = R.string.create_new_post),
                modifier = Modifier.padding(horizontal = 20.dp),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge
            )
        }

        item {
            Box(modifier = Modifier.padding(horizontal = 20.dp)) {
                TextField(
                    value = newPostTextValue,
                    onValueChange = { newPostTextValue = it },
                    placeholder = {
                        Text(
                            text = stringResource(
                                id = R.string.write_something
                            )
                        )
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp)
                )
            }

            Spacer(modifier = Modifier.height(15.dp))
        }

        item {
            Text(
                text = stringResource(id = R.string.see_posts_from),
                modifier = Modifier.padding(horizontal = 20.dp),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge
            )
        }

        item {
            LazyRow(contentPadding = PaddingValues(horizontal = 20.dp)) {
                item {
                    ElevatedButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Rounded.Add,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = stringResource(id = R.string.add_more))
                    }
                }
            }
            Spacer(modifier = Modifier.height(15.dp))
        }

        item {
            Text(
                text = stringResource(id = R.string.newest_posts),
                modifier = Modifier.padding(horizontal = 20.dp),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge
            )
        }
        items(10) {
            CodePost(
                post = Constants.post
            )
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun CodePost(post: Post) {
    var isLiked by remember {
        mutableStateOf(false)
    }

    val animatedLikeButton = if (!isLiked) Icons.Rounded.FavoriteBorder else Icons.Rounded.Favorite
    val likeColor by animateColorAsState(
        targetValue = if (isLiked) MaterialTheme.colorScheme.inversePrimary else MaterialTheme.colorScheme.onBackground,
        animationSpec = tween(durationMillis = 250)
    )

    Column(
        modifier = Modifier.clickable {}
    ) {
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
                icon = animatedLikeButton,
                modifier = Modifier.weight(1f),
                onClick = {
                    isLiked = !isLiked
                },
                tint = likeColor
            )

            IconButtonBeforeText(
                text = "(${post.commentCount})",
                icon = Icons.Rounded.ChatBubbleOutline,
                modifier = Modifier.weight(1f),
                onClick = {

                }
            )
            Box(
                modifier = Modifier
                    .weight(1f)
                    .wrapContentWidth(align = Alignment.CenterHorizontally)
            ) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Rounded.NotInterested,
                        contentDescription = null
                    )
                }
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .wrapContentWidth(align = Alignment.CenterHorizontally)
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
    onClick: () -> Unit,
    tint: Color = MaterialTheme.colorScheme.onBackground
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        IconButton(onClick = onClick) {
            Icon(
                imageVector = icon,
                contentDescription = contentDescription,
                tint = tint
            )
        }
        Text(text = text)
    }
}

@ExperimentalMaterialApi
@Preview(showBackground = true)
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
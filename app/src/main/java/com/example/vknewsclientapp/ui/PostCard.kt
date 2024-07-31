package com.example.vknewsclientapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.vknewsclientapp.R
import com.example.vknewsclientapp.domain.FeedPost
import com.example.vknewsclientapp.domain.StatisticItem
import com.example.vknewsclientapp.domain.StatisticType
import com.example.vknewsclientapp.ui.theme.VkNewsClientAppTheme

@Composable
fun PostCard(
    modifier: Modifier = Modifier,
    feedPost: FeedPost,
    onStatisticItemClickListener: (StatisticItem) -> Unit
) {
    Card(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .padding(all = 8.dp)
        ) {
            PostHeader(feedPost)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = feedPost.contentText,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                painter = painterResource(id = feedPost.contentImageResId),
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )
            Spacer(modifier = Modifier.height(8.dp))
            Statistics(
                feedPost.statistic,
                onItemClickListener = onStatisticItemClickListener
            )
        }
    }
}

@Composable
private fun PostHeader(
    feedPost: FeedPost
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape),
            painter = painterResource(id = feedPost.avatarResId),
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            Text(
                text = feedPost.communityName,
                color = MaterialTheme.colorScheme.onPrimary,
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = feedPost.publicationDate,
                color = MaterialTheme.colorScheme.onSecondary,
            )
        }

        Icon(
            imageVector = Icons.Rounded.MoreVert,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSecondary
        )
    }
}

@Composable
private fun Statistics(
    statistics: List<StatisticItem>,
    onItemClickListener: (StatisticItem) -> Unit
) {
    Row {
        Row(
            modifier = Modifier
                .weight(1f),
        ) {
            val viewsItem = statistics.getItemByType(StatisticType.VIEWS)
            IconWithText(
                iconResId = R.drawable.ic_views_count,
                text = viewsItem.count.toString(),
                onItemClickListener = {
                    onItemClickListener(viewsItem)
                }
            )
        }

        Row(
            modifier = Modifier
                .weight(1f),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val shareItem = statistics.getItemByType(StatisticType.SHARES)
            IconWithText(
                iconResId = R.drawable.ic_share,
                text = shareItem.count.toString(),
                onItemClickListener = {
                    onItemClickListener(shareItem)
                }
            )

            val commentItem = statistics.getItemByType(StatisticType.COMMENTS)
            IconWithText(
                iconResId = R.drawable.ic_comment,
                text = commentItem.count.toString(),
                onItemClickListener = {
                    onItemClickListener(commentItem)
                }
            )

            val likeItem = statistics.getItemByType(StatisticType.LIKES)
            IconWithText(
                iconResId = R.drawable.ic_like,
                text = likeItem.count.toString(),
                onItemClickListener = {
                    onItemClickListener(likeItem)
                }
            )
        }
    }
}

private fun List<StatisticItem>.getItemByType(type: StatisticType): StatisticItem {
    return this.find { it.type == type } ?: throw IllegalStateException()
}

@Composable
private fun IconWithText(
    iconResId: Int,
    text: String,
    onItemClickListener: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable {
            onItemClickListener()
        }
    ) {
        Icon(
            painter = painterResource(id = iconResId),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSecondary
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onSecondary
        )
    }
}

@Preview
@Composable
private fun PostCardLightPreview() {
    VkNewsClientAppTheme(darkTheme = false) {
        PostCard(
            Modifier.padding(8.dp),
            FeedPost(),
            onStatisticItemClickListener = {}
        )
    }
}


@Preview
@Composable
private fun PostCardDarkPreview() {
    VkNewsClientAppTheme(darkTheme = true) {
        PostCard(
            Modifier.padding(8.dp),
            FeedPost(),
            onStatisticItemClickListener = {}
        )
    }
}
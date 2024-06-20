package ru.paramonov.cryptocurrencytracker.presentation.screens.detailnews

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import ru.paramonov.cryptocurrencytracker.R
import ru.paramonov.cryptocurrencytracker.domain.entity.NewsInfo
import ru.paramonov.cryptocurrencytracker.presentation.ui.theme.LightGrey

@Composable
fun DetailNewsScreen(
    paddingValues: PaddingValues,
    newsInfo: NewsInfo
) {
    DetailNewsContent(
        paddingValues = paddingValues,
        guid = newsInfo.guid,
        imageUrl = newsInfo.imageUrl,
        title = newsInfo.title,
        body = newsInfo.body,
        titleNews = newsInfo.titleNews
    )
}

@Composable
fun DetailNewsContent(
    paddingValues: PaddingValues,
    guid: String,
    imageUrl: String,
    title: String,
    body: String,
    titleNews: String
) {
    val context = LocalContext.current

    val scrollState = rememberScrollState()

    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(state = scrollState)
            .background(color = LightGrey)
            .padding(paddingValues)
    ) {
        Text(
            text = stringResource(id = R.string.detail_news_info),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp )
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp, end = 8.dp, start = 8.dp),
            shape = MaterialTheme.shapes.medium,
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.elevatedCardElevation(
                defaultElevation = 8.dp
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
                Text(
                    text = titleNews,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
        }

        Text(
            text = stringResource(id = R.string.description),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp )
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp, end = 8.dp, start = 8.dp),
            shape = MaterialTheme.shapes.medium,
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.elevatedCardElevation(
                defaultElevation = 8.dp
            )
        ) {
            Text(
                text = title,
                fontSize = 16.sp,
                color = Color.Black,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            )
        }

        Text(
            text = stringResource(id = R.string.full_description),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp )
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp, end = 8.dp, start = 8.dp),
            shape = MaterialTheme.shapes.medium,
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.elevatedCardElevation(
                defaultElevation = 8.dp
            )
        ) {
            Text(
                text = body,
                fontSize = 16.sp,
                color = Color.Black,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .animateContentSize(
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioLowBouncy,
                            stiffness = Spring.StiffnessLow
                        )
                        )
                    .clickable {
                        expanded = !expanded
                    },
                maxLines = if (!expanded) 2 else 10,
            )
        }

        TextButton(
            onClick = {
                Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse(guid)
                }.also { intent -> context.startActivity(intent) }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(text = "Статья", color = Color(color = 0xFF5EDE99), fontSize = 20.sp)
        }
    }
}

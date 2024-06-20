package ru.paramonov.cryptocurrencytracker.presentation.screens.news

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import ru.paramonov.cryptocurrencytracker.R
import ru.paramonov.cryptocurrencytracker.domain.entity.NewsInfo
import ru.paramonov.cryptocurrencytracker.presentation.app.getApplicationComponent

@Composable
fun NewsScreen(
    paddingValues: PaddingValues,
    onClickNews: (NewsInfo) -> Unit
) {
    val component = getApplicationComponent()
    val viewModel: NewsViewModel = viewModel(factory = component.getViewModel())
    val newsInfoList = viewModel.newsList.collectAsStateWithLifecycle(initialValue = emptyList())

    NewsContent(
        paddingValues = paddingValues,
        onClickNews = onClickNews,
        listNews = newsInfoList
    )
}

@Composable
private fun NewsContent(
    paddingValues: PaddingValues,
    onClickNews: (NewsInfo) -> Unit,
    listNews: State<List<NewsInfo>>
) {
    LazyColumn(
        modifier = Modifier.padding(paddingValues),
        contentPadding = PaddingValues(
            top = 16.dp,
            start = 8.dp,
            end = 8.dp,
            bottom = 8.dp
        ),
        verticalArrangement = Arrangement.spacedBy(space = 8.dp)
    ) {
        item {
            Text(
                text = stringResource(id = R.string.news_info),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 12.dp)
            )
        }
        items(
            items = listNews.value,
            key = { news -> news.id }
        ) { news ->
            NewsItem(
                newsInfo = news,
                onClickNews = onClickNews
            )
        }
    }
}

@Composable
fun NewsItem(
    newsInfo: NewsInfo,
    onClickNews: (NewsInfo) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .clickable { onClickNews(newsInfo) },
        shape = RoundedCornerShape(size = 20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 8.dp
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = newsInfo.imageUrl,
                contentDescription = "",
                modifier = Modifier
                    .size(size = 72.dp)
                    .padding(8.dp)
                    .clip(shape = CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text(
                    text = newsInfo.titleNews,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                
                Text(text = newsInfo.title)
            }
        }
    }
}

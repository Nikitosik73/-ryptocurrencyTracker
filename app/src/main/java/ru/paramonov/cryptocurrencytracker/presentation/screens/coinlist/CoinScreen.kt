package ru.paramonov.cryptocurrencytracker.presentation.screens.coinlist

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import ru.paramonov.cryptocurrencytracker.domain.entity.CoinInfo
import ru.paramonov.cryptocurrencytracker.presentation.app.getApplicationComponent

@Composable
fun CoinListScreen(
    paddingValues: PaddingValues,
    onClickCoin: (String) -> Unit
) {
    val component = getApplicationComponent()
    val viewModel: CoinViewModel = viewModel(factory = component.getViewModel())
    val coinInfo = viewModel.coinInfoList.collectAsStateWithLifecycle(initialValue = emptyList())

    CoinListContent(
        paddingValues = paddingValues,
        listCoinInfo = coinInfo.value,
        onClickCoin = onClickCoin
    )
}

@Composable
private fun CoinListContent(
    paddingValues: PaddingValues,
    listCoinInfo: List<CoinInfo>,
    onClickCoin: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.LightGray.copy(alpha = 0.5f))
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
                Row {
                    Text(
                        text = "Ваши активы: ",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                    Text(
                        text = "271,61 \$",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF5EDE99),
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(space = 8.dp),
                    contentPadding = PaddingValues(
                        start = 8.dp,
                        end = 8.dp,
                    )
                ) {
                    items(
                        items = CoinList.coinList,
                        key = { coin -> coin.id }
                    ) { coin ->
                        CoinItemRow(
                            imageUrl = coin.imageCoin,
                            fromSymbol = coin.symbol,
                            toSymbol = coin.name,
                            price = coin.price
                        )
                    }
                }
            }
            item {
                Text(
                    text = "Список отслеживаемых криптовалют",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            items(
                items = listCoinInfo,
                key = { coin -> coin.fromSymbol }
            ) { coin ->
                CoinItem(
                    imageUrl = coin.imageUrl,
                    fromSymbol = coin.fromSymbol,
                    toSymbol = coin.toSymbol,
                    price = coin.price,
                    onClickCoin = onClickCoin
                )
            }
        }
    }
}

@Composable
private fun CoinItemRow(
    modifier: Modifier = Modifier,
    imageUrl: Int,
    fromSymbol: String,
    toSymbol: String,
    price: String
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .width(300.dp)
            .height(88.dp),
        shape = RoundedCornerShape(
            topEnd = 8.dp,
            topStart = 8.dp,
            bottomEnd = 8.dp,
            bottomStart = 8.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = Color.Black
        ),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 8.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(88.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(size = 72.dp)
                    .padding(vertical = 8.dp, horizontal = 8.dp)
                    .clip(shape = RoundedCornerShape(size = 4.dp))
                    .background(color = Color.LightGray.copy(alpha = 0.5f)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = imageUrl),
                    contentDescription = "",
                    modifier = Modifier
                        .size(64.dp)
                        .padding(all = 8.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.FillBounds
                )
            }

            Spacer(modifier = Modifier.width(width = 16.dp))

            Column {
                Text(text = fromSymbol, fontSize = 20.sp, fontWeight = FontWeight.Bold)

                Spacer(modifier = Modifier.height(height = 8.dp))


                Text(text = toSymbol)
            }

            Spacer(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
            )

            Text(
                text = "$price \$",
                modifier = Modifier.padding(horizontal = 8.dp),
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun CoinItem(
    modifier: Modifier = Modifier,
    imageUrl: String,
    fromSymbol: String,
    toSymbol: String?,
    price: String?,
    onClickCoin: (String) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(height = 88.dp)
            .clickable { onClickCoin(fromSymbol) },
        shape = RoundedCornerShape(
            topEnd = 8.dp,
            topStart = 8.dp,
            bottomEnd = 8.dp,
            bottomStart = 8.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = Color.Black
        ),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 8.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(88.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(size = 72.dp)
                    .padding(vertical = 8.dp, horizontal = 8.dp)
                    .clip(shape = RoundedCornerShape(size = 4.dp))
                    .background(color = Color.LightGray.copy(alpha = 0.5f)),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = "",
                    modifier = Modifier
                        .size(64.dp)
                        .padding(all = 8.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.FillBounds
                )
            }

            Spacer(modifier = Modifier.width(width = 16.dp))

            Column {
                Text(text = fromSymbol, fontSize = 20.sp, fontWeight = FontWeight.Bold)

                Spacer(modifier = Modifier.height(height = 8.dp))

                toSymbol?.let {
                    Text(text = it)
                }
            }

            Spacer(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
            )

            price?.let {
                Text(
                    text = "${formatToTwoDecimalPlaces(it)} \$",
                    modifier = Modifier.padding(horizontal = 8.dp),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@SuppressLint("DefaultLocale")
private fun formatToTwoDecimalPlaces(price: String): String? {
    return try {
        val number = price.toDouble()
        String.format("%.2f", number)
    } catch (e: NumberFormatException) {
        null
    }
}
package ru.paramonov.cryptocurrencytracker.presentation.screens.deteilcoin

import android.annotation.SuppressLint
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import ru.paramonov.cryptocurrencytracker.R
import ru.paramonov.cryptocurrencytracker.domain.entity.CoinInfo
import ru.paramonov.cryptocurrencytracker.presentation.app.getApplicationComponent
import ru.paramonov.cryptocurrencytracker.presentation.screens.coinlist.CoinViewModel
import ru.paramonov.cryptocurrencytracker.presentation.screens.deteilcoin.terminal.component.Terminal

@Composable
fun DetailCoinInfo(
    paddingValues: PaddingValues,
    fromSymbol: String,
    onClickChatCoin: (String) -> Unit
) {
    val component = getApplicationComponent()
    val viewModel: CoinViewModel = viewModel(factory = component.getViewModel())
    val coinInfo = viewModel.getDetailInfo(fSym = fromSymbol).collectAsStateWithLifecycle(
        initialValue = CoinInfo()
    )

    DetailCoinContent(
        paddingValues = paddingValues,
        coinInfo = coinInfo,
        onClickChatCoin = onClickChatCoin
    )
}

@Composable
private fun DetailCoinContent(
    paddingValues: PaddingValues,
    coinInfo: State<CoinInfo>,
    onClickChatCoin: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.LightGray.copy(alpha = 0.5f))
    ) {
        LazyColumn(
            modifier = Modifier.padding(paddingValues),
            contentPadding = PaddingValues(
                top = 16.dp, bottom = 8.dp,
                start = 4.dp, end = 4.dp
            ),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                Text(
                    text = stringResource(id = R.string.detail_coin_info),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 12.dp)
                )
            }
            item {
                DetailCoinCard(coinInfo = coinInfo.value)
            }
            item {
                CardFullInformation(coinInfo = coinInfo.value)
            }
            item {
                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .height(650.dp),
                    shape = RoundedCornerShape(size = 20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    elevation = CardDefaults.elevatedCardElevation(
                        defaultElevation = 8.dp
                    )
                ) {
                    Text(
                        text = stringResource(R.string.graphic),
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(16.dp)
                    )
                    Box(modifier = Modifier.weight(1f)) {
                        Terminal()
                    }
                }
            }
            item {
                TextButton(
                    onClick = { onClickChatCoin(coinInfo.value.fromSymbol) },
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(id = R.string.chat_coin),
                        color = Color(color = 0xFF5EDE99),
                        fontSize = 20.sp
                    )
                }
            }
        }
    }
}

@Composable
private fun DetailCoinCard(
    coinInfo: CoinInfo
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(size = 20.dp),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 8.dp
        )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            AsyncImageAnimation(url = coinInfo.imageUrl)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = coinInfo.fromSymbol,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
    }
}

@Composable
private fun AsyncImageAnimation(
    url: String
) {
    var isNotCircle by remember {
        mutableStateOf(false)
    }

    val percent by animateIntAsState(
        targetValue = if (!isNotCircle) 50 else 4,
        animationSpec = tween(
            durationMillis = 500,
            easing = LinearOutSlowInEasing
        ),
        label = "Circle"
    )

    val size by animateDpAsState(
        targetValue = if (!isNotCircle) 150.dp else 300.dp,
        animationSpec = tween(
            durationMillis = 500,
            easing = LinearOutSlowInEasing
        ),
        label = "Size"
    )

    AsyncImage(
        model = url,
        contentDescription = null,
        modifier = Modifier
            .size(size = size)
            .clip(shape = RoundedCornerShape(percent = percent))
            .clickable { isNotCircle = !isNotCircle }
    )
}

@Composable
private fun CardFullInformation(
    coinInfo: CoinInfo
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(size = 20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 8.dp
        )
    ) {
        Text(
            text = stringResource(R.string.full_information),
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Color.Black,
            modifier = Modifier.padding(16.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp)
        ) {
            Information(
                icon = Icons.Outlined.AccountCircle,
                label = stringResource(R.string.name_full_coin),
                value = buildAnnotatedString {
                    withStyle(
                        SpanStyle(
                            color = Color.Black
                        )
                    ) {
                        append(text = "${coinInfo.fromSymbol}/${coinInfo.toSymbol}")
                    }
                }
            )
            Information(
                icon = Icons.Outlined.DateRange,
                label = stringResource(R.string.lastupdate),
                value = buildAnnotatedString {
                    withStyle(
                        SpanStyle(
                            color = Color(color = 0xFF5EDE99)
                        )
                    ) {
                        append(text = coinInfo.lastUpdate)
                    }
                }
            )
            Information(
                icon = Icons.Outlined.ShoppingCart,
                label = stringResource(id = R.string.price),
                value = buildAnnotatedString {
                    withStyle(
                        SpanStyle(
                            color = Color.Black
                        )
                    ) {
                        coinInfo.price?.let { coinPrice ->
                            append(text = "${formatToTwoDecimalPlaces(coinPrice)} \$")
                        }
                    }
                }
            )
            Information(
                icon = Icons.Outlined.ShoppingCart,
                label = stringResource(id = R.string.price_low_day),
                value = buildAnnotatedString {
                    withStyle(
                        SpanStyle(
                            color = Color.Black
                        )
                    ) {
                        coinInfo.lowDay?.let { coinPrice ->
                            append(text = "${formatToTwoDecimalPlaces(coinPrice)} \$")
                        }
                    }
                }
            )
            Information(
                icon = Icons.Outlined.ShoppingCart,
                label = stringResource(id = R.string.price_high_day),
                value = buildAnnotatedString {
                    withStyle(
                        SpanStyle(
                            color = Color.Black
                        )
                    ) {
                        coinInfo.highDay?.let { coinPrice ->
                            append(text = "${formatToTwoDecimalPlaces(coinPrice)} \$")
                        }
                    }
                }
            )
            Information(
                icon = Icons.Outlined.ShoppingCart,
                label = stringResource(id = R.string.last_market),
                value = buildAnnotatedString {
                    withStyle(
                        SpanStyle(
                            color = Color.Black
                        )
                    ) {
                        coinInfo.lastMarket?.let { coinPrice ->
                            append(text = coinPrice)
                        }
                    }
                },
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
    }
}

@Composable
private fun Information(
    icon: ImageVector,
    label: String,
    value: AnnotatedString,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color(color = 0xFF5EDE99)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = label,
            color = Color.Black
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = value,
            color = Color.Black,
            modifier = modifier.padding(end = 8.dp)
        )
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
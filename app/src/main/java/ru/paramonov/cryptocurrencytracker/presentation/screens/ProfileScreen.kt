package ru.paramonov.cryptocurrencytracker.presentation.screens

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
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import coil.compose.AsyncImage
import ru.paramonov.cryptocurrencytracker.R
import ru.paramonov.cryptocurrencytracker.domain.entity.Profile

@Composable
fun ProfileScreen(paddingValues: PaddingValues) {

    val profile = Profile()

    Box(modifier = Modifier.fillMaxSize().background(color = Color.LightGray.copy(alpha = 0.5f))) {
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
                    text = stringResource(id = R.string.navigation_item_profile),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 12.dp)
                )
            }
            item {
                ProfileCard(profile = profile)
            }
            item {
                CardFullInformation(profile = profile)
            }
        }
    }
}

@Composable
private fun ProfileCard(
    profile: Profile
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
            AsyncImageAnimation(url = profile.profilePhoto)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = profile.fullName,
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
    profile: Profile
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
                label = stringResource(R.string.name_profile),
                value = buildAnnotatedString {
                    withStyle(
                        SpanStyle(
                            color = Color.Black
                        )
                    ) {
                        append(text = profile.screenName)
                    }
                }
            )
            Information(
                icon = Icons.Outlined.DateRange,
                label = stringResource(R.string.b_date),
                value = buildAnnotatedString {
                    withStyle(
                        SpanStyle(
                            color = Color(color = 0xFF5EDE99)
                        )
                    ) {
                        append(text = profile.bDate)
                    }
                }
            )
            Information(
                icon = Icons.Outlined.LocationOn,
                label = stringResource(id = R.string.main_town),
                value = buildAnnotatedString {
                    withStyle(
                        SpanStyle(
                            color = Color.Black
                        )
                    ) {
                        append(text = profile.homeTown)
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
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = value,
            color = Color.Black,
            modifier = modifier
        )
    }
}
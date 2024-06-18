package ru.paramonov.cryptocurrencytracker.presentation.screens.chat

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.paramonov.cryptocurrencytracker.R
import ru.paramonov.cryptocurrencytracker.presentation.ui.theme.LightGrey

@Composable
fun ChatScreen(coinName: String, onPopBackStack: () -> Unit) {
    val viewModel: MessageViewModel = viewModel()
    val messages = viewModel.messages.collectAsStateWithLifecycle()
    val currentMessage = viewModel.currentMessage.collectAsStateWithLifecycle()

    ChatContent(
        coinName = coinName,
        viewModel = viewModel,
        currentMessage = currentMessage,
        messages = messages,
        onPopBackStack = onPopBackStack
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ChatContent(
    coinName: String,
    viewModel: MessageViewModel,
    currentMessage: State<String>,
    messages: State<List<MessageChat>>,
    onPopBackStack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = coinName, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                },
                navigationIcon = {
                    IconButton(onClick = { onPopBackStack() }) {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBack,
                            contentDescription = "Back button"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        MessageList(
            paddingValues = innerPadding,
            viewModel = viewModel,
            currentMessage = currentMessage,
            messages = messages
        )
    }
}

@Composable
private fun MessageList(
    paddingValues: PaddingValues,
    viewModel: MessageViewModel,
    currentMessage: State<String>,
    messages: State<List<MessageChat>>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = LightGrey)
    ) {
        LazyColumn(
            contentPadding = PaddingValues(
                top = 8.dp,
                bottom = 72.dp,
                end = 8.dp,
                start = 8.dp
            ),
            verticalArrangement = Arrangement.spacedBy(space = 8.dp),
            modifier = Modifier.padding(paddingValues)
        ) {
            items(
                items = messages.value,
                key = { item: MessageChat -> item.id }
            ) { message ->
                MessageItem(message = message)
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        MessageSend(currentMessage = currentMessage, viewModel = viewModel)
    }
}

@Composable
private fun MessageSend(
    currentMessage: State<String>,
    viewModel: MessageViewModel
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, end = 16.dp, start = 16.dp, bottom = 72.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = currentMessage.value,
            onValueChange = { message ->
                viewModel.changeMessage(message = message)
            },
            modifier = Modifier
                .weight(1f)
                .padding(8.dp)
        )

        IconButton(
            onClick = { viewModel.sendMessage() },
        ) {
            Icon(imageVector = Icons.Rounded.Send, contentDescription = "Send message")
        }
    }
}

@Composable
fun MessageItem(
    message: MessageChat
) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = if (message.username == "Nikita") Alignment.BottomEnd else Alignment.BottomStart
    ) {
        Row(
            modifier = Modifier
                .wrapContentWidth()
                .padding(all = 8.dp)
                .clip(shape = CircleShape)
                .background(color = Color.White),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Profile image",
                modifier = Modifier
                    .size(size = 56.dp)
                    .padding(8.dp)
                    .clip(shape = CircleShape)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column(
                modifier = Modifier.padding(end = 12.dp)
            ) {
                Text(
                    text = message.username,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 4.dp)
                )

                Spacer(modifier = Modifier.height(height = 8.dp))

                Text(text = message.message, fontSize = 14.sp)
            }
        }
    }
}
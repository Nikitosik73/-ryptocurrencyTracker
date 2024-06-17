package ru.paramonov.cryptocurrencytracker.presentation.screens.chat

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MessageViewModel : ViewModel() {

    private var messageId: Int = 0

    private val _messages = MutableStateFlow(value = MessageData.messageList)
    val messages = _messages.asStateFlow()

    private val _currentMessage = MutableStateFlow(value = "")
    val currentMessage = _currentMessage.asStateFlow()

    fun sendMessage() {
        if (currentMessage.value.isNotBlank()) {
            val message = MessageChat(
                id = messageId++,
                message = _currentMessage.value,
                username = "Nikita"
            )
            _messages.value += message
            _currentMessage.value = ""
        }
    }

    fun changeMessage(message: String) {
        _currentMessage.value = message
    }
}
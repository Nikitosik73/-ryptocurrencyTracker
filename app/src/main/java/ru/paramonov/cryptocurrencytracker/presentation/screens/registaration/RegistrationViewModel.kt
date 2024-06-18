package ru.paramonov.cryptocurrencytracker.presentation.screens.registaration

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class RegistrationViewModel : ViewModel() {

    private val _username: MutableStateFlow<String> = MutableStateFlow("")
    val username = _username.asStateFlow()

    private val _name: MutableStateFlow<String> = MutableStateFlow("")
    val name = _name.asStateFlow()

    private val _lastname: MutableStateFlow<String> = MutableStateFlow("")
    val lastname = _lastname.asStateFlow()

    private val _password: MutableStateFlow<String> = MutableStateFlow("")
    val password = _password.asStateFlow()

    fun registration() {

    }

    fun changeUsername(value: String) {
        _username.value = value
    }

    fun changeName(value: String) {
        _name.value = value
    }

    fun changeLastname(value: String) {
        _lastname.value = value
    }

    fun changePassword(value: String) {
        _password.value = value
    }
}
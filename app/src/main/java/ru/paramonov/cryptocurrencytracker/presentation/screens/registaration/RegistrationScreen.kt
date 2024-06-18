package ru.paramonov.cryptocurrencytracker.presentation.screens.registaration

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.paramonov.cryptocurrencytracker.R
import ru.paramonov.cryptocurrencytracker.presentation.ui.theme.LightGrey

@Composable
fun RegistrationScreen(
    onRegistrationClick: () -> Unit
) {

    val viewModel: RegistrationViewModel = viewModel()
    val username = viewModel.username.collectAsStateWithLifecycle()
    val name = viewModel.name.collectAsStateWithLifecycle()
    val lastname = viewModel.lastname.collectAsStateWithLifecycle()
    val password = viewModel.password.collectAsStateWithLifecycle()

    RegistrationContent(
        viewModel = viewModel,
        username = username,
        name = name,
        lastname = lastname,
        password = password,
        onRegistrationClick = onRegistrationClick
    )
}

@Composable
private fun RegistrationContent(
    viewModel: RegistrationViewModel,
    username: State<String>,
    name: State<String>,
    lastname: State<String>,
    password: State<String>,
    onRegistrationClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(all = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier.padding(top = 42.dp)
        )

        Spacer(modifier = Modifier.height(height = 72.dp))

        Text(text = "Добро пожаловать", fontSize = 32.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(height = 16.dp))

        Text(
            text = "Доверие миллионов пользователей по всему\nмиру",
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            color = Color(0xFF5EDE99)
        )

        Spacer(modifier = Modifier.height(height = 16.dp))

        ChangeText(
            value = username.value,
            label = stringResource(id = R.string.enter_username),
            onTextChange = { viewModel.changeUsername(it) }
        )
        ChangeText(
            value = name.value,
            label = stringResource(id = R.string.enter_name),
            onTextChange = { viewModel.changeName(it) }
        )
        ChangeText(
            value = lastname.value,
            label = stringResource(id = R.string.enter_lastname),
            onTextChange = { viewModel.changeLastname(it) }
        )
        ChangeText(
            value = password.value,
            label = stringResource(id = R.string.enter_password),
            onTextChange = { viewModel.changePassword(it) }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { onRegistrationClick() },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(color = 0xFF5EDE99)
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Регистрация")
        }

        TextButton(onClick = { /*TODO*/ }) {
            Text(
                text = stringResource(id = R.string.fargot_password),
                color = Color(color = 0xFF5EDE99)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ChangeText(
    modifier: Modifier = Modifier,
    value: String,
    label: String,
    onTextChange: (String) -> Unit,
) {
    TextField(
        value = value,
        onValueChange = { text -> onTextChange(text) },
        label = { Text(text = label) },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = LightGrey,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedSupportingTextColor = Color.Black
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 16.dp)
    )
}

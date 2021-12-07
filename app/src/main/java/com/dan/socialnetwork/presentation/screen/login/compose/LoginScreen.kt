package com.dan.socialnetwork.presentation.screen.login.compose

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dan.socialnetwork.R
import com.dan.socialnetwork.presentation.screen.login.compose.component.SignUpLink
import com.dan.socialnetwork.presentation.screen.login.viewmodel.LoginViewModel
import com.dan.socialnetwork.presentation.ui.theme.Size_16
import com.dan.socialnetwork.presentation.ui.theme.Size_24
import com.dan.socialnetwork.presentation.util.Screen
import com.dan.socialnetwork.presentation.util.compose.text_field.PasswordTextField
import com.dan.socialnetwork.presentation.util.compose.text_field.StandardTextField

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(Size_16)
            .padding(
                bottom = Size_24
            )
    ) {
        Column(
            modifier=  Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.login),
                style = MaterialTheme.typography.h1,
                color = MaterialTheme.colors.onBackground,
            )
            Spacer(modifier = Modifier.height(Size_16))
            StandardTextField(
                text = viewModel.username.value,
                hint = stringResource(R.string.hint_username),
                onValueChange = viewModel::setUsername,
                errorMessage = viewModel.usernameErrorMessage.value
            )
            Spacer(modifier = Modifier.height(Size_16))
            PasswordTextField(
                text = viewModel.password.value,
                onValueChange = viewModel::setPassword,
                showPassword = viewModel.showPassword.value,
                onTogglePasswordVisibility = viewModel::setShowPassword,
                errorMessage = viewModel.passwordErrorMessage.value
            )
            Spacer(modifier = Modifier.height(Size_16))
            Button(
                modifier = Modifier
                    .align(Alignment.End),
                onClick = {
                    
                }
            ) {
                Text(
                    text = stringResource(R.string.login),
                    color = MaterialTheme.colors.onPrimary
                )
            }
        }
        SignUpLink(
            modifier = Modifier
                .align(Alignment.BottomCenter),
            onClick = {
                navController.navigate(Screen.Signup.route)
            }
        )
    }
}

package com.tongminhnhut.android_compose.compose_app.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tongminhnhut.android_compose.compose_app.presentation.base.common.SpacerSmall
import com.tongminhnhut.android_compose.compose_app.presentation.base.component.AppBarView
import com.tongminhnhut.android_compose.compose_app.presentation.base.component.XInput

@Composable
fun InputScreen(rootNavController: NavController) {
    Scaffold(
        topBar = {
            AppBarView(title = "Inputs", onBackClick = rootNavController::popBackStack)
        }
    ) {paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues).padding(16.dp)) {
            item {
                Column {
                    var text by remember { mutableStateOf("") }
                    XInput(
                        label = "Email",
                        value = text,
                        onValueChange = { text = it },
                        placeholder = "example@mail.com",
                        keyboardType = KeyboardType.Email,
                        maxLines = 1
                    )

                    SpacerSmall()

                    var numberText by remember { mutableStateOf("") }
                    XInput(
                        label = "Quantity",
                        value = numberText,
                        onValueChange = { numberText = it.filter { ch -> ch.isDigit() } }, // simple number-only filter
                        placeholder = "0",
                        keyboardType = KeyboardType.Number,
                        maxLines = 1
                    )

                    SpacerSmall()

                    var multi by remember { mutableStateOf("") }
                    XInput(
                        label = "Note",
                        value = multi,
                        onValueChange = { multi = it },
                        placeholder = "Enter note...",
                        minLines = 3,
                        maxLines = 5,
                        keyboardType = KeyboardType.Text
                    )

                    SpacerSmall()

                    var errorInput by remember { mutableStateOf("Input something...") }
                    XInput(
                        label = "Note",
                        value = errorInput,
                        isRequired = true,
                        onValueChange = { errorInput = it },
                        placeholder = "Input something...",
                        minLines = 3,
                        maxLines = 5,
                        errorMessage = "*Text too long",
                        isError = errorInput.length >= 6,
                        keyboardType = KeyboardType.Text
                    )
                }
            }
        }

    }
}

@Preview
@Composable
private fun PreviewContent(modifier: Modifier = Modifier) {
    InputScreen(rootNavController = rememberNavController())
}
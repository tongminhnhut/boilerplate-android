package com.tongminhnhut.android_compose.compose_app.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tongminhnhut.android_compose.compose_app.presentation.base.common.SpacerNormal
import com.tongminhnhut.android_compose.compose_app.presentation.base.common.SpacerTinySmall
import com.tongminhnhut.android_compose.compose_app.presentation.base.component.AppBarView
import com.tongminhnhut.android_compose.compose_app.presentation.base.component.BaseDialog
import com.tongminhnhut.android_compose.compose_app.presentation.base.component.MenuItemData
import com.tongminhnhut.android_compose.compose_app.presentation.base.component.XButton
import com.tongminhnhut.android_compose.compose_app.presentation.base.component.XDropDownView
import com.tongminhnhut.android_compose.ui.theme.AppColors

@Composable
fun ButtonScreen(rootNavController: NavController) {
    var loading by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        BaseDialog(
            title = "Delete this file?",
            subtitle = "Are you sure you want to permanently delete this item? This action cannot be undone.",
            confirmText = "Delete",
            cancelText = "Cancel",
            onConfirm = {
                showDialog = false
            },
            onCancel = { showDialog = false }
        )
    }

    var selectedCountry by remember { mutableStateOf<String?>(null) }
    val countries = listOf("Vietnam", "Thailand", "Japan", "Singapore")

    Scaffold(
        topBar = {
            AppBarView(title = "Buttons", onBackClick = rootNavController::popBackStack, menuItems = listOf(
                MenuItemData(
                    title = "Setting",
                    icon = Icons.Default.Settings,
                    onClick = {}
                )))
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            item {
                Column {
                    XDropDownView(
                        label = "Country",
                        items = countries,
                        selectedItem = selectedCountry,
                        onItemSelected = { selectedCountry = it },
                        placeholder = "Select your country",
                        isRequired = true
                    )

                    SpacerNormal()


                    Text("Button normal")
                    SpacerTinySmall()
                    XButton(
                        text = "Submit",
                        iconLeft = Icons.Default.Send,
                        onClick = {
                        }
                    )

                    SpacerNormal()

                    Text("Disable Button")
                    SpacerTinySmall()
                    XButton(
                        enabled = false,
                        text = if (loading) "Processing..." else "Submit",
                        iconLeft = Icons.Default.Send,
                        onClick = {
                        }
                    )

                    SpacerNormal()

                    Text("Loading Button")
                    SpacerTinySmall()
                    XButton(
                        enabled = true,
                        text = if (loading) "Processing..." else "Submit",
                        iconLeft = Icons.Default.Send,
                        isLoading = loading,
                        onClick = {
                            loading = !loading
                        }
                    )

                    SpacerNormal()

                    Text("Cancel Button")
                    SpacerTinySmall()
                    XButton(
                        enabled = true,
                        containerColor = AppColors.Red,
                        text = "Cancel",
                        onClick = {
                        }
                    )

                    SpacerNormal()

                    Text("Show Dialog Button")
                    SpacerTinySmall()
                    XButton(
                        enabled = true,
                        text = "Show",
                        onClick = {
                            showDialog = true
                        }
                    )



                }

            }
        }

    }
}

@Preview
@Composable
private fun PreviewContent(modifier: Modifier = Modifier) {
    ButtonScreen(rootNavController = rememberNavController())
}
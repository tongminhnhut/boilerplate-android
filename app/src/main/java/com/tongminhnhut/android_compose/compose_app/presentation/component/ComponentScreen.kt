package com.tongminhnhut.android_compose.compose_app.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tongminhnhut.android_compose.compose_app.presentation.base.component.AppBarView
import com.tongminhnhut.android_compose.core.navigation.AppCoordinator
import com.tongminhnhut.android_compose.ui.theme.AppTextStyle

@Composable
fun ComponentScreen(rootNavController: NavController) {
    val menuList = ComponentTypeEnum.entries.toList()
    Scaffold(
        topBar = {
            AppBarView(title = "Components", onBackClick = rootNavController::popBackStack)
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            LazyColumn(modifier = Modifier.padding(16.dp)) {
                items(count = menuList.size, itemContent = {
                    val item = menuList[it]
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp)
                            .clickable {
                                when (item) {
                                    ComponentTypeEnum.TEXT_INPUT -> {
                                        AppCoordinator.pushToInputScreen(rootNavController)
                                    }

                                    ComponentTypeEnum.BUTTON -> {
                                        AppCoordinator.pushToButtonScreen(rootNavController)
                                    }

                                    ComponentTypeEnum.DROP_DOWN -> {
                                        AppCoordinator.pushToDropDownScreen(rootNavController)
                                    }

                                    ComponentTypeEnum.IMAGE_VIEW -> {
                                        AppCoordinator.pushToImageViewScreen(rootNavController)
                                    }

                                    ComponentTypeEnum.LIST_VIEW -> {
                                        AppCoordinator.pushToListViewScreen(rootNavController)
                                    }
                                }
                            },
                        shape = MaterialTheme.shapes.medium,
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surface,
                            contentColor = MaterialTheme.colorScheme.onSurface
                        )
                    ) {
                        Text(
                            text = item.displayName,
                            style = AppTextStyle.Normal.copy(fontWeight = FontWeight.W600),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp, horizontal = 20.dp)
                        )
                    }

                })

            }
        }
    }
}

@Preview
@Composable
private fun PreviewContent(modifier: Modifier = Modifier) {
    ComponentScreen(rootNavController = rememberNavController())
}
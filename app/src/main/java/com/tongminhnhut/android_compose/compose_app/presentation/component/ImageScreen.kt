package com.tongminhnhut.android_compose.compose_app.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.tongminhnhut.android_compose.compose_app.presentation.base.common.SpacerSmall
import com.tongminhnhut.android_compose.compose_app.presentation.base.component.AppBarView
import com.tongminhnhut.android_compose.compose_app.presentation.base.component.MenuItemData
import com.tongminhnhut.android_compose.compose_app.presentation.base.component.XImageSlider
import com.tongminhnhut.android_compose.compose_app.presentation.base.component.XImageView

@Composable
fun ImageScreen(rootNavController: NavController) {
    Scaffold(
        topBar = {
            AppBarView(
                title = "Image Views",
                onBackClick = rootNavController::popBackStack,
                menuItems = listOf(
                    MenuItemData(
                        title = "Setting",
                        icon = Icons.Default.Settings,
                        onClick = {}
                    ),
                    MenuItemData(
                        title = "Others",
                        icon = Icons.Default.AccountCircle,
                        onClick = {}
                    ),
                ),
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    XImageView(
                        imageUrl = "https://images.unsplash.com/photo-1628519592419-bf288f08cef5?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8M3x8c3BvcnRzJTIwY2FyfGVufDB8fDB8fHww&fm=jpg&q=60&w=3000",
                        modifier = Modifier
                            .width(200.dp)
                            .height(300.dp)
                    )

                    XImageView(
                        imageUrl = "https://media.architecturaldigest.com/photos/66a914f1a958d12e0cc94a8e/16:9/w_2240,c_limit/DSC_5903.jpg",
                        modifier = Modifier
                            .width(300.dp)
                            .height(180.dp)
                    )

                    XImageView(
                        imageUrl = "",
                        modifier = Modifier
                            .width(200.dp)
                            .height(150.dp)
                    )

                    SpacerSmall()

                    XImageSlider(imageUrls = listOf(
"https://images.unsplash.com/photo-1614200179396-2bdb77ebf81b?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8bHV4dXJ5JTIwY2FyfGVufDB8fDB8fHww&fm=jpg&q=60&w=3000",
                        "https://cdn.pixabay.com/photo/2024/07/13/07/40/cars-8891625_1280.jpg",
                        "https://i.abcnewsfe.com/a/f43853f3-9eaf-4048-9ae7-757332c5787e/mclaren-1-ht-gmh-240412_1712928561648_hpMain_16x9.jpg?w=1600",
                        "https://media.architecturaldigest.com/photos/66758a12539a07cf706eefaa/2:3/w_1500,h_2250,c_limit/EMBARGO-BUGATTI-World-Premiere-Presskit-Images-26.jpg",
                        "https://koenigsegg-cdn-g7eehhd6f0ewcaff.z02.azurefd.net/drupal/styles/1920x1400/azure/2025-06/KG14%20BANNER%202.png?h=52705c42&itok=px_otKqm"
                    ))
                }
            }
        }
    }
}
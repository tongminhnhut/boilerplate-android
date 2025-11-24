package com.tongminhnhut.android_compose.compose_app.presentation.base.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign

@Composable
fun XButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    isLoading: Boolean = false,
    enabled: Boolean = true,
    iconLeft: ImageVector? = null,
    iconRight: ImageVector? = null,
    contentColor: Color = Color.White,
    containerColor: Color = MaterialTheme.colorScheme.primary,
) {
    Button(
        onClick = { if (!isLoading) onClick() },
        modifier = modifier
            .height(48.dp)
            .fillMaxWidth(),
        enabled = enabled && !isLoading,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor,
            disabledContainerColor = containerColor.copy(alpha = 0.4f),
            disabledContentColor = contentColor.copy(alpha = 0.5f)
        ),
        shape = MaterialTheme.shapes.medium,
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            this@Button.AnimatedVisibility(
                visible = isLoading,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                CircularProgressIndicator(
                    color = contentColor,
                    strokeWidth = 2.dp,
                    modifier = Modifier.size(22.dp)
                )
            }

            this@Button.AnimatedVisibility(
                visible = !isLoading,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    if (iconLeft != null) {
                        Icon(
                            imageVector = iconLeft,
                            contentDescription = null,
                            modifier = Modifier
                                .size(20.dp)
                                .padding(end = 6.dp)
                        )
                    }

                    Text(
                        text = text,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyMedium
                    )

                    if (iconRight != null) {
                        Icon(
                            imageVector = iconRight,
                            contentDescription = null,
                            modifier = Modifier
                                .size(20.dp)
                                .padding(start = 6.dp)
                        )
                    }
                }
            }
        }
    }
}



@Preview
@Composable
private fun PreviewButton(modifier: Modifier = Modifier) {
    var loading by remember { mutableStateOf(true) }

    XButton(
        text = if (loading) "Processing..." else "Submit",
        isLoading = loading,
        iconLeft = Icons.Default.Send,
        onClick = {
        }
    )
}
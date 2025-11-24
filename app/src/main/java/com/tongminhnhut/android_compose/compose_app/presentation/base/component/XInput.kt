package com.tongminhnhut.android_compose.compose_app.presentation.base.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.focus.onFocusChanged
import com.tongminhnhut.android_compose.compose_app.presentation.base.common.SpacerSmall
import com.tongminhnhut.android_compose.ui.theme.AppTextStyle

@Composable
fun XInput(
    modifier: Modifier = Modifier,
    label: String? = null,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String? = null,
    minLines: Int = 1,
    maxLines: Int = 1,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Done,
    isError: Boolean = false,
    enabled: Boolean = true,
    shape: Shape = RoundedCornerShape(9.dp),
    borderColor: Color = Color(0xFFDDDDDD), // light gray
    focusedBorderColor: Color = Color(0xFF9E9E9E),
    borderWidthDp: Float = 1f,
    bgColor: Color = Color.White,
    errorMessage: String? = null,
    isRequired: Boolean = false,
    textStyle: TextStyle = TextStyle(fontSize = 16.sp, color = Color.Black),
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    var isFocused by remember { mutableStateOf(false) }

    val resolvedBorderColor = when {
        isError -> Color(0xFFF44336) // red if error
        isFocused -> focusedBorderColor
        else -> borderColor
    }

    Column(modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
        if (!label.isNullOrBlank()) {
            val labelDisplay = if (isRequired) {
                "*$label"
            } else {
                label
            }
            Text(
                text = labelDisplay,
                style = TextStyle(fontSize = 13.sp, fontWeight = FontWeight.Medium),
                color = Color.Gray,
                modifier = Modifier.padding(bottom = 6.dp)
            )
        }

        val baseModifier = Modifier
            .fillMaxWidth()
            .clip(shape)
            .background(bgColor, shape)
            .border(
                width = with(LocalDensity.current) { borderWidthDp.dp },
                color = resolvedBorderColor,
                shape = shape
            )
            .padding(horizontal = 12.dp, vertical = 10.dp)

        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            enabled = enabled,
            singleLine = (maxLines == 1),
            textStyle = textStyle,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = keyboardType,
                imeAction = imeAction
            ),
            minLines = minLines,
            maxLines = maxLines,
            visualTransformation = visualTransformation,
            interactionSource = remember { MutableInteractionSource() },
            cursorBrush = SolidColor(Color.Black),
            modifier = baseModifier.onFocusChanged { focusState ->
                isFocused = focusState.isFocused
            },
            decorationBox = { innerTextField ->
                // place placeholder when empty
                if (value.isEmpty() && !placeholder.isNullOrBlank()) {
                    Text(
                        text = placeholder,
                        style = textStyle.copy(color = Color(0xFF9E9E9E))
                    )
                }
                // the actual text field
                innerTextField()
            }
        )

        if (isError && !errorMessage.isNullOrEmpty()) {
            Text(
                text = errorMessage,
                style = AppTextStyle.Small,
                color = Color.Red,
                modifier = Modifier.padding(top = 4.dp)
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewUI() {
    var text by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
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
            onValueChange = {
                numberText = it.filter { ch -> ch.isDigit() }
            }, // simple number-only filter
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
    }
}


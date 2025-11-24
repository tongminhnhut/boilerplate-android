package com.tongminhnhut.android_compose.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

object AppTextStyle {

    val Small = TextStyle(
        fontSize = 12.sp,
        lineHeight = 16.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily.Default,
        letterSpacing = 0.1.sp
    )

    val Normal = TextStyle(
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily.Default,
        letterSpacing = 0.15.sp
    )

    val Medium = TextStyle(
        fontSize = 16.sp,
        lineHeight = 22.sp,
        fontWeight = FontWeight.Medium,
        fontFamily = FontFamily.Default,
        letterSpacing = 0.15.sp
    )

    val Large = TextStyle(
        fontSize = 20.sp,
        lineHeight = 26.sp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = FontFamily.Default,
        letterSpacing = 0.sp
    )
}

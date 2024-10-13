package ru.itis.liiceberg.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = fontFamilyW400,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodySmall = TextStyle(
        fontFamily = fontFamilyW400,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.5.sp
    ),
    titleLarge = TextStyle(
        fontFamily = fontFamilyW600,
        fontSize = 36.sp,
        lineHeight = 44.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = fontFamilyW500,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )

)
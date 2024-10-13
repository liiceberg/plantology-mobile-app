package ru.itis.liiceberg.ui.theme

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import ru.itis.liiceberg.ui.R

val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

val fontName = GoogleFont("Roboto")

val fontFamilyW400 = FontFamily(
    Font(
        googleFont = fontName,
        fontProvider = provider,
        weight = FontWeight.W400,
        style = FontStyle.Normal
    )
)

val fontFamilyW500 = FontFamily(
    Font(
        googleFont = fontName,
        fontProvider = provider,
        weight = FontWeight.W500,
        style = FontStyle.Normal
    )
)
val fontFamilyW600 = FontFamily(
    Font(
        googleFont = fontName,
        fontProvider = provider,
        weight = FontWeight.W600,
        style = FontStyle.Normal
    )
)
package ru.itis.liiceberg.ui.components

import android.app.Activity
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.content.ContextCompat
import ru.itis.liiceberg.ui.R

@Composable
fun RuntimePermissionDialog(
    permission: String,
    rationaleTitle: String = stringResource(R.string.permission_needed),
    rationaleText: String,
    onPermissionGranted: () -> Unit,
    onPermissionPermanentlyDenied: () -> Unit,
) {
    val ctx = LocalContext.current
    var showRationale by remember { mutableStateOf(false) }

    val requestPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->

        if (isGranted) {
            onPermissionGranted()
        } else {
            if ((ctx as? Activity)?.shouldShowRequestPermissionRationale(permission) == true) {
                showRationale = true
            } else {
                onPermissionPermanentlyDenied()
            }
        }
    }

    if (showRationale) {
        SimpleAlertDialog(
            title = rationaleTitle,
            text = rationaleText,
            onConfirm = {
                requestPermissionLauncher.launch(permission)
                showRationale = false
            },
            onCancel = {
                showRationale = false
                onPermissionPermanentlyDenied()
            }
        )
    }

    LaunchedEffect(permission) {
        when (ContextCompat.checkSelfPermission(ctx, permission)) {
            PackageManager.PERMISSION_GRANTED -> {
                onPermissionGranted()
            }

            else -> {
                if ((ctx as? Activity)?.shouldShowRequestPermissionRationale(permission) == true) {
                    showRationale = true
                } else {
                    requestPermissionLauncher.launch(permission)
                }
            }
        }
    }

}
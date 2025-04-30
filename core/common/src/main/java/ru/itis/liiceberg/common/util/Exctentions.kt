package ru.itis.liiceberg.common.util

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import ru.itis.liiceberg.common.R
import ru.itis.liiceberg.common.model.TimeUnit
import ru.itis.liiceberg.common.model.TimeValues
import ru.itis.liiceberg.common.resources.ResourceManager

fun Context.showShortToast(@StringRes msg: Int) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun TimeValues.getValueInString(resManager: ResourceManager) : String {
    val id = when (this.periodUnit) {
        TimeUnit.DAYS -> R.string.care_in_days
        TimeUnit.WEEKS-> R.string.care_in_week
        TimeUnit.MONTHS -> R.string.care_in_month
    }
    return resManager.getString(id, this.periodValue)
}
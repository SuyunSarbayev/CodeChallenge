package com.excelsior.codechallenge.infrastructure.utils

import android.content.Context
import android.widget.Toast

object Extensions {

    fun Context.showToast(
        message: String
    ) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT)
            .show()
    }
}
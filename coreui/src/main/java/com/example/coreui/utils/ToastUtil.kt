package com.example.coreui.utils

import android.content.Context
import android.widget.Toast
import com.example.coreui.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun showConnectionFailedToast(context: Context) {
    CoroutineScope(Dispatchers.Main).launch {
        Toast.makeText(
            context,
            R.string.connection_faled,
            Toast.LENGTH_LONG
        ).show()
    }
}

package com.example.core

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.net.ConnectException

fun CoroutineScope.fetchCatching(
    onConnectException: () -> Unit,
    block: suspend () -> Unit
) = launch {
    try {
        block()
    }
    catch (e: ConnectException) {
        onConnectException()
        Log.e(TAG_CORE, e.stackTraceToString())
    }
    catch (e: Exception) {
        Log.e(TAG_CORE, e.stackTraceToString())
    }
}
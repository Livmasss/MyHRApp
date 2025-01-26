package com.example.core

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.net.ConnectException

private const val TAG = "CoroutinesUtil"
fun CoroutineScope.fetchCatching(
    onConnectException: () -> Unit,
    block: suspend () -> Unit
) = launch {
    try {
        block()
    }
    catch (e: ConnectException) {
        onConnectException()
        Log.e(TAG, e.stackTraceToString())
    }
    catch (e: Exception) {
        Log.e(TAG, e.stackTraceToString())
    }
}
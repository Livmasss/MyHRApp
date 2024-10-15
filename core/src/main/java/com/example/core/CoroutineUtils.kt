package com.example.core

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
    }
    catch (_: Exception) {}
}
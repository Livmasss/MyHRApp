package com.example.core.data.remote.dataSources

import retrofit2.Response

/**
 * Throws [IllegalStateException] cause to [check] calls if body cannot be taken
 * Ensures that the response is successful and the body is not null
 */
fun <T> Response<T>.takeBody(): T {
    try {
        check (isSuccessful)

        val body = this.body()
        checkNotNull(body)
        return body
    }
    catch (e: Exception) {
        throw IllegalStateException("Take body failed")
    }
}
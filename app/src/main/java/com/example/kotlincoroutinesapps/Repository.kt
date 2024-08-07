package com.example.kotlincoroutinesapps

import java.net.HttpURLConnection
import java.net.URL

sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}

class Repository {
    private val url = "https://api.jikan.moe/v4/anime"

    fun urlRequest() : Result<String> {
        var url1 = URL(url).openConnection() as HttpURLConnection
        url1.connect()
        url1.requestMethod = "GET"

        if (url1.responseCode != 200) {
            return Result.Error(Exception("Error Data tidak tampil"))
        } else {
            return Result.Success(url1.inputStream.bufferedReader().readText())
        }
    }
}
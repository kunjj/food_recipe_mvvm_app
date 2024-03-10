package com.example.foodrecipesapplication.network

sealed class NetworkResponse<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T): NetworkResponse<T>(data)

    class Error<T>(message: String): NetworkResponse<T>(message = message)

    class Loading<T>: NetworkResponse<T>()
}
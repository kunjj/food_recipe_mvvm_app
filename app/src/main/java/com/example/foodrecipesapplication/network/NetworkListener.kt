package com.example.foodrecipesapplication.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import kotlinx.coroutines.flow.MutableStateFlow

class NetworkListener : ConnectivityManager.NetworkCallback() {
    private var isNetworkAvailable = MutableStateFlow(false)

    fun checkNetworkAvailability(context: Context): MutableStateFlow<Boolean> {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.registerDefaultNetworkCallback(this)

        val network = connectivityManager.activeNetwork ?: return isNetworkAvailable

        val capabilities =
            connectivityManager.getNetworkCapabilities(network) ?: return isNetworkAvailable
        return when {
            capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) -> {
                isNetworkAvailable.value = true
                isNetworkAvailable
            }

            else -> {
                isNetworkAvailable.value = false
                isNetworkAvailable
            }
        }
    }

    override fun onAvailable(network: Network) {
        this.isNetworkAvailable.value = true
    }

    override fun onLost(network: Network) {
        this.isNetworkAvailable.value = false
    }
}
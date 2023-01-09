package com.example.cleanapp.view

import com.configcat.ConfigCatClient
import com.configcat.LogLevel
import com.configcat.PollingModes

object ConfigClient {
    val client = ConfigCatClient.newBuilder()
        .mode(PollingModes.autoPoll(3 /* polling interval in seconds */))
        .build("o8naCDr5hUyGAvpqnc4ZjA/f01iT21DdEi9eUa7NoMfLw")
}


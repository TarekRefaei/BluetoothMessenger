package com.tarekrefaei.bluetoothmessenger.features.chat.domain

data class BluetoothMessage(
    val message : String,
    val senderName : String,
    val isFromLocalUser : Boolean
)

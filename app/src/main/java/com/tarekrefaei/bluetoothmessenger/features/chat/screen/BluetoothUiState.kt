package com.tarekrefaei.bluetoothmessenger.features.chat.screen

import com.tarekrefaei.bluetoothmessenger.features.chat.domain.BluetoothDeviceDomain
import com.tarekrefaei.bluetoothmessenger.features.chat.domain.BluetoothMessage

data class BluetoothUiState(
    val scannedDevices: List<BluetoothDeviceDomain> = emptyList(),
    val pairedDevices: List<BluetoothDeviceDomain> = emptyList(),
    val isConnected: Boolean = false,
    val isConnecting: Boolean = false,
    val error: String? = null,
    val messages : List<BluetoothMessage> = emptyList()
)

package com.tarekrefaei.bluetoothmessenger.features.scanning.screen

import com.tarekrefaei.bluetoothmessenger.features.scanning.domain.BluetoothDeviceDomain

data class BluetoothUiState(
    val scannedDevices: List<BluetoothDeviceDomain> = emptyList(),
    val pairedDevices: List<BluetoothDeviceDomain> = emptyList(),
    val isConnected: Boolean = false,
    val isConnecting: Boolean = false,
    val error: String? = null,
)

package com.tarekrefaei.bluetoothmessenger.features.scanning.domain

import kotlinx.coroutines.flow.StateFlow

interface BluetoothController {

    val scannedDevices : StateFlow<List<BluetoothDeviceDomain>>
    val pairedDevices : StateFlow<List<BluetoothDeviceDomain>>

    fun startScanning()
    fun stopScanning()
    fun release()
}
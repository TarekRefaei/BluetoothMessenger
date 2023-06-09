package com.tarekrefaei.bluetoothmessenger.features.scanning.data

import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice
import com.tarekrefaei.bluetoothmessenger.features.scanning.domain.BluetoothDeviceDomain

@SuppressLint("MissingPermission")
fun BluetoothDevice.toBluetoothDeviceDomain() : BluetoothDeviceDomain {
    return BluetoothDeviceDomain(
        name = name,
        address = address
    )
}
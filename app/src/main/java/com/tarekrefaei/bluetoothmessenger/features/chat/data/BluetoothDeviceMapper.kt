package com.tarekrefaei.bluetoothmessenger.features.chat.data

import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice
import com.tarekrefaei.bluetoothmessenger.features.chat.domain.BluetoothDeviceDomain

@SuppressLint("MissingPermission")
fun BluetoothDevice.toBluetoothDeviceDomain() : BluetoothDeviceDomain {
    return BluetoothDeviceDomain(
        name = name,
        address = address
    )
}
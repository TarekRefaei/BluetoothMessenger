package com.tarekrefaei.bluetoothmessenger.features.chat.screen

import android.Manifest
import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.tarekrefaei.bluetoothmessenger.features.chat.domain.BluetoothDeviceDomain

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BluetoothLeScanner(
    permissionLauncher: ActivityResultLauncher<Array<String>>,
    context: Context,
) {

    val viewModel = hiltViewModel<BluetoothViewModel>()
    val state by viewModel.state.collectAsState()

    LaunchedEffect(key1 = state.error) {
        state.error?.let { message ->
            Toast.makeText(
                context, message, Toast.LENGTH_LONG
            ).show()
        }
    }
    LaunchedEffect(key1 = state.isConnected) {
        if (state.isConnected) {
            Toast.makeText(
                context, "Connected", Toast.LENGTH_LONG
            ).show()
        }
    }

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        permissionLauncher.launch(
            arrayOf(
                Manifest.permission.BLUETOOTH_SCAN,
                Manifest.permission.BLUETOOTH_CONNECT,
            )
        )
    }

    when {
        state.isConnecting -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator()
                Text(text = "Connecting ...")
            }
        }
        state.isConnected -> {
            ChatScreen(
                state = state,
                onDisconnect = viewModel::disconnectFromDevice,
                onSendMessage = viewModel::sendMessage
            )
        }
        else -> {
            Column {
                BluetoothDeviceList(
                    scannedDevices = state.scannedDevices,
                    pairedDevices = state.pairedDevices,
                    onClick = viewModel::connectToDevice,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Button(
                        onClick = { viewModel.startScanning() }
                    ) {
                        Text(text = "Start Scan")
                    }
                    Button(
                        onClick = { viewModel.stopScanning() }
                    ) {
                        Text(text = "Stop Scan")
                    }
                    Button(
                        onClick = { viewModel.waitForIncomingConnection() }
                    ) {
                        Text(text = "Start Server")
                    }
                }
            }
        }
    }
}

@Composable
fun BluetoothDeviceList(
    scannedDevices: List<BluetoothDeviceDomain>,
    pairedDevices: List<BluetoothDeviceDomain>,
    onClick: (BluetoothDeviceDomain) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        item {
            Text(
                text = "Paired Devices",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier.padding(16.dp)
            )
        }
        items(pairedDevices) { device ->
            Text(
                text = device.name ?: "(No Name)",
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .clickable { onClick(device) }
            )
        }
        item {
            Text(
                text = "Scanned Devices",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier.padding(16.dp)
            )
        }
        items(scannedDevices) { device ->
            Text(
                text = device.name ?: "(No Name)",
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .clickable { onClick(device) }
            )
        }
    }
}
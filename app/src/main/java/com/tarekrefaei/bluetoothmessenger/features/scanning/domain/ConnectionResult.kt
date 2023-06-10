package com.tarekrefaei.bluetoothmessenger.features.scanning.domain

sealed interface ConnectionResult {
    object ConnectionEstablished : ConnectionResult
    data class Error (val message : String) : ConnectionResult
}
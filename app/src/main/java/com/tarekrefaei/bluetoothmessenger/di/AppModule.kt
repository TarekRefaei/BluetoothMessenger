package com.tarekrefaei.bluetoothmessenger.di

import android.content.Context
import com.tarekrefaei.bluetoothmessenger.features.scanning.data.BluetoothControllerImpl
import com.tarekrefaei.bluetoothmessenger.features.scanning.domain.BluetoothController
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideBluetoothController(@ApplicationContext context: Context): BluetoothController {
        return BluetoothControllerImpl(
            context = context
        )
    }
}
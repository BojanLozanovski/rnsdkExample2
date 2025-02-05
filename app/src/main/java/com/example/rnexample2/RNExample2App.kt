package com.example.rnexample2

import com.example.rnsdk.MyRNSDK
import com.example.rnsdk.application.BaseSdkApplication
import com.example.rnsdk.communicator.IMySDKSharedPreferencesManager

class RNExample2App : BaseSdkApplication() {

    override fun onCreate() {
        super.onCreate()

        val sharedPreferences: IMySDKSharedPreferencesManager =
            object : IMySDKSharedPreferencesManager {
                override val accessToken: String
                    get() = "accessToken_v2"
                override val refreshToken: String
                    get() = "refreshToken_v2"

                override fun getDeviceId(): String = "deviceId_v2"

            }
        MyRNSDK.init(
            sharedPreferences = sharedPreferences
        )
    }
}
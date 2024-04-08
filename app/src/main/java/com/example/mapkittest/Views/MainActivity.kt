package com.example.mapkittest.Views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mapkittest.BuildConfig
import com.example.mapkittest.R
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.distribute.Distribute
import com.yandex.mapkit.MapKitFactory


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setApiKey(savedInstanceState)
        val mapFragment = MapFragment()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, mapFragment).commit()
        AppCenter.start(application, "09a82e4e-b5b8-4721-a72a-94c3c7973a73", Distribute::class.java)
    }

    private fun setApiKey(savedInstanceState: Bundle?) {
        val haveApiKey = savedInstanceState?.getBoolean("haveApiKey") ?: false // При первом запуске приложения всегда false
        if (!haveApiKey) {
            MapKitFactory.setApiKey(BuildConfig.MAPKIT_API_KEY)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("haveApiKey", true)
    }

}
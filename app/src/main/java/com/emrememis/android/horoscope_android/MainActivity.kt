package com.emrememis.android.horoscope_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.emrememis.android.horoscope.HoroscopeManager
import com.emrememis.android.horoscope.util.HoroscopeType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * @author emre.memis.49@gmail.com
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /**
         * with flow {best way to use}
         */
        CoroutineScope(Dispatchers.IO).launch {
            HoroscopeManager.getFlow(HoroscopeType.BASAK).collect { horoscope ->
                Log.d(TAG, "${horoscope.name}")
                Log.d(TAG, "${horoscope.birthdayRange}")
                Log.d(TAG, "${horoscope.motto}")
                Log.d(TAG, "${horoscope.rulingPlanet}")
                Log.d(TAG, "${horoscope.element}")
                Log.d(TAG, "${horoscope.personalCharacteristics}")
                Log.d(TAG, "${horoscope.group}")
                Log.d(TAG, "${horoscope.colors}")
                Log.d(TAG, "${horoscope.luckyStones}")
                Log.d(TAG, "${horoscope.luckyNumbers}")
                Log.d(TAG, "${horoscope.luckyDay}")
                Log.d(TAG, "${horoscope.friendHoroscope}")
                Log.d(TAG, "${horoscope.cities}")
                Log.d(TAG, "${horoscope.metal}")
                Log.d(TAG, "${horoscope.flowers}")
                Log.d(TAG, "${horoscope.trees}")
                Log.d(TAG, "${horoscope.dailyHoroscope}")
                Log.d(TAG, "${horoscope.weeklyHoroscope}")
                Log.d(TAG, "${horoscope.monthlyHoroscope}")
                Log.d(TAG, "${horoscope.yearlyHoroscope}")
            }
        }
        /**
         * with async/await
         */
        CoroutineScope(Dispatchers.IO).launch {
            val horoscope = HoroscopeManager.fetchAsync(HoroscopeType.BASAK, this).await()
            Log.d(TAG, "${horoscope.name}")
            Log.d(TAG, "${horoscope.birthdayRange}")
            Log.d(TAG, "${horoscope.motto}")
            Log.d(TAG, "${horoscope.rulingPlanet}")
            Log.d(TAG, "${horoscope.element}")
            Log.d(TAG, "${horoscope.personalCharacteristics}")
            Log.d(TAG, "${horoscope.group}")
            Log.d(TAG, "${horoscope.colors}")
            Log.d(TAG, "${horoscope.luckyStones}")
            Log.d(TAG, "${horoscope.luckyNumbers}")
            Log.d(TAG, "${horoscope.luckyDay}")
            Log.d(TAG, "${horoscope.friendHoroscope}")
            Log.d(TAG, "${horoscope.cities}")
            Log.d(TAG, "${horoscope.metal}")
            Log.d(TAG, "${horoscope.flowers}")
            Log.d(TAG, "${horoscope.trees}")
            Log.d(TAG, "${horoscope.dailyHoroscope}")
            Log.d(TAG, "${horoscope.weeklyHoroscope}")
            Log.d(TAG, "${horoscope.monthlyHoroscope}")
            Log.d(TAG, "${horoscope.yearlyHoroscope}")
        }
        /**
         * with run blocking {not recommended}
         */
        HoroscopeManager.get(HoroscopeType.AKREP) { horoscope ->
            Log.d(TAG, "${horoscope.name}")
            Log.d(TAG, "${horoscope.birthdayRange}")
            Log.d(TAG, "${horoscope.motto}")
            Log.d(TAG, "${horoscope.rulingPlanet}")
            Log.d(TAG, "${horoscope.element}")
            Log.d(TAG, "${horoscope.personalCharacteristics}")
            Log.d(TAG, "${horoscope.group}")
            Log.d(TAG, "${horoscope.colors}")
            Log.d(TAG, "${horoscope.luckyStones}")
            Log.d(TAG, "${horoscope.luckyNumbers}")
            Log.d(TAG, "${horoscope.luckyDay}")
            Log.d(TAG, "${horoscope.friendHoroscope}")
            Log.d(TAG, "${horoscope.cities}")
            Log.d(TAG, "${horoscope.metal}")
            Log.d(TAG, "${horoscope.flowers}")
            Log.d(TAG, "${horoscope.trees}")
            Log.d(TAG, "${horoscope.dailyHoroscope}")
            Log.d(TAG, "${horoscope.weeklyHoroscope}")
            Log.d(TAG, "${horoscope.monthlyHoroscope}")
            Log.d(TAG, "${horoscope.yearlyHoroscope}")
        }
    }
    companion object {
        private const val TAG = "MainActivity.kt"
    }
}
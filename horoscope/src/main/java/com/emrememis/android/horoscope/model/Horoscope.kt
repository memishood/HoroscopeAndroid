package com.emrememis.android.horoscope.model

/**
 * @author emre.memis.49@gmail.com
 */
data class Horoscope(
    val name: String?,
    var birthdayRange: String?,
    val motto: String?,
    val rulingPlanet: String?,
    val element: String?,
    val personalCharacteristics: String?,
    val group: MutableList<String>?,
    val colors: MutableList<String>?,
    val luckyStones: MutableList<String>?,
    val luckyNumbers: MutableList<Int>?,
    val luckyDay: String?,
    val friendHoroscope: String?,
    val cities: MutableList<String>?,
    val metal: String?,
    val flowers: MutableList<String>?,
    val trees: MutableList<String>?,
    val dailyHoroscope: String?,
    val weeklyHoroscope: String?,
    val monthlyHoroscope: String?,
    val yearlyHoroscope: String?
)
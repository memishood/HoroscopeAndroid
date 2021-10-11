package com.emrememis.android.horoscope

import com.emrememis.android.horoscope.model.Horoscope
import com.emrememis.android.horoscope.util.HoroscopeTime
import com.emrememis.android.horoscope.util.HoroscopeType
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.flow
import org.jsoup.Jsoup
import java.util.*

/**
 * @author emre.memis.49@gmail.com
 */
object HoroscopeManager {
    /**
     * fetch horoscope data with all arguments from https://hurriyet.com.tr & https://sabah.com.tr
     * @param type {type of horoscope as birthday time}
     * @param action {result callback}
     */
    fun get(type: HoroscopeType, action: (horoscope: Horoscope) -> Unit) = runBlocking(Dispatchers.IO) {
        val url = "https://www.sabah.com.tr/astroloji/${type.name.lowercase()}-burcu-ozellikleri"
        val url2 = "https://www.hurriyet.com.tr/mahmure/astroloji/${type.name.lowercase()}-burcu-ozellikleri/"
        val document = Jsoup.connect(url).get()
        val document2 = Jsoup.connect(url2).get()
        val body = document.body().text()
        val body2 = document2.body().text()
        val dailyHoroscope = getHoroscopeContent(type, HoroscopeTime.GUNLUK)
        val weeklyHoroscope = getHoroscopeContent(type, HoroscopeTime.HAFTALIK)
        val monthlyHoroscope = getHoroscopeContent(type, HoroscopeTime.AYLIK)
        val yearlyHoroscope = getHoroscopeContent(type, HoroscopeTime.YILLIK)

        val horoscopeName = body.substringCatch(
            body.indexOf("Burcu Genel Burç Yorumu") + "Burcu Genel Burç Yorumu".length,
            body.indexOf("BURCU")
        )?.trim()?.lowercase()?.replaceFirstChar { it.uppercase() } ?: type.name

        val birthdayRange = document.body().getElementsByClass("burclarB").first()
            ?.child(0)
            ?.child(0)
            ?.child(0)
            ?.child(1)
            ?.child(0)
            ?.ownText()

        val group = body.substringCatch(
            body.indexOf("Grup:") + "Grup:".length,
            body.indexOf("Yönetici Gezegen:")
        )?.trim()?.toStringArray

        val rulingPlanet = body.substringCatch(
            body.indexOf("Yönetici Gezegen:") + "Yönetici Gezegen:".length,
            body.indexOf("Renk:")
        )?.trim()

        val colors = body.substringCatch(
            body.indexOf("Renk:") + "Renk:".length,
            body.indexOf("Uğurlu Taş:")
        )?.trim()?.toStringArray

        val luckyStones = body.substringCatch(
            body.indexOf("Uğurlu Taş:") + "Uğurlu Taş:".length,
            body.indexOf("Şanslı Sayı:")
        )?.trim()?.toStringArray

        val luckyNumbers = body.substringCatch(
            body.indexOf("Şanslı Sayı:") + "Şanslı Sayı:".length,
            body.indexOf("Şanslı Gün:")
        )?.trim()?.toIntArray

        val luckyDay = body.substringCatch(
            body.indexOf("Şanslı Gün:") + "Şanslı Gün:".length,
            body.indexOf("Karşıt Burç:")
        )?.trim()

        val friendHoroscope = body.substringCatch(
            body.indexOf("Karşıt Burç:") + "Karşıt Burç:".length,
            body.indexOf("Şehirler:")
        )?.trim()

        val cities = body.substringCatch(
            body.indexOf("Şehirler:") + "Şehirler:".length,
            body.indexOf("Metal:")
        )?.trim()?.toStringArray

        val metal = body.substringCatch(
            body.indexOf("Metal:") + "Metal:".length,
            body.indexOf("Çiçekler:")
        )?.trim()

        val flowers = body.substringCatch(
            body.indexOf("Çiçekler:") + "Çiçekler:".length,
            body.indexOf("Ağaçlar:")
        )?.trim()?.toStringArray

        val trees = body.substringCatch(
            body.indexOf("Ağaçlar:") + "Ağaçlar:".length,
            body.indexOf("KİŞİSEL ÖZELLİKLERİ:")
        )?.trim()?.toStringArray

        val personalCharacteristics = body.substringCatch(
            body.indexOf("KİŞİSEL ÖZELLİKLERİ:") + "KİŞİSEL ÖZELLİKLERİ:".length,
            body.indexOf("FİZİKSEL ÖZELLİKLERİ:")
        )?.trim()

        val motto = body2.substringCatch(
            body2.indexOf("Mottosu:") + "Mottosu:".length,
            body2.indexOf("Yönetici Gezegeni:")
        )?.trim()

        val element = body2.substringCatch(
            body2.indexOf("Elementi:") + "Elementi:".length,
            body2.indexOf("PAYLAŞ")
        )?.trim()

        val horoscope = Horoscope(
            horoscopeName, birthdayRange, motto, rulingPlanet, element, personalCharacteristics, group,
            colors, luckyStones, luckyNumbers, luckyDay, friendHoroscope, cities, metal,
            flowers, trees, dailyHoroscope, weeklyHoroscope, monthlyHoroscope, yearlyHoroscope
        )

        withContext(Dispatchers.Default) {
            action(horoscope)
        }
    }
    suspend fun getFlow(type: HoroscopeType) = flow {
        val url = "https://www.sabah.com.tr/astroloji/${type.name.lowercase()}-burcu-ozellikleri"
        val url2 = "https://www.hurriyet.com.tr/mahmure/astroloji/${type.name.lowercase()}-burcu-ozellikleri/"
        val document = Jsoup.connect(url).get()
        val document2 = Jsoup.connect(url2).get()
        val body = document.body().text()
        val body2 = document2.body().text()
        val dailyHoroscope = getHoroscopeContent(type, HoroscopeTime.GUNLUK)
        val weeklyHoroscope = getHoroscopeContent(type, HoroscopeTime.HAFTALIK)
        val monthlyHoroscope = getHoroscopeContent(type, HoroscopeTime.AYLIK)
        val yearlyHoroscope = getHoroscopeContent(type, HoroscopeTime.YILLIK)

        val horoscopeName = body.substringCatch(
            body.indexOf("Burcu Genel Burç Yorumu") + "Burcu Genel Burç Yorumu".length,
            body.indexOf("BURCU")
        )?.trim()?.lowercase()?.replaceFirstChar { it.uppercase() } ?: type.name

        val birthdayRange = document.body().getElementsByClass("burclarB").first()
            ?.child(0)
            ?.child(0)
            ?.child(0)
            ?.child(1)
            ?.child(0)
            ?.ownText()

        val group = body.substringCatch(
            body.indexOf("Grup:") + "Grup:".length,
            body.indexOf("Yönetici Gezegen:")
        )?.trim()?.toStringArray

        val rulingPlanet = body.substringCatch(
            body.indexOf("Yönetici Gezegen:") + "Yönetici Gezegen:".length,
            body.indexOf("Renk:")
        )?.trim()

        val colors = body.substringCatch(
            body.indexOf("Renk:") + "Renk:".length,
            body.indexOf("Uğurlu Taş:")
        )?.trim()?.toStringArray

        val luckyStones = body.substringCatch(
            body.indexOf("Uğurlu Taş:") + "Uğurlu Taş:".length,
            body.indexOf("Şanslı Sayı:")
        )?.trim()?.toStringArray

        val luckyNumbers = body.substringCatch(
            body.indexOf("Şanslı Sayı:") + "Şanslı Sayı:".length,
            body.indexOf("Şanslı Gün:")
        )?.trim()?.toIntArray

        val luckyDay = body.substringCatch(
            body.indexOf("Şanslı Gün:") + "Şanslı Gün:".length,
            body.indexOf("Karşıt Burç:")
        )?.trim()

        val friendHoroscope = body.substringCatch(
            body.indexOf("Karşıt Burç:") + "Karşıt Burç:".length,
            body.indexOf("Şehirler:")
        )?.trim()

        val cities = body.substringCatch(
            body.indexOf("Şehirler:") + "Şehirler:".length,
            body.indexOf("Metal:")
        )?.trim()?.toStringArray

        val metal = body.substringCatch(
            body.indexOf("Metal:") + "Metal:".length,
            body.indexOf("Çiçekler:")
        )?.trim()

        val flowers = body.substringCatch(
            body.indexOf("Çiçekler:") + "Çiçekler:".length,
            body.indexOf("Ağaçlar:")
        )?.trim()?.toStringArray

        val trees = body.substringCatch(
            body.indexOf("Ağaçlar:") + "Ağaçlar:".length,
            body.indexOf("KİŞİSEL ÖZELLİKLERİ:")
        )?.trim()?.toStringArray

        val personalCharacteristics = body.substringCatch(
            body.indexOf("KİŞİSEL ÖZELLİKLERİ:") + "KİŞİSEL ÖZELLİKLERİ:".length,
            body.indexOf("FİZİKSEL ÖZELLİKLERİ:")
        )?.trim()

        val motto = body2.substringCatch(
            body2.indexOf("Mottosu:") + "Mottosu:".length,
            body2.indexOf("Yönetici Gezegeni:")
        )?.trim()

        val element = body2.substringCatch(
            body2.indexOf("Elementi:") + "Elementi:".length,
            body2.indexOf("PAYLAŞ")
        )?.trim()

        val horoscope = Horoscope(
            horoscopeName, birthdayRange, motto, rulingPlanet, element, personalCharacteristics, group,
            colors, luckyStones, luckyNumbers, luckyDay, friendHoroscope, cities, metal,
            flowers, trees, dailyHoroscope, weeklyHoroscope, monthlyHoroscope, yearlyHoroscope
        )
        emit(horoscope)
    }
    fun fetchAsync(type: HoroscopeType, scope: CoroutineScope) = scope.async {
        val url = "https://www.sabah.com.tr/astroloji/${type.name.lowercase()}-burcu-ozellikleri"
        val url2 = "https://www.hurriyet.com.tr/mahmure/astroloji/${type.name.lowercase()}-burcu-ozellikleri/"
        val document = Jsoup.connect(url).get()
        val document2 = Jsoup.connect(url2).get()
        val body = document.body().text()
        val body2 = document2.body().text()
        val dailyHoroscope = getHoroscopeContent(type, HoroscopeTime.GUNLUK)
        val weeklyHoroscope = getHoroscopeContent(type, HoroscopeTime.HAFTALIK)
        val monthlyHoroscope = getHoroscopeContent(type, HoroscopeTime.AYLIK)
        val yearlyHoroscope = getHoroscopeContent(type, HoroscopeTime.YILLIK)

        val horoscopeName = body.substringCatch(
            body.indexOf("Burcu Genel Burç Yorumu") + "Burcu Genel Burç Yorumu".length,
            body.indexOf("BURCU")
        )?.trim()?.lowercase()?.replaceFirstChar { it.uppercase() } ?: type.name

        val birthdayRange = document.body().getElementsByClass("burclarB").first()
            ?.child(0)
            ?.child(0)
            ?.child(0)
            ?.child(1)
            ?.child(0)
            ?.ownText()

        val group = body.substringCatch(
            body.indexOf("Grup:") + "Grup:".length,
            body.indexOf("Yönetici Gezegen:")
        )?.trim()?.toStringArray

        val rulingPlanet = body.substringCatch(
            body.indexOf("Yönetici Gezegen:") + "Yönetici Gezegen:".length,
            body.indexOf("Renk:")
        )?.trim()

        val colors = body.substringCatch(
            body.indexOf("Renk:") + "Renk:".length,
            body.indexOf("Uğurlu Taş:")
        )?.trim()?.toStringArray

        val luckyStones = body.substringCatch(
            body.indexOf("Uğurlu Taş:") + "Uğurlu Taş:".length,
            body.indexOf("Şanslı Sayı:")
        )?.trim()?.toStringArray

        val luckyNumbers = body.substringCatch(
            body.indexOf("Şanslı Sayı:") + "Şanslı Sayı:".length,
            body.indexOf("Şanslı Gün:")
        )?.trim()?.toIntArray

        val luckyDay = body.substringCatch(
            body.indexOf("Şanslı Gün:") + "Şanslı Gün:".length,
            body.indexOf("Karşıt Burç:")
        )?.trim()

        val friendHoroscope = body.substringCatch(
            body.indexOf("Karşıt Burç:") + "Karşıt Burç:".length,
            body.indexOf("Şehirler:")
        )?.trim()

        val cities = body.substringCatch(
            body.indexOf("Şehirler:") + "Şehirler:".length,
            body.indexOf("Metal:")
        )?.trim()?.toStringArray

        val metal = body.substringCatch(
            body.indexOf("Metal:") + "Metal:".length,
            body.indexOf("Çiçekler:")
        )?.trim()

        val flowers = body.substringCatch(
            body.indexOf("Çiçekler:") + "Çiçekler:".length,
            body.indexOf("Ağaçlar:")
        )?.trim()?.toStringArray

        val trees = body.substringCatch(
            body.indexOf("Ağaçlar:") + "Ağaçlar:".length,
            body.indexOf("KİŞİSEL ÖZELLİKLERİ:")
        )?.trim()?.toStringArray

        val personalCharacteristics = body.substringCatch(
            body.indexOf("KİŞİSEL ÖZELLİKLERİ:") + "KİŞİSEL ÖZELLİKLERİ:".length,
            body.indexOf("FİZİKSEL ÖZELLİKLERİ:")
        )?.trim()

        val motto = body2.substringCatch(
            body2.indexOf("Mottosu:") + "Mottosu:".length,
            body2.indexOf("Yönetici Gezegeni:")
        )?.trim()

        val element = body2.substringCatch(
            body2.indexOf("Elementi:") + "Elementi:".length,
            body2.indexOf("PAYLAŞ")
        )?.trim()

        Horoscope(
            horoscopeName, birthdayRange, motto, rulingPlanet, element, personalCharacteristics, group,
            colors, luckyStones, luckyNumbers, luckyDay, friendHoroscope, cities, metal,
            flowers, trees, dailyHoroscope, weeklyHoroscope, monthlyHoroscope, yearlyHoroscope
        )
    }
    private fun getHoroscopeContent(type: HoroscopeType, time: HoroscopeTime): String? {
        val url = when (time) {
            HoroscopeTime.GUNLUK -> "https://www.hurriyet.com.tr/mahmure/astroloji/${type.name.lowercase()}-burcu/"
            else -> "https://www.hurriyet.com.tr/mahmure/astroloji/${type.name.lowercase()}-burcu-${time.name.lowercase()}-yorum/"
        }
        val document = Jsoup.connect(url).get()
        val body = document.body()
        return body.getElementsByClass("horoscope-detail-tab-content").first()?.text()
    }
    /**
     * return null if couldn't find any data in text
     * @param startIndex
     * @param endIndex
     */
    private fun String.substringCatch(startIndex: Int, endIndex: Int) : String? {
        return try {
            substring(
                startIndex,
                endIndex
            )
        } catch (e: Exception) {
            null
        }
    }
    private val String.toStringArray: MutableList<String>?
        get() {
            return try {
                val result = mutableListOf<String>()
                val data = replace(" ", "")
                    .replace("/", ",")
                    .replace("ve", ",")
                    .replace("-", ",")
                data.split(",").forEach {
                    result.add(
                        it.lowercase()
                            .replaceFirstChar { char -> char.uppercase() }
                    )
                }
                result
            } catch (e: Exception) {
                null
            }
        }
    private val String.toIntArray: MutableList<Int>?
        get() {
            return try {
                val result = mutableListOf<Int>()
                val data = replace(" ", "")
                    .replace("/", ",")
                    .replace("ve", ",")
                    .replace("-", ",")
                data.split(",").forEach {
                    result.add(it.trim().toInt())
                }
                result
            } catch (e: Exception) {
                null
            }
        }
    /**
     * get horoscope type from date
     */
    val Date.horoscopeName: String
        get() {
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = time
            val day = calendar[Calendar.DAY_OF_MONTH]
            return when (calendar[Calendar.MONTH]) {
                0 -> when {
                    day < 20 -> "Oğlak"
                    else -> "Kova"
                }
                1 -> when {
                    day < 20 -> "Kova"
                    else -> "Balık"
                }
                2 -> when {
                    day < 20 -> "Balık"
                    else -> "Koç"
                }
                3 -> when {
                    day < 20 -> "Koç"
                    else -> "Boğa"
                }
                4 -> when {
                    day < 20 -> "Boğa"
                    else -> "İkizler"
                }
                5 -> when {
                    day < 20 -> "İkizler"
                    else -> "Yengeç"
                }
                6 -> when {
                    day < 20 -> "Yengeç"
                    else -> "Aslan"
                }
                7 -> when {
                    day < 20 -> "Aslan"
                    else -> "Başak"
                }
                8 -> when {
                    day < 20 -> "Başak"
                    else -> "Terazi"
                }
                9 -> when {
                    day < 20 -> "Terazi"
                    else -> "Akrep"
                }
                10 -> when {
                    day < 20 -> "Akrep"
                    else -> "Yay"
                }
                else -> when {
                    day < 20 -> "Yay"
                    else -> "Oğlak"
                }
            }
        }
    /**
     * generate horoscope image from date
     */
    val Date.horoscopeImage: Int
        get() = when (horoscopeName) {
            "Oğlak" -> R.drawable.capricorn_icon
            "Kova" -> R.drawable.aquarius_icon
            "Balık" -> R.drawable.pisces_icon
            "Koç" -> R.drawable.aries_icon
            "Boğa" -> R.drawable.taurus_icon
            "İkizler" -> R.drawable.gemini_icon
            "Yengeç" -> R.drawable.cancer_icon
            "Aslan" -> R.drawable.leo_icon
            "Başak" -> R.drawable.virgo_icon
            "Terazi" -> R.drawable.libra_icon
            "Akrep" -> R.drawable.scorpio_icon
            else -> R.drawable.sagittarius_icon
        }
}
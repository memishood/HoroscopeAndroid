[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](https://raw.githubusercontent.com/memishood/display-name-view/master/LICENSE)
[![](https://jitpack.io/v/memishood/HoroscopeAndroid.svg)](https://jitpack.io/#memishood/HoroscopeAndroid)
## Horoscope Library for Android
This library fetching horoscope without the need for a server in Android

### In your code:
```kotlin
HoroscopeManager.get(HoroscopeType.AKREP) { horoscope ->
    Log.d(TAG, "${horoscope.name}")
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
```

### Setup:
#### 1.Add this in your root `build.gradle` at the end of repositories:
    allprojects {
        repositories {
            ...
            maven { url "https://jitpack.io" }
        }
    }

#### 2.Add this dependency in your app level `build.gradle`:
    dependencies {
        ...
        implementation("com.github.memishood:com.emrememis.HoroscopeAndroid:1.1.0")
    }

## 🤝 License

```
MIT License

Copyright (c) 2021 memishood

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
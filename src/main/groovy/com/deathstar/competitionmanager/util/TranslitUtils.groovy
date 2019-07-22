package com.deathstar.competitionmanager.util

import org.springframework.stereotype.Component

import javax.annotation.PostConstruct

@Component
class TranslitUtils {
    Map<Character, String> replacament = [:]

    @PostConstruct
    void init() {
        replacament.put("а".charAt(0), "a")
        replacament.put("б".charAt(0), "b")
        replacament.put("в".charAt(0), "v")
        replacament.put("г".charAt(0), "g")
        replacament.put("д".charAt(0), "d")
        replacament.put("е".charAt(0), "e")
        replacament.put("ё".charAt(0), "e")
        replacament.put("ж".charAt(0), "dj")
        replacament.put("з".charAt(0), "z")
        replacament.put("и".charAt(0), "i")
        replacament.put("й".charAt(0), "i'")
        replacament.put("к".charAt(0), "k")
        replacament.put("л".charAt(0), "l")
        replacament.put("м".charAt(0), "m")
        replacament.put("н".charAt(0), "n")
        replacament.put("о".charAt(0), "o")
        replacament.put("п".charAt(0), "p")
        replacament.put("р".charAt(0), "r")
        replacament.put("с".charAt(0), "s")
        replacament.put("т".charAt(0), "t")
        replacament.put("у".charAt(0), "u")
        replacament.put("ф".charAt(0), "ph")
        replacament.put("х".charAt(0), "h")
        replacament.put("ц".charAt(0), "c")
        replacament.put("ч".charAt(0), "ch")
        replacament.put("ш".charAt(0), "sh")
        replacament.put("щ".charAt(0), "sh'")
        replacament.put("ъ".charAt(0), "'")
        replacament.put("ы".charAt(0), "ii")
        replacament.put("ь".charAt(0), "'")
        replacament.put("э".charAt(0), "a")
        replacament.put("ю".charAt(0), "iu")
        replacament.put("я".charAt(0), "ia")
    }

    String convertToTranslit(String string) {
        return string.toLowerCase().toCharArray().toList().collect { replacament.getOrDefault(it, it as String) }.sum()
    }
}

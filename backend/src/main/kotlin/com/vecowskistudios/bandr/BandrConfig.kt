package com.vecowskistudios.bandr

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource

@Configuration
class BandrConfig {

    @Bean
    fun artists(): Map<String, Set<String>> {
        val json = ClassPathResource("artists.json").inputStream.bufferedReader().readText().lowercase()
        return Gson().fromJson(json, object : TypeToken<HashMap<String, HashSet<String>>>() {}.type)
    }

    // invert artists map
    @Bean
    fun bands(artists: Map<String, Set<String>>): Map<String, Set<String>> {
        val bandToArtists = mutableMapOf<String, MutableSet<String>>()
        artists.forEach{ (artist, bands) ->
            bands.forEach {
                if (bandToArtists[it] == null) {
                    bandToArtists[it] = hashSetOf(artist)
                } else {
                    bandToArtists[it]!!.add(artist)
                }
            }
        }
        println(Gson().toJson(bandToArtists))
        return bandToArtists
    }

}
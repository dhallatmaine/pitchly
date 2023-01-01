package com.vecowskistudios.bandr

import org.springframework.stereotype.Service
import java.lang.RuntimeException

@Service
class SearchService(
    private val artists: Map<String, Set<String>>,
    private val bands: Map<String, Set<String>>,
) {

    // Search for a band, get a list of bands back
    fun search(band: String): List<String> {
        val results = mutableSetOf<String>()

        val artistsInBand = bands[band] ?: throw RuntimeException("$band not found.")

        // todo: make recursive
        for (artistInBand in artistsInBand) {
            val bands = artists[artistInBand] ?: emptySet()
            results.addAll(bands.filterNot { it == band })
        }

        return results.toList()
    }



}
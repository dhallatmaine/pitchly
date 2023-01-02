package com.vecowskistudios.pitchly

import org.springframework.stereotype.Service
import java.lang.RuntimeException
import java.util.LinkedList

@Service
class SearchService(
    private val artists: Map<String, Set<String>>,
    private val bands: Map<String, Set<String>>,
) {

    // Search for a band, get a list of bands back
    fun search(searchBand: String): List<String> {
        val results = mutableSetOf<String>()

        val artistsInBand = bands[searchBand] ?: throw RuntimeException("$searchBand not found.")

        val artistQueue = LinkedList(artistsInBand)
        val visitedArtists = mutableSetOf<String>()

        val bandQueue = LinkedList<String>()
        val visitedBands = mutableSetOf(searchBand)

        // first we check each artist in the band for additional bands
        // then we go through those bands and add artists we haven't checked yet
        while (artistQueue.isNotEmpty()) {
            val artist = artistQueue.pollFirst()
            visitedArtists.add(artist)

            val artistBands = (artists[artist] ?: emptySet())
                .filterNot { visitedBands.contains(it) }
            bandQueue.addAll(artistBands)

            while (bandQueue.isNotEmpty()) {
                val band = bandQueue.pollFirst()
                visitedBands.add(band)
                results.add(band)

                val artists = (bands[band] ?: emptySet())
                    .filterNot { visitedArtists.contains(it) }
                artistQueue.addAll(artists)
            }
        }

        return results.toList()
    }



}
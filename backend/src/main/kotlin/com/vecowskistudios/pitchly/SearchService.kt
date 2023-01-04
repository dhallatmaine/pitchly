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
    fun search(searchBand: String): List<BandLevel> {
        val results = mutableSetOf<BandLevel>()

        val artistQueue = LinkedList<List<String>>()
        val visitedArtists = mutableSetOf<String>()

        val bandQueue = LinkedList(listOf(listOf(searchBand)))
        val visitedBands = mutableSetOf(searchBand)

        var level = 0
        while (bandQueue.isNotEmpty()) {
            val bandsInQueue = bandQueue.pollFirst()
            if (bandsInQueue.isEmpty()) continue;
            results.addAll(bandsInQueue.map { BandLevel(it, level) })
            level++

            val bandArtists = bandsInQueue
                .flatMapTo(mutableSetOf()) { bands[it] ?: emptySet() }
                .filterNot { visitedArtists.contains(it) }
            visitedArtists.addAll(bandArtists)
            artistQueue.add(bandArtists)

            while (artistQueue.isNotEmpty()) {
                val artistsInQueue = artistQueue.pollFirst()
                if (artistsInQueue.isEmpty()) continue;

                val artistBands = artistsInQueue
                    .flatMapTo(mutableSetOf()) { artists[it] ?: emptySet() }
                    .filterNot { visitedBands.contains(it) }
                visitedBands.addAll(artistBands)
                bandQueue.add(artistBands)
            }
        }

        return results.toList()
            .filterNot { it.name == searchBand }
            .sortedBy { it.level }
    }



}
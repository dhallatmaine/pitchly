package com.vecowskistudios.bandr

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class SearchController(
    private val searchService: SearchService,
) {

    @GetMapping("/")
    fun search(@RequestParam(value = "band") band: String): List<String> {
        return searchService.search(band)
    }

}
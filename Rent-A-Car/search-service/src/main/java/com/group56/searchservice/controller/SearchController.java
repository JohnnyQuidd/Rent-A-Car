package com.group56.searchservice.controller;

import com.group56.searchservice.DTO.AdvancedQueryDTO;
import com.group56.searchservice.DTO.AdvertFilterDTO;
import com.group56.searchservice.DTO.AdvertQueryDTO;
import com.group56.searchservice.DTO.AdvertSortDTO;
import com.group56.searchservice.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/search-service")
public class SearchController {
    private SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping
    public ResponseEntity<?> findRelevantAdverts(@RequestBody AdvertQueryDTO advertDTO) {
        return searchService.findRelevantAdverts(advertDTO);
    }

    @GetMapping("/advert")
    public ResponseEntity<?> getAllAdverts() {
        return searchService.getActiveAdverts();
    }

    @GetMapping("/advert/user/{username}")
    public ResponseEntity<?> getAdvertsForSpecificUser(@PathVariable("username") String username) {
        return searchService.getAdvertsForSpecificUser(username);
    }

    @GetMapping("/advert/{advertUUID}")
    public ResponseEntity<?> findSingleAdvert(@PathVariable("advertUUID") String advertUUID) {
        return searchService.findAdvertByUUID(advertUUID);
    }

    @PostMapping("/filter")
    public ResponseEntity<?> filterAdverts(@RequestBody AdvertFilterDTO advertDTO) {
        return searchService.filterAdverts(advertDTO);
    }

    @PostMapping("/advanced/filter")
    public ResponseEntity<?> advancedFilter(@RequestBody AdvancedQueryDTO advertDTO) {
        return searchService.advancedFilter(advertDTO);
    }

    @PostMapping("/advertList")
    public ResponseEntity<?> getListAdverts(@RequestBody ArrayList<String> uuid) {
        return searchService.getListAdverts(uuid);
    }


    @PostMapping("/sort")
    public ResponseEntity<?> sortAdverts(@RequestBody AdvertSortDTO advertSortDTO) {
        return searchService.sortAdverts(advertSortDTO);
    }
}

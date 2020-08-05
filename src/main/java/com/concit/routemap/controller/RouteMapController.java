package com.concit.routemap.controller;

import com.concit.routemap.service.RouteMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RouteMapController {

    @Autowired
    private RouteMapService routeMapService;

    /**
     * Route Connection Service, determines connection between cities
     *
     * @param city1
     * @param city2
     * @return Response Entity
     */
    @GetMapping("/connected")
    public ResponseEntity areCititesConnected(@RequestParam ( value = "origin", defaultValue = "") String city1,
                                              @RequestParam (value="destination", defaultValue = "") String city2) {

        return ResponseEntity.ok().body(routeMapService.routeExists(city1, city2));
    }
}

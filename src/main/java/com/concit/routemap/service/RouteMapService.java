package com.concit.routemap.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class RouteMapService {

    public static final Logger logger = LoggerFactory.getLogger(RouteMapService.class);

    /**
     * Initialization of Route Service with Graph implementation
     * @throws IOException File parsing exception
     */
    public RouteMapService() throws IOException {
        this.adjCities= new HashMap<>();
        // File file = new File("src/main/resources/city.txt");
        //BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/city.txt"));
        InputStream inputStream = getClass().getResourceAsStream("/city.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String s;
        while ((s = reader.readLine()) != null) {
            String[] cities = s.split(",");
            if (cities.length == 2) {
                City city1 = new City(cities[0]);
                City city2 = new City(cities[1]);
                if (adjCities.isEmpty()) {
                    adjCities.put(city1, Stream.of(city1, city2).collect(Collectors.toList()));
                } else {
                    if (adjCities.containsKey(city1)) {
                        adjCities.get(city1).add(city2);
                    }
                    else{
                        adjCities.put(city1, Stream.of(city1, city2).collect(Collectors.toList()));
                    }
                }
            }
            else{
                logger.error("not a valid line two cities");
            }
        }

    }

    public RouteMapService(Map<City, List<City>> adjCities) {
        this.adjCities = adjCities;
    }

    public static class City {

        private String city;

        public City(String city) {
            this.city = city;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            City city1 = (City) o;
            return Objects.equals(city, city1.city);
        }

        @Override
        public int hashCode() {
            final int i = 11;
            int result = 1;
            result = i * result + city.hashCode();
            return result;
        }
    }

    private Map<RouteMapService.City, List<RouteMapService.City>> adjCities;

    private Set<City> connectedCites = new HashSet<>();


    public String routeExists(String city1, String city2) {

        //traverse Graph from city1

        Stack<String> stack = new Stack<>();
        stack.push(city1);
        while (!stack.isEmpty()) {
            String refCity = stack.pop();
            City o = new City(refCity);
            if (!connectedCites.contains(o)) {
                connectedCites.add(o);
                for (City c : adjCities.keySet()) {
                    stack.push(c.city);
                }
            }
        }

        if (connectedCites.contains(new City(city1)) && connectedCites.contains(new City(city2))) {
            return "Yes";
        }
        else{
            return "No";
        }

    }


}

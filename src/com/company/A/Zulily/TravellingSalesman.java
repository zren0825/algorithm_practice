package com.company.A.Zulily;

import java.util.*;

class City {
    String name;
    Map<City, Integer> neis;

    public City(String name, Map<City, Integer> neis) {
        this.name = name;
        this.neis = neis;
    }
}

public class TravellingSalesman {
    // 这里input理论上不应该是这样 应该给一个完整的图 不过这样的input逻辑上是够的
    public List<City> shortestWayToVisitAllCity(City startCity, int numCities) {
        List<City> shortestWay = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        visited.add(startCity.name);
        shortestWayToVisitAllCity(shortestWay, new LinkedList<>(), startCity, startCity, 0, new int[]{Integer.MAX_VALUE}, numCities, visited);
        return shortestWay;
    }

    private void shortestWayToVisitAllCity(List<City> shortestWay, List<City> curOrder, City startCity, City cur, int dist, int[] minDist, int numCities, Set<String> visited ) {
        if(curOrder.size() == numCities) {
            Integer returnDist = cur.neis.get(startCity.name);
            if(returnDist != null && returnDist + dist < minDist[0]) {
                shortestWay = new LinkedList<>(curOrder);
                minDist[0] = dist + returnDist;
            }
            return;
        }
        for(Map.Entry<City, Integer> entry: cur.neis.entrySet()) {
            City nextCity = entry.getKey();
            if(visited.contains(nextCity.name)) {
                continue;
            }
            Integer distToNextCity = entry.getValue();
            curOrder.add(nextCity);
            visited.add(nextCity.name);
            shortestWayToVisitAllCity(shortestWay, curOrder, startCity, nextCity, dist + distToNextCity, minDist, numCities, visited);
            curOrder.remove(curOrder.size() - 1);
            visited.remove(nextCity.name);
        }
    }
}

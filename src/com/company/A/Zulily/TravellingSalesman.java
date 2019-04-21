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
        shortestWayToVisitAllCity(shortestWay, new LinkedList<>(), new HashSet<>(), new int[]{0}, 0, startCity, numCities);
        return shortestWay;
    }

    private void shortestWayToVisitAllCity(List<City> shortestWay, List<City> curOrder, Set<String> visited, int[] minDist, int dist, City cur, int numCities) {
        if (curOrder.size() == numCities && dist < minDist[0]) {
            shortestWay = new LinkedList<>(curOrder);
            minDist[0] = dist;
            return;
        }
        if (curOrder.size() == numCities) {
            return;
        }
        for (Map.Entry<City, Integer> entry : cur.neis.entrySet()) {
            City nextCity = entry.getKey();
            if (visited.contains(nextCity.name)) {
                continue;
            }
            Integer distToNextCity = entry.getValue();
            curOrder.add(nextCity);
            visited.add(nextCity.name);
            shortestWayToVisitAllCity(shortestWay, curOrder, visited, minDist, dist + distToNextCity, nextCity, numCities);
            curOrder.remove(curOrder.size() - 1);
            visited.remove(nextCity.name);
        }
    }
}

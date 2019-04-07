package com.company.A.S190222;

import java.util.*;

enum Status {
    INIT, PROC, DONE
}

class Vertex {
    int label;
    List<Vertex> nei;
    Status status;

    public Vertex(int label) {
        this.label = label;
        this.status = Status.INIT;
        this.nei = new LinkedList<>();
    }
}

class Course {
    int label;
    Status status;
    List<Course> neis;
    Course(int label) {
        this.label = label;
        this.status = Status.INIT;
        this.neis = new LinkedList<>();
    }
}

public class CourseSchedule {
    private boolean isCyclic(Vertex v, Vertex[] graph) {
        if (v.status == Status.PROC) return true;
        if (v.status == Status.DONE) return false;
        v.status = Status.PROC;
        for (Vertex n : v.nei) {
            if (isCyclic(n, graph)) {
                return true;
            }
        }
        v.status = Status.DONE;
        return false;
    }

    public boolean canFinish(int numCourses, int[][] pre) {
        if (numCourses == 0 || pre.length == 0) {
            return true;
        }
        // build graph
        Vertex[] graph = new Vertex[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new Vertex(i);
        }
        for (int[] p : pre) {
            graph[p[1]].nei.add(graph[p[0]]);
        }
        // find cycle
        for (int i = 0; i < numCourses; i++) {
            if (isCyclic(graph[i], graph)) {
                return false;
            }
        }
        return true;
    }

    private boolean isCyclic2(int[] topo, Course c, int[] index){
        if(c.status == Status.PROC) return true;
        if(c.status == Status.DONE) return false;
        c.status = Status.PROC;
        for(Course nei : c.neis) {
            if(isCyclic2(topo, nei, index)) {
                return true;
            }
        }
        c.status = Status.DONE;
        topo[index[0]] = c.label;
        index[0] -= 1;
        return false;
    }
    public int[] findOrder(int n, int[][] p) {
        int[] topo = new int[n];
        if(p.length == 0) {
            for(int i = 0; i < n; i++){
                topo[i] = i;
            }
            return topo;
        }
        Course[] courses = new Course[n];
        for(int i = 0; i < n; i++) {
            courses[i] = new Course(i);
        }
        for(int i = 0; i < p.length; i++) {
            int prev = p[i][1];
            int next = p[i][0];
            courses[prev].neis.add(courses[next]);
        }

        int[] index = new int[]{n - 1};
        for(int i = 0; i < n; i++) {
            if(isCyclic2(topo, courses[i], index)) {
                return new int[]{};
            }
        }
        return topo;
    }

    public static void main(String[] args) {

    }
}

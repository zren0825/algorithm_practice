package com.company.A.S190428;

public class RoadPlanning {
    class V {
        String name;
        V parent;
        int size;

        V(String name) {
            this.name = name;
            this.parent = this;
            this.size = 1;
        }
    }

    private boolean find(V v1, V v2) {
        return root(v1) == root(v2);
    }

    private V root(V v) {
        V cur = v;
        while (cur.parent != cur) {
            // update every node when jump on
            cur.parent = cur.parent.parent;
            // jump two steps
            cur = cur.parent;
        }
        // update initial node
        v.parent = cur;
        return cur;
    }

    private void union(V v1, V v2) {
        V r1 = root(v1), r2 = root(v2);
        if (r1.size < r2.size) {
            r1.parent = r2;
            r2.size += r1.size;
        } else {
            r2.parent = r1;
            r1.size += r2.size;
        }
    }

    public void add(V a, V b) {
        union(a, b);
    }

    public boolean isConnected(V a, V b) {
        return find(a, b);
    }
}

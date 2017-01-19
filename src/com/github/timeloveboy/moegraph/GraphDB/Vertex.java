package com.github.timeloveboy.moegraph.GraphDB;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by timeloveboy on 17-1-19.
 */
public class Vertex {
    public Vertex(int vid) {
        this.vid = vid;
        like = new HashSet<Vertex>();
        fans = new HashSet<Vertex>();
    }

    public int getVid() {
        return vid;
    }

    public Set<Vertex> getFans() {
        return fans;
    }

    public Set<Vertex> getLike() {
        return like;
    }

    int vid;
    Set<Vertex> like;
    Set<Vertex> fans;
}

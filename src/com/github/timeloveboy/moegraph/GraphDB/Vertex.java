package com.github.timeloveboy.moegraph.GraphDB;

import java.util.List;

/**
 * Created by timeloveboy on 17-1-19.
 */
public class Vertex {
    public Vertex(int vid) {
        this.vid = vid;
    }

    public int getVid() {
        return vid;
    }

    public List<Vertex> getFans() {
        return fans;
    }

    public List<Vertex> getLike() {
        return like;
    }

    int vid;
    List<Vertex> like;
    List<Vertex> fans;
}

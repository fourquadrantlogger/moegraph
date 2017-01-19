package com.github.timeloveboy.moegraph.GraphDB;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by timeloveboy on 17-1-19.
 */
public class VLIst {
    List<Vertex> vertexList;

    public VLIst(List<Vertex> vertexList) {
        this.vertexList = vertexList;
    }

    public VLIst() {
        this.vertexList = new CopyOnWriteArrayList<Vertex>();
    }
}

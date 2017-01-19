package com.github.timeloveboy.moegraph.GraphDB;

import java.util.Set;

/**
 * Created by timeloveboy on 17-1-19.
 */
public class Graph {
    public VLIst getVertexLIst() {
        return vertexLIst;
    }

    VLIst vertexLIst;

    public void NewVertex(int vertex) {
        Vertex v = new Vertex(vertex);
        vertexLIst.vertexList.add(vertex, v);
    }

    public void Like(int fans, int beliked) {
        Vertex v_fans = vertexLIst.vertexList.get(fans);
        Vertex v_beliked = vertexLIst.vertexList.get(beliked);

        v_fans.getLike().add(v_beliked);
        v_beliked.getFans().add(v_fans);
    }

    public Set<Vertex> GetLike(int vertex) {
        return vertexLIst.vertexList.get(vertex).getLike();
    }

    public Set<Vertex> GetFans(int vertex) {
        return vertexLIst.vertexList.get(vertex).getFans();
    }

    public void DisLike(int fans, int beliked) {
        Vertex v_fans = vertexLIst.vertexList.get(fans);
        Vertex v_beliked = vertexLIst.vertexList.get(beliked);

        v_fans.getLike().remove(v_beliked);
        v_beliked.getFans().remove(v_fans);
    }
}

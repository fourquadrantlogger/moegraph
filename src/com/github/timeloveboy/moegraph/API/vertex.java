package com.github.timeloveboy.moegraph.API;

import com.github.timeloveboy.moegraph.GraphDB.Vertex;
import com.github.timeloveboy.moegraph.finalDB;
import com.github.timeloveboy.moeserver.DefaultHandle;
import com.github.timeloveboy.moeserver.IHttpRequest;
import com.github.timeloveboy.moeserver.IHttpResponse;
import utils.UrlUtil;

import java.io.IOException;

/**
 * Created by timeloveboy on 17-1-20.
 */
public class vertex extends DefaultHandle {
    @Override
    public void GET(IHttpRequest req, IHttpResponse resp) throws IOException {
        int vid = Integer.parseInt(UrlUtil.splitQuery(req.getUrl().toURL()).get("vid"));
        Vertex v = finalDB.DB.getVertexList().get(1);
        resp.write("get");
    }
}
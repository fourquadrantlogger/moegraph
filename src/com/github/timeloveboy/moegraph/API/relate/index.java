package com.github.timeloveboy.moegraph.API.relate;

import com.alibaba.fastjson.JSON;
import com.github.timeloveboy.moegraph.finalDB;
import com.github.timeloveboy.moeserver.DefaultHandle;
import com.github.timeloveboy.moeserver.IHttpRequest;
import com.github.timeloveboy.moeserver.IHttpResponse;
import utils.UrlUtil;

import java.io.IOException;
import java.util.Map;

/**
 * Created by timeloveboy on 17-1-20.
 */
public class index extends DefaultHandle {
    @Override
    public void GET(IHttpRequest req, IHttpResponse resp) throws IOException {
        Map<String, String> param = UrlUtil.splitQuery(req.getUrl().getQuery());
        if (param.containsKey("vid1") && param.containsKey("vid2")) {
            int vid1 = Integer.parseInt(param.get("vid1"));
            int vid2 = Integer.parseInt(param.get("vid2"));

            resp.write(JSON.toJSONString(finalDB.GetDB().GetRelate(vid1, vid2)));
        } else {
            resp.write("require query param " + "vid1 vid2");
        }
    }

    @Override
    public void POST(IHttpRequest req, IHttpResponse resp) throws IOException {
        Map<String, String> param = UrlUtil.splitQuery(req.getUrl().getQuery());
        if (param.containsKey("vid1") && param.containsKey("vid2") && param.containsKey("relate")) {
            int vid1 = Integer.parseInt(param.get("vid1"));
            int vid2 = Integer.parseInt(param.get("vid2"));
            int relate = Integer.parseInt(param.get("relate"));
            finalDB.GetDB().SetRelate(vid1, vid2, relate);
            resp.write("ok");
        } else {
            resp.write("require query param " + "vid1 vid2");
        }
    }

    @Override
    public void DELETE(IHttpRequest req, IHttpResponse resp) throws IOException {
        Map<String, String> param = UrlUtil.splitQuery(req.getUrl().getQuery());
        if (param.containsKey("vid1") && param.containsKey("vid2") && param.containsKey("relate")) {
            int vid1 = Integer.parseInt(param.get("vid1"));
            int vid2 = Integer.parseInt(param.get("vid2"));

            finalDB.GetDB().DisMakeFriend(vid1, vid2);
            resp.write("ok");
        } else {
            resp.write("require query param " + "vid1 vid2");
        }
    }
}
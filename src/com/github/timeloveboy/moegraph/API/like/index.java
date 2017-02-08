package com.github.timeloveboy.moegraph.API.like;

import com.alibaba.fastjson.JSON;
import com.github.timeloveboy.moegraph.finalDB;
import com.github.timeloveboy.moeserver.DefaultHandle;
import com.github.timeloveboy.moeserver.IHttpRequest;
import com.github.timeloveboy.moeserver.IHttpResponse;

import java.io.IOException;
import java.util.Map;

/**
 * Created by timeloveboy on 17-1-20.
 */
public class index extends DefaultHandle {
    @Override
    public void GET(IHttpRequest req, IHttpResponse resp) throws IOException {
        Map<String, String> param = utils.UrlUtil.splitQuery(req.getUrl().getQuery());
        if (param.containsKey("vid")) {
            int vid = Integer.parseInt(param.get("vid"));
            resp.write(JSON.toJSONString(finalDB.GetDB().getUser(vid).getLike()));
        } else {
            resp.write("require query param " + "vid");
        }
    }

    @Override
    public void POST(IHttpRequest req, IHttpResponse resp) throws Exception {
        Map<String, String> param = utils.UrlUtil.splitQuery(req.getUrl().getQuery());
        if (param.containsKey("vid") && param.containsKey("beliked")) {
            int vid = Integer.parseInt(param.get("vid"));
            int beliked = Integer.parseInt(param.get("beliked"));
            finalDB.GetDB().Like(vid, beliked);
            resp.write("ok");
        } else {
            resp.write("require query param " + "vid beliked");
        }
    }

    @Override
    public void DELETE(IHttpRequest req, IHttpResponse resp) throws Exception {
        Map<String, String> param = utils.UrlUtil.splitQuery(req.getUrl().getQuery());
        if (param.containsKey("vid") && param.containsKey("beliked")) {
            int vid = Integer.parseInt(param.get("vid"));
            int beliked = Integer.parseInt(param.get("beliked"));
            finalDB.GetDB().DisLike(vid, beliked);
            resp.write("ok");
        } else {
            resp.write("require query param " + "vid beliked");
        }
    }
}
package com.github.timeloveboy.moegraph.API.filter;

import com.alibaba.fastjson.JSONObject;
import com.github.timeloveboy.moeserver.DefaultHandle;
import com.github.timeloveboy.moeserver.IHttpRequest;
import com.github.timeloveboy.moeserver.IHttpResponse;
import utils.StreamUtil;

import java.util.List;
import java.util.Map;

/**
 * Created by paidian on 17-2-8.
 */
public class n extends DefaultHandle {

    public void OPTIONS(IHttpRequest req, IHttpResponse resp) throws Exception {
        Map<String, String> param = utils.UrlUtil.splitQuery(req.getUrl().getQuery());
        String type = "";
        int min, max;
        if (param.containsKey("type")) {
            type = param.get("type");
        } else {
            resp.write("require query param " + "type");
        }
        if (param.containsKey("min")) {
            min = Integer.parseInt(param.get("min"));
        }
        if (param.containsKey("max")) {
            max = Integer.parseInt(param.get("max"));
        }
        List<Integer> userids = (List<Integer>) JSONObject.parseArray(StreamUtil.getBody(req.getBody()), Integer.class);
        switch (type) {
            case "fanscount":

                break;
            case "likescount":
                break;
        }
    }
}

package com.github.timeloveboy.moegraph.API.fans;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.timeloveboy.moegraph.finalDB;
import com.github.timeloveboy.moeserver.DefaultHandle;
import com.github.timeloveboy.moeserver.IHttpRequest;
import com.github.timeloveboy.moeserver.IHttpResponse;
import utils.StreamUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

/**
 * Created by paidian on 17-2-8.
 */
public class n extends DefaultHandle {

    @Override
    public void POST(IHttpRequest req, IHttpResponse resp) throws Exception {

        Map<String, String> param = utils.UrlUtil.splitQuery(req.getUrl().getQuery());
        String type = "";
        if (param.containsKey("type")) {
            type = param.get("type");
        } else {
            resp.write("require query param " + "type");
        }

        int ok_count = 0;
        switch (type) {
            case "json":

                JSONArray O = (JSONArray) JSONObject.parse(StreamUtil.getBody(req.getBody()));
                for (Object o : O) {
                    JSONObject User_Like = (JSONObject) o;
                    int vid1 = Integer.parseInt(User_Like.get("vid1").toString());
                    int vid2 = Integer.parseInt(User_Like.get("vid2").toString());

                    finalDB.GetDB().Like(vid2, vid1);
                    ok_count++;
                }
                break;
            case "row":
                BufferedReader reader = new BufferedReader(new InputStreamReader(req.getBody()));
                StringBuilder sb = new StringBuilder();
                String line = "";
                try {
                    while ((line = reader.readLine()) != null) {
                        String[] user_fans = line.split(",");
                        if (user_fans.length == 2) {
                            int vid1 = Integer.parseInt(user_fans[0]);
                            int vid2 = Integer.parseInt(user_fans[1]);
                            finalDB.GetDB().Like(vid2, vid1);
                            ok_count++;
                        }
                    }
                } catch (IOException e) {
                    e.getMessage();
                }

                break;
        }
        resp.write("import count" + ok_count);
    }
}

package com.github.timeloveboy.moegraph.API.count;

import com.github.timeloveboy.moegraph.finalDB;
import com.github.timeloveboy.moeserver.DefaultHandle;
import com.github.timeloveboy.moeserver.IHttpRequest;
import com.github.timeloveboy.moeserver.IHttpResponse;

import java.io.IOException;

/**
 * Created by paidian on 17-2-15.
 */
public class user extends DefaultHandle {
    @Override
    public void GET(IHttpRequest req, IHttpResponse resp) throws IOException {
        resp.write(finalDB.GetDB().GetUserCount().toString());
    }
}

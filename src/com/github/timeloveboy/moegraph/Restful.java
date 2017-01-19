package com.github.timeloveboy.moegraph;

import com.github.timeloveboy.moeserver.Server;

/**
 * Created by timeloveboy on 17-1-19.
 */
public class Restful {
    Server s = Server.getInstance();

    s.RegisterDriver(new

    nettyServer()

    .

    setBufMax(1024*10)

    );

    //s.RegisterDriver(new sunServer());
    //s.RegisterDriver(new jettyServer());
    s.RegisterModulePath("webdemo.routers").

    SetPort(8090);

    s.Run();
}

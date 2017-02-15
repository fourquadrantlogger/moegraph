import com.github.timeloveboy.moeserver.Server;
import com.github.timeloveboy.moeserver.ServerDriver.netty.nettyServer;

/**
 * Created by timeloveboy on 17-1-19.
 */
public class Restful {
    public static void main(String[] args) throws Exception {
        Server web = Server.getInstance();

        web.RegisterDriver(new nettyServer().setBufMax(1024 * 1024 * 100));

        //s.RegisterDriver(new sunServer());
        //s.RegisterDriver(new jettyServer());
        web.RegisterModulePath("com.github.timeloveboy.moegraph.API").SetPort(8010);
        web.Run();
    }
}

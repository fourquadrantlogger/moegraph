package com.github.timeloveboy.moegraph.Computing;

import com.github.timeloveboy.moegraph.GraphDB.User;
import com.github.timeloveboy.moegraph.finalDB;
import utils.Log;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by paidian on 17-2-15.
 */
public class computing {

    public static void reset() {
        Now_vid = 1;
        Start = false;
        Maxfans = 1000000;
        Mincount = 10;
        result = new LinkedBlockingQueue<>(100);
        Result = new ConcurrentHashMap();
        task = new LinkedBlockingQueue<>(100000);
    }

    public static void save(String resultfilename) {
        CreateFileUtil.createDir("output");
        try {
            FileWriter fw = new FileWriter("output/" + resultfilename, false);
            fw.write(Result.toString());
            Log.v("end duce ");
            reset();

        } catch (IOException E) {
            E.printStackTrace();
        }
    }
    public static boolean Start = false;
    public static Integer Now_vid = 1;
    public static int Maxfans = 1000000;
    public static int Mincount = 10;
    public static LinkedBlockingQueue<Map<Integer, Integer>> result = new LinkedBlockingQueue<>(100);
    public static Map<Integer, Integer> Result = new ConcurrentHashMap();
    public static LinkedBlockingQueue<Integer> task = new LinkedBlockingQueue<>(100000);


    public static void Mapper(Integer[] Ids, String resultfilename) {
        Log.v("start mapping");

        for (int i = 0; i < Runtime.getRuntime().availableProcessors(); i++) {
            new Thread(new Runnable() {
                public void run() {
                    while (true) {
                        try {
                            Integer uid = task.take();
                            User user = finalDB.GetDB().getUser(uid);
                            Map<Integer, Integer> id_count_max10 = new HashMap<Integer, Integer>();
                            if (user != null) {
                                Set<Integer> u_likes = user.getLike();
                                Set<Integer> u_likes_min1000000 = filter.filter_fanscount(u_likes, 1000000, 0);
                                Map<Integer, Integer> id_count = Common.GetThemCommonFans(u_likes_min1000000);
                                id_count_max10 = filter.filter_map(id_count, 1 << 32, 10);
                            }
                            result.put(id_count_max10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
            ).start();
        }

        Arrays.sort(Ids);
        Log.v("start jobber ", Ids.length);
        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < Ids.length; i++) {
                    try {
                        task.put(Ids[i]);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Log.v("end jobber ");
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                Log.v("start duce ");
                for (int d = 0; d < Ids.length; d++) {
                    try {
                        Map<Integer, Integer> c = result.take();
                        Now_vid++;
                        Iterator<Integer> f = c.keySet().iterator();
                        while (f.hasNext()) {
                            Integer k = f.next();
                            Integer v = c.get(k);

                            if (Result.containsKey(k)) {
                                Result.put(k, Result.get(k) + 1);
                            } else {
                                Result.put(k, 1);
                            }
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                save(resultfilename);

            }
        }).start();
    }
}

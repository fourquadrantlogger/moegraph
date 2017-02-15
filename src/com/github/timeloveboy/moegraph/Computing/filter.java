package com.github.timeloveboy.moegraph.Computing;

import com.github.timeloveboy.moegraph.GraphDB.User;
import com.github.timeloveboy.moegraph.finalDB;

import java.util.*;

/**
 * Created by paidian on 17-2-15.
 */
public class filter {
    public static Set<Integer> filter_fanscount(Set<Integer> ids, int Maxfans, int Minfans) {
        Set<Integer> result = new HashSet<>();
        Iterator<Integer> i = ids.iterator();
        while (i.hasNext()) {
            Integer uid = i.next();
            User u = finalDB.GetDB().getUser(uid);
            if (u.getFans().size() >= Minfans && u.getFans().size() <= Maxfans) {
                result.add(uid);
            }
        }
        return result;
    }

    public static Map<Integer, Integer> filter_map(Map<Integer, Integer> src, Integer max, Integer min) {
        Map<Integer, Integer> out = new HashMap<>();
        for (Integer key : src.keySet()) {
            Integer value = src.get(key);
            if (value > min && value < max) {
                out.put(key, value)
            }
        }

        return src;
    }
}

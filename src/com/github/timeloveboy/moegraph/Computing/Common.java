package com.github.timeloveboy.moegraph.Computing;


import com.github.timeloveboy.moegraph.GraphDB.User;
import com.github.timeloveboy.moegraph.finalDB;

import java.util.*;

/**
 * Created by paidian on 17-2-15.
 */
public class Common {
    public static Map<Integer, Integer> GetThemCommonFans(Set<Integer> ids) {
        Date start = new Date();
        Map<Integer, Integer> likesmap = new HashMap<>();
        Integer i = 0;
        Iterator<Integer> it = ids.iterator();
        while (it.hasNext()) {
            User user = finalDB.GetDB().getUser(it.next());
            Iterator<Integer> f = user.getFans().iterator();
            while (f.hasNext()) {
                Integer fans = f.next();
                if (likesmap.containsKey(fans)) {
                    likesmap.put(fans, likesmap.get(fans));
                } else {
                    likesmap.put(fans, 1);
                }

            }
        }
        return likesmap;
    }
}

package com.github.timeloveboy.moegraph;

import com.github.timeloveboy.moegraph.GraphDB.RelateGraph;

/**
 * Created by timeloveboy on 17-1-20.
 */
public class finalDB {
    static RelateGraph db;

    public static RelateGraph GetDB() {
        if (db == null) {
            db = new RelateGraph(15000 * 10000);
        }
        return db;
    }
}

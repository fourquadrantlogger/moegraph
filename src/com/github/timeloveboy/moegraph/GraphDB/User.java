package com.github.timeloveboy.moegraph.GraphDB;

import org.eclipse.jetty.util.ConcurrentHashSet;

import java.util.Set;

/**
 * Created by timeloveboy on 17-1-19.
 */
public class User {
    public User(int vid) {
        this.vid = vid;
        like = new ConcurrentHashSet<>();
        fans = new ConcurrentHashSet<>();
    }

    public int getVid() {
        return vid;
    }

    public Set<Integer> getFans() {
        return fans;
    }

    public Set<Integer> getLike() {
        return like;
    }

    int vid;
    Set<Integer> like;
    Set<Integer> fans;

    @Override
    public String toString() {
        return "{ \"Uid\":" + vid + ",\"FansCount\":" + fans.size() + ",\"LikesCount\":" + like.size() + "}";
    }
}

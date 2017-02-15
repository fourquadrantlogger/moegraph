package com.github.timeloveboy.moegraph.GraphDB;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by timeloveboy on 17-1-19.
 */
public class RelateGraph {
    public Map<Integer, User> getUsers() {
        return users;
    }

    public void setUsers(Map<Integer, User> users) {
        this.users = users;
    }

    public RelateGraph() {
        this.users = new ConcurrentHashMap<>();
    }

    public RelateGraph(int cap) {
        this.users = new ConcurrentHashMap<>(cap);
    }

    Map<Integer, User> users;

    public User getUser(int vid) {
        return users.get(vid);
    }

    public User getorCreateUser(int vid) {
        if (users.containsKey(vid)) {
            return users.get(vid);
        } else {
            users.put(vid, new User(vid));
            return users.get(vid);
        }
    }

    // region 统计
    public int GetUserRelateCount() {
        int relatecount = 0;

        for (User value : users.values()) {
            relatecount += value.getFans().size();
        }
        return relatecount;
    }

    public Integer GetUserCount() {
        return users.size();
    }

    public Map<Integer, Integer> GetLikeCountCount() {
        Map<Integer, Integer> likesmap = new HashMap<>();
        for (User value : users.values()) {
            Integer k, v;
            k = value.getFans().size();
            v = likesmap.getOrDefault(value.getLike().size(), new Integer(0));
            likesmap.put(k, v);
        }
        return likesmap;
    }

    public Map<Integer, Integer> GetFansCountCount() {
        Map<Integer, Integer> likesmap = new HashMap<>();
        for (User value : users.values()) {
            Integer k, v;
            k = value.getFans().size();
            v = likesmap.getOrDefault(value.getFans().size(), new Integer(0));
            likesmap.put(k, v);
        }
        return likesmap;
    }
    //endregion

    //region 关系
    public void Like(int vid, int beliked) {
        getorCreateUser(vid).getLike().add(beliked);
        getorCreateUser(beliked).getFans().add(vid);
    }

    public void DisLike(int vid, int beliked) {
        getorCreateUser(vid).getLike().remove(beliked);
        getorCreateUser(beliked).getFans().remove(vid);
    }

    public void MakeFriend(int vid, int beliked) {
        getorCreateUser(vid).getLike().remove(beliked);
        getorCreateUser(beliked).getFans().remove(vid);
        getorCreateUser(vid).getFans().remove(beliked);
        getorCreateUser(beliked).getLike().remove(vid);
    }

    public void DisMakeFriend(int vid, int beliked) {
        getorCreateUser(vid).getLike().remove(beliked);
        getorCreateUser(beliked).getFans().remove(vid);
        getorCreateUser(vid).getFans().remove(beliked);
        getorCreateUser(beliked).getLike().remove(vid);
    }

    public int GetRelate(int vid1, int vid2) {
        int relate = 0;
        if (users.containsKey(vid1) && users.containsKey(vid2)) {
            if (getUser(vid1).getLike().contains(vid2)) {
                relate += 1;
            }
            if (getUser(vid1).getFans().contains(vid2)) {
                relate += 2;
            }
            return relate;
        } else {
            return -1;
        }
    }

    public void SetRelate(int vid1, int vid2, int relate) {
        switch (relate) {
            case 0:
                DisMakeFriend(vid1, vid2);
                break;
            case 1:
                Like(vid1, vid2);
                DisLike(vid2, vid1);
                break;
            case 2:
                Like(vid2, vid1);
                DisLike(vid1, vid2);
                break;
            case 3:
                MakeFriend(vid1, vid2);
        }
    }
    //endregion
}

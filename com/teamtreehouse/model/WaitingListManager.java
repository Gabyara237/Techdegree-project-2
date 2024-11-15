package com.teamtreehouse.model;

import java.util.TreeSet;

public class WaitingListManager {
    private TreeSet<Player> mWaitList;

    public WaitingListManager(TreeSet<Player> waitList) {
        mWaitList = waitList;
    }

    public TreeSet<Player> getWaitList() {
        return mWaitList;
    }
}

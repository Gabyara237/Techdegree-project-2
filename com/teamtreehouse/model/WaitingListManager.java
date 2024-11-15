package com.teamtreehouse.model;

import java.util.List;


public class WaitingListManager {
    private final List<Player> mWaitList;

    // Constructor
    public WaitingListManager(List<Player> waitList) {
        mWaitList = waitList;
    }

    public List<Player> getWaitList() {
        return mWaitList;
    }
}

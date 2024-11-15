package com.teamtreehouse.model;

import java.util.List;


public class WaitingListManager {
    private List<Player> mWaitList;

    public WaitingListManager(List<Player> waitList) {
        mWaitList = waitList;
    }

    public List<Player> getWaitList() {
        return mWaitList;
    }
}

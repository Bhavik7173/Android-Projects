package com.gi.waitinglist;

import android.provider.BaseColumns;

public class StudentModel {

    public int id;
    public String name;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static final class WaitListEntry implements BaseColumns {
        public static final String TABLE_NAME = "waitList";
        public static final String GUEST_NAME = "guestName";
        public static final String PARTY_SIZE = "partySize";
        public static final String TIME_STAMP = "timeStamp";
    }
}


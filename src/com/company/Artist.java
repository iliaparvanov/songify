package com.company;

public class Artist {
    public final int id;
    public final String name;

    public Artist(int id, String name) {
        this.id = id;
        this.name = name;
    }

    DbConnection connection = DbConnectionFactory.getDbConnection();
}

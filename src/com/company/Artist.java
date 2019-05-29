package com.company;

public class Artist {
    public final int id;
    public String name;

    public Artist(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    DbConnection connection = DbConnectionFactory.getDbConnection();
}

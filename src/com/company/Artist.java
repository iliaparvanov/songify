package com.company;

public class Artist {
    public final String name;

    public Artist(String name) {
        this.name = name;
    }

    DbConnection connection = DbConnectionFactory.getDbConnection();
}

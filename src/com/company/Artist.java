package com.company;

public class Artist {
    public final int id;
    public String name;

    public Artist(int id, String name) {
        this.id = id;
        this.name = name;
    }

    private final DbConnection connection = DbConnectionFactory.getDbConnection();

    @Override
    public String toString() {
        return name;
    }
}

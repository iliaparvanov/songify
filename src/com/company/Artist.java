package com.company;

import javafx.beans.property.SimpleStringProperty;

public class Artist {
    public final int id;
    private SimpleStringProperty name;

    public Artist(int id, String name) {
        this.id = id;
        this.name = new SimpleStringProperty(name);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }

    private final DbConnection connection = DbConnectionFactory.getDbConnection();

    @Override
    public String toString() {
        return name.get();
    }
}

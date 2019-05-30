package com.company;

import javafx.beans.property.SimpleStringProperty;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Genre {
    public int id;

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }

    public SimpleStringProperty name;

    public Genre(String name) {
        this.name = new SimpleStringProperty(name);
    }

    public Genre(int id, String name) {
        this.id = id;
        this.name = new SimpleStringProperty(name);
    }

    @Override
    public String toString() {
        return "" + name.get();
    }
}

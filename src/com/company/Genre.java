package com.company;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Genre {
    public int id;

    public final String name;

    public Genre(String name) {
        this.name = name;
    }
}

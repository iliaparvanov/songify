package com.company;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Genre {
    public final String name;

    static DbConnection connection = DbConnectionFactory.getDbConnection();

    public Genre(String name) {
        this.name = name;
    }
}

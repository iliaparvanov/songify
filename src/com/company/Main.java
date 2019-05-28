package com.company;

import com.company.db_builder.TableInitializer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        TableInitializer.createSongTable();
    }
}

package com.company.db_builder;

import com.company.DbConnection;
import com.company.DbConnectionFactory;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class TableInitializer {

    final static DbConnection connection = DbConnectionFactory.getIliaDbConnection();

    public static void createSongTable() throws SQLException {
        connection.insertStatement(
                            "create table Song ( " +
                        "Id int not null auto_increment primary key," +
                        "title varchar(20)," +
                        "releaseDate date," +
                        "lenght varchar(4)," +
                        "albumId int not null);");
//                            "FOREIGN KEY (albumId) REFERENCES Album(Id));");


        System.out.println("Song table initilized");
    }

    public static void createArtistTable() throws SQLException {
        connection.insertStatement(
                "CREATE TABLE Artist(" +
                "Id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                "Name VARCHAR(150) NOT NULL);");

        System.out.println("Artist table initialised");
    }
}

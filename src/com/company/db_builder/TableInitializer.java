package com.company.db_builder;

import com.company.DbConnection;

import java.sql.SQLException;
import java.sql.Statement;

public class TableInitializer {

    final static DbConnection connection = new DbConnection("Songify", "root", "root");

    public static void createSongTable() throws SQLException {
        connection.insertStatment(
                            "create table Song ( " +
                        "Id int not null auto_increment primary key," +
                        "title varchar(20)," +
                        "releaseDate date," +
                        "length varchar(4)," +
                        "albumId int);");
//                            "FOREIGN KEY (albumId) REFERENCES Album(Id));");


        System.out.println("Song table initilized");
    }
    public static void createGenreTable() throws SQLException {
        connection.insertStatment(
                "create table Genre ( " +
                        "Id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                        "name VARCHAR(150) NOT NULL);");
//
        System.out.println("Genre table initilized");
    }

    public static void createAlbumTable() throws SQLException {
        connection.insertStatment(
                "create table Album ( " +
                        "Id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                        "Title VARCHAR(150) NOT NULL," +
                        "ArtistID INTEGER NOT NULL);");
//                            "FOREIGN KEY (ArtistID) REFERENCES Artist(Id));");


        System.out.println("Album table initilized");
    }
}

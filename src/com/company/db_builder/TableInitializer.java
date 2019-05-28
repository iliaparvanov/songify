package com.company.db_builder;

import com.company.DbConnection;
import com.company.DbConnectionFactory;

import java.sql.SQLException;
import java.sql.Statement;

public class TableInitializer {

    final static DbConnection connection = DbConnectionFactory.getDbConnection();

    public static void createAllTables() throws SQLException {
        TableInitializer.createGenreTable();
        TableInitializer.createSongTable();
        TableInitializer.createAlbumTable();
        TableInitializer.createArtistTable();
        TableInitializer.createArtistSongTable();
    }

    public static void createSongTable() throws SQLException {
        connection.getConn().createStatement().execute(
                            "create table Song ( " +
                        "Id int not null auto_increment primary key," +
                        "title varchar(20)," +
                        "releaseDate date," +
                        "length varchar(4)," +
                        "albumId int,"+
                        "artistId int);");
//                            "FOREIGN KEY (albumId) REFERENCES Album(Id));");


        System.out.println("Song table initilized");
    }
    public static void createGenreTable() throws SQLException {
        connection.getConn().createStatement().execute(
                "create table Genre ( " +
                        "Id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                        "name VARCHAR(150) NOT NULL);");
//
        System.out.println("Genre table initilized");
    }

    public static void createAlbumTable() throws SQLException {
        connection.getConn().createStatement().execute(
                "create table Album ( " +
                        "Id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                        "Title VARCHAR(150) NOT NULL," +
                        "ArtistID INTEGER NOT NULL);");
//                            "FOREIGN KEY (ArtistID) REFERENCES Artist(Id));");


        System.out.println("Album table initilized");
    }

    public static void createArtistTable() throws SQLException {
        connection.getConn().createStatement().execute(
                "CREATE TABLE Artist(" +
                        "Id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                        "Name VARCHAR(150) NOT NULL);");

        System.out.println("Artist table initialised");
    }

    public static void createArtistSongTable() throws SQLException {
        connection.getConn().createStatement().execute(
                "CREATE TABLE ArtistSong(" +
                        "ArtistId INTEGER NOT NULL," +
                        "SongId VARCHAR(13) NOT NULL," +
                        "PRIMARY KEY (ArtistId, SongId)," +
                        "FOREIGN KEY (ArtistId) REFERENCES Artist(Id) ON DELETE CASCADE," +
                        "FOREIGN KEY (SongId) REFERENCES Song(Id) ON DELETE CASCADE);"
        );

        System.out.println("ArtistSong table initialised");
    }
}

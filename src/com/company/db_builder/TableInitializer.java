package com.company.db_builder;

import com.company.Album;
import com.company.Artist;
import com.company.DbConnection;
import com.company.DbConnectionFactory;
import com.company.controllers.AlbumsController;
import com.company.controllers.ArtistsController;
import com.company.controllers.GenresController;
import com.company.controllers.SongsController;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

public class TableInitializer {

    final static DbConnection connection = DbConnectionFactory.getDbConnection();

    public static void seedDb() throws SQLException {
        GenresController.create("Indie");
        GenresController.create("Pop");

        Artist eden = ArtistsController.create("EDEN");
        ArtistsController.create("Alec Benjamin");
        Artist billie = ArtistsController.create("Billie Eilish");

        Album vertigo = AlbumsController.create("vertigo", eden);
        Album billieAlbum = AlbumsController.create("WHEN WE ALL FALL ASLEEP WHERE DO WE GO", billie);

        SongsController.create("wrong", "2018-01-19", "1:04", vertigo, Arrays.asList(eden));
        SongsController.create("take care", "2018-01-19", "3:16", vertigo, Arrays.asList(eden));
        SongsController.create("bad guy", "2019-03-29", "3:14", billieAlbum, Arrays.asList(billie));
        SongsController.create("xanny", "2019-03-29", "4:04", billieAlbum, Arrays.asList(billie));
    }

    public static void createAllTables() throws SQLException {
        TableInitializer.createGenreTable();
        TableInitializer.createArtistTable();
        TableInitializer.createSongTable();
        TableInitializer.createAlbumTable();
        TableInitializer.createArtistSongTable();
    }

    public static void createSongTable() throws SQLException {
        connection.getConn().createStatement().execute(
                            "create table Song ( " +
                        "Id int not null auto_increment primary key," +
                        "title varchar(20)," +
                        "releaseDate date," +
                        "length varchar(4)," +
                        "albumId int," +
                            "FOREIGN KEY (albumId) REFERENCES Album(Id));");


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

    public static void createArtistTable() throws SQLException {
        connection.getConn().createStatement().execute(
                "CREATE TABLE Artist(" +
                        "Id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                        "Name VARCHAR(150) NOT NULL);");

        System.out.println("Artist table initialised");
    }

    public static void createAlbumTable() throws SQLException {
        connection.getConn().createStatement().execute(
                "create table Album ( " +
                        "Id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                        "Title VARCHAR(150) NOT NULL," +
                        "ArtistID INTEGER NOT NULL, " +
                            "FOREIGN KEY (ArtistID) REFERENCES Artist(Id) ON DELETE CASCADE);");


        System.out.println("Album table initilized");
    }

    public static void createArtistSongTable() throws SQLException {
        connection.getConn().createStatement().execute(
                "CREATE TABLE ArtistSong(" +
                        "ArtistId INTEGER NOT NULL," +
                        "SongId INTEGER NOT NULL," +
                        "PRIMARY KEY (ArtistId, SongId)," +
                        "FOREIGN KEY (ArtistId) REFERENCES Artist(Id) ON DELETE CASCADE," +
                        "FOREIGN KEY (SongId) REFERENCES Song(Id) ON DELETE CASCADE);"
        );

        System.out.println("ArtistSong table initialised");
    }
}

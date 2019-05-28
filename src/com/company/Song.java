package com.company;

import java.sql.SQLException;

public class Song {
    private final String title;
    private final String releaseDate;
    private final String lenght;
    private final String albumId;
    private final String genre;

    DbConnection connection = new DbConnection("Songify", "root", "root");

    public Song(String title, String releaseDate, String lenght, String albumId, String genre) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.lenght = lenght;
        this.albumId = albumId;
        this.genre = genre;
    }

    private void create() throws SQLException {
        connection.insertStatment(
                "INSERT INTO Song VALUES(" + title + "," + releaseDate + "," + lenght + "," + albumId + ");"
        );
    }
}

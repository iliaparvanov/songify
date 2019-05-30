package com.company;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.stream.Collectors;

public class Song {
    public int id;
    private SimpleStringProperty title;
    private SimpleStringProperty releaseDate;
    private SimpleStringProperty length;
    private  Album album;
    private ObservableList<Artist> artists;

    public Song(int id, String title, String releaseDate, String length, Album album, List<Artist> artists) throws SQLException {
        this.id = id;
        this.title = new SimpleStringProperty(title);
        this.releaseDate = new SimpleStringProperty(releaseDate);
        this.length = new SimpleStringProperty(length);
        this.album = album;
        this.artists = FXCollections.observableList(artists);
    }

    public Album getAlbum() {
        return album;
    }

    public String getTitle() {
        return title.get();
    }

    public String getReleaseDate() {
        return releaseDate.get();
    }

    public String getLength() {
        return length.get();
    }

    public List<Artist> getArtists() {
        return artists.stream().collect(Collectors.toList());
    }

    public void setTitle(String title) {
        this.title = new SimpleStringProperty(title);
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = new SimpleStringProperty(releaseDate);
    }

    public void setLength(String length) {
        this.length = new SimpleStringProperty(length);
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = FXCollections.observableList(artists);
    }


    @Override
    public String toString() {
        String aStrings = "";
        for (Artist a : artists) {
            aStrings = aStrings + artists.toString() + " ";
        }
        String albumString = "";
        if (album != null)
            albumString = album.toString();
        return  title.get() + ", by " + aStrings + " with length " + this.length.get() + " from album " + albumString;
    }
}

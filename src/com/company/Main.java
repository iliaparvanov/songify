package com.company;

import com.company.controllers.AlbumsController;
import com.company.controllers.ArtistsController;
import com.company.controllers.GenresController;
import com.company.controllers.SongsController;
import com.company.db_builder.TableInitializer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {
        TableInitializer.createGenreTable();
        TableInitializer.createSongTable();
        TableInitializer.createAlbumTable();
        TableInitializer.createArtistTable();



        System.out.println("Wellcome to Songify");

        Scanner scanner = new Scanner(System.in);
        String crud = scanner.next();
        String type = scanner.next();
        switch (crud){
            case "find":
                switch (type){
                    case "song":

                        SongsController.find(scanner.next());
                        break;

                    case "genre":

                        GenresController.find(scanner.next());
                        break;

                    case "artist":

                        ArtistsController.find(scanner.next());
                        break;

                    case "album":

                        AlbumsController.find(scanner.next());
                        break;

                }
                break;
            case "create":
                switch (type){
                    case "song":

                        Song song = new Song(scanner.next(),scanner.next(), scanner.next(), null);
                        SongsController.create(song);
                        break;

                    case "genre":
                        Genre genre = new Genre(scanner.next());
                        GenresController.create(genre);
                        break;

                    case "artist":
                        Artist artist = new Artist(scanner.next());
                        ArtistsController.create(artist);
                        break;

                    case "album":
                        Album album = new Album(scanner.next(), 1);
                        AlbumsController.create(album);
                        break;

                }

                break;

            case "update":
                System.out.println("");

            case "delete":

            case "index":
        }


    }
}

package com.company;

import com.company.controllers.AlbumsController;
import com.company.controllers.ArtistsController;
import com.company.controllers.GenresController;
import com.company.controllers.SongsController;
import com.company.db_builder.TableInitializer;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static <E> int options(List<E> options) {
        int i=0;
        for (E e : options) {
            i++;
            System.out.println(i+". " + e.toString());
        }
        Scanner sc = new Scanner(System.in);
        return Integer.valueOf(sc.next()) - 1;
    }

    public static <E> void print_index(List<E> elements) {
        for(E e : elements) {
            System.out.println(e.toString());
        }
    }

    public static void main(String[] args) throws SQLException {

        TableInitializer.createAllTables();
        TableInitializer.seedDb();

        System.out.println("Wellcome to Songify");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Choose function:\ncreate\nfind\nindex\nexit\n");
        while (scanner.hasNext()) {

            String crud = scanner.next();
            if(crud.equals("exit"))
                break;
            System.out.print("song\nalbum\ngenre\nartist\n");
            String type = scanner.next();
            String option;
            int index;

            switch (crud) {
                case "find":
                    System.out.print("Enter name: ");

                    switch (type) {
                        case "song":

                            List<Song> songs = SongsController.find(scanner.next());
                            if (songs.isEmpty()) {
                                System.out.println("No songs found...");
                                break;
                            }
                            System.out.println("Choose a song");

                            index = options(songs);

                            Song song = songs.get(index);

                            System.out.println("Choose an option:\ndelete\nupdate\nsave\nback\n");
                            while (scanner.hasNext()) {
                                option = scanner.next();

                                switch (option) {
                                    case "delete":
                                        SongsController.delete(song.id);
                                        break;
                                    case "update":
                                        while (scanner.hasNext()) {
                                            String parameter = scanner.next();
                                            if (parameter.equals("name:")) {
                                                song.setTitle(scanner.next());
                                            } else if (parameter.equals("releaseDate:")) {
                                                song.setReleaseDate(scanner.next());
                                            } else if (parameter.equals("length:")) {
                                                song.setLength(scanner.next());
                                            } else if (parameter.equals("album:")) {
                                                List<Album> albums = AlbumsController.find(scanner.next());
                                                int choice = options(albums);
                                                song.setAlbum(albums.get(choice));
                                            } else if (parameter.equals("artist:")) {
                                                List<Artist> artists = ArtistsController.find(scanner.next());
                                                int choice = options(artists);
                                                song.setArtists(Arrays.asList(artists.get(choice)));
                                            }

                                            if (parameter.contains(";")) {
                                                break;
                                            }
                                        }
                                        break;

                                    case "save":
                                        SongsController.update(song);
                                        break;
                                }
                                if (option.equals("back"))
                                    break;
                                System.out.println("Choose an option:\ndelete\nupdate\nsave\nback\n");
                            }
                            break;

                        case "genre":

                            Genre genre = GenresController.find(scanner.next());
                            if (genre == null) {
                                System.out.println("No such genre");
                                break;
                            }
                            System.out.println("Choose an option: \nupdate\ndelete\nsave\nback");
                            while (scanner.hasNext()) {
                                option = scanner.next();
                                switch (option) {
                                    case "delete":
                                        GenresController.delete(genre.id);
                                        break;
                                    case "update":
                                        if (scanner.next().equals("name:")) {
                                            genre.name = scanner.next();
                                        }
                                        break;

                                    case "save":
                                        GenresController.update(genre);
                                        break;
                                }
                                if (option.equals("back"))
                                    break;
                                System.out.println("Choose an option: \nupdate\ndelete\nsave\nback");
                            }
                            break;

                        case "artist":

                            List<Artist> artists = ArtistsController.find(scanner.next());
                            if (artists.isEmpty()) {
                                System.out.println("No such artist");
                                break;
                            }

                            int choice = options(artists);
                            Artist artist = artists.get(choice);
                            System.out.println("Choose an option: \nupdate\ndelete\nsave\nback");
                            while (scanner.hasNext()) {

                                option = scanner.next();
                                switch (option) {
                                    case "delete":
                                        ArtistsController.delete(artist.id);
                                        break;
                                    case "update":
                                        if (scanner.next().equals("name:")) {
                                            artist.setName(scanner.next());
                                        }
                                        break;
                                    case "save":
                                        ArtistsController.update(artist);
                                }
                                if (option.equals("back"))
                                    break;
                                System.out.println("Choose an option: \nupdate\ndelete\nsave\nback");
                            }
                            break;

                        case "album":
                            List<Album> albums = AlbumsController.find(scanner.next());
                            if(albums.isEmpty()) {
                                System.out.println("No such album");
                                break;
                            }
                            choice = options(albums);

                            Album album = albums.get(choice);

                            System.out.println("Choose an option: \nupdate\ndelete\nsave\nback");
                            while (scanner.hasNext()) {

                                option = scanner.next();
                                switch (option) {
                                    case "delete":
                                        AlbumsController.delete(album.id);
                                        break;
                                    case "update":
                                        while (scanner.hasNext()) {
                                            String parameter = scanner.next();
                                            if (parameter.equals("name:")) {
                                                album.title = scanner.next();
                                            } else if (parameter.equals("artist:")) {
                                                artists = ArtistsController.find(scanner.next());
                                                if (artists == null) {
                                                    System.out.println("No such artist");
                                                    break;
                                                }
                                                choice = options(artists);
                                                artist = artists.get(choice);
                                                album.artist = artist;
                                            } else if (parameter.contains(";")) {
                                                break;
                                            }
                                        }
                                    case "save":
                                        AlbumsController.update(album);
                                        break;
                                    case "back":
                                        break;
                                }
                                if (option.equals("back"))
                                    break;
                                System.out.println("Choose an option: \nupdate\ndelete\nsave\nback");
                            }
                            break;
                    }
                    break;
                case "create":
                    switch (type) {
                        case "song":
                            System.out.print("Name: ");
                            String name = scanner.next();

                            System.out.print("Release date: ");
                            String releaseDate = scanner.next();

                            System.out.print("Length: ");
                            String length = scanner.next();

                            System.out.print("Album: ");
                            List<Album> albums = AlbumsController.find(scanner.next());
                            if(albums.isEmpty()){
                                System.out.println("No such album");
                                break;
                            }

                            int choice = options(albums);

                            Album album = albums.get(choice);

                            System.out.print("Artist: ");
                            List<Artist> artists = ArtistsController.find(scanner.next());
                            if (artists == null) {
                                System.out.println("No such artist");
                                break;
                            }
                            choice = options(artists);
                            Artist artist = artists.get(choice);

                            SongsController.create(name, releaseDate, length, album, Arrays.asList(artist));
                            break;

                        case "genre":
                            System.out.print("Name: ");
                            name = scanner.next();

                            GenresController.create(name);
                            break;

                        case "artist":
                            System.out.print("Name: ");
                            name = scanner.next();

                            ArtistsController.create(name);
                            break;

                        case "album":
                            System.out.print("Name: ");
                            name = scanner.next();

                            System.out.print("Artist: ");
                            artists = ArtistsController.find(scanner.next());
                            if (artists == null) {
                                System.out.println("No such artist");
                                break;
                            }
                            choice = options(artists);
                            artist = artists.get(choice);

                            AlbumsController.create(name, artist);
                            break;
                    }
                    break;
                case "index":
                    System.out.println();
                    switch (type) {
                        case "song":
                            print_index(SongsController.index());
                            break;
                        case "album":
                            print_index(AlbumsController.index());
                            break;
                        case "artist":
                            print_index(ArtistsController.index());
                            break;
                        case "genre":
                            print_index(GenresController.index());
                            break;
                    }
                    System.out.println();
            }

            System.out.print("Choose function:\ncreate\nfind\nindex\nexit\n");
        }
    }
}
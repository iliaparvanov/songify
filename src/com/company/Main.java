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

        Scanner scanner = new Scanner(System.in);

        System.out.println("Initialize DB?\nYes\nNo");
        String command = scanner.next();

        if(command.equals("Yes") || command.equals("yes")) {
            TableInitializer.createAllTables();
            TableInitializer.seedDb();
        }

        System.out.println("Welcome to Songify");

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
                            boolean songWasFound = true;
                            List<Song> songs = SongsController.find(scanner.next());
                            while (songs.isEmpty()) {
                                System.out.println("No songs found...");
                                String input = scanner.next();
                                if (input.equals("back")) {
                                    songWasFound = false;
                                    break;
                                }
                                songs = SongsController.find(input);
                            }
                            if (!songWasFound) {
                                break;
                            }
                            System.out.println("Choose a song");

                            index = options(songs);

                            Song song = songs.get(index);

                            System.out.println("Choose an option:\ndelete\nupdate\nsave\nback\n");
                            while (scanner.hasNext()) {
                                option = scanner.next();

                                if (option.equals("delete")) {
                                        SongsController.delete(song.id);
                                        break; }
                                    if (option.equals("update")) {
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
                                }
                                    if (option.equals("save")) {
                                    SongsController.update(song);
                                    break;
                                }

                                if (option.equals("back")) {
                                    break;
                                }
                                System.out.println("Choose an option:\ndelete\nupdate\nsave\nback\n");
                            }
                            break;

                        case "genre":
                            boolean genreWasFound = true;
                            List<Genre> genres = GenresController.find(scanner.next());
                            while(genres.isEmpty()) {
                                System.out.println("No such genre");
                                String input = scanner.next();
                                if (input.equals("back")) {
                                    genreWasFound = false;
                                    break;
                                }
                                genres = GenresController.find(input);
                            }

                            if (!genreWasFound) {
                                break;
                            }

                            int choice = options(genres);
                            Genre genre = genres.get(choice);

                            System.out.println("Choose an option: \nupdate\ndelete\nsave\nback");
                            while (scanner.hasNext()) {
                                option = scanner.next();
                                if (option.equals("delete")) {
                                    GenresController.delete(genre.id);
                                    break;
                                }
                                if (option.equals("update")) {
                                    if (scanner.next().equals("name:")) {
                                        genre.setName(scanner.next());
                                    }
                                    break;
                                }
                            if (option.equals("save")) {
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
                            while (artists.isEmpty()) {
                                System.out.println("No such artist");
                                artists = ArtistsController.find(scanner.next());
                            }

                            choice = options(artists);
                            Artist artist = artists.get(choice);
                            System.out.println("Choose an option: \nupdate\ndelete\nsave\nback");
                            while (scanner.hasNext()) {

                                option = scanner.next();
                                if (option.equals("delete")) {
                                    ArtistsController.delete(artist.id);
                                    break;
                                }
                                    if (option.equals("update")) {
                                        if (scanner.next().equals("name:")) {
                                            artist.setName(scanner.next());
                                        }
                                        break;
                                        }
                                    if (option.equals("save")) {
                                        ArtistsController.update(artist);
                                        break;
                                }
                                if (option.equals("back"))
                                    break;
                                System.out.println("Choose an option: \nupdate\ndelete\nsave\nback");
                            }
                            break;

                        case "album":
                            boolean albumWasFound = true;
                            List<Album> albums = AlbumsController.find(scanner.next());
                            while(albums.isEmpty()) {
                                System.out.println("No such album");
                                String input = scanner.next();
                                if (input.equals("back")) {
                                    albumWasFound = false;
                                    break;
                                }
                                albums = AlbumsController.find(input);
                            }
                            if (!albumWasFound) {
                                break;
                            }
                            choice = options(albums);

                            Album album = albums.get(choice);

                            System.out.println("Choose an option: \nupdate\ndelete\nsave\nback");
                            while (scanner.hasNext()) {

                                option = scanner.next();
                                if(option.equals("delete")) {
                                    AlbumsController.delete(album.id);
                                    break;
                                }
                                    if(option.equals("update")) {
                                        while (scanner.hasNext()) {
                                            String parameter = scanner.next();
                                            if (parameter.equals("name:")) {
                                                album.setTitle(scanner.next());
                                            } else if (parameter.equals("artist:")) {
                                                artists = ArtistsController.find(scanner.next());
                                                if (artists == null) {
                                                    System.out.println("No such artist");
                                                    break;
                                                }
                                                choice = options(artists);
                                                artist = artists.get(choice);
                                                album.setArtist(artist);
                                            } else if (parameter.contains(";")) {
                                                break;
                                            }
                                        }
                                    }
                                    if (option.equals("save")) {
                                    AlbumsController.update(album);
                                    break;
                                }
                                    if (option.equals("back")) {

                                        break;
                                }
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
                            while (albums.isEmpty()){
                                System.out.println("No such album");
                                albums = AlbumsController.find(scanner.next());
                            }

                            int choice = options(albums);

                            Album album = albums.get(choice);

                            System.out.print("Artist: ");
                            List<Artist> artists = ArtistsController.find(scanner.next());
                            while (artists == null) {
                                System.out.println("No such artist");
                                artists = ArtistsController.find(scanner.next());
                            }
                            choice = options(artists);
                            Artist artist = artists.get(choice);


                            System.out.print("Type genre name: ");
                            List<Genre> genres = GenresController.find(scanner.next());
                            while(genres.isEmpty()) {
                                System.out.println("No such genre");
                                genres = GenresController.find(scanner.next());
                            }

                            choice = options(genres);

                            SongsController.create(name, releaseDate, length, album, Arrays.asList(artist), genres.get(choice));
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
                            while (artists == null) {
                                System.out.println("No such artist");
                                artists = ArtistsController.find(scanner.next());
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
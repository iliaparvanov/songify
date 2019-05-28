package com.company;

import com.company.controllers.AlbumsController;
import com.company.controllers.ArtistsController;
import com.company.controllers.GenresController;
import com.company.controllers.SongsController;
import com.company.db_builder.TableInitializer;
import com.mysql.cj.xdevapi.Table;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static <E> int options(List<E> options) {
        for (E e : options) {
            System.out.println(e.toString());
        }
        Scanner sc = new Scanner(System.in);
        return Integer.valueOf(sc.next());
    }

    public static void main(String[] args) throws SQLException {

        TableInitializer.createAllTables();

        System.out.println("Wellcome to Songify");

        Scanner scanner = new Scanner(System.in);
        String crud = scanner.next();
        String type = scanner.next();
        String option;
        int i=0;
        switch (crud){
            case "find":
                switch (type){
                    case "song":

                        List<Song> songs =  SongsController.find(scanner.next());
                        i=0;
                        for(Song s : songs){
                            i++;
                            System.out.println(i +". " + s.title + "- by " + s.artist.name);

                        }
                        System.out.println("Choose a song");

                        int index = scanner.nextInt();

                        Song song = songs.get(index-1);

                        System.out.println("Choose an option");
                        option = scanner.next();
                        switch (option){
                            case "delete":
                                SongsController.delete(song.id);
                                break;
                            case "update":
                                while(scanner.hasNext()){
                                    if(scanner.next().equals("name:")){
                                        song.title = scanner.next();
                                    }
                                    if(scanner.next().equals("releaseDate:")){
                                        song.releaseDate = scanner.next();
                                    }
                                    if(scanner.next().equals("length:")){
                                        song.length = scanner.next();
                                    }
                                    if(scanner.next().equals("album")) {
                                        List<Album> albums = AlbumsController.find(scanner.next());
                                        int choice = options(albums);
                                        song.album = albums.get(choice);
                                    }
                                }



                            case "back":
                                break;
                        }
                        break;

                    case "genre":

                        GenresController.find(scanner.next());

                        option = scanner.next();
                        switch (option){
                            case "delete":

                                break;
                            case "update":

                                break;

                            case "back":
                                break;
                        }
                        break;

                    case "artist":

                        ArtistsController.find(scanner.next());

                        option = scanner.next();
                        switch (option){
                            case "delete":

                                break;
                            case "update":

                                break;

                            case "back":
                                break;
                        }
                        break;

                    case "album":

                        AlbumsController.find(scanner.next());
                        option = scanner.next();
                        switch (option){
                            case "delete":

                                break;
                            case "update":

                                break;

                            case "back":
                                break;
                        }
                        break;

                }
                break;
            case "create":
                switch (type){
                    case "song":
                        SongsController.create(scanner.next(),scanner.next(), scanner.next(), null);
                        break;

                    case "genre":
                        GenresController.create(scanner.next());
                        break;

                    case "artist":
                        ArtistsController.create(scanner.next());
                        break;

                    case "album":
                        AlbumsController.create(scanner.next(), ArtistsController.find(1));
                        break;

                }

                break;



            case "index":
        }


    }
}

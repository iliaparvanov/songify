package com.company;

import com.company.controllers.AlbumsController;
import com.company.controllers.ArtistsController;
import com.company.controllers.GenresController;
import com.company.controllers.SongsController;
import com.company.db_builder.TableInitializer;
import com.mysql.cj.xdevapi.Table;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {

        TableInitializer.createAllTables();

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

            case "update":
                System.out.println("");

            case "delete":

            case "index":
        }


    }
}

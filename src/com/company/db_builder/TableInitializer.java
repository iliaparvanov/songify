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
                        "lenght varchar(4)," +
                        "albumId int not null);");
//                            "FOREIGN KEY (albumId) REFERENCES Album(Id));");


        System.out.println("Song table initilized");
    }
}

    първа българска армия 67
        читалище искра

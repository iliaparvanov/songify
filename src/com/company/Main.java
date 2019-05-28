package com.company;

import com.company.db_builder.TableInitializer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
//            TableInitializer.createGenreTable();
//            TableInitializer.createSongTable();
//            TableInitializer.createAlbumTable();

//        Song song = new Song("asf", "2008-10-10", "2:53", "1");
//        Song.update(4, "asdf", "2012-12-12", "3:00", "6");
        Song.delete(4);

    }
}

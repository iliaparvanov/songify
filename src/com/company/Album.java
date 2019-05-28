package com.company;

import com.company.DbConnection;
import com.mysql.cj.x.protobuf.MysqlxCrud;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Album {
    public int id;
    public final String title;
    public final int artistId;

    public Album(String title, int artistId) {
        this.title = title;
        this.artistId = artistId;
    }
}

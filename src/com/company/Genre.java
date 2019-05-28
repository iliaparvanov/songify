package com.company;

import java.sql.SQLException;

public class Genre {

    private final String name;
    DbConnection connection = new DbConnection("Songify", "xothas", "asdf");

    public Genre(String name) {
        this.name = name;
    }
    private void create(){
        try {
            connection.insertStatment("INSERT INTO Genre (name) VALUES("+ this.name +");");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void delete(){
        try {
            connection.insertStatment("DELETE FROM Genre WHERE name = "+ this.name +";");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void update(){

    }
    private void show(){
        try {
            connection.insertStatment("SELECT * FROM Genre Where name="+ this.name+ ";");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

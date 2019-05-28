package com.company;

import java.sql.*;


public class DbConnection {

    private static final String dbDriver = "com.mysql.cj.jdbc.Driver";
    private static final String connectionString = "jdbc:mysql://localhost:3306/";
    private Connection con;
    private String dbName;
    private String user;
    private String pass;

    public DbConnection(String dbName, String user, String pass) {
        this.dbName = dbName;
        this.user = user;
        this.pass = pass;
    }

    public void connect() throws SQLException, ClassNotFoundException {

        Class.forName(dbDriver);

        con = DriverManager.getConnection(connectionString + dbName, user, pass);

        System.out.println("Connected to " + dbName);
    }

    public ResultSet insertStatment(String sql) throws SQLException {
        Statement statement = con.createStatement();
        ResultSet result = statement.executeQuery(sql);
        return result;
    }
}

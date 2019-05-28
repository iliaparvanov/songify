package com.company;

import java.sql.*;
import java.util.Properties;


public class DbConnection {

    private static final String dbDriver = "com.mysql.cj.jdbc.Driver";
    private static final String connectionString = "jdbc:mysql://localhost:3306/";

    public Connection getCon() {
        return con;
    }

    private Connection con;
    private String dbName;
    private String user;
    private String pass;
    private Properties info;

    public DbConnection(String dbName, String user, String pass, Properties info) {
        this.dbName = dbName;
        this.user = user;
        this.pass = pass;
        this.info = info;
        try {
            connect();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void connect() throws SQLException, ClassNotFoundException {

        Class.forName(dbDriver);

        Properties properties = new java.util.Properties();
        properties.put("user", user);
        properties.put("password", pass);
        properties.putAll(info);
        con = DriverManager.getConnection(connectionString + dbName, properties);

        System.out.println("Connected to " + dbName);
    }

    public void insertStatement(String sql) throws SQLException {
        Statement statement = con.createStatement();
        statement.execute(sql);
    }

    public int update(PreparedStatement statement) throws SQLException {
        return statement.executeUpdate();
    }

    public ResultSet makeQuery(String query) throws SQLException {
        Statement statement = con.createStatement();
        return statement.executeQuery(query);
    }
}

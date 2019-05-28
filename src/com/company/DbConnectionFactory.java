package com.company;

import java.util.Properties;

public class DbConnectionFactory {
    //AKO NESHTO GURMI, PROMENI FUNCKIQTA, KOQTO VRUSHTA TAZI FUNKCIQ
    public static DbConnection getDbConnection() {
        return getStandardDbConnection();
    }

    private static DbConnection getStandardDbConnection() {
        return new DbConnection("Songify", "root", "root", new java.util.Properties());
    }

    private static DbConnection getIliaDbConnection() {
        Properties props = new java.util.Properties();
        props.put("useUnicode", "true");
        props.put("useJDBCCompliantTimezoneShift", "true");
        props.put("useLegacyDatetimeCode", "false");
        props.put("serverTimezone", "UTC");
        return new DbConnection("Songify", "songifyuser", "songifypassword", props);
    }
}

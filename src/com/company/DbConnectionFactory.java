package com.company;

import java.util.Properties;

public class DbConnectionFactory {
    public static DbConnection getDbConnection() {
        return new DbConnection("Songify", "root", "root", new java.util.Properties());
    }

    public static DbConnection getIliaDbConnection() {
        Properties props = new java.util.Properties();
        props.put("useUnicode", "true");
        props.put("useJDBCCompliantTimezoneShift", "true");
        props.put("useLegacyDatetimeCode", "false");
        props.put("serverTimezone", "UTC");
        return new DbConnection("Songify", "songifyuser", "songifypassword", props);
    }
}

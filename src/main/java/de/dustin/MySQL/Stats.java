package de.dustin.MySQL;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Stats {

    public static boolean playerExists(String UUID) {
        ResultSet rs = MySQL.query("SELECT * FROM Stats WHERE UUID= '" + UUID + "'");
        try {
            if (rs.next() && rs.getString("UUID") != null) {
                MySQL.closeResultset(rs);
                return true;
            }
        } catch (SQLException ignored) {}
            MySQL.closeResultset(rs);
            return false;

    }

    public static void createPlayer(String UUID) {
        MySQL.update("INSERT INTO Stats(UUID, KILLS, DEATHS) VALUES ('" + UUID + "', '0', '0');");
    }


    public static Integer getDeaths(String uuid) {
        ResultSet rs = MySQL.query("SELECT * FROM Stats WHERE UUID= '" + uuid + "'");
        try {
            if (rs.next()) {
                Integer i = Integer.valueOf(rs.getInt("DEATHS"));
                MySQL.closeResultset(rs);
                return i;
            }
        } catch (SQLException sQLException) {}
        MySQL.closeResultset(rs);
        return Integer.valueOf(0);
    }

    public static Integer getKills(String uuid) {
        ResultSet rs = MySQL.query("select * from Stats where UUID= '" + uuid + "'");
        try {
            if (rs.next()) {
                Integer i = Integer.valueOf(rs.getInt("KILLS"));
                MySQL.closeResultset(rs);
                return i;
            }
        } catch (SQLException sQLException) {}
        MySQL.closeResultset(rs);
        return Integer.valueOf(0);
    }
    public static void setKills(String uuid, Integer kills) {
        MySQL.update("UPDATE Stats SET KILLS= '" + kills + "' WHERE UUID= '" + uuid + "';");
    }

    public static void setDeaths(String uuid, Integer deaths) {
        MySQL.update("UPDATE Stats SET DEATHS= '" + deaths + "' WHERE UUID= '" + uuid + "';");
    }

    public static void addKills(String uuid, Integer kills) {
        setKills(uuid, Integer.valueOf(getKills(uuid).intValue() + kills.intValue()));
    }

    public static void addDeaths(String uuid, Integer deaths) {
        setDeaths(uuid, Integer.valueOf(getDeaths(uuid).intValue() + deaths.intValue()));
    }

}

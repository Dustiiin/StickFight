package de.dustin.MySQL;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.sql.*;
import java.util.concurrent.FutureTask;

public class MySQL {
    public static Connection con;

    public static String host = "localhost";
    public static String database = "Stickfight";
    public static int port = 3306;
    public static String user = "admin";
    public static String pass = "RedBull2020";


    public static void connect() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?autoReconnect=true", user, pass);
            System.out.println("[MYSQL] Konnte geladen werden");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("[MYSQL] Konnte nicht geladen werden!");
        }
    }
    public static void createStatsTable() {
        if (con != null)
            try {
                con.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS Stats (UUID varchar(64), KILLS int, DEATHS int)");
            }catch (SQLException e) {
                e.printStackTrace();
            }
    }


    public static void update(final String qry) {
        if (isConnected()) {
            (new FutureTask(new Runnable() {
                PreparedStatement ps;

                public void run() {
                    try {
                        this.ps = con.prepareStatement(qry);
                        this.ps.executeUpdate();
                        this.ps.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }, String.valueOf(1))).run();
        } else {
            connect();
        }
    }

    public static void close () {
        if (con != null)
            try {
                con.close();
                Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "[MYSQL] Verbindung wurde geschlossen!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    public static ResultSet query(String qry) {
        ResultSet rs = null;
        try {
            PreparedStatement st = con.prepareStatement(qry);
            rs = st.executeQuery(qry);
        } catch (SQLException e) {
            connect();
            System.err.println(e);
        }
        return rs;
    }

    public static void closeResultset(ResultSet rs) {
        if (rs != null)
            try {
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    public static void closeStatement(PreparedStatement st) {
        if (st != null)
            try {
                st.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }


    public static boolean isConnected () {
        return (con != null);
    }
}
package com.Classes;

import java.io.File;
import java.sql.*;

public class DatabaseConnection {
    private final String fileName;
    private Connection conn;
    private PreparedStatement insertSQL;
    private PreparedStatement selectSQL;

    public DatabaseConnection() {
        fileName = "cvdb.db";
        File file = new File(fileName);
        boolean firstRun = !file.exists();
        conn = null;

        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:" + fileName);

            if (firstRun) {
                Statement stat = conn.createStatement();
                stat.executeUpdate("create table cvs(" +
                        "id integer primary key ," +
                        "firstName text," +
                        "lastName text," +
                        "title text," +
                        "email text," +
                        "phone text," +
                        "city text," +
                        "country text);");
            }

            // tek tek almak mantıklı mı? nasıl bir iyileşme yapılabilir konuşulacak
            // career objective hata veriyor şimdilik onsuz test ediyorum

            insertSQL = conn.prepareStatement("insert into cvs (firstName, " +
                    "lastName, title, email, " +
                    "phone, city, country) values (?, ?, ?, ?, ?, ?, ?)");

            selectSQL = conn.prepareStatement("select * from cvs " +
                    "order by title;");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
    }

    public void addCV(String firstName , String lastName, String title ,
                      String email , String phone,
                      String city , String country ) {
        try {
            insertSQL.setString(1, firstName);
            insertSQL.setString(2, lastName);
            insertSQL.setString(3, title);
            insertSQL.setString(4, email);
            insertSQL.setString(5, phone);
            insertSQL.setString(6, city);
            insertSQL.setString(7, country);
            insertSQL.execute();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

//    public void listContacts() {
//        try {
//            ResultSet rs = selectSQL.executeQuery();
//            while (rs.next()) {
//                System.out.println(rs.getString("name") + ", "
//                        + rs.getString("email"));
//            }
//        } catch (SQLException e) {
//            System.out.println(e);
//        }
//    }

}



/*
    genel durumlar:
    dynamic olması uygulamanın büyük değişiklikler gerektiyior birlikte düşünmek lazım
    scene geçişlerinin tamamlanması lazım ilerleyebilmem için
    zorunlu bilgi girişleri tutmak ister miyiz ona göre db ayarlaması gerekebilir
    kullanıcının gördüğü ama girmek istemediği kısımlar
    kullanıcının görmediği ve ekstra eklemek istediği alanlar
    bunlar ayarlanması lazım
    filter search vb. komutlar database tarafından implement edilebilir onları nasıl edicez?
 */
package com.example.tgservice.repository;

import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Repository
public class TgRepository {

    public static Connection connection;

//    static {
//        try {
//            connection = DriverManager.getConnection("jdbc:sqlite:E:/JAVA/db/tgBase.db");
//        } catch (SQLException e) {
//            System.out.println("Проблемы с подключением к базе данных");
//            e.printStackTrace();
//        }
//    }

    public List<Integer> getListChannelIdByCategory(int idCategory) {
        List<Integer> result = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement("SELECT * from Channel where category = '" + idCategory + "'")) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                result.add(resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return result;
        }
        return result;
    }

    public String getChannelUrlById(int id) {
        String result = "";

        try (PreparedStatement statement = connection.prepareStatement("SELECT * from Channel where id = '" + id + "'")) {

            ResultSet resultSet = statement.executeQuery();

            result = resultSet.getString("url");

        } catch (SQLException e) {
            e.printStackTrace();
            return result;
        }
        return result;
    }

    public int getCountRecord(int idChannel) {
        int count = 1;

        try (PreparedStatement statement = connection.prepareStatement("SELECT * from countRecord where idChannel = '" + idChannel + "'")) {

            ResultSet resultSet = statement.executeQuery();

            count = resultSet.getInt("countRecord");

        } catch (SQLException e) {
            e.printStackTrace();
            return count;
        }
        return count;
    }

    public void updateCountRecord(int idChannel, int countRecord) {
        System.out.println("idChannel = " + idChannel + " countRecord = " + countRecord);
        try (PreparedStatement statement = connection.prepareStatement("UPDATE countRecord SET countRecord = ? WHERE idChannel = ?")) {
            statement.setInt(1, countRecord);
            statement.setInt(2, idChannel);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void addNameImgTgToBd(String name, int category) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO ImgName VALUES (?, ?, ?)")) {
            statement.setString(2, name);
            statement.setInt(3, category);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public int getCountImg() {
        int count = 0;

        try (PreparedStatement statement = connection.prepareStatement("SELECT seq from sqlite_sequence where name = 'ImgName'")) {

            ResultSet resultSet = statement.executeQuery();

            count = resultSet.getInt("seq");

        } catch (SQLException e) {
            e.printStackTrace();
            return count;
        }
        return count;
    }
}



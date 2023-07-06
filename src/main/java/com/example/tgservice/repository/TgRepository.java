package com.example.tgservice.repository;

import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Repository
public class TgRepository {

    public static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:C:/TEMP/db/MainBaseMse.db");
        } catch (SQLException e) {
            System.out.println("Проблемы с подключением к базе данных");
            e.printStackTrace();
        }
    }

    public List<Integer> getListChannelByCategory(int idCategory){
        List<Integer> result = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement("SELECT * from Channel where category = '"+idCategory+"'")) {

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




}

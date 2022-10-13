package com.example.webshopdat22v2.repository;

import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class ProductRepository {

    public void getAll(){
        try {
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/webshop",
                    "root", "qJiw03K2zwJD");
            PreparedStatement psts = conn.prepareStatement("SELECT * from product");
            ResultSet resultSet = psts.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                int price = resultSet.getInt(3);
                System.out.println(id + name + price);

            }
        }
        catch (SQLException e)
        {
            System.out.println("Couldn't connect to db");
            e.printStackTrace();
        }

    }
}

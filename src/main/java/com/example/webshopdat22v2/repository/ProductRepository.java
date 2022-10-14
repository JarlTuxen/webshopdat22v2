package com.example.webshopdat22v2.repository;

import com.example.webshopdat22v2.model.Product;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

@Repository
public class ProductRepository {

    public List<Product> getAll(){

        List<Product> productList = new LinkedList<>();

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
                productList.add(new Product(id, name, price));
                System.out.println(id + name + price);
            }


        }
        catch (SQLException e)
        {
            System.out.println("Couldn't connect to db");
            e.printStackTrace();
        }

        return productList;
    }

    public void addProduct(Product newProduct) {
        try {
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/webshop",
                    "root", "qJiw03K2zwJD");
            String queryCreate = "INSERT INTO product (name, price) VALUES (?, ?)";
            PreparedStatement psts = conn.prepareStatement(queryCreate);


            //indsæt name og price i prepared statement
            psts.setString(1, newProduct.getName());
            psts.setInt(2, newProduct.getPrice());

            //execute query
            psts.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Couldn't connect to db");
            e.printStackTrace();
        }
    }

    public Product findProductById(int id){

        Product product = new Product();
        product.setId(id);

        try {
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/webshop",
                    "root", "qJiw03K2zwJD");
            String queryCreate = "SELECT * FROM product WHERE id=?";
            PreparedStatement psts = conn.prepareStatement(queryCreate);

            //indsæt name og price i prepared statement
            psts.setInt(1, id);

            //execute query
            ResultSet rs = psts.executeQuery();

            rs.next();
            String name = rs.getString(2);
            int price = rs.getInt(3);
            product.setName(name);
            product.setPrice(price);
            System.out.println(product);

        } catch (SQLException e) {
            System.out.println("Couldn't connect to db");
            e.printStackTrace();
        }
        return product;

    }
}

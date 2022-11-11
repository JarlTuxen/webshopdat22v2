package com.example.webshopdat22v2.repository;

import com.example.webshopdat22v2.model.Product;

import com.example.webshopdat22v2.util.ConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

@Repository
public class ProductRepository {

    @Value("${spring.datasource.url}")
    private String db_url;

    @Value("${spring.datasource.username}")
    private String uid;

    @Value("${spring.datasource.password}")
    private String pwd;

    public List<Product> getAll(){

        List<Product> productList = new LinkedList<>();

        try {
            //Connection conn = DriverManager.getConnection(db_url, uid, pwd);
                    //"jdbc:mysql://localhost:3306/webshop",
                    //"webshop_dat22v2", "DetErSnartJul!");
            //ConnectionManager connMgr = new ConnectionManager();
            //Connection conn = connMgr.getConnection();
            Connection conn = ConnectionManager.getConnection(db_url, uid, pwd);
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
            Connection conn = ConnectionManager.getConnection(db_url, uid, pwd);
            /*Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/webshop",
                    "root", "qJiw03K2zwJD");*/
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
            Connection conn = ConnectionManager.getConnection(db_url, uid, pwd);
            /* Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/webshop",
                    "root", "qJiw03K2zwJD");*/
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

    public void updateProduct(Product product){
        try {
            Connection conn = ConnectionManager.getConnection(db_url, uid, pwd);
            /*
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/webshop",
                    "root", "qJiw03K2zwJD");*/
            String queryCreate = "UPDATE product " +
                    "SET name=?, price=? WHERE id=?";
            PreparedStatement psts = conn.prepareStatement(queryCreate);


            //indsæt name og price i prepared statement
            psts.setString(1, product.getName());
            psts.setInt(2, product.getPrice());
            psts.setInt(3,product.getId());

            //execute query
            psts.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Couldn't connect to db");
            e.printStackTrace();
        }
    }

    public void deleteProductById(int sletId){
        try {
            Connection conn = ConnectionManager.getConnection(db_url, uid, pwd);
            /*Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/webshop",
                    "root", "qJiw03K2zwJD");*/
            String queryCreate = "DELETE FROM product WHERE id=?";
            PreparedStatement psts = conn.prepareStatement(queryCreate);

            psts.setInt(1, sletId);

            //execute query
            psts.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Couldn't connect to db");
            e.printStackTrace();
        }
    }

}

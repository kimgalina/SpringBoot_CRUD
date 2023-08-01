package com.example.SpringBoot_CRUD.DAO;

import com.example.SpringBoot_CRUD.models.Product;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductDAO {
    private String URL ="jdbc:postgresql://localhost:5433/SpringBoot_CRUD_products";
    private String USERNAME= "postgres";
    private String PASSWORD= "vyhuhol05.kg";
    private Connection connection;

    {// checking class Driver for a successful installation
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            // connecting to our DB
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Product> getAllProducts(){
        List<Product> products = new ArrayList<>();
        String SQL = "SELECT * FROM products";
        ResultSet result;
        try {
            Statement statement = connection.createStatement();
            result = statement.executeQuery(SQL);
            while(result.next()){
                Product product = new Product();
                product.setId(result.getInt("id"));
                product.setProductName(result.getString("product_name"));
                product.setCost(result.getFloat("cost"));
                product.setProvider(result.getString("product_provider"));
                products.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;

    }

}

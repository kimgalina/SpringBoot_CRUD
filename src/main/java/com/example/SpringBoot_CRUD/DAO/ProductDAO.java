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

    public Product findById(int id){
        Product product = null;
        String SQL= "SELECT *FROM products WHERE id=?";
        try {
            PreparedStatement prepStatement = connection.prepareStatement(SQL);
            prepStatement.setInt(1,id);
            ResultSet result = prepStatement.executeQuery();
            while(result.next()){
                product = new Product();
                product.setId(result.getInt("id"));
                product.setProductName(result.getString("product_name"));
                product.setCost(result.getFloat("cost"));
                product.setProvider(result.getString("product_provider"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return product;
    }


    public void addProduct(Product product){
        String SQL= "INSERT INTO products (product_name,cost,product_provider) values (?,?,?)";
        try {
            PreparedStatement prepStatement = connection.prepareStatement(SQL);
            prepStatement.setString(1,product.getProductName());
            prepStatement.setFloat(2,product.getCost());
            prepStatement.setString(3,product.getProvider());

            prepStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Product updatedProduct, int id){
        String SQL = "UPDATE products SET product_name =?, cost =?, product_provider =? WHERE id =?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1,updatedProduct.getProductName());
            preparedStatement.setFloat(2,updatedProduct.getCost());
            preparedStatement.setString(3,updatedProduct.getProvider());
            preparedStatement.setInt(4,id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id){
        String SQL = "DELETE FROM products WHERE id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}

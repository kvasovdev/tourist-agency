package ru.vsu.touristagency.persistance;

import ru.vsu.touristagency.domain.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientRepository implements IRepository<Client> {
    private static final String URL = "jdbc:h2:./tourism_db";
    private static final String USER = "admin";
    private static final String PASSWORD = "admin";

    public ClientRepository() {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver class not found" + e.getMessage());
        }
        createTable();
    }



    @Override
    public Client find(Long id) {
        IDBMapper<Client, ResultSet> a = new IDBMapper<Client, ResultSet>() { //a = mapper
            @Override
            public Client map(ResultSet resultSet) {
                Client client = new Client();
                try {
                    while (resultSet.next()) {
                        client.setId(resultSet.getLong("id"));
                        client.setFullName(resultSet.getString("fullName"));
                        client.setNumberPhone(resultSet.getInt("phoneNumber"));
                    }
                }catch(SQLException ex){
                    System.out.println("Result set get fail. " + ex.getMessage());
                }
                return client;
            }
        };
        //query
        String query = "SELECT * from clients WHERE id = " + id.toString();
        //connect try
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD); //проводник в БД
             Statement statement = connection.createStatement();){

            ResultSet resultSet = statement.executeQuery(query);//лучше в ед. числе называть

            return a.map(resultSet);
        } catch (SQLException e) {
            System.out.println("Connection error" + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Client> findAll(){
        IDBMapper<List<Client>, ResultSet> a = new IDBMapper<List<Client>, ResultSet>() { //a = mapper
            @Override
            public List<Client> map(ResultSet resultSet) {
                List<Client> clients = new ArrayList<>();
                try {
                    while (resultSet.next()) {
                        Client client = new Client();
                        client.setId(resultSet.getLong("id"));
                        client.setFullName(resultSet.getString("fullName"));
                        client.setNumberPhone(resultSet.getInt("phoneNumber"));
                        clients.add(client);
                    }
                }catch(SQLException ex){
                    System.out.println("Result set get fail. " + ex.getMessage());
                }
                return clients;
            }
        };
        //query
        String query = "SELECT * from clients";
        //connect try
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD); //проводник в БД
             Statement statement = connection.createStatement();){

            ResultSet resultSet = statement.executeQuery(query);//лучше в ед. числе называть

            return a.map(resultSet);
        } catch (SQLException e) {
            System.out.println("Connection error" + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean create(Client o) {
        String query = "INSERT INTO clients (fullName, phoneNumber)\nVALUES(\'%s\', %d);";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD); //проводник в БД
             Statement statement = connection.createStatement();){

            //query = String.format(query, o.getFullName(), o.getNumberPhone());
            return statement.execute(String.format(query, o.getFullName(), o.getNumberPhone()));//лучше в ед. числе называть
        } catch (SQLException e) {
            System.out.println("Connection error" + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(Client o) {
        String query = "UPDATE clients\nSET id = %d, fullName = \'%s\', phoneNumber = %d\nWHERE id = %d;";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD); //проводник в БД
             Statement statement = connection.createStatement();){

            //query = String.format(query, o.getFullName(), o.getNumberPhone());
            return statement.execute(String.format(query, o.getId(), o.getFullName(), o.getNumberPhone(), o.getId()));//лучше в ед. числе называть
        } catch (SQLException e) {
            System.out.println("Connection error" + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(Client o) {
        String query = "DELETE FROM clients WHERE id = %d;";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD); //проводник в БД
             Statement statement = connection.createStatement();){

            //query = String.format(query, o.getFullName(), o.getNumberPhone());
            return statement.execute(String.format(query, o.getId()));//лучше в ед. числе называть
        } catch (SQLException e) {
            System.out.println("Connection error" + e.getMessage());
        }
        return false;
    }




    private void createTable(){
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD); //проводник в БД
             Statement statement = connection.createStatement();) {
            statement.execute("CREATE TABLE clients  (" + "id INT NOT NULL PRIMARY KEY auto_increment, " +
                    "fullName VARCHAR (100) NOT NULL," +
                    "phoneNumber INT NOT NULL " + ")");

            //statement.execute("INSERT INTO categories" + "(name, created_at)" + "VALUES ('category_1', '2019-11-05')");


        }
        catch (SQLException e) {
            System.out.println("DDL or DML statement error" + e.getMessage());
        }
    }

}

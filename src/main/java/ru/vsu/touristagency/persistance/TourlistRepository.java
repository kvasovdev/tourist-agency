package ru.vsu.touristagency.persistance;

import ru.vsu.touristagency.domain.Tourlist;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TourlistRepository implements IRepository<Tourlist> {
    private static final String URL = "jdbc:h2:./tourism_db";
    private static final String USER = "admin";
    private static final String PASSWORD = "admin";

    public TourlistRepository() {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver class not found" + e.getMessage());
        }
        createTable();
    }



    @Override
    public Tourlist find(Long id) {
        IDBMapper<Tourlist, ResultSet> a = new IDBMapper<Tourlist, ResultSet>() { //a = mapper
            @Override
            public Tourlist map(ResultSet resultSet) {
                Tourlist tourlist = new Tourlist();
                try {
                    while (resultSet.next()) {
                        tourlist.setId(resultSet.getLong("id"));
                        tourlist.setClientId(resultSet.getLong("clientId"));
                        tourlist.setTourId(resultSet.getLong("tourId"));
                        tourlist.setStatus(resultSet.getInt("status"));
                        tourlist.setDate(resultSet.getDate("date"));
                    }
                }catch(SQLException ex){
                    System.out.println("Result set get fail. " + ex.getMessage());
                }
                return tourlist;
            }
        };
        //query
        String query = "SELECT * from tourslist WHERE id = " + id.toString();
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
    public List<Tourlist> findAll(){
        IDBMapper<List<Tourlist>, ResultSet> a = new IDBMapper<List<Tourlist>, ResultSet>() { //a = mapper
            @Override
            public List<Tourlist> map(ResultSet resultSet) {
                List<Tourlist> tourslist = new ArrayList<>();
                try {
                    while (resultSet.next()) {
                        Tourlist tourlist = new Tourlist();
                        tourlist.setId(resultSet.getLong("id"));
                        tourlist.setClientId(resultSet.getLong("clientId"));
                        tourlist.setTourId(resultSet.getLong("tourId"));
                        tourlist.setStatus(resultSet.getInt("status"));
                        tourlist.setDate(resultSet.getDate("date"));
                        tourslist.add(tourlist);
                    }
                }catch(SQLException ex){
                    System.out.println("Result set get fail. " + ex.getMessage());
                }
                return tourslist;
            }
        };
        //query
        String query = "SELECT * from tourslist";
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
    public boolean create(Tourlist o) {
        String query = "INSERT INTO tourslist (clientId, tourId, status, date)\nVALUES(%d, %d, %d, %tF);";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD); //проводник в БД
             Statement statement = connection.createStatement();){

            //query = String.format(query, o.getFullName(), o.getNumberPhone());
            return statement.execute(String.format(query, o.getClientId(), o.getTourId(), o.getStatus(), o.getDate()));//лучше в ед. числе называть
        } catch (SQLException e) {
            System.out.println("Connection error" + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(Tourlist o) {
        String query = "UPDATE clients\nSET id = %d, clientId = %d, tourId = %d, status = %d, date = %tF\nWHERE id = %d;";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD); //проводник в БД
             Statement statement = connection.createStatement();){

            //query = String.format(query, o.getFullName(), o.getNumberPhone());
            return statement.execute(String.format(query, o.getId(), o.getClientId(), o.getTourId(), o.getStatus(), o.getDate(), o.getId()));//лучше в ед. числе называть
        } catch (SQLException e) {
            System.out.println("Connection error" + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(Tourlist o) {
        String query = "DELETE FROM Tourslist WHERE id = %d;";
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
            statement.execute("CREATE TABLE tourslist  (" + "id INT NOT NULL PRIMARY KEY auto_increment, " +
                    "clientId INT NOT NULL," +
                    "tourId INT NOT NULL," +
                    "status INT NOT NULL," +
                    "date DATE NOT NULL " + ")");

            //statement.execute("INSERT INTO categories" + "(name, created_at)" + "VALUES ('category_1', '2019-11-05')");


        }
        catch (SQLException e) {
            System.out.println("DDL or DML statement error" + e.getMessage());
        }
    }

}

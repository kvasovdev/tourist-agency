package ru.vsu.touristagency.persistance;

import ru.vsu.touristagency.domain.Tour;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

    public class TourRepository implements IRepository<Tour> {
        private static final String URL = "jdbc:h2:./tourism_db";
        private static final String USER = "admin";
        private static final String PASSWORD = "admin";

        public TourRepository() {
            try {
                Class.forName("org.h2.Driver");
            } catch (ClassNotFoundException e) {
                System.out.println("Driver class not found" + e.getMessage());
            }
            createTable();
        }

    @Override
    public Tour find(Long id) {
        IDBMapper<Tour, ResultSet> a = new IDBMapper<Tour, ResultSet>() { //a = mapper
            @Override
            public Tour map(ResultSet resultSet) {
                Tour tour = new Tour();
                try {
                    while (resultSet.next()) {
                        tour.setId(resultSet.getLong("id"));
                        tour.setCountry(resultSet.getString("Country"));
                        tour.setHotel(resultSet.getString("Cost"));
                        tour.setNutrition(resultSet.getBoolean("Nutrition"));
                        tour.setCost(resultSet.getDouble("Cost"));
                    }
                }catch(SQLException ex){
                    System.out.println("Result set get fail. " + ex.getMessage());
                }
                return tour;
            }
        };
        //query
        String query = "SELECT * from tours WHERE id = " + id.toString();
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
    public List<Tour> findAll(){
        IDBMapper<List<Tour>, ResultSet> a = new IDBMapper<List<Tour>, ResultSet>() { //a = mapper
            @Override
            public List<Tour> map(ResultSet resultSet) {
                List<Tour> tours = new ArrayList<>();
                try {
                    while (resultSet.next()) {
                        Tour tour = new Tour();
                        tour.setId(resultSet.getLong("id"));
                        tour.setCountry(resultSet.getString("Country"));
                        tour.setHotel(resultSet.getString("Hotel"));
                        tour.setNutrition(resultSet.getBoolean("Nutrition"));
                        tour.setCost(resultSet.getDouble("Cost"));
                        tours.add(tour);
                    }
                }catch(SQLException ex){
                    System.out.println("Result set get fail. " + ex.getMessage());
                }
                return tours;
            }
        };
        //query
        String query = "SELECT * from tours";
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
    public boolean create(Tour o) {
        String query = "INSERT INTO tours (country, hotel, nutrition, cost)\nVALUES(\'%s\',\'%s\', %b, %f);";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD); //проводник в БД
             Statement statement = connection.createStatement();){

            //query = String.format(query, o.getFullName(), o.getNumberPhone());
            return statement.execute(String.format(query, o.getCountry(), o.getHotel(), o.getNutrition(), o.getCost()));//лучше в ед. числе называть
        } catch (SQLException e) {
            System.out.println("Connection error" + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(Tour o) {
        String query = "UPDATE tours\nSET id = %d, country= \'%s\', hotel = \'%s\', nutrition = %b, cost = %f\nWHERE id = %d;";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD); //проводник в БД
             Statement statement = connection.createStatement();){

            //query = String.format(query, o.getFullName(), o.getNumberPhone());
            return statement.execute(String.format(query, o.getId(), o.getCountry(), o.getHotel(), o.getNutrition(), o.getCost(), o.getId()));//лучше в ед. числе называть
        } catch (SQLException e) {
            System.out.println("Connection error" + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(Tour o) {
        String query = "DELETE FROM tours WHERE id = %d;";
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
            statement.execute("CREATE TABLE tours  (" + "id INT NOT NULL PRIMARY KEY auto_increment, " +
                    "Country VARCHAR (100) NOT NULL," +
                    "Hotel VARCHAR (100) NOT NULL," +
                    "Nutrition INT NOT NULL," +
                    "Cost INT NOT NULL " + ")");

            //statement.execute("INSERT INTO categories" + "(name, created_at)" + "VALUES ('category_1', '2019-11-05')");


        }
        catch (SQLException e) {
            System.out.println("DDL or DML statement error" + e.getMessage());
        }
    }

}

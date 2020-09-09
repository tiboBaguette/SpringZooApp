package be.vdab.springmvc.doa;


import be.vdab.springmvc.models.Animal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static be.vdab.springmvc.ConnectionUtils.*;

@Repository
public class AnimalDAOImpl implements AnimalDAO {

    private Logger logger = LoggerFactory.getLogger(AnimalDAOImpl.class);
    private List<Animal> animals = new ArrayList<Animal>();

    private Connection createConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            logger.error("Error mysql driver:", e);
        }
        return DriverManager.getConnection(ADRESS, USER, PASSWORD);
    }

    public List<Animal> getAllAnimals() {
        try (PreparedStatement preparedStatement = createConnection().prepareStatement("select * from animals")) {
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            List<Animal> animals = new ArrayList<>();
            while (resultSet.next()) {
                Animal animal = new Animal();
                animal.setId(resultSet.getInt("id"));
                animal.setName(resultSet.getString("name"));
                animals.add(animal);
            }
            return animals;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Collections.emptyList();
    }

    public void addAnimal(Animal animal) {
        try (PreparedStatement preparedStatement = createConnection().prepareStatement("insert into animals(id, name) values ((SELECT LAST_INSERT_ID()),?)")) {
            preparedStatement.setString(1, animal.getName());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void deleteAnimal(int id) {
        try (PreparedStatement preparedStatement = createConnection().prepareStatement("DELETE FROM animals WHERE id = ? ")) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Animal findById(int id) {
        try (PreparedStatement preparedStatement = createConnection().prepareStatement("select * from animals where id = ? ")) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            resultSet.next();
            Animal animal = new Animal();
            animal.setId(resultSet.getInt("id"));
            animal.setName(resultSet.getString("name"));
            return animal;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Animal animal) {
        try (PreparedStatement preparedStatement = createConnection().prepareStatement("update animals set name= ? where id = ? ")) {
            preparedStatement.setString(1, animal.getName());
            preparedStatement.setInt(2, animal.getId());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

package repository.dao.game.impl;

import org.apache.log4j.Logger;
import pojo.game.Publisher;
import repository.ConnectionManager.ConnectionManager;
import repository.ConnectionManager.ConnectionManagerMobileDB;
import repository.dao.game.interfaces.PublisherDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PublisherDaoImpl implements PublisherDao {

    private static final Logger LOGGER = Logger.getLogger(PublisherDaoImpl.class);
    private static ConnectionManager connectionManager =
            ConnectionManagerMobileDB.getInstance();

    @Override
    public boolean add(Publisher pub) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO publishers values (DEFAULT, ?) RETURNING id ")) {

            preparedStatement.setString(1, pub.getName());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Integer id = resultSet.getInt(1);
                    System.out.println("The new object Publisher in DB was assigned the id: "
                            + id);
                }
                return true;
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return false;
    }

    @Override
    public Publisher getById(Integer id) {
        try(Connection connection = connectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM publishers WHERE id = ?")) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Publisher(
                            resultSet.getInt(1),
                            resultSet.getString(2));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return null;
    }

    @Override
    public List<Publisher> getAll() {
        List<Publisher> publishers = null;
        try (Connection connection = connectionManager.getConnection();
             Statement statement = connection.createStatement()) {

            try(ResultSet resultSet = statement.executeQuery("SELECT * FROM publishers")) {
                publishers = new ArrayList<>();
                while (resultSet.next()) {
                    publishers.add(new Publisher(
                            resultSet.getInt(1),
                            resultSet.getString(2)));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return publishers;
    }
}

import dto.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientService {
    private static final String CREATE_CLIENT = "INSERT INTO client (name) VALUES (?) ";
    private static final String GET_BY_ID_CLIENT = "SELECT name FROM client WHERE id = ?";
    private static final String SET_NAME_CLIENT = "UPDATE client SET name = ? WHERE id = ?";
    private static final String DELETE_BY_ID_CLIENT = "DELETE FROM client WHERE id = ?";
    private static final String ALL_CLIENT = "SELECT *  FROM client";
    private static final String RETURN_GENERATED_KEYS = "SELECT MAX(id) AS maxId FROM client";

    public long create(String name) {
        long id = 0;

        if (name.length() > 2 & name.length() <= 1000) {
            try (Connection connection = Database.getInstance().getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(CREATE_CLIENT)) {
                preparedStatement.setString(1, name);
                preparedStatement.executeUpdate();

                try (PreparedStatement prepareStatement = connection.prepareStatement(RETURN_GENERATED_KEYS);
                     ResultSet rs = prepareStatement.executeQuery()) {
                    if (rs.next()) {
                        id = rs.getLong(1);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return id;
        } else {
            throw new IllegalArgumentException("Nice try! Incorrect data transfer to the method create!");
        }
    }

    public String getById(long id) {
        String name = "";

        if (id > 0) {
            try (Connection connection = Database.getInstance().getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID_CLIENT)) {
                preparedStatement.setLong(1, id);

                try (ResultSet rs = preparedStatement.executeQuery()) {
                    rs.next();
                    return rs.getString("name");
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return name;
        } else {
            throw new IllegalArgumentException("Nice try! Incorrect data transfer to the method getById!");
        }
    }

    public void setName(long id, String name) {

        if (name.length() > 2 & name.length() <= 1000 & id > 0) {
            try (Connection connection = Database.getInstance().getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(SET_NAME_CLIENT)) {
                preparedStatement.setString(1, name);
                preparedStatement.setLong(2, id);
                preparedStatement.executeUpdate();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else {
            throw new IllegalArgumentException("Nice try! Incorrect data transfer to the method setName!");
        }
    }

    public void deleteById(long id) {

        if (id > 0) {
            try (Connection connection = Database.getInstance().getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID_CLIENT)) {
                preparedStatement.setLong(1, id);
                preparedStatement.execute();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else {
            throw new IllegalArgumentException("Nice try! Incorrect data transfer to the method deleteById!");
        }
    }

    public List<Client> listAll() {
        List<Client> clientList = new ArrayList<>();

        try (Connection connection = Database.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ALL_CLIENT);
             ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                clientList.add(new Client(rs.getLong("id"), rs.getString("name")));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return clientList;
    }
}

package org.academiadecodigo.vim4cache.persistence;

import org.academiadecodigo.vim4cache.user.User;

import java.sql.*;

/**
 * Created by codecadet on 31/03/17.
 */
public class JdcbMySQLConnection {
    Connection connection = null;
    ConnectionManager connectionManager;

    public JdcbMySQLConnection(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public boolean authenticate(String user, String pass) {
        try {
            if (connection == null) {
                connection = connectionManager.getConnection();
            }
            PreparedStatement statement = null;
            String query = "SELECT name FROM list WHERE name = ? and auser_pass = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, user);
            statement.setString(2, pass);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return true;
            }

        } catch (SQLException ex) {
            System.out.println("Failure to connect to database : " + ex.getMessage());
        }
        return false;
    }

    public void addUser(User user) {
        if (connection == null) {
            connection = connectionManager.getConnection();
        }
        PreparedStatement statement = null;
        try {
            connection.setAutoCommit(false);
            String query = "INSERT INTO list (name,score) VALUES ('name'= ?, 'score'= ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, user.getName());
            statement.setString(2, String.valueOf(user.getScore()));
            statement.executeQuery();
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void setScore(String name, int score) {
        if (connection == null) {
            connection = connectionManager.getConnection();
        }
        PreparedStatement statement = null;
        try {
            String query = "UPDATE score FROM list WHERE NAME =? SET  score = ?";
            statement.setString(1, name);
            statement.setString(2, String.valueOf(score));
            statement = connection.prepareStatement(query);
            statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    public User findByName(String userName) {
        if (connection == null) {
            connection = connectionManager.getConnection();
        }
        PreparedStatement statement = null;
        try {
            String query = "SELECT NAME FROM list WHERE name = ? ;";
            statement = connection.prepareStatement(query);
            statement.setString(1, userName);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String usernameValue = resultSet.getString("name");
                int scoreValue = resultSet.getInt("score");
                int id = resultSet.getInt("id");
                User user = new User(usernameValue, scoreValue, id);
                connection.commit();
                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public User getScores() {
        if (connection == null) {
            connection = connectionManager.getConnection();
        }
        PreparedStatement statement = null;
        try {
            String query = " SELECT name from list ORDER BY score DESC";
            statement = connection.prepareStatement(query);
            String name = String.valueOf(statement.executeQuery());
            User user = findByName(name);
            connection.commit();
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}



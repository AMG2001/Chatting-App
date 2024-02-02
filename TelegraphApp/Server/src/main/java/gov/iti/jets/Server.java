package gov.iti.jets;

import RemoteInterfaces.callback.RemoteCallbackInterface;
import gov.iti.jets.Persistence.mysql.DBConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Server {
    public static void main(String[] args) {
        /**
         * to load and init all application services :
         * 1. initialize database Connection .
         * 2. init all rowsets object to be able to perform crud operations on all tables .
         */
        //ApplicationServices.initApplicationServices();
        ArrayList<RemoteCallbackInterface> onlineUsers;

        List<User> userList = new ArrayList<>();

        try (Connection connection = DBConnectionPool.DATASOURCE.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet2 = statement.executeQuery("SHOW STATUS LIKE 'Threads_connected'")) {
            String query = "SELECT * FROM User";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    // Assuming User class has appropriate constructor and getters
                    User user = new User(
                            resultSet.getString("name"),
                            resultSet.getString("email")
                    );
                    userList.add(user);
                }
            }
            if (resultSet2.next()) {
                int connectionCount = resultSet2.getInt("Value");
                System.out.println("Number of active connections: " + connectionCount);
            }

            for (User user : userList) {
                System.out.println(user);
            }

            // Close the connection pool when done
            //DBConnectionFactory.INSTANCE.close();

        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately in your application
        }
    }

    public static class User {

        String username;
        String email;

        public User( String username, String email) {
            this.username = username;
            this.email = email;
        }

        @Override
        public String toString() {
            return "User{" +

                    ", username='" + username + '\'' +
                    ", email='" + email + '\'' +
                    '}';
        }
    }

}

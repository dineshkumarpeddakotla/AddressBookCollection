/*
 Purpose : Program is written to handle Database contacts into address_book_service database
 @author Dinesh Kumar Peddakotla
 @version 1.0
 @since 10-05-2021
 */
package com.addressbook;

import java.sql.*;

public class AddressBookDBService {

    /**
     * main method thread to run program
     * @param args arguments
     */
    public static void main(String[] args) {
        AddressBookDBService addressBookDBService = new AddressBookDBService();
        addressBookDBService.retrieveAddressBookData();
    }

    /**
     * getConnection method is to create connection with database
     * using database url , username and password
     * added try and catch blocks to throw sql exception
     * @return connection
     */
    private Connection getConnection()  {
        String jdbcURL = "jdbc:mysql://localhost:3306/address_book_service?useSSL=false";
        String username = "root";
        String password = "Dinnu@247";
        Connection connection = null;
        System.out.println("Connecting to database " +jdbcURL);
        try {
            connection = DriverManager.getConnection(jdbcURL, username, password);
            System.out.println("Connection is Successful! " +connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * retrieveAllDataFromDB method is used retrieve data from database
     * by making connection with db and create statement and execute sql query and
     * uses getMetaData interface of ResultSet to get all data about data
     * from db and uses loops to get all data from database table
     * added try and catch blocks to throw sql exception
     */
    private void retrieveAddressBookData() {
        String sql = "select address_book_name.name,type.type_name,contact_details.* " +
                     "from type join contact_type on contact_type.type_id = type.type_id\n" +
                     "join address_book_name on address_book_name.name_id = contact_type.name_id\n" +
                     "join contact_details on contact_type.contact_id = contact_details.contact_id;";
        try(Connection connection = this.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

            while (resultSet.next()) {
                for(int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = resultSet.getString(i);
                    System.out.print(resultSetMetaData.getColumnName(i) + " : " +columnValue );
                }
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

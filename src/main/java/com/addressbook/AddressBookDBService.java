/*
 Purpose : Program is written to handle Database contacts into address_book_service database
 @author Dinesh Kumar Peddakotla
 @version 1.0
 @since 10-05-2021
 */
package com.addressbook;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddressBookDBService {
    private PreparedStatement contactDetailsDataStatement;
    private List<ContactDetails> contactDetailsList;

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
     * @return contactDetailsList
     */
    public List<String> retrieveAddressBookData() {
        List<String> contactDetailsList = new ArrayList<>();
        String sql = "select address_book_name.name,type.type_name,contact_details.* " +
                     "from type join contact_type on contact_type.type_id = type.type_id\n" +
                     "join address_book_name on address_book_name.name_id = contact_type.name_id\n" +
                     "join contact_details on contact_type.contact_id = contact_details.contact_id;";
        try(Connection connection = this.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            String columnValue = null;
            while (resultSet.next()) {
                for(int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                    if (i > 1) System.out.print(", ");
                    columnValue = resultSet.getString(i);
                    System.out.print(resultSetMetaData.getColumnName(i) + " : " +columnValue );
                }
                System.out.println();
                contactDetailsList.add(columnValue);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contactDetailsList;
    }

    /**
     * created a updateEmployeeData method to update data in database table
     * added try and catch block to throw sql exception
     * @param name name
     * @param phone_number salary
     */
    public void updateContactDetails(String name, String phone_number) {
        System.out.println(this.updateContactDetailsUsingStatement(name, phone_number));
    }

    /**
     * created updateEmployeeDataUsingStatement method to update data in database by using sql query
     * added try and catch block to throw sql exception
     * @param name first name or last name
     * @param phone_number phone number
     * @return statement.executeUpdate(sql)
     */
    private int updateContactDetailsUsingStatement(String name, String phone_number) {
        String sql = String.format("update contact_details set phone_number = '%s' where firstname = '%s' ", name,phone_number);
        try(Connection connection = this.getConnection()) {
            Statement statement = connection.createStatement();
            return statement.executeUpdate(sql);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return 0;
    }

    /**
     * getContactDetailsData method is used to retrieve data from database
     * using prepared statement and in it giving sql query to get data
     * try and catch blocks are added to throw sql exception
     * @param name first name of contact person
     * @return contactDetailsList
     */
    private List<ContactDetails> getContactDetailsData(String name) {
          contactDetailsList = null;
        if (this.contactDetailsDataStatement == null)
            this.prepareStatementForAddressBookData();
        try {
            contactDetailsDataStatement.setString(1, name);
            ResultSet resultSet = contactDetailsDataStatement.executeQuery();
            contactDetailsList = this.getContactDetailsData(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contactDetailsList;
    }

    /**
     * getContactDetailsData method is used to retrieve data
     * according with table headers of database
     * @param resultSet resultSet of prepared statement execute query from getContactDetailsData method
     * @return contactDetailsList having data according to contact_details table of address_book_base
     */
    private List<ContactDetails> getContactDetailsData(ResultSet resultSet) {
        List<ContactDetails> contactDetailsList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                String firstname = resultSet.getString("firstname");
                String lastname = resultSet.getString("lastname");
                String address = resultSet.getString("address");
                String city = resultSet.getString("city");
                String state = resultSet.getString("state");
                int zipcode = resultSet.getInt("zipcode");
                String phone_number = resultSet.getString("phone_number");
                String email = resultSet.getString("email");
                contactDetailsList.add(new ContactDetails(firstname, lastname,address,city,state,zipcode,phone_number,email));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contactDetailsList;
    }

    /**
     * prepareStatementForAddressBookData method to make connection with address_book_service database
     * and giving sql query to get data from database
     */
    private void prepareStatementForAddressBookData() {
        try (Connection connection = this.getConnection()){
            String sql = "SELECT * FROM contact_details Where firstname = ?";
            contactDetailsDataStatement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *  checkAddressBookSyncWithDB method check update details whether synced or not with database
     * @param name first name of contact details
     * @return  boolean
     */
    public boolean checkAddressBookSyncWithDB(String name) {
        List<ContactDetails> addressBookList = getContactDetailsData(name);
        return addressBookList.get(0).equals(getContactDetailData(name));
    }

    /**
     * getContactDetailData method to get the data of person from contact details list y using stream api
     * @param name first name
     * @return contactDetailsData of name matching with parameter
     */
    private ContactDetails getContactDetailData(String name) {
        ContactDetails contactDetailsData;
        contactDetailsData = this.contactDetailsList.stream()
                                                    .filter(contactDetails -> contactDetails
                                                    .getFirstName().equals(name))
                                                    .findFirst()
                                                    .orElse(null);
        return contactDetailsData;
    }
}

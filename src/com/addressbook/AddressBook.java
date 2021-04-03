package com.addressbook;

import java.util.Scanner;

public class AddressBook {
    static Scanner input = new Scanner(System.in);
    static ContactDetails person = new ContactDetails();
    public static void setContactDetails(){
        System.out.println("Enter the First Name");
        person.setFirstName(input.nextLine());
        System.out.println("Enter the Last Name");
        person.setLastName(input.nextLine());
        System.out.println("Enter the Address");
        person.setAddress(input.nextLine());
        System.out.println("Enter the City");
        person.setCity(input.nextLine());
        System.out.println("Enter the State");
        person.setState(input.nextLine());
        System.out.println("Enter the Zip Code");
        person.setZipCode(input.nextLine());
        System.out.println("Enter the Phone Number");
        person.setPhoneNumber(input.nextLine());
        System.out.println("Enter the Email");
        person.setEmail(input.nextLine());
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Address Book");
        setContactDetails();
        System.out.println(person.toString());
    }
}
class ContactDetails{
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private String phoneNumber;
    private String email;

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getZipCode() {
        return zipCode;
    }
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String toString(){
        return ("First Name: " +firstName+"\nLast Name: " +lastName+"\nAddress: "+address+"\nCity: "+city+
                "\nState: "+state+"\nZip Code: "+zipCode+"\nPhone Number: "+phoneNumber+"\nEmail: "+email);
    }
}

package com.addressbook;
import java.util.ArrayList;
import java.util.Scanner;

public class AddressBook {
    public static final int ADD_DETAILS = 1;
    public static final int EDIT_DETAILS = 1;
    ArrayList<ContactDetails> personsdata;
    public AddressBook() {
        personsdata = new ArrayList<>();
    }
    public void addPerson() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the First Name");
        String firstName = sc.nextLine();
        System.out.println("Enter the Last Name");
        String lastName = sc.nextLine();
        System.out.println("Enter the Address");
        String address = sc.nextLine();
        System.out.println("Enter the City");
        String city = sc.nextLine();
        System.out.println("Enter the State");
        String state = sc.nextLine();
        System.out.println("Enter the Zip Code");
        String zipCode = sc.nextLine();
        System.out.println("Enter the Phone Number");
        String phoneNumber = sc.nextLine();
        System.out.println("Enter the Email");
        String email = sc.nextLine();
        ContactDetails person = new ContactDetails(firstName, lastName, address, city, state, zipCode, phoneNumber, email);
        personsdata.add(person);
    }
    public void editPersonContactDetails(String firstName) {
        Scanner sc = new Scanner(System.in);
        for (ContactDetails person : personsdata) {
            if (firstName.equals(person.getFirstName())) {
                System.out.println("1.First Name\n2.Last Name\n3.Address\n4.City\n" +
                        "5.State\n6.Zip Code\n7.Phone Number\n8.Email\n9.Exit");
                System.out.println("Which Details You Want to Change From Above List And Please Enter Edit Choice");
                int editChoice = sc.nextInt();
                Scanner input = new Scanner(System.in);
                switch (editChoice) {
                    case 1:
                        System.out.println("Enter First Name to Change");
                        String fName = input.nextLine();
                        person.setFirstName(fName);
                        break;
                    case 2:
                        System.out.println("Enter Last Name to Change");
                        String lastName = input.nextLine();
                        person.setLastName(lastName);
                        break;
                    case 3:
                        System.out.println("Enter Address to Change");
                        String address = input.nextLine();
                        person.setAddress(address);
                        break;
                    case 4:
                        System.out.println("Enter City to Change");
                        String city = input.nextLine();
                        person.setCity(city);
                        break;
                    case 5:
                        System.out.println("Enter State to Change");
                        String state = input.nextLine();
                        person.setState(state);
                        break;
                    case 6:
                        System.out.println("Enter Zip Code to Change");
                        String zipCode = input.nextLine();
                        person.setZipCode(zipCode);
                        break;
                    case 7:
                        System.out.println("Enter Phone Number to Change");
                        String phoneNumber = input.nextLine();
                        person.setPhoneNumber(phoneNumber);
                        break;
                    case 8:
                        System.out.println("Enter Email to Change");
                        String email = input.nextLine();
                        person.setEmail(email);
                        break;
                    default:
                        System.out.println("Exit");
                }
            }
        }
        System.out.println(personsdata);
    }
    public static void main(String[] args) {
        System.out.println("Welcome to Address Book");
        AddressBook addressBook = new AddressBook();
        Scanner sc = new Scanner(System.in);
        System.out.println("1.If You Want to Person Details");
        int addChoice = sc.nextInt();
        if (addChoice == ADD_DETAILS) {
            addressBook.addPerson();
            System.out.println("1.If You Want to Edit Entered details");
            int choice2 = sc.nextInt();
            if (choice2 == EDIT_DETAILS) {
                Scanner input = new Scanner(System.in);
                System.out.println("Enter First Name of Edit Person Details");
                String firstName = input.nextLine();
                addressBook.editPersonContactDetails(firstName);
            }
        }
    }
}
class ContactDetails extends AddressBook {
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
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public void setState(String state) {
        this.state = state;
    }
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public ContactDetails(String firstName, String lastName, String address, String city, String state, String zipCode, String phoneNumber, String email) {
        setFirstName(firstName);
        setLastName(lastName);
        setAddress(address);
        setCity(city);
        setState(state);
        setZipCode(zipCode);
        setPhoneNumber(phoneNumber);
        setEmail(email);
    }
    public String toString() {
        return ("First Name: " + firstName + "\nLast Name: " + lastName + "\nAddress: " + address + "\nCity: " + city +
                "\nState: " + state + "\nZip Code: " + zipCode + "\nPhone Number: " + phoneNumber + "\nEmail: " + email);
    }
}
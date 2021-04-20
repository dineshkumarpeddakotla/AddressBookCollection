package com.addressbook;
import java.util.*;

public class AddressBook {
    private static final int ADD_PERSON_DETAILS = 1;
    private static final int EDIT_PERSON_DETAILS = 2;
    private static final int DELETE_PERSON_DETAILS = 3;

    static ArrayList<ContactDetails> personsData;

    public AddressBook() {
        personsData = new ArrayList<>();
    }

    public static ContactDetails addPerson() {
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
        ContactDetails person = new ContactDetails(firstName,lastName,address,
                city,state,zipCode,phoneNumber,email);
        personsData.add(person);
        sc.close();
        return person;
    }

    public static void editPersonContactDetails(String firstName) {
        Scanner sc = new Scanner(System.in);

        for (ContactDetails person : personsData) {

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
                input.close();
            }
        }
        System.out.println(personsData);
        sc.close();
    }

    public void deletePersonContactDetails(String firstName) {
        personsData.removeIf(person -> firstName.equals(person.getFirstName()));
        System.out.println(personsData);
    }

    private static boolean checkDuplicate(String name) {
        return personsData.stream()
                .anyMatch(person -> person.getFirstName().equals(name) || person.getLastName().equals(name));
    }
    private static String getName() {
        System.out.println("Please enter name");
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }
    public void addressBookOperations() {

        Scanner scanner = new Scanner(System.in);
        boolean isExit = false;

        while (!isExit) {

            System.out.println("Please select \n1.Adding Person\n2.Updating Person\n3.Delete Person\n4.Exit");
            int choice = scanner.nextInt();
            switch (choice) {

                case ADD_PERSON_DETAILS:
                    if (!checkDuplicate(getName())) {
                        personsData.add(addPerson());
                    }
                    System.out.println(personsData.toString());
                    break;
                case EDIT_PERSON_DETAILS:
                    System.out.println("Please enter name to Update");
                    String name = scanner.nextLine();
                    editPersonContactDetails(name);
                    break;
                case DELETE_PERSON_DETAILS:
                    System.out.println("Please enter name to Delete");
                    String deleteName = scanner.nextLine();
                    deletePersonContactDetails(deleteName);
                    System.out.println(personsData.toString());
                    break;
                default: isExit = true;
            }
        }
    }
}

package com.addressbook;
import java.util.*;

public class AddressBook {
    //Declared and Initialized Constants
    private static final int ADD_PERSON_DETAILS = 1;
    private static final int EDIT_PERSON_DETAILS = 2;
    private static final int DELETE_PERSON_DETAILS = 3;
    private static final int Exit = 4;
    private static final Scanner scanner = new Scanner(System.in);
    //Array contactDetailsList Declared
    private final ArrayList<ContactDetails> contactDetailsList;

    //default constructor declared
    public AddressBook() {
        contactDetailsList = new ArrayList<>();
    }

    //Adding Person Contact Details
    private ContactDetails addPerson() {
        System.out.println("Enter the First Name");
        String firstName = scanner.next();
        System.out.println("Enter the Last Name");
        String lastName = scanner.next();
        System.out.println("Enter the Address");
        String address = scanner.next();
        System.out.println("Enter the City");
        String city = scanner.next();
        System.out.println("Enter the State");
        String state = scanner.next();
        System.out.println("Enter the Zip Code");
        String zipCode = scanner.next();
        System.out.println("Enter the Phone Number");
        String phoneNumber = scanner.next();
        System.out.println("Enter the Email");
        String email = scanner.next();

        return new ContactDetails(firstName,lastName,address,
                                  city,state,zipCode,phoneNumber,email);
    }

    /**
     * created editPersonContactDetails method to edit contact details of existing person
     * @param person  person name as a parameter
     */
    private void editPersonContactDetails(ContactDetails person) {
        boolean isExit = false;
        while (!isExit){
            System.out.println("Which Details You Want to Change From Above List And Please Enter Edit Choice");
            System.out.println("1.First Name\n2.Last Name\n3.Address\n4.City\n" +
                    "5.State\n6.Zip Code\n7.Phone Number\n8.Email\n9.Exit");

            int editChoice = scanner.nextInt();

            switch (editChoice) {
                case 1:
                    System.out.println("Enter First Name to Change");
                    String firstName = scanner.next();
                    person.setFirstName(firstName);
                    break;
                case 2:
                    System.out.println("Enter Last Name to Change");
                    String lastName = scanner.next();
                    person.setLastName(lastName);
                    break;
                case 3:
                    System.out.println("Enter Address to Change");
                    String address = scanner.next();
                    person.setAddress(address);
                    break;
                case 4:
                    System.out.println("Enter City to Change");
                    String city = scanner.next();
                    person.setCity(city);
                    break;
                case 5:
                    System.out.println("Enter State to Change");
                    String state = scanner.next();
                    person.setState(state);
                    break;
                case 6:
                    System.out.println("Enter Zip Code to Change");
                    String zipCode = scanner.next();
                    person.setZipCode(zipCode);
                    break;
                case 7:
                    System.out.println("Enter Phone Number to Change");
                    String phoneNumber = scanner.next();
                    person.setPhoneNumber(phoneNumber);
                    break;
                case 8:
                    System.out.println("Enter Email to Change");
                    String email = scanner.next();
                    person.setEmail(email);
                    break;
                case 9:isExit = true;
                    System.out.println("Exit");

                default:
                    System.out.println("Please Enter Valid Input");
            }
        }
    }

    /**
     * created deletePersonContactDetails method to delete person contact details
     * @param firstName  person firstName as a parameter
     */
    private void deletePersonContactDetails(String firstName) {
        contactDetailsList.removeIf(person -> firstName.equals(person.getFirstName()));
        System.out.println(contactDetailsList);
    }

    /**
     * created checkDuplicate method to check duplicate entry
     * @param name name of person
     * @return boolean true or false
     */
    private boolean checkDuplicate(String name) {
        return contactDetailsList.stream().anyMatch(person -> person.getFirstName().equals(name) ||
                person.getLastName().equals(name));
    }

    /**
     * created searchContactDetails method to search person contact details
     * @param name person name
     * @return contactDetails
     */
    private ContactDetails searchContactDetails(String name) {
        ContactDetails contactDetails = contactDetailsList.stream()
                                        .filter(personDetails -> personDetails.getFirstName().equals(name)
                                                && personDetails.getLastName().equals(name))
                                        .findFirst().orElse(null);

        if (contactDetails == null) {
            System.out.println("No Person Present Matching with Your Given Name");
            return null;
        } else {
            return contactDetails;
        }
    }

    private String getName() {
        return scanner.next();
    }

    /**
     * created addressBookOperations method to access different method
     */
    public void addressBookOperations() {
        boolean isExit = false;

        while (!isExit) {
            System.out.println("Please select \n1.Adding Person\n2.Edit Person Details\n3.Delete Person\n4.Exit");
            int choice = scanner.nextInt();

            switch (choice) {
                case ADD_PERSON_DETAILS:
                    System.out.println("Enter Name to Check Whether Contact Exits or Not");
                    if (!checkDuplicate(getName())) {
                        contactDetailsList.add(addPerson());
                        System.out.println(contactDetailsList);
                    } else {
                        System.out.println("Contact Details Are Already Exits With That Name");
                    }
                    break;
                case EDIT_PERSON_DETAILS:
                    System.out.println("Please Enter Name to Edit Details");
                    ContactDetails person = searchContactDetails(getName());
                    if (person != null){
                        editPersonContactDetails(person);
                        System.out.println("Edit Person Details : "+person);
                    } else {
                        System.out.println("Contact Details are Not Exits");
                    }
                    break;
                case DELETE_PERSON_DETAILS:
                    System.out.println("Please Enter Name to Delete");
                    String deleteName = scanner.nextLine();
                    deletePersonContactDetails(deleteName);
                    System.out.println(contactDetailsList.toString());
                    break;
                case Exit:
                    isExit = true;
                    break;
                default:
                    System.out.println("Please Enter Valid Option");
                    scanner.close();
            }
        }
    }

    /**
     * getters for ContactDetailsList
     * @return contactDetailsList
     */
    public ArrayList<ContactDetails> getContactDetailsList() {
        return contactDetailsList;
    }

    //override the toString method
    @Override
    public String toString() {
        return "AddressBook{" +
                "personsData=" + contactDetailsList +
                '}';
    }
}

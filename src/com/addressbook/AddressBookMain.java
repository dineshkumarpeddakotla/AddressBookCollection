package com.addressbook;

import java.util.*;
import java.util.stream.Collectors;

public class AddressBookMain {
    /**
     * Created Dictionaries : addressBookDic,cityContactDetailsDic,stateContactDetailsDic
     */
    private final HashMap<String, AddressBook> addressBookDic = new HashMap<>();
    private final HashMap<String, List<List<ContactDetails>>> cityContactDetailsDic = new HashMap<>();
    private final HashMap<String, List<List<ContactDetails>>> stateContactDetailsDic = new HashMap<>();

    //Created Class Member Object
    static AddressBookMain addressBookMain = new AddressBookMain();

    public static void main(String[] args) {
        System.out.println("Welcome to Address Book");

        boolean isExit = false;
        while (!isExit) {
            System.out.println("1.Create New Address Book\n2.Add Person to AddressBook " +
                               "\n3.View Person by City\n4.View Person by State" +
                               "\n5.Persons Count in City or State\n6.Sort Contact Details in AddressBook\n7.Exit");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            System.out.println(choice);
            switch (choice) {
                case 1:
                    System.out.println("Enter address book name");
                    addressBookMain.addressBookDic.put(scanner.next(), new AddressBook());
                    break;
                case 2:
                    System.out.println("Enter address book name to add person");
                    String addressBookName = scanner.next();
                     AddressBook addressBook = new AddressBook();
                    if (addressBookMain.addressBookDic.containsKey(addressBookName)) {
                        addressBookMain.addressBookDic.put(addressBookName, addressBook);
                        addressBook.addressBookOperations();
                    } else {
                        System.out.println("Address Book is not present");
                    }

                    System.out.println(addressBookMain.addressBookDic.values().toString());
                    break;
                case 3:
                    try {
                        System.out.println("Enter City Name");
                        Scanner searchInput = new Scanner(System.in);
                        String city = searchInput.nextLine();
                        addressBookMain.viewPersonByCity(city);
                    } catch (Exception e) {
                        System.out.println("Please Enter Valid Input");
                    }
                    break;
                case 4:
                    try {
                        System.out.println("Enter State Name");
                        Scanner search = new Scanner(System.in);
                        String state = search.nextLine();
                        addressBookMain.viewPersonByState(state);
                    } catch (Exception e) {
                        System.out.println("Please Enter Valid Input");
                    }
                    break;
                case 5:
                    try {
                        System.out.println("Enter City or State to Get Count");
                        Scanner countInput = new Scanner(System.in);
                        String cityOrState = countInput.nextLine();
                        addressBookMain.countContactDetailsByCityOrState(cityOrState);
                    } catch (Exception e) {
                        System.out.println("Please Enter Valid Input");
                    }
                    break;
                case 6:
                    addressBookMain.sortingOperations();
                    break;
                case 7:
                    isExit = true;
                    break;
                default:
                    System.out.println("Please Enter Valid Option");
            }
        }
        addressBookMain.addressBookDic.forEach((key, value) -> System.out.println("Key : "+key + " Value-> " + value));
    }

    /**
     * View Person Details By City Using Stream API
     * @param city City
     */
    private void viewPersonByCity(String city) {
        List<List<ContactDetails>> cityContactDetailsList = new ArrayList<>();
        for (Map.Entry<String,AddressBook> addressBookEntry : addressBookMain.addressBookDic.entrySet()) {
            List<ContactDetails> cityData = addressBookEntry.getValue()
                                                            .getPersonsData()
                                                            .stream()
                                                            .filter(person -> person.getCity().equals(city))
                                                            .collect(Collectors.toList());
            cityContactDetailsList.add(cityData);
        }
        cityContactDetailsDic.put(city, cityContactDetailsList);
        System.out.println(cityContactDetailsDic.toString());
    }

    /**
     * View Person Details by State Using Stream API
     * @param state State
     */
    private void viewPersonByState(String state) {
        List<List<ContactDetails>>stateContactDetailsList = new ArrayList<>();
        for (Map.Entry<String,AddressBook> addressBookEntry : addressBookMain.addressBookDic.entrySet()) {
            List<ContactDetails> stateData = addressBookEntry.getValue()
                                                             .getPersonsData().stream()
                                                             .filter(person -> person.getState().equals(state))
                                                             .collect(Collectors.toList());
            stateContactDetailsList.add(stateData);
        }
        stateContactDetailsDic.put(state, stateContactDetailsList);
        System.out.println(stateContactDetailsDic.toString());
    }

    /**
     * Count Person ContactDetails By City Or State
     * @param cityOrState City Or State
     */
    private void countContactDetailsByCityOrState(String cityOrState) {
            int count = 0;
            for (Map.Entry<String, AddressBook> addressBookEntry : addressBookDic.entrySet()) {
                for (int i = 0; i < (addressBookEntry.getValue()).getPersonsData().size(); i++) {
                    ContactDetails contactDetails = addressBookEntry.getValue().getPersonsData().get(i);
                    if (contactDetails.getCity().equals(cityOrState)||contactDetails.getState().equals(cityOrState)) {
                        count++;
                    }
                }
            }
            System.out.println("Total Number of ContactDetails in  " +cityOrState + " is: " + count);
    }

    /**
     * Sort Operations Method for Different Sorted Methods
     */
    public void sortingOperations(){
        Scanner scanner = new Scanner(System.in);
        boolean isExit = false;
        while(!isExit) {
            System.out.println("Select the Option: \n1.Sort by First Name\n2.Sort by LastName" +
                               "\n3.Sort by City\n4.Sort by State\n5.Sort by ZipCode\n6.Exit");
            int option = scanner.nextInt();
            switch(option) {
                case 1:
                    addressBookMain.sortContactDetailsByFirstName();
                    break;
                case 2:
                    addressBookMain.sortContactDetailsByLastName();
                    break;
                case 3:
                    addressBookMain.sortContactDetailsByCity();
                    break;
                case 4:
                    addressBookMain.sortContactDetailsByState();
                    break;
                case 5:
                    addressBookMain.sortContactDetailsByZipCode();
                    break;
                case 6:
                    isExit = true;
                    System.out.println("Thank you!");
                    break;
                default:
                    System.out.println("Please Enter Valid Option");
            }
        }
    }

    //Sort Contact Details by First Name in Address Book
    public void sortContactDetailsByFirstName() {
        addressBookDic.forEach((key, value) ->
                               System.out.println("Sorted ContactDetails by First Name : "
                                                  + value.getPersonsData()
                                                         .stream()
                                                         .sorted(Comparator.comparing(ContactDetails::getFirstName))
                                                         .collect(Collectors.toList())
                                                 )
                              );
    }

    //Sort Contact Details by Last Name in Address Book
    public void sortContactDetailsByLastName() {
        addressBookDic.forEach((key, value) ->
                               System.out.println("Sorted ContactDetails by Last Name : "
                                                  + value.getPersonsData()
                                                         .stream()
                                                         .sorted(Comparator.comparing(ContactDetails::getLastName))
                                                         .collect(Collectors.toList())
                                                 )
        );
    }

    //Sort Contact Details by City in Address Book
    public void sortContactDetailsByCity() {
        addressBookDic.forEach((key, value) ->
                               System.out.println("Sorted ContactDetails by City : "
                                                  + value.getPersonsData()
                                                         .stream()
                                                         .sorted(Comparator.comparing(ContactDetails::getCity))
                                                         .collect(Collectors.toList())));
    }

    //Sort Contact Details by State in Address Book
    public void sortContactDetailsByState() {
       addressBookDic.forEach((key, value) ->
                              System.out.println("Sorted ContactDetails by State : "
                                                 + value.getPersonsData()
                                                        .stream()
                                                        .sorted(Comparator.comparing(ContactDetails::getState))
                                                        .collect(Collectors.toList())
                                                )
                             );
    }

    //Sort Contact Details by ZipCode in Address Book
    public void sortContactDetailsByZipCode() {
        addressBookDic.forEach((key, value) ->
                               System.out.println("Sorted ContactDetails by ZipCode : "
                                                  + value.getPersonsData()
                                                         .stream()
                                                         .sorted(Comparator.comparing(ContactDetails::getZipCode))
                                                         .collect(Collectors.toList())
                                                 )
                              );
    }
}

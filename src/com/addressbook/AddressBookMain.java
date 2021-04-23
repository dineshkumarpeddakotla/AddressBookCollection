package com.addressbook;

import java.util.*;
import java.util.stream.Collectors;

public class AddressBookMain {
    private final HashMap<String, AddressBook> addressBookDic = new HashMap<>();
    private final HashMap<String, List<List<ContactDetails>>> cityContactDetailsDic = new HashMap<>();
    private final HashMap<String, List<List<ContactDetails>>> stateContactDetailsDic = new HashMap<>();

    static AddressBookMain addressBookMain = new AddressBookMain();

    public static void main(String[] args) {
        System.out.println("Welcome to Address Book");
        boolean isExit = false;
        while (!isExit) {
            System.out.println("1.Create New Address Book\n2.Add Person to AddressBook " +
                               "\n3.View Person by City\n4.View Person by State\n5.Persons Count in City or State\n6.Exit");
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
                    System.out.println("Enter City Name");
                    Scanner searchInput = new Scanner(System.in);
                    String city = searchInput.nextLine();
                    addressBookMain.viewPersonByCity(city);
                    break;
                case 4:
                    System.out.println("Enter State Name");
                    Scanner search = new Scanner(System.in);
                    String state = search.nextLine();
                    addressBookMain.viewPersonByState(state);
                    break;
                case 5:
                    System.out.println("Enter City or State to Get Count");
                    Scanner countInput = new Scanner(System.in);
                    String cityOrState = countInput.nextLine();
                    addressBookMain.countContactDetailsByCityOrState(cityOrState);
                    break;
                default:
                    isExit = true;
                    break;
            }
        }
        addressBookMain.addressBookDic.forEach((key, value) -> System.out.println("Key : "+key + " Value-> " + value));
    }

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
}

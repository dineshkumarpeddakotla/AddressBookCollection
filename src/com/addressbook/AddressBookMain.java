package com.addressbook;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AddressBookMain {
    private final HashMap<String, AddressBook> addressBookDic = new HashMap<>();

    static AddressBookMain addressBookMain = new AddressBookMain();

    public static void main(String[] args) {
        System.out.println("Welcome to Address Book");
        boolean isExit = false;
        while (!isExit) {
            System.out.println("1.Create New Address Book\n2.Add Person to AddressBook " +
                               "\n3.View Person by City\n4.View Person by State\n5.Exit");
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
                    System.out.println("Enter City or State Name");
                    Scanner searchInput = new Scanner(System.in);
                    String cityOrState = searchInput.nextLine();
                    addressBookMain.searchByCityOrState(cityOrState);
                    break;
                default:
                    isExit = true;
                    break;
            }
        }
        addressBookMain.addressBookDic.forEach((key, value) -> System.out.println("Key : "+key + " Value-> " + value));
    }

    public void searchByCityOrState(String cityOrState) {
        for (Map.Entry<String,AddressBook> addressBookEntry : addressBookMain.addressBookDic.entrySet()) {
            List<ContactDetails> cityOrStateData = addressBookEntry.getValue().getPersonsData().stream()
                    .filter(person -> person.getCity().equals(cityOrState) || person.getState().equals(cityOrState)).collect(Collectors.toList());
            System.out.println(cityOrStateData);
        }
    }
}

package com.addressbook;

import java.util.HashMap;
import java.util.Scanner;

public class AddressBookMain {
    public static HashMap<String, AddressBook> addressBookDic = new HashMap<>();

    public static void main(String[] args) {
        System.out.println("Welcome to Address Book");

        boolean isExit = false;
        Scanner scanner = new Scanner(System.in);
        while (!isExit) {
            System.out.println("1.add book\n2.add person\n3.exit");
            int choice = scanner.nextInt();
            System.out.println(choice);
            switch (choice) {
                case 1:
                    System.out.println("Enter address book name");
                    addressBookDic.put(scanner.next(), new AddressBook());
                    System.out.println(addressBookDic.toString());
                    break;
                case 2:
                    System.out.println("Enter address book name to add person");
                    String addressBookName = scanner.next();
                    if (addressBookDic.containsKey(addressBookName)) {
                        AddressBook addressBook = new AddressBook();
                        addressBookDic.put(addressBookName, addressBook);
                        addressBook.addressBookOperations();
                    } else {
                        System.out.println("Address Book is not present");
                    }

                    System.out.println(addressBookDic.toString());
                    break;
                default:
                    isExit = true;
                    break;
            }
        }
        addressBookDic.forEach((key, value) -> System.out.println(key + " -> " + value));
    }
}

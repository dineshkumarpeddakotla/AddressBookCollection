package com.addressbook;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class AddressBookFileIO {
    /**
     * address book file path is declared and initialized
     */
    public static String ADDRESS_BOOK_FILE_PATH = "AddressBook.txt";

    /**
     * created writeDataToAddressBookFIle method to write data into a file
     * added try and catch for exceptions
     * @param addressBookDic addressBookDic where data is present
     */
    public void writeDataToAddressBookFIle(HashMap<String, AddressBook> addressBookDic) {
      StringBuffer addressBookBuffer = new StringBuffer();
      addressBookDic.forEach((key, addressBook) -> {
          String addressBookData = addressBook.toString().concat("\n");
          addressBookBuffer.append(addressBookData);
      } );
      try {
          Files.write(Paths.get(ADDRESS_BOOK_FILE_PATH), addressBookBuffer.toString().getBytes(StandardCharsets.UTF_8) );
      } catch (IOException e) {
          e.printStackTrace();
      }
    }

    /**
     * created readAddressBookData method to read data from a file
     * @return addressBookDic
     */
    public HashMap<String, AddressBook> readAddressBookData() {
        HashMap<String, AddressBook> addressBookDic = new HashMap<>();
        try {
            Files.lines(new File(ADDRESS_BOOK_FILE_PATH).toPath()).map(String::trim).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return addressBookDic;
    }
}

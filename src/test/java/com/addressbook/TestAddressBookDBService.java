/*
 Purpose : Program is written to test handle of Database
 contacts into address_book_service database by AddressBookDBService
 @author Dinesh Kumar Peddakotla
 @version 1.0
 @since 10-05-2021
 */
package com.addressbook;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestAddressBookDBService {

    //when data is retrieved from database should check whether count is match or not and tested using test cases
    @Test
    public void givenContactDetailsInDB_WhenDataRetrieved_ShouldMatchContactDetailsCount() {
        AddressBookDBService addressBookDBService = new AddressBookDBService();
        List<String> contactDetailsList = addressBookDBService.retrieveAddressBookData();
        Assertions.assertEquals(6, contactDetailsList.size());
    }
}

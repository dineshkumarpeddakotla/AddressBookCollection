package com.addressbook;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class AddressBookJsonFileHandler {

    public void writeDataInJasonFile(HashMap<String, AddressBook> addressBookDic)  {
        String FILE_NAME = "PersonsContactDetails.json";
        try(Writer writer = Files.newBufferedWriter(Paths.get(FILE_NAME))) {
            Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                                         .setPrettyPrinting().create();
            String json = gson.toJson(addressBookDic);
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

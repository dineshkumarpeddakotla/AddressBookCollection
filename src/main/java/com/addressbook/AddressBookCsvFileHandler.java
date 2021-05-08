package com.addressbook;

import com.opencsv.CSVWriter;
import com.opencsv.bean.*;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;


public class AddressBookCsvFileHandler {
    private static final String FILE_NAME = "PersonsContactDetails.csv";

    public void writeAddressBookDataInCsv(ArrayList<ContactDetails> contactDetailsList) {
        try (Writer writer = Files.newBufferedWriter(Paths.get(FILE_NAME))){
            CustomMappingStrategy<ContactDetails> mappingStrategy = new CustomMappingStrategy<>();
            StatefulBeanToCsv<ContactDetails> statefulBeanToCsv = new StatefulBeanToCsvBuilder<ContactDetails>(writer)
                                                                      .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                                                                      .withMappingStrategy(mappingStrategy).build();
            System.out.println("in CSVHandler: "+contactDetailsList);
            statefulBeanToCsv.write(contactDetailsList);
        } catch (IOException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException exception) {
            exception.printStackTrace();
        }
    }
}

class CustomMappingStrategy<T> extends ColumnPositionMappingStrategy<T> {
    @Override
    public String[] generateHeader(T bean) throws CsvRequiredFieldEmptyException {

        super.setColumnMapping(new String[ FieldUtils.getAllFields(bean.getClass()).length]);
        final int numColumns = headerIndex.findMaxIndex();
        if (numColumns == -1) {
            return super.generateHeader(bean);
        }

        String[] header = new String[numColumns + 1];


        for (int i = 0; i <= numColumns; i++) {
            BeanField<T,Integer> beanField = findField(i);
            String columnHeaderName = extractHeaderName(beanField);
            header[i] = columnHeaderName;
        }
        return header;
    }

    private String extractHeaderName(final BeanField<T,Integer> beanField) {
        if (beanField == null || beanField.getField() == null ||
                beanField.getField().getDeclaredAnnotationsByType(CsvBindByName.class).length == 0) {
            return StringUtils.EMPTY;
        }

        final CsvBindByName bindByNameAnnotation = beanField.getField()
                .getDeclaredAnnotationsByType(CsvBindByName.class)[0];
        return bindByNameAnnotation.column();
    }
}
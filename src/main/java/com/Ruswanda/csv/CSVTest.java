package com.Ruswanda.csv;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;

public class CSVTest {


    @Test
  public void createCsv() throws IOException {

        StringWriter writer = new StringWriter();
        CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT);
        printer.printRecord("ruswanda", "aja",100);
        printer.printRecord("dika", "zaini",700);
        printer.flush();

        String csv = writer.getBuffer().toString();
        System.out.println(csv);
  }

  @Test
  public void  readCvs() throws IOException {

      Path path = Path.of("sample.csv");
      Reader reader = Files.newBufferedReader(path);

      CSVParser parser = new CSVParser(reader,CSVFormat.DEFAULT);
      for (CSVRecord record : parser){
          System.out.println("firstname : "+ record.get(0));
          System.out.println("midelname : "+ record.get(1));
          System.out.println("value : "+ record.get(2));
      }

  }

    @Test
    public void  readCvsWithHeader() throws IOException {

        Path path = Path.of("sample.csv");
        Reader reader = Files.newBufferedReader(path);

        CSVFormat format = CSVFormat.DEFAULT.builder().setHeader().build();
        CSVParser parser = new CSVParser(reader,format);
        for (CSVRecord record : parser){
            System.out.println("firstname : "+ record.get("FIRST NAME"));
            System.out.println("midelname : "+ record.get("LAST NAME"));
            System.out.println("value : "+ record.get("VALUE"));
        }

    }


    @Test
    public void createCsvWithHeader() throws IOException {

        StringWriter writer = new StringWriter();

        CSVFormat format = CSVFormat.DEFAULT.builder().setHeader("Firstname","LastName", "value").build();
        CSVPrinter printer = new CSVPrinter(writer, format);
        printer.printRecord("ruswanda", "aja",100);
        printer.printRecord("dika", "zaini",700);
        printer.flush();

        String csv = writer.getBuffer().toString();
        System.out.println(csv);
    }

    @Test
    public void createCsvWithTDF() throws IOException {

        StringWriter writer = new StringWriter();

        CSVFormat format = CSVFormat.TDF.builder().setHeader("Firstname","LastName", "value").build();
        CSVPrinter printer = new CSVPrinter(writer, format);
        printer.printRecord("ruswanda", "aja",100);
        printer.printRecord("dika", "zaini",700);
        printer.flush();

        String csv = writer.getBuffer().toString();
        System.out.println(csv);
    }


}

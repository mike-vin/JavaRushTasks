package com.javarush.task.task19.task1904;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Solution {

  public static void main(String[] args) {

  }

  public static class PersonScannerAdapter implements PersonScanner {
    private final Scanner fileScanner;

    public PersonScannerAdapter(Scanner fileScanner) {
      this.fileScanner = fileScanner;
    }

    @Override
    public Person read() throws IOException {
      String str = this.fileScanner.nextLine();
      String s[] = str.split(" ");
      int year, month, day;
      year = Integer.parseInt(s[5]);
      month = Integer.parseInt(s[4]) - 1;
      day = Integer.parseInt(s[3]);
      Date date = (new GregorianCalendar(year, month, day)).getTime();
      return new Person(s[1], s[2], s[0], date);
    }

    @Override
    public void close() throws IOException {
      fileScanner.close();
    }
  }
}
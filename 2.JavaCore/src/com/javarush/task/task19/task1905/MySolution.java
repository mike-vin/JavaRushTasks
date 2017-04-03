package com.javarush.task.task19.task1905;

import java.util.HashMap;
import java.util.Map;

public class MySolution {
  static Map<String, String> countries = new HashMap<String, String>();

  static {
    countries.put("UA", "Ukraine");
    countries.put("RU", "Russia");
    countries.put("CA", "Canada");
  }

  public static void main(String[] args) {
    Solution.DataAdapter dataAdapter = new Solution.DataAdapter(new Solution.Customer() {
      @Override
      public String getCompanyName() {
        return "JavaRush Ltd.";
      }

      @Override
      public String getCountryName() {
        return "Ukraine";
      }
    }, new Solution.Contact() {
      @Override
      public String getName() {
        return "Ivanov, Ivan";
      }

      @Override
      public String getPhoneNumber() {
        return "+38(050)123-45-67";
      }
    });

    System.out.println(dataAdapter.getCountryCode());
    System.out.println(dataAdapter.getCompany());
    System.out.println(dataAdapter.getContactFirstName());
    System.out.println(dataAdapter.getContactLastName());
    System.out.println(dataAdapter.getDialString());
  }

  public static class DataAdapter implements RowItem {
    private Customer customer;
    private Contact contact;

    public DataAdapter(Customer customer, Contact contact) {
      this.customer = customer;
      this.contact = contact;
    }

    @Override
    public String getCountryCode() {
      return countries.entrySet().stream()
          .filter(set -> set.getValue().equalsIgnoreCase(this.customer.getCountryName())).findFirst().get().getKey();
    }

    @Override
    public String getCompany() {
      return this.customer.getCompanyName().trim();
    }

    @Override
    public String getContactFirstName() {
      return this.contact.getName().split(", ")[1].trim();
    }

    @Override
    public String getContactLastName() {
      return this.contact.getName().split(", ")[0].trim();
    }

    @Override
    public String getDialString() {
      return "callto://+" + contact.getPhoneNumber().replaceAll("\\D", "");
    }
  }

  public static interface RowItem {
    String getCountryCode();        //example UA

    String getCompany();            //example JavaRush Ltd.

    String getContactFirstName();   //example Ivan

    String getContactLastName();    //example Ivanov

    String getDialString();         //example callto://+380501234567
  }

  public static interface Customer {
    String getCompanyName();        //example JavaRush Ltd.

    String getCountryName();        //example Ukraine
  }

  public static interface Contact {
    String getName();               //example Ivanov, Ivan

    String getPhoneNumber();        //example +38(050)123-45-67
  }
}
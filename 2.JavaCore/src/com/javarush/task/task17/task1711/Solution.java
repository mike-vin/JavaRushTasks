package com.javarush.task.task17.task1711;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class Solution {
    public static volatile List<Person> allPeople = new ArrayList<Person>();
    static SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    static SimpleDateFormat outFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) throws Exception {

        switch (args[0]) {
            case "-с":
                addNewPeople(args);
                break;
            case "-u":
                updatePeople(args);
                break;
            case "-d":
                deletePeople(args);
                break;
            case "-i":
                infoPeople(args);
                break;
        }
    }

    private static void infoPeople(String[] args) {
        for (int i = 1; i < args.length; i++) {
            System.out.println(allPeople.get(Integer.parseInt(args[i])));
        }
    }

    private static void deletePeople(String[] args) {
        for (int i = 1; i < args.length; i++) {
            Person person = allPeople.get(Integer.parseInt(args[i]));
            person.setName(null);
            person.setSex(null);
            person.setBirthDay(null);
        }
    }

    private static void updatePeople(String[] args) throws ParseException {
        int ID;
        String name;
        Sex sex;
        Date birth;
        for (int i = 1; i < args.length; ) {
            ID = Integer.parseInt(args[i++]);
            name = args[i++];
            sex = args[i++].equals("м") ? Sex.MALE : Sex.FEMALE;
            birth = inputFormat.parse(args[i++]);

            Person person = allPeople.get(ID);
            person.setName(name);
            person.setSex(sex);
            person.setBirthDay(birth);

        }
    }

    private static void addNewPeople(String[] args) throws ParseException {
        String name;
        Date birth;
        for (int i = 1; i < args.length; i += 3) {
            name = args[i];
            birth = inputFormat.parse(args[i + 2]);

            Person person = args[i + 1].equals("м") ?
                    Person.createMale(name, birth) : Person.createFemale(name, birth);
            allPeople.add(person);
            System.out.println(allPeople.size() - 1);
        }
    }
}
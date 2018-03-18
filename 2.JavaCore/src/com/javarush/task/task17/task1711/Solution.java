package com.javarush.task.task17.task1711;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.IntStream;

/* 
CRUD 2
*/

public class Solution {
    public static volatile List<Person> allPeople = new ArrayList<Person>();
    private static final SimpleDateFormat inputFormatter = new SimpleDateFormat("dd/MMM/yyyy", Locale.ENGLISH);

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) throws ParseException {

        switch (args[0]) {

            case "-c":// не проходит ->
                synchronized (allPeople) {
                    for (int i = 1; i < args.length; i += 3) {
                        if (args[i - 1].equalsIgnoreCase("м"))
                            allPeople.add(Person.createMale(args[i - 2], inputFormatter.parse(args[i])));
                        else
                            allPeople.add(Person.createFemale(args[i - 2], inputFormatter.parse(args[i])));
                        System.out.println(allPeople.size() - 1);
                    }
                }
                break;

            case "-u": // не проходит ->
                synchronized (allPeople) {
                    for (int i = 1; i < args.length; i += 4) {
                        if (args[i - 2].equalsIgnoreCase("м"))
                            allPeople.set(Integer.parseInt(args[i - 3]), Person.createMale(args[i - 2], inputFormatter.parse(args[i])));
                        else
                            allPeople.set(Integer.parseInt(args[i - 3]), Person.createFemale(args[i - 2], inputFormatter.parse(args[i])));
                    }
                }
                break;

            case "-d":
                synchronized (allPeople) {
                    IntStream.range(1, args.length).filter(i -> i <= allPeople.size()).mapToObj(i -> Integer.parseInt(args[i])).forEach(i -> {
                        Person person = allPeople.get(i);
                        person.setName(null);
                        person.setSex(null);
                        person.setBirthDay(null);
                    });
                }
                break;

            case "-i":
                synchronized (allPeople) {
                    IntStream.range(1, args.length).map(i -> Integer.parseInt(args[i])).filter(i -> i <= allPeople.size()).mapToObj(i -> allPeople.get(i)).forEach(System.out::println);
                }
                break;
        }
    }
}
/*
-c Миронов м 15/Apr/1990 Миронова ж 25/Apr/1997
-i 0 1 2 3 4
-d 0 1 2 3 4
-u 0 Pedro ж 15/Apr/2020 1 Federico ж 15/Apr/2020
*/
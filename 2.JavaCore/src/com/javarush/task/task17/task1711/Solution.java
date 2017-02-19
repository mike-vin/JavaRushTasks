package com.javarush.task.task17.task1711;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/* 
name — имя, String
sex — пол, «м» или «ж», одна буква
bd — дата рождения в следующем формате 15/04/1990

-с — добавляет всех людей с заданными параметрами в конец allPeople,
выводит id (index) на экран в соответствующем порядке
-u — обновляет соответствующие данные людей с заданными id
-d — производит логическое удаление человека с id, заменяет все его данные на null
-i — выводит на экран информацию о всех людях с заданными id:
name sex bd... id соответствует индексу в списке
*/

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
        System.out.println("People 1 = " + allPeople.get(0));
        System.out.println("People 2 = " + allPeople.get(1));
        System.out.println("allPeople.size = " + allPeople.size());
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

            System.out.println(person);
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
            System.out.println(person);
            System.out.println(allPeople.size() - 1);
        }
    }
}
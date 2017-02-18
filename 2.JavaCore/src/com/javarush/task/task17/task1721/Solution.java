package com.javarush.task.task17.task1721;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {
    public static List<String> allLines = new ArrayList<String>();
    public static List<String> forRemoveLines = new ArrayList<String>();

    public static void main(String[] args) throws Exception {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String line1 = reader.readLine();
            String line2 = reader.readLine();

            reader = new BufferedReader(new FileReader(line1));
            allLines.addAll(reader.lines().collect(Collectors.toList()));


            reader = new BufferedReader(new FileReader(line2));
            forRemoveLines.addAll(reader.lines().collect(Collectors.toList()));

            new Solution().joinData();

            reader.close();
        } catch (Exception e) {
        }
    }

    public void joinData() throws CorruptedDataException {
        if (allLines.containsAll(forRemoveLines)) {
            allLines.removeAll(forRemoveLines);
            //System.out.println("contains All");
            return;
        }
        for (String line : forRemoveLines) {
            if (!allLines.contains(line)) {
                allLines.clear();
                // System.out.println("noContains: " + allLines.size());
                throw new CorruptedDataException();
            }
        }
    }
}
// /home/mike/Рабочий стол/1
// /home/mike/Рабочий стол/2
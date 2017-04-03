package com.javarush.task.task19.task1907;

/* 
Считаем слово
Считать с консоли имя файла.
Файл содержит слова, разделенные знаками препинания.
Вывести в консоль количество слов «world«, которые встречаются в файле.
Закрыть потоки.


Требования:
1. Программа должна считывать имя файла с консоли (используй BufferedReader).
2. BufferedReader для считывания данных с консоли должен быть закрыт.
3. Программа должна считывать содержимое файла (используй FileReader c конструктором String).
4. Поток чтения из файла (FileReader) должен быть закрыт.
5. Программа должна выводить в консоль количество слов "world", которые встречаются в файле.
*/

import java.io.*;

public class Solution {
  public static void main(String[] args) throws Exception {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String fileName = reader.readLine();
    FileReader fileReader = new FileReader(fileName);

    String word = "";
    int worldCounter = 0;

    while (fileReader.ready()) {
      char symbol = (char) fileReader.read();
      if (Character.isLetter(symbol)) word += symbol;
      else {
        if (word.equals("world"))
          worldCounter++;
        word = "";
      }
    }

    System.out.println(worldCounter);
    reader.close();
    fileReader.close();
  }
}
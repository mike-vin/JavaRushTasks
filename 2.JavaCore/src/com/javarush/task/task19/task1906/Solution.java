package com.javarush.task.task19.task1906;

/*Четные байты
Считать с консоли 2 имени файла.
Вывести во второй файл все байты с четным индексом.

Пример:
второй байт, четвертый байт, шестой байт и т.д.

Закрыть потоки ввода-вывода.

Требования:
1. Программа должна считывать имена файлов с консоли (используй BufferedReader).
2. BufferedReader для считывания данных с консоли должен быть закрыт.
3. Программа должна считывать содержимое первого файла (используй FileReader c конструктором String).
4. Поток чтения из файла (FileReader) должен быть закрыт.
5. Программа должна записывать во второй файл все байты из первого файла с четным индексом (используй FileWriter).
6. Поток записи в файл (FileWriter) должен быть закрыт.*/


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;

public class Solution {
  public static void main(String[] args) throws Exception {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String firstFile = reader.readLine();
    String secondFile = reader.readLine();
    reader.close();
    FileReader fileReader = new FileReader(firstFile);
    FileWriter fileWriter = new FileWriter(secondFile);
    int byteCounter = 0;
    while (fileReader.ready()) {
      int c = fileReader.read();
      byteCounter++;
      if (byteCounter % 2 == 0)
        fileWriter.write(c);
    }
    fileReader.close();
    fileWriter.close();
  }
}

package java_io.optional_tasks;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Solution1 {
    static List<Integer> sortedList = new ArrayList<>();

    public static void main(String[] args) {
        List<Integer> randomList = new ArrayList<>();
        Random random = new Random();
        String fileName = "output.txt";

        for (int i = 0; i < 100; i++) {
            randomList.add(random.nextInt(100));
        }

        writeToFile(fileName, randomList);

        sortNumericFileContents(readFromFile(fileName));

        writeToFile(fileName, sortedList);
    }

    public static void writeToFile(String fileName, List<Integer> valueList) {
        try (FileWriter writer = new FileWriter(fileName)) {
            for (Integer value : valueList) {
                writer.write(value + " ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readFromFile(String fileName) {
        StringBuilder fileContents = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while (reader.ready()) {
                fileContents.append(reader.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileContents.toString();
    }

    public static void sortNumericFileContents(String fileContents) {
        String[] array = fileContents.split(" ");

        for (String value : array) {
            sortedList.add(Integer.parseInt(value));
        }

        Collections.sort(sortedList);
    }


}

package java_io.optional_tasks;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

private class Solution1 {
    static List<Integer> sortedList = new ArrayList<>();

    private static void main(String[] args) {
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

    private static void writeToFile(String fileName, List<Integer> valueList) {
        try (FileWriter writer = new FileWriter(fileName)) {
            for (Integer value : valueList) {
                writer.write(value + " ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String readFromFile(String fileName) {
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

    private static void sortNumericFileContents(String fileContents) {
        String[] array = fileContents.split(" ");

        for (String value : array) {
            sortedList.add(Integer.parseInt(value));
        }

        Collections.sort(sortedList);
    }


}

package java_io.optional_tasks;

import java.io.*;

public class Solution5 {
    public static void main(String[] args) {
        String separator = File.separator;
        String fileName = "testFiles" + separator +"Solution5Files" + separator + "studentList" +
                ".txt";
        StringBuilder editedFileContents = new StringBuilder();
        String[] lineArray;

        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            while (reader.ready()) {
                lineArray = reader.readLine().split(" ");
                int gradeCount = 0;
                int gradeSum = 0;

                for (String value : lineArray) {
                    if (valueIsNumeric(value)) {
                        gradeCount++;
                        gradeSum += Integer.parseInt(value);
                    }
                }

                if (gradeCount > 0 && gradeSum > 0) {
                    if ((double) gradeSum / (double) gradeCount > 7) {
                        lineArray[1] = lineArray[1].toUpperCase();
                    }
                }

                for (int i = 0; i < lineArray.length; i++) {
                    if (i < lineArray.length - 1) {
                        editedFileContents.append(lineArray[i]).append(" ");
                    } else {
                        editedFileContents.append(lineArray[i]).append("\n");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(editedFileContents.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean valueIsNumeric(String value) {
        try {
           Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}

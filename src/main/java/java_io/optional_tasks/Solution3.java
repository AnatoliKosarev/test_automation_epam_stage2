package java_io.optional_tasks;

import java.io.*;

public class Solution3 {
    public static void main(String[] args) {

        try (BufferedInputStream reader = new BufferedInputStream(new FileInputStream("testFiles"
                + File.separator + "Solution3Files" + File.separator + "inputFile.java "));
             BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream("testFiles"
                     + File.separator + "Solution3Files" + File.separator + "outputFile.java ", true))) {

            byte[] buffer = new byte[reader.available()];

            while (reader.available() > 0) {
                int count = reader.read(buffer);
            }

            writer.write(reverseByteArray(buffer));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static byte[] reverseByteArray(byte[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            byte temp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = temp;
        }
        return array;
    }
}

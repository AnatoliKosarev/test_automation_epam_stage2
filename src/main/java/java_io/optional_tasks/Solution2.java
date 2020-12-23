package java_io.optional_tasks;

import java.io.*;

public class Solution2 {
    public static void main(String[] args) {
        String line;

        try(BufferedReader reader = new BufferedReader(new FileReader("testFiles" +
                File.separator + "Solution2Files" + File.separator + "inputFile.java "));
            BufferedWriter writer = new BufferedWriter(new FileWriter("testFiles" +
                    File.separator + "Solution2Files" + File.separator + "outputFile.java ", true))) {

            while (reader.ready()) {
                line = reader.readLine();
                if (line.contains("public")) {
                    writer.write(line.replace("public", "private") + "\n");
                } else {
                    writer.write(line + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

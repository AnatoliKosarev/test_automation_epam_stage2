package java_io.optional_tasks;

import java.io.*;

public class Solution2 {
    public static void main(String[] args) {
        StringBuilder editedFileContents = new StringBuilder();
        String fileName = "testFiles" + File.separator + "Solution2Files" + File.separator
                + "testFile.java";

        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            while (reader.ready()) {
                String line = reader.readLine();

                if (line.contains("public")) {
                    editedFileContents.append(line.replace("public", "private"));
                } else {
                    editedFileContents.append(line);
                }
                editedFileContents.append("\n");
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
}

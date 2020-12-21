package java_io.main_task;

import java.io.*;
import java.util.Objects;

public class FileStructureHandler {
    private final String separator = File.separator;
    private final String filePrefix = "|\t";
    private final String directoryPrefix = "|-----";
    private final String lineDelimiter = "|";
    private FileWriter fileWriter = null;
    private BufferedReader fileReader = null;
    private StringBuilder formattedInputValue;
    private int fileCount = 0;
    private int directoryCount = 0;
    private int totalFileNameLength = 0;
    private double filesInDirectoryAverage;
    private double fileNameLengthAverage = 0;

    public String readConsoleInput() {
        try (BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))) {
            formatConsoleInput(consoleReader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return formattedInputValue.toString();
    }

    public void formatConsoleInput(String notFormattedInputValue) {
        String[] inputValueArray = notFormattedInputValue.split("/|\\\\");
        formattedInputValue = new StringBuilder(inputValueArray.length);

        for (int i = 0; i < inputValueArray.length; i++) {
            if (i != (inputValueArray.length - 1)) {
                formattedInputValue.append(inputValueArray[i]).append(separator);
            } else {
                formattedInputValue.append(inputValueArray[i]);
            }
        }
    }

    public void executeFileStructureReader(String path) {
        File queryEntity = new File(path);

        if (queryEntity.isDirectory()) {
            try {
                fileWriter = new FileWriter(path + separator + "output.txt", true);
            } catch (IOException e) {
                e.printStackTrace();
            }
            writeToFile(queryEntity.getName());
            writeDirectoryContents(queryEntity);
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } else if (queryEntity.isFile()) {
            try {
                fileReader = new BufferedReader(new FileReader(path));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            readFileContents();
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("Invalid entry.");
        }
    }

    public void writeDirectoryContents(File directory) {
        String[] directoryArray = directory.list();

        assert directoryArray != null;
        for (String directoryElementName : directoryArray) {
            File directoryElement =
                    new File(directory.toString() + separator + directoryElementName);

            checkIfEntityFileOrDirectory(directoryElement);
            if (directoryElementName.equals(directoryArray[directoryArray.length - 1])) {
                writeToFile(lineDelimiter);
            }
        }
    }

    public void checkIfEntityFileOrDirectory(File entity) {
        if (entity.isDirectory()) {
            if (Objects.requireNonNull(entity.list()).length == 0) {
                writeToFile(directoryPrefix + entity.getName());
                writeToFile(lineDelimiter);
            } else {
                writeToFile(directoryPrefix + entity.getName());
                writeDirectoryContents(entity);
            }
        } else if (entity.isFile()) {
            writeToFile(filePrefix + entity.getName());
        }
    }

    public void writeToFile(String value) {
        if (fileWriter != null) {
            try {
                fileWriter.write(value + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void readFileContents() {
        String line;

        if (fileReader != null) {
            try {
                while (fileReader.ready()) {
                    line = fileReader.readLine();
                    calculateFileLineData(line);
                }
                prepareFileData();
                printFileData();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void calculateFileLineData(String line) {
        if (line.startsWith(filePrefix)) {
            fileCount++;
            totalFileNameLength += line.substring(filePrefix.length()).length();
        } else if (line.startsWith(directoryPrefix) || !(line.startsWith(lineDelimiter))) {
            directoryCount++;
        }
    }

    public void prepareFileData() {
        if (directoryCount != 0 && fileCount != 0) {
            filesInDirectoryAverage = (double) fileCount / (double) directoryCount;
            fileNameLengthAverage = (double) totalFileNameLength / (double) fileCount;
        }
    }

    public void printFileData() {
        System.out.printf("Directory quantity, including main directory: %d;%nFile quantity: %d;" +
                "%nAverage quantity of " + "files in directory: %.2f;%nAverage file name length: " +
                "%.2f.%n", directoryCount, fileCount, filesInDirectoryAverage, fileNameLengthAverage);
    }
}

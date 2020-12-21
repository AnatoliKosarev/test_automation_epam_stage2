package java_io.main_task;

public class FileStructureRunner {
    public static void main(String[] args) {
        FileStructureHandler handler = new FileStructureHandler();

        String inputValue = handler.readConsoleInput();

        handler.executeFileStructureReader(inputValue);
    }
}

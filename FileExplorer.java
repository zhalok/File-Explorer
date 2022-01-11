import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.crypto.AEADBadTagException;

public class FileExplorer {
    // ArrayList dir_stack = new ArrayList<String>();

    void print_files_and_directories(String[] contents) {
        for (int i = 0; i < contents.length; i++) {
            System.out.println(contents[i]);
        }
    }

    void Explorer(String Direcotry) {

        try {
            File directoryPath = new File(Direcotry);
            String contents[] = directoryPath.list();
            System.out.println("Inside the " + Direcotry);
            print_files_and_directories(contents);

        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    String create_path(String directory, ArrayList<String> dir_stack) {
        String path = "/";

        for (int i = 0; i < dir_stack.size(); i++) {
            path += dir_stack.get(i);
            path += '/';
        }
        path += directory;
        return path;
    }

    void show_root() {
        String path = "/home";
        Explorer(path);

    }

    public static void main(String args[]) throws IOException {

        FileExplorer fileExplorer = new FileExplorer();
        ArrayList<String> dir_stack = new ArrayList<String>();
        dir_stack.add("home");
        fileExplorer.show_root();

        while (true) {
            System.out.println("Please select a directory");

            Scanner scanner = new Scanner(System.in);

            String directory = scanner.nextLine();
            if (directory.matches("quit")) {
                scanner.close();
                break;
            }
            String path = fileExplorer.create_path(directory, dir_stack);

            dir_stack.add(directory);
            // scanner.close();
            fileExplorer.Explorer(path);
        }

    }
}
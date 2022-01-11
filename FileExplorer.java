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
            System.out.println("Please give your desired command");
            Scanner scan_command = new Scanner(System.in);
            Scanner scan_parameter = new Scanner(System.in);
            String command = scan_command.nextLine();
            command = command.trim();
            if (command.matches("go to")) {
                System.out.println("Please select a directory");
                String directory = scan_parameter.nextLine();

                String path = fileExplorer.create_path(directory, dir_stack);

                dir_stack.add(directory);
                // scanner.close();
                fileExplorer.Explorer(path);
            } else if (command.matches("go back")) {
                int last_idx = dir_stack.size();
                if (last_idx == 1) {
                    System.out.println("You are at the root");
                } else {
                    dir_stack.remove(last_idx - 1);
                    String path = fileExplorer.create_path("", dir_stack);
                    fileExplorer.Explorer(path);
                }

            }

            else if (command.matches("quit")) {
                System.out.println("Good bye");
                scan_command.close();
                scan_parameter.close();
                break;
            }

        }

    }
}
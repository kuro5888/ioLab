import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileInputStream {


    void printFile()
    {
        System.out.println("File contents:");

        try (FileReader fileReader = new FileReader("README.txt");
             BufferedReader reader = new BufferedReader(fileReader)) {

            // When there are no more lines, readLine() return null.
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                //Check if it's valid (not # or blank line)
                if(line.length() == 0 || line.charAt(0) == '#')
                {  }
                else
                {
                    takeAction(line);
                }
                //If it is, send line to a switch method
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void takeAction(String action) {
        String[] wordSplit = action.split(" ", 2);
        switch(wordSplit[0]) {
            case "CREATE":
                create(wordSplit[1]);
                break;
            case "APPEND":
                append(wordSplit[1]);
                break;
            case "DELETE":
                delete(wordSplit[1]);
                break;
            case "COPY":
                copy(wordSplit[1]);
                break;
            default:
                // code block
        }
    }
//elements.data
    public void create(String fileName) {
        File file = new File(fileName);
        try {
            if (file.createNewFile()) {
                System.out.println(fileName + " created.");
            } else {
                System.out.println(fileName + " already exists.");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void append(String input) {
        String[] anotherSplit = input.split(" ", 2);
        String fileName = anotherSplit[0];
        String content = anotherSplit[1];
        try (FileWriter fileWriter = new FileWriter(fileName, true);
             PrintWriter writer = new PrintWriter(fileWriter)) {
             writer.println(content);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public void delete(String fileName) {
        File file = new File(fileName);
        boolean success = file.delete();
        if (success) {
            System.out.println(fileName + " was deleted.");
        } else {
            System.out.println(fileName + " was NOT deleted.");
        }
    }

    public void copy(String input){
        String[] anotherSplit = input.split(" ", 2);
        Path source = Paths.get(anotherSplit[0]);
        Path destination = Paths.get(anotherSplit[1]);
        if (anotherSplit.length == 2) {
            try{Files.createDirectories(destination.getParent());
            String content = Files.readString(source);
            Files.writeString(destination, content, StandardOpenOption.CREATE, StandardOpenOption.APPEND);}
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
    }



}

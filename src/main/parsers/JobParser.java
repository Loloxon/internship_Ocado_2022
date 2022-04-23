package main.parsers;

import main.assistants.Cords;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class JobParser {
    private Cords start;
    private Cords end;
    private String name;
    public JobParser(String filename){
        try {
            File file = new File("src\\resources\\" + filename);
            Scanner sc = new Scanner(file);

            String input = sc.nextLine();
            String[] numbers = input.split(" ");
            start = new Cords(Integer.parseInt(numbers[0]),Integer.parseInt(numbers[1]));

            input = sc.nextLine();
            numbers = input.split(" ");
            end = new Cords(Integer.parseInt(numbers[0]),Integer.parseInt(numbers[1]));

            name = sc.nextLine();
        }
        catch (FileNotFoundException e) {
            System.err.println("'Job' file does not exist");
            System.exit(4);
        }
    }
    public Cords getStart() {
        return start;
    }
    public Cords getEnd() {
        return end;
    }
    public String getName() {
        return name;
    }
}

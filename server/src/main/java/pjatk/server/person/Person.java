package pjatk.server.person;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Person {
    private static Random random = new Random();
    private static ArrayList<String> names = readFile("names.txt");
    private static ArrayList<String> surnames = readFile("surnames.txt");

    private static ArrayList<String> readFile(String path) {
        ArrayList<String> out = new ArrayList<>();
        try {

            File file = new File(Person.class.getClassLoader().getResource(path).getFile());
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) out.add(scanner.next());
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out;
    }

    private static String chooseRandom(ArrayList<String> strings) {
        return strings.get(random.nextInt(strings.size()));
    }

    private String name;
    private String surname;

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
    private int leavesAt;

    public int leavesAt() {
        return leavesAt;
    }

    public String toString() {
        return name + " " + surname;
    }

    public Person(int leavesAt) {
        this.name = chooseRandom(names);
        this.surname = chooseRandom(surnames);
        this.leavesAt=leavesAt;
    }
}

package pjatk.server;

import pjatk.server.person.Person;
import pjatk.server.railway.Line;
import pjatk.server.train.Train;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Simulation {

    private final int trainCount;
    private final int cartsPerTrain;
    private final int peoplePerCart;

    private static Random random = new Random();

    private HashMap<Long, Train> trains = new HashMap<>();
    private Line line = Line.getDefault();

    public static Simulation getFromConfig() throws FileNotFoundException {
        int x, y, z;
        File file = new File(Person.class.getClassLoader().getResource("config.txt").getFile());
        Scanner scanner = new Scanner(file);
        x = scanner.nextInt();
        y = scanner.nextInt();
        z = scanner.nextInt();
        return new Simulation(x, y, z);
    }

    public Simulation(int trainCount, int cartsPerTrain, int peoplePerCart) {
        this.trainCount = trainCount;
        this.cartsPerTrain = cartsPerTrain;
        this.peoplePerCart = peoplePerCart;
        for (int i = 0; i < trainCount; i++) {
            Train train = new Train(cartsPerTrain, peoplePerCart);
            trains.put(train.getId(), train);
            train.putOnLine(line, random.nextInt(line.getLength()) , random.nextBoolean());
        }
    }

    public void move() {
        trains.forEach((id, train) -> train.move());
    }

    public ArrayList<Long> getTrainIDs() {
        ArrayList<Long> ids = new ArrayList<>();
        trains.forEach((id, train) -> {
            ids.add(id);
        });
        return ids;
    }

    public Train getTrain(Long id) {
        return trains.get(id);
    }

    public HashMap<Long, Train> getTrains() {
        return trains;
    }

    public ArrayList<ArrayList<Person>> getPassengers(Long id) {
        ArrayList<ArrayList<Person>> out = new ArrayList<>();
        getTrain(id).getCarts().forEach((cart) -> {
            ArrayList<Person> peopleInCart = new ArrayList<>();
            peopleInCart.addAll(cart.getPeople());
            out.add(peopleInCart);
        });
        return out;
    }
}

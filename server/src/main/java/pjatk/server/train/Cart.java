package pjatk.server.train;

import pjatk.server.person.Person;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Cart {
    private int capacity;
    private final List<Person> people;
    private Train train;

    public int getCapacity() {
        return capacity;
    }

    public List<Person> getPeople() {
        return people;
    }

    Cart(Train train, int capacity) {
        this.train = train;
        this.capacity = capacity;
        people = new ArrayList<>(capacity);
    }

    public String toString(){
        StringBuilder out = new StringBuilder();
        for (Person person : people) {
            out.append(person+"\n");
        }
        return out.toString();
    }

    public boolean letIn(Person person) {
        if (people.size() < capacity) {
            people.add(person);
            return true;
        } else return false;
    }

    private void letOut(Person person) {
        people.remove(person);
    }

    public void passengersOut() {
        int currentIndex = train.getStationIndex();
        HashSet<Person> leaving = new HashSet<>();
        for (Person person : people) {
            if (person.leavesAt() == currentIndex) leaving.add(person);
        }
        for (Person person : leaving) letOut(person);
    }
}

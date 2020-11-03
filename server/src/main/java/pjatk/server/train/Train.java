package pjatk.server.train;

import pjatk.server.person.Person;
import pjatk.server.railway.Line;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Train {
    private static Random random = new Random();
    private static long idCounter = 0;

    private final long id;
    private List<Cart> carts;

    public long getId() {
        return id;
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public boolean isHeadingForwards() {
        return headingForwards;
    }

    private int stationIndex;
    private Line line;
    private boolean headingForwards;
    private int coolDown;

    public int getStationIndex() {
        return stationIndex;
    }

    public Train(int cartCount, int peoplePerCart) {
        this.id = idCounter++;
        this.coolDown = 0;
        this.carts = new ArrayList<>(cartCount);
        for (int i = 0; i < cartCount; i++) carts.add(new Cart(this, peoplePerCart));
    }

    public String getStation(){
        return line.getStation(stationIndex).toString();
    }

    public ArrayList<Double> getUsage(){
        ArrayList<Double> out = new ArrayList<>();
        carts.forEach(cart->{
            out.add((double) cart.getPeople().size() / cart.getCapacity());
        });
        return out;
    }

    public String toString(){
        StringBuilder out = new StringBuilder();
        out.append(line.getStation(stationIndex)+"\n");
        int i=0;
        for (Cart cart : carts) {
            out.append("CART "+i+":\n"+cart);
            i++;
        }
        return out.toString();
    }

    public void move() {
        if (coolDown > 0) {
            coolDown--;
            return;
        }

        passengersIn();
        if (headingForwards) stationIndex++;
        else stationIndex--;
        passengersOut();

        if (endOfTheLine()) {
            headingForwards = !headingForwards;
            coolDown = 2;
        }
    }

    public void putOnLine(Line line, int stationIndex, boolean headingForwards) {
        this.line = line;
        this.stationIndex = stationIndex;
        this.headingForwards = headingForwards;
        if (endOfTheLine()) this.headingForwards = !this.headingForwards;
    }

    private boolean endOfTheLine() {
        return ((!headingForwards && stationIndex == 0) || (headingForwards && stationIndex == line.getLength() - 1));
    }

    private void passengersOut() {
        for (Cart cart : carts) {
            cart.passengersOut();
        }
    }

    private void passengersIn() {
        int newPassengerCount = random.nextInt(7)+2;
        for(int i=0;i<newPassengerCount;i++){
            int leavesAt;
            if(headingForwards) leavesAt=random.nextInt(line.getLength()-stationIndex-1)+stationIndex+1;
            else                leavesAt=random.nextInt(stationIndex);
            Person person = new Person(leavesAt);
            for(int j=0;j<10;j++){
                if(carts.get(random.nextInt(carts.size())).letIn(person))break;
            }
        }
    }
}

package pjatk.server;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pjatk.server.person.Person;
import pjatk.server.train.Train;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;

@RestController
public class Controller {
    private static Simulation simulation;

    static { try { simulation = Simulation.getFromConfig();} catch (FileNotFoundException e) {} }

    @RequestMapping("/move")
    void move(){
        simulation.move();
    }

    @RequestMapping("/trains")
    Collection<Train> trainIDs(){
        return simulation.getTrains().values();
    }

    @RequestMapping("/peopleInTrain")
    ArrayList<ArrayList<Person>> people(Long id){
        return simulation.getPassengers(id);
    }
}

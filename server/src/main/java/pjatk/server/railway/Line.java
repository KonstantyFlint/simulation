package pjatk.server.railway;

import java.util.ArrayList;

public class Line {
    private ArrayList<Station> stations;

    public int getLength(){
        return stations.size();
    }

    public Station getStation(int index){
        return stations.get(index);
    }

    public Line(ArrayList<Station> stations){
        this.stations=stations;
    }

    public static Line getDefault(){
        String[] names = {
                "GDANSK GLOWNY",
                "GDANSK STOCZNIA",
                "GDANSK POLITECHNIKA",
                "GDANSK WRZESZCZ",
                "GDANSK ZASPA",
                "GDANSK PRZYMORZE-UNIWERSYTET",
                "GDANSK OLIWA",
                "GDANSK ZABIANKA-AWFIS",
                "SOPOT WYSCIGI",
                "SOPOT",
                "SOPOT KAMIENNY POTOK",
                "GDYNIA ORLOWO",
                "GDYNIA WZGORZE SW.MAKSYMILIANA",
                "GDYNIA GLOWNA"
        };
        ArrayList<Station> stations = new ArrayList<>();
        for (String name : names)stations.add(new Station(name));
        return new Line(stations);
    }
}
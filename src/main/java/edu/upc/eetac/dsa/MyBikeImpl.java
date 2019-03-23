package edu.upc.eetac.dsa;

import org.apache.log4j.Logger;

import java.util.*;

public class MyBikeImpl implements MyBike {

    //Logger
    final static Logger log = Logger.getLogger(MyBikeImpl.class.getName());

    //Facade
    private static MyBikeImpl instance;
    private int numStations;
    private Station[] stations;
    private HashMap<String, User> users;

    //Private constructor
    private MyBikeImpl() {
        this.numStations = 0;
        this.stations = new Station[S];
        this.users = new HashMap<String, User>();
    }

    //GetInstance Method
    public static MyBike getInstance() {
        if (instance == null) instance = new MyBikeImpl();
        return instance;
    }

    public void addUser(String idUser, String name, String surname) {
        this.users.put(idUser, new User(idUser, name, surname));
        log.info(users.size());
        log.info("edu.upc.eetac.dsa.User added:" + this.users.get(idUser));
    }

    public void addStation(String idStation, String description, int max, double lat, double lon) {
        if (numStations != S) {
            this.stations[this.numStations++] = new Station(idStation, description, max, lat, lon);
            log.info("edu.upc.eetac.dsa.Station added: " + this.stations[numStations - 1]);
        } else {
            log.info("Maximum stations added");
        }
    }

    public void addBike(String idBike, String description, double kms, String idStation) throws StationFullException, StationNotFoundException {
        Bike bike = new Bike(idBike, description, kms, idStation);
        int stationPos = this.getStationById(idStation);
        log.info("edu.upc.eetac.dsa.Station found");
        if (this.stations[stationPos].getBikes().size() < this.stations[stationPos].getMax()) {
            this.stations[stationPos].addBike(bike);
            log.info("edu.upc.eetac.dsa.Bike added");
        } else {
            log.info("edu.upc.eetac.dsa.Station full");
            throw new StationFullException();
        }
    }

    public List<Bike> bikesByStationOrderByKms(String idStation) throws StationNotFoundException {
        int stationPos = this.getStationById(idStation);
        log.info("edu.upc.eetac.dsa.Station found");
        LinkedList<Bike> bikes = this.stations[stationPos].getBikes();
        log.info("List of bikes without order: " + bikes);
        Collections.sort(bikes, new Comparator<Bike>() {
            public int compare(Bike o1, Bike o2) {
                return (int) (o1.getKm() - o2.getKm());
            }
        });
        log.info("List of bikes ordered: " + bikes);
        return bikes;
    }

    public Bike getBike(String stationId, String userId) throws UserNotFoundException, StationNotFoundException {
        int stationPos = this.getStationById(stationId);
        log.info("edu.upc.eetac.dsa.Station found");
        Bike bike = this.stations[stationPos].getBikes().removeFirst();
        User user = this.users.get(userId);
        if (user != null) {
            log.info("First bike: " + bike);
            user.addBike(bike);
            return bike;
        } else {
            log.info("edu.upc.eetac.dsa.User not found");
            throw new UserNotFoundException();
        }
    }

    public List<Bike> bikesByUser(String userId) throws UserNotFoundException {
        User user = this.users.get(userId);
        if (user != null) {
            LinkedList<Bike> bikes = user.getBikes();
            log.info("List of bikes of " + userId + ": " + bikes);
            return bikes;
        } else {
            log.info("edu.upc.eetac.dsa.User not found");
            throw new UserNotFoundException();
        }
    }

    public int numUsers() {
        log.info("Number of users: " + this.users.size());
        return this.users.size();
    }

    public int numStations() {
        log.info("Number of stations: " + this.numStations);
        return this.numStations;
    }

    public int numBikes(String idStation) throws StationNotFoundException {
        int stationPos = this.getStationById(idStation);
        log.info("edu.upc.eetac.dsa.Station found");
        log.info("Number of bikes: " + this.stations[stationPos].getBikes().size());
        return this.stations[stationPos].getBikes().size();
    }

    public void clear() {
        this.numStations = 0;
        this.stations = new Station[S];
        this.users = new HashMap<String, User>();
        log.info("Data cleared");
    }

    private int getStationById(String stationId) throws StationNotFoundException {
        for (int i = 0; i < this.numStations; i++) {
            if (stationId.equals(this.stations[i].getidStation())) return i;
        }
        log.info("edu.upc.eetac.dsa.Station not found");
        throw new StationNotFoundException();
    }
}

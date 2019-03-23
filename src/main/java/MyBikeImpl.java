import java.util.List;

public class MyBikeImpl implements MyBike {
    private static MyBikeImpl ourInstance = new MyBikeImpl();

    public static MyBikeImpl getInstance() {
        return ourInstance;
    }

    private MyBikeImpl() {
    }

    public void addUser(String idUser, String name, String surname) {

    }

    public void addStation(String idStation, String description, int max, double lat, double lon) {

    }

    public void addBike(String idBike, String description, double kms, String idStation) throws StationFullException, StationNotFoundException {

    }

    public List<Bike> bikesByStationOrderByKms(String idStation) throws StationNotFoundException {
        return null;
    }

    public Bike getBike(String stationId, String userId) throws UserNotFoundException, StationNotFoundException {
        return null;
    }

    public List<Bike> bikesByUser(String userId) throws UserNotFoundException {
        return null;
    }

    public int numUsers() {
        return 0;
    }

    public int numStations() {
        return 0;
    }

    public int numBikes(String idStation) throws StationNotFoundException {
        return 0;
    }

    public void clear() {

    }
}

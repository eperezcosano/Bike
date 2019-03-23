import java.util.LinkedList;

public class Station {

    //Attributes
    private String id;
    private String description;
    private int max;
    private double lat;
    private double lon;
    private LinkedList<Bike> bikes;

    //Constructors
    public Station() {
        this.bikes = new LinkedList<Bike>();
    }

    public Station(String id, String description, int max, double lat, double lon) {
        this.id = id;
        this.description = description;
        this.max = max;
        this.lat = lat;
        this.lon = lon;
        this.bikes = new LinkedList<Bike>();
    }

    //Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public LinkedList<Bike> getBikes() {
        return bikes;
    }

    public void setBikes(LinkedList<Bike> bikes) {
        this.bikes = bikes;
    }

    //Methods
    public void addBike(Bike bike) {
        this.bikes.add(bike);
    }
}

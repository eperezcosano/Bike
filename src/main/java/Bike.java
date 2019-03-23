public class Bike {

    //Attributes
    private String id;
    private String description;
    private double km;
    private String idStation;

    //Constructors
    public Bike() {

    }

    public Bike(String id, String description, double km, String idStation) {
        this.id = id;
        this.description = description;
        this.km = km;
        this.idStation = idStation;
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

    public double getKm() {
        return km;
    }

    public void setKm(double km) {
        this.km = km;
    }

    public String getIdStation() {
        return idStation;
    }

    public void setIdStation(String idStation) {
        this.idStation = idStation;
    }
}

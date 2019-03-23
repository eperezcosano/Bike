import java.util.LinkedList;

public class User {

    //Attributes
    private String idUser;
    private String name;
    private String surname;
    private LinkedList<Bike> bikes;

    //Constructors
    public User() {
        this.bikes = new LinkedList<Bike>();
    }

    public User(String idUser, String name, String surname) {
        this.idUser = idUser;
        this.name = name;
        this.surname = surname;
        this.bikes = new LinkedList<Bike>();
    }

    //Getters and Setters
    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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

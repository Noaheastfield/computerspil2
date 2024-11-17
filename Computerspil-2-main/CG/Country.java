import java.util.*;

/**
 * Country class represents a country with a network of cities and roads.
 * @author Simon Hesselberg & Noah Østfeldt
 * @version 09-11-2024
 */
public class Country {
    private String name;
    private Map<City, Set<Road>> network;
    private Game game;

    /**
     * Constructor for the Country class.
     * @param name - the name of the country
     */
    public Country(String name) {
        this.name = name;
        network = new TreeMap<>();
    }

    /**
     * Returns the name of the country.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns a set of all cities in the country.
     * @return - a set of all cities in the country
     */
    public Set<City> getCities() {
        return network.keySet();
    }

    /**
     * Returns a city with the given name.
     * If no city with the given name exists, the method returns null.
     * @param name - the name of the city
     * @return - the city with the given name
     */
    public City getCity(String name){
        for(City c : getCities()){
            if(c.getName().equals(name)){
                return c;
            }
        }
        return null;
    }

    /**
     * Returns a set of all roads in the country.
     * @return - a set of all roads in the country
     */
    public Set<Road> getRoads(City c){
        if(network.containsKey(c)){
            return network.get(c);
        }
        return new TreeSet<>();
    }

    /**
     * Returns the game object.
     * @return game - the game object
     */
    public Game getGame(){
        return game;
    }

    /**
     * Sets the game object.
     * @param game - the game to be set
     */
    public void setGame(Game game){
        this.game = game;
    }

    /**
     * Resets all cities in the country.
     */
    public void reset(){
        for(City c : getCities()){
            c.reset();
        }
    }

    /**
     * Returns a random number between 0 and the value parameter.
     * If the value is less than or equal to 0, the method returns 0.
     * @param value - the maximum value of the random number
     * @return a random number between 0 and the value parameter
     */
    public int bonus(int value){
        Random random = game.getRandom();
        if(value > 0){
            return random.nextInt(value+1);
        }
        return 0;
    }

    /**
     * Adds a city to the network. If the city already exists, it is not added.
     * @param c - the city to be added
     */
    public void addCity(City c){
        network.put(c, new TreeSet<>());
    }

    /**
     * Adds a road between two cities with a given length.
     * If the length is less than or equal to 0 or the cities are the same, the road is not added.
     * @param a - the cities to connect
     * @param b - the cities to connect
     * @param length - the length of the road
     */
    public void addRoads(City a, City b, int length){
        if(length <= 0 || a.equals(b)){
            return;
        }
        if(network.containsKey(a)){
            network.get(a).add(new Road(a, b, length));
        }
        if(network.containsKey(b)){
            network.get(b).add(new Road(b, a, length));
        }
    }

    /**
     * Returns a position object with the city as both from and to and a length of 0.
     * @param city - the city to start from
     * @return - a position object
     */
    public Position position(City city){
        return new Position(city, city, 0);
    }

    /**
     * Returns a position object that represents if the player is ready to travel from one city to another.
     * The length is the length of the road between the cities.
     * If the cities are the same, the length is 0.
     * If the cities are not connected, the length is 0.
     * @param from - the city to start from
     * @param to - the city to travel to
     * @return - a position object
     */
    public Position readyToTravel(City from, City to){
        if(from.equals(to)){
            return new Position(from, to, 0);
        }
        if(!network.containsKey(from)){
            return position(from);
        }
        for(Road r : network.get(from)){
            if(r.getTo().equals(to)){
                return new Position(from, to, r.getLength());
            }
        }
        return position(from);
    }

    /**
     * Overrides the toString method for the country class.
     * @return a string representation of the country name
     */
    @Override
    public String toString(){
        return name;
    }

    /**
     * Overrides the equals method for the country class.
     * A country is equal to another country if the name of the country is equal.
     * @param otherObject - the object to be compared.
     * @return a boolean value based on the comparison.
     */
    @Override
    public boolean equals(Object otherObject){
        if(this == otherObject){
            return true;
        }
        if(otherObject == null){
            return false;
        }
        if(getClass() != otherObject.getClass()){
            return false;
        }
        Country otherCountry = (Country) otherObject;
        return name.equals(otherCountry.name);
    }

    /**
     * Overrides the hashCode method for the country class.
     * @return a unique hash code for the country object.
     */
    @Override
    public int hashCode(){
        return 11 * name.hashCode();
    }
}

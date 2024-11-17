/**
 * The Position class is used to represent a position in the game. It has a from city, a to city, a distance and a total distance.
 * @author Simon Hesselberg & Noah Østfeldt
 * @version 09-11-2024
 */
public class Position {
    private City from;
    private City to;
    private int distance;
    private int total;

    /**
     * Constructor for the Position class.
     * @param from - the city the position goes from
     * @param to - the city the position goes to
     * @param distance - the distance between the two cities
     */
    public Position(City from, City to, int distance) {
        this.from = from;
        this.to = to;
        this.distance = distance;
        this.total = distance;
    }

    /**
     * Returns the city the position goes from.
     * @return from
     */
    public City getFrom() {
        return from;
    }

    /**
     * Returns the city the position goes to.
     * @return to
     */
    public City getTo() {
        return to;
    }

    /**
     * Returns the distance between the two cities.
     * @return distance
     */
    public int getDistance() {
        return distance;
    }

    /**
     * Returns the total distance between the two cities.
     * @return total
     */
    public int getTotal() {
        return total;
    }

    /**
     * Moves the position one step closer to the to city.
     * @return true if the position has moved, false otherwise
     */
    public boolean move(){
        int compareDistance = distance;
        if(distance > 0){
            distance--;
        }
        return compareDistance != distance;
    }

    /**
     * Turns the position around, so the from city becomes the to city and vice versa.
     */
    public void turnAround(){
        City temp = from;
        from = to;
        to = temp;
        distance = total - distance;
    }

    /**
     * Returns true if the position has arrived at the to city, false otherwise.
     * @return true if the position has arrived, false otherwise
     */
    public boolean hasArrived(){
        return distance == 0;
    }

    /**
     * Overrides the toString method to return a string representation of the Position object.
     * @return string representation of the Position object.
     */
    @Override
    public String toString() {
        return from.getName() + " (" + from.getValue() + ") -> " + to.getName() + " (" + to.getValue() + ") : "
                + distance + "/" + total;
    }

    /**
     * Overrides the equals method to compare two Position objects. Two Position objects are equal if their from and to cities are equal and their distances are equal.
     * @param otherObject the object to be compared.
     * @return true if the two Position objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object otherObject) {
        if(this == otherObject){
            return true;
        }
        if(otherObject == null){
            return false;
        }
        if(getClass() != otherObject.getClass()){
            return false;
        }
        Position otherPosition = (Position) otherObject;
        return  from.equals(otherPosition.from) &&
                to.equals(otherPosition.to) &&
                distance == otherPosition.distance &&
                total == otherPosition.total;
    }

    /**
     * Overrides the hashCode method to return a unique hash code for the Position object.
     * @return a unique hash code for the Position object.
     */
    @Override
    public int hashCode() {
        return 11 * from.hashCode() +
                13 * to.hashCode() +
                17 * Integer.hashCode(distance) +
                19 * Integer.hashCode(total);
    }
}

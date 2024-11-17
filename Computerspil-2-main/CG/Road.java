/**
 * This class represents a road between two cities. The road has a length and two cities, from and to.
 * @author Simon Hesselberg & Noah Østfeldt
 * @version 09-11-2024
 */
public class Road implements Comparable<Road>{
private City from;
private City to;
private int length;

    /**
     * Constructor for the Road class.
     * @param from - the city the road goes from
     * @param to - the city the road goes to
     * @param length - the length of the road
     */
    public Road(City from, City to, int length) {
        this.from = from;
        this.to = to;
        this.length = length;
    }

    /**
     * Returns the city the road goes from.
     * @return from
     */
    public City getFrom() {
        return from;
    }

    /**
     * Returns the city the road goes to.
     * @return to
     */
    public City getTo() {
        return to;
    }

    /**
     * Returns the length of the road.
     * @return length
     */
    public int getLength() {
        return length;
    }

    /**
     * Overrides the toString method to return a string representation of the Road object.
     * @return string representation of the Road object.
     */
    @Override
    public String toString() {
        return from.getName() + " (" + from.getValue() + ") -> " + to.getName() + " (" + to.getValue() + ") : " + length;
    }

    /**
     * Overrides the compareTo method to compare two Road objects. The comparison is done by comparing the from city, then the to city and finally the length of the road.
     * @param other the object to be compared.
     * @return negative, zero or positive integer depending on the comparison.
     */
    @Override
    public int compareTo(Road other) {
        if(from.compareTo(other.from) != 0){
            return from.compareTo(other.from);
        }
        if(to.compareTo(other.to) != 0){
            return to.compareTo(other.to);
        }
        return length - other.length;
    }

    /**
     * Overrides the equals method to compare two Road objects. Two Road objects are equal if their from and to cities are equal and their lengths are equal.
     * @param otherObject the object to be compared.
     * @return true if the two Road objects are equal, false otherwise.
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
        Road otherRoad = (Road) otherObject;
        return  from.equals(otherRoad.from) &&
                to.equals(otherRoad.to) &&
                length == otherRoad.length;
    }

    /**
     * Overrides the hashCode method to return a unique hash code for each Road object.
     * @return hash code for this Road object.
     */
    @Override
    public int hashCode() {
        return 11 * from.hashCode() +
               13 * to.hashCode() +
               17 * Integer.hashCode(length);
    }
}

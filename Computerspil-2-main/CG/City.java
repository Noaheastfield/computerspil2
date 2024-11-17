/**
 * This class represents a city in a country. The city has a name and a value. The value can be changed and reset.
 * @author Simon Hesselberg & Noah Østfeldt
 * @version 09-11-2024
 */
public class City implements Comparable<City> {
    private String name;
    private int value;
    private int initialValue;
    private Country country;

    /**
     * Constructor for the City class.
     * @param name - name of the city
     * @param value - value of the city. Both the value and initialValue field variables are assigned to this parameter
     */
    public City(String name, int value, Country country) {
        this.name = name;
        this.value = value;
        this.initialValue = value;
        this.country = country;
    }

    /**
     * Returns the country of the city
     * @return country
     */
    public Country getCountry(){
        return country;
    }

    /**
     * Returns the name of the city
     * @return name
     */
    public String getName(){
        return name;
    }

    /**
     * Returns the value of the city
     * @return value
     */
    public int getValue(){
        return value;
    }

    /**
     * Returns the initial value of the city
     * @return initialValue
     */
    public int getInitialValue(){
        return initialValue;
    }

    /**
     * Changes the value by adding the parameter amount to the variable value
     * @param amount - the amount to be added to the value
     */
    public void changeValue(int amount){
        value += amount;
    }

    /**
     * Resets the value to the initial value set in the constructor
     */
    public void reset(){
        value = initialValue;
    }

    /**
     * Returns the bonus of the city and changes the value of the city by subtracting the bonus.
     * @return the bonus of the city
     */
    public int arrive() {
        int bonus = country.bonus(value);
        changeValue(-bonus);
        return bonus;
    }

    /**
     * Overrides the toString method for the city class
     * @return a string representation of the city
     */
    @Override
    public String toString(){
        return name + " (" + value + ")";
    }

    /**
     * Compares the city to another city based on the name of the city. It sorts alphabetically.
     * @param other the object to be compared.
     * @return an integer value based on the comparison.
     */
    @Override
    public int compareTo(City other){
        return name.compareTo(other.name);
    }

    /**
     * Overrides the equals method for the city class. A city is equal to another city if the name of the city and the country of the city are equal.
     * @param otherObject the object to be compared.
     * @return true if the two cities are equal, false otherwise.
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
        City other = (City) otherObject;
        return name.equals(other.name) &&
               country == other.country;
    }

    /**
     * Overrides the hashCode method for the city class.
     * @return a unique hash code for the city object.
     */
    @Override
    public int hashCode(){
        return 11 * name.hashCode() +
               13 * country.hashCode();
    }
}



import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Set;
import java.util.HashSet;
import java.util.TreeSet;

/**
 * Testklassen CountryTest.
 *
 * @author (dit navn her)
 * @version (versionsnummer eller dato her)
 */
public class CountryTest
{
    private Game game;
    private Country country1;
    private City cityA, cityB, cityC, cityD;
    
    @BeforeEach
    public void setUp(){
        // Create game object
        game = new Game(0);
        // Create country
        country1 = new Country("Country 1");
        country1.setGame(game);
        // Create cities
        cityA = new City("City A", 80, country1);
        cityB = new City("City B", 60, country1);
        cityC = new City("City C", 40, country1);
        cityD = new City("City D", 100, country1);
        
         // add cities to our country
        country1.addCity(cityA);
        country1.addCity(cityB);
        country1.addCity(cityC);
        country1.addCity(cityD);
        
        // Create roads
        country1.addRoads(cityA, cityB,10);
        country1.addRoads(cityC, cityD,20);
    
    }

    
    @Test
    public void getCities(){
        //Test our starting getCities
        assertEquals("[City A (80), City B (60), City C (40), City D (100)]", country1.getCities().toString());
        //Modify the values
        cityA.changeValue(10);
        cityB.changeValue(10);
        cityC.changeValue(20);
        cityD.changeValue(20);
        //Test our modified version getCities
        assertEquals("[City A (90), City B (70), City C (60), City D (120)]", country1.getCities().toString());
    }
    @Test
    public void getCity(){
        //Test if the right city is returned by converting each result 'toString'.
        assertEquals("City A (80)", country1.getCity("City A").toString());
        assertEquals("City B (60)", country1.getCity("City B").toString());
        assertEquals("City C (40)", country1.getCity("City C").toString());
        assertEquals("City D (100)", country1.getCity("City D").toString());
    }
    @Test
    public void getRoads(){
         //Test if each city returns the correct road-connections
        assertEquals(cityB, country1.getRoads(cityA));
        assertEquals(cityA, country1.getRoads(cityB));
        assertEquals(cityD, country1.getRoads(cityC));
        assertEquals(cityC, country1.getRoads(cityD));
    }
    @Test
    public void reset(){
        //Modify original values
        cityA.changeValue(10);
        cityB.changeValue(10);
        cityC.changeValue(20);
        cityD.changeValue(20);
        //Test if modified values have been passed correctly
        assertEquals(90, cityA.getValue());
        assertEquals(70, cityB.getValue());
        assertEquals(60, cityC.getValue());
        assertEquals(120, cityD.getValue());
        //reset our cities
        country1.reset();
        //Test if the values have returned to initial values 
        assertEquals(80, cityA.getValue());
        assertEquals(60, cityB.getValue());
        assertEquals(40, cityC.getValue());
        assertEquals(100, cityD.getValue());
    }
    @Test
    public void testToString(){
        //Test if the correct name is returned
        assertEquals("Country 1", country1.toString());
    }
    @Test
    public void bonus(){
        int maxBonusValue = 100; 
        int testRuns = 10000;
        //Creat a Set to track our observed values
        Set<Integer> observedValues = new HashSet<>();
        //Test bonus calls 1000 times
        int sum = 0;
        for(int i =0 ; i <testRuns; i++){
            int bonusValue = country1.bonus(maxBonusValue);
            sum+= country1.bonus(maxBonusValue);
            //Add the result to our Set
            observedValues.add(bonusValue);
            //Test if the bonus is within the range  
            assertTrue(bonusValue >= 0 && bonusValue <= maxBonusValue);
        }
        
        // Check that all expected values have been hit
        for (int i = 0; i <= maxBonusValue; i++) {
            assertTrue(observedValues.contains(i));
        }
        
        //Test if the avarage is correct with a small tolerance 
        double avarage = sum / (double) testRuns;
        double expectedAvarage = maxBonusValue / 2.0; 
        assertTrue(Math.abs(avarage - expectedAvarage) < 0.25);
        
        // Test edge cases 
        assertEquals(0, country1.bonus(0));
        assertTrue(country1.bonus(1) == 0 || country1.bonus(1) == 1);
    }
    @Test
    public void addCity(){
        //Creat new cityE and add to our existing country and roads.
        City cityE;
        cityE = new City("City E", 300, country1);
        country1.addCity(cityE);
        
        //Test if cityE prints correctly
        assertEquals("City E (300)", country1.getCity("City E").toString());
        //Test our new getCities()
        assertEquals("[City A (80), City B (60), City C (40), City D (100), City E (300)]", country1.getCities().toString());
    }
    @Test
    public void addRoad(){
        // Test adding a valid road between two already existing cities
        assertEquals("[City A (80) -> City B (60) : 10]", country1.getRoads(cityA).toString());
    
        // Test adding a road with invalid length; 0 and -5
        country1.addRoads(cityA, cityC, 0); 
        country1.addRoads(cityA, cityC, -5); 
        assertEquals("[City A (80) -> City B (60) : 10]", country1.getRoads(cityA).toString()); 
        assertEquals("[City C (40) -> City D (100) : 20]", country1.getRoads(cityC).toString());
    
        // Test adding a road between the same city
        country1.addRoads(cityA, cityA, 25); 
        assertEquals("[City A (80) -> City B (60) : 10]", country1.getRoads(cityA).toString()); 
    
        // Test adding duplicate roads
        country1.addRoads(cityA, cityC, 15);
        assertEquals(2, country1.getRoads(cityA).size());
        country1.addRoads(cityA, cityC, 15);
        assertEquals(2, country1.getRoads(cityC).size());
        country1.addRoads(cityA, cityC, 15);
        assertEquals(2, country1.getRoads(cityC).size());
        assertEquals("[City A (80) -> City B (60) : 10, City A (80) -> City C (40) : 15]", country1.getRoads(cityA).toString()); 
    }
    @Test
    public void position(){
        //Test possition for each city
        assertEquals("City A (80) -> City A (80) : 0/0", country1.position(cityA).toString());
        assertEquals("City B (60) -> City B (60) : 0/0", country1.position(cityB).toString());
        assertEquals("City C (40) -> City C (40) : 0/0", country1.position(cityC).toString());
        assertEquals("City D (100) -> City D (100) : 0/0", country1.position(cityD).toString());
        //Create a new cityE
        City cityE;
        cityE = new City("City E", 300, country1);
        //Test the possition
        assertEquals("City E (300) -> City E (300) : 0/0", country1.position(cityE).toString());
    }
    @Test
    public void readyToTravel(){
        // Test traveling to same city (cityA --> cityA)
        Position travelSameCity= new Position(cityA, cityA, 0);
        assertEquals(travelSameCity , country1.readyToTravel(cityA,cityA));
        
        // Test traveling to a city outside of network (cityA --> cityE)
        City cityE = new City("City E", 300, country1);
        assertEquals("City B (60) -> City B (60) : 0/0" , country1.readyToTravel(cityB,cityE).toString());
        
        // Test traveling between two connected cities
        Position travelConnected = new Position(cityA, cityB, 10); // cityA -> cityB with length 10
        assertEquals(travelConnected, country1.readyToTravel(cityA, cityB));
    
    }
}

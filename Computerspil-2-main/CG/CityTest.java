import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * @author Simon Hesselberg & Noah Østfeldt
 * @version 18-11-2024
 */
public class CityTest {
    private Game game;
    private Country country1;
    private City cityA, cityB, cityC, cityD;

    @BeforeEach
    public void setUp() {
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
    }

    @Test
    public void constructor() {
        // Tests if the different cities are assigned the right value
        assertEquals(80, cityA.getValue());
        assertEquals(60, cityB.getValue());
        assertEquals(40, cityC.getValue());
        assertEquals(100, cityD.getValue());

        // Tests if the different cities are assigned the right initial value
        assertEquals(80, cityA.getInitialValue());
        assertEquals(60, cityB.getInitialValue());
        assertEquals(40, cityC.getInitialValue());
        assertEquals(100, cityD.getInitialValue());

        // Tests if the different cities are assigned the right name
        assertEquals("City A", cityA.getName());
        assertEquals("City B", cityB.getName());
        assertEquals("City C", cityC.getName());
        assertEquals("City D", cityD.getName());

        // Tests if the different cities are assigned the right country
        assertEquals(country1, cityA.getCountry());
        assertEquals(country1, cityB.getCountry());
        assertEquals(country1, cityC.getCountry());
        assertEquals(country1, cityD.getCountry());
    }

    @Test
    public void changeValue(){
        cityA.changeValue(10);
        assertEquals(90, cityA.getValue());
        cityB.changeValue(10);
        assertEquals(70, cityB.getValue());
        cityC.changeValue(20);
        assertEquals(60, cityC.getValue());
        cityD.changeValue(20);
        assertEquals(120, cityD.getValue());
        
        // Test negative values
        cityA.changeValue(-100);
        assertEquals(-10, cityA.getValue());
        cityB.changeValue(-70);
        assertEquals(0, cityB.getValue());
        cityC.changeValue(-200);
        assertEquals(-140, cityC.getValue());
        cityD.changeValue(-20);
        assertEquals(100, cityD.getValue());
        

        //Test Int Overflow
        cityD.changeValue(-100);
        assertEquals(0, cityD.getValue());
        cityD.changeValue(Integer.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE, cityD.getValue());
        cityD.changeValue(1);
        assertEquals(-(Integer.MAX_VALUE)-1, cityD.getValue());
    }

    @Test
    public void reset(){
        // Tests if the cities values are reset to their initial values
        cityA.reset();
        assertEquals(80, cityA.getInitialValue());
        cityB.reset();
        assertEquals(60, cityB.getInitialValue());
        cityC.reset();
        assertEquals(40, cityC.getInitialValue());
        cityD.reset();
        assertEquals(100, cityD.getInitialValue());
    }

    @Test
    public void testToString() {
        assertEquals("City A (80)", cityA.toString());
        assertEquals("City B (60)", cityB.toString());
        assertEquals("City C (40)", cityC.toString());
        assertEquals("City D (100)", cityD.toString());
    }

    @Test
    public void arrive(){
        for(int seed = 0; seed < 1000; seed++){
            game.getRandom().setSeed(seed);
            int bonus = country1.bonus(80);
            game.getRandom().setSeed(seed);
            assertEquals(bonus, cityA.arrive());
            assertEquals(cityA.getInitialValue() - bonus, cityA.getValue());
            cityA.reset();
        }
    }

    @Test
    public void arrive1(){
        for(int seed = 0; seed < 1000; seed++){
            game.getRandom().setSeed(seed);
            int bonus = country1.bonus(0);
            game.getRandom().setSeed(seed);
            assertEquals(cityA.getInitialValue()- bonus, cityA.getInitialValue());
            assertEquals(cityA.getInitialValue()- bonus, cityA.getValue());
            cityA.reset();
        }
    }
}

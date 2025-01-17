import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Simon Hesselberg & Noah Østfeldt
 * @version 13-11-2024
 */
public class PositionTest {
    private Game game;
    private Country country1;
    private City cityA, cityB, cityC, cityD;
    private Position pos1, pos2;

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

        // Create positions
        pos1 = new Position(cityA, cityB, 4);
        pos2 = new Position(cityC, cityD, 2);
    }

    @Test
    public void constructor() {
        assertEquals(cityA, pos1.getFrom());
        assertEquals(cityB, pos1.getTo());
        assertEquals(4, pos1.getDistance());
        assertEquals(4, pos1.getTotal());
        assertEquals(cityC, pos2.getFrom());
        assertEquals(cityD, pos2.getTo());
        assertEquals(2, pos2.getDistance());
        assertEquals(2, pos2.getTotal());
    }

    @Test
    public void move() {
        assertEquals(true, pos2.move());;
        assertEquals(1, pos2.getDistance());
        assertEquals(true, pos2.move());;
        assertEquals(0, pos2.getDistance());
        assertEquals(false, pos2.move());;
        assertEquals(0, pos2.getDistance());
    }

    @Test
    public void turnAround() {
        pos1.move();
        pos1.turnAround();
        assertEquals(cityB, pos1.getFrom());
        assertEquals(cityA, pos1.getTo());
        assertEquals(1, pos1.getDistance());
        pos1.turnAround();
        assertEquals(cityA, pos1.getFrom());
        assertEquals(cityB, pos1.getTo());
        assertEquals(3, pos1.getDistance());
        pos2.turnAround();
        assertEquals(cityD, pos2.getFrom());
        assertEquals(cityC, pos2.getTo());
        assertEquals(0, pos2.getDistance());
        pos2.turnAround();
        assertEquals(cityC, pos2.getFrom());
        assertEquals(cityD, pos2.getTo());
        assertEquals(2, pos2.getDistance());
    }

    @Test
    public void hasArrived() {
        assertEquals(false, pos2.hasArrived());
        pos2.move();
        assertEquals(false, pos2.hasArrived());
        pos2.move();
        assertEquals(true, pos2.hasArrived());
        pos2.move();
        assertEquals(true, pos2.hasArrived());
    }

    @Test
    public void testToString(){
        assertEquals("City A (80) -> City B (60) : 4/4", pos1.toString());
        assertEquals("City C (40) -> City D (100) : 2/2", pos2.toString());

        pos2.move();
        assertEquals("City C (40) -> City D (100) : 1/2", pos2.toString());
        pos2.move();
        assertEquals("City C (40) -> City D (100) : 0/2", pos2.toString());
    }
}
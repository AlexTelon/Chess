package test.main.position;

import main.position.Position;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;


/**
 * Position Tester.
 *
 * @author Alex Telon
 * @since <pre>sep 1, 2013</pre>
 * @version 1.0
 */
public class PositionTest {
    private Position position = new Position(0,0);

    /**
     *
     * Method: setX(int x)
     *
     */
    @Test
    public void testSetX() throws Exception {
        for (int temp = 0; temp < 8; temp++) {
            assertEquals(position.setX(temp), true);
            assertEquals(position.getX(), temp);
        }

        // this value should not be changed by the invalid data below
        position.setX(1);
        assertEquals(position.setX(-1), false);
        assertEquals(position.getX(), 1);
        assertEquals(position.setX(8), false);
        assertEquals(position.getX(), 1);
    }


    /**
     *
     * Method: setY(int y)
     *
     */
    @Test
    public void testSetY() throws Exception {
        for (int temp = 0; temp < 8; temp++) {
            assertEquals(position.setY(temp), true);
            assertEquals(position.getY(), temp);
        }

        // this value should not be changed by the invalid data below
        position.setY(1);
        assertEquals(position.setY(-1), false);
        assertEquals(position.getY(), 1);
        assertEquals(position.setY(8), false);
        assertEquals(position.getY(), 1);
    }


} 

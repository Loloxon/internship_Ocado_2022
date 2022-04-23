package test;

import main.parsers.GridParser;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class GridParserTest {
    @Test
    public void GridParserTest(){
        GridParser gridParser = new GridParser("grid-1.txt");

        assertEquals(4, gridParser.getX());
        assertEquals(3, gridParser.getY());
        assertEquals(3, gridParser.getN());

        char[][] types = {  {'H','H','S','H'},
                            {'H','B','H','H'},
                            {'H','H','O','S'}};
        assertEquals(types[0][0], gridParser.getGrid()[0][0].getType());
        assertEquals(types[0][1], gridParser.getGrid()[0][1].getType());
        assertEquals(types[0][2], gridParser.getGrid()[0][2].getType());
        assertEquals(types[0][3], gridParser.getGrid()[0][3].getType());

        assertEquals(types[1][0], gridParser.getGrid()[1][0].getType());
        assertEquals(types[1][1], gridParser.getGrid()[1][1].getType());
        assertEquals(types[1][2], gridParser.getGrid()[1][2].getType());
        assertEquals(types[1][3], gridParser.getGrid()[1][3].getType());

        assertEquals(types[2][0], gridParser.getGrid()[2][0].getType());
        assertEquals(types[2][1], gridParser.getGrid()[2][1].getType());
        assertEquals(types[2][2], gridParser.getGrid()[2][2].getType());
        assertEquals(types[2][3], gridParser.getGrid()[2][3].getType());


        assertEquals("P1", gridParser.getProducts().get(0).getName());
        assertEquals("P1", gridParser.getProducts().get(1).getName());
        assertEquals("P2", gridParser.getProducts().get(2).getName());

        assertEquals(3, gridParser.getProducts().get(0).getCords().x);
        assertEquals(2, gridParser.getProducts().get(0).getCords().y);
        assertEquals(0, gridParser.getProducts().get(1).getCords().x);
        assertEquals(2, gridParser.getProducts().get(1).getCords().y);
        assertEquals(1, gridParser.getProducts().get(2).getCords().x);
        assertEquals(1, gridParser.getProducts().get(2).getCords().y);

        assertEquals(1, gridParser.getProducts().get(0).getLayer());
        assertEquals(2, gridParser.getProducts().get(1).getLayer());
        assertEquals(2, gridParser.getProducts().get(2).getLayer());
    }
}
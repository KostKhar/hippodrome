import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoSession;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class HippodromeTest {

    @Spy
    List<Horse> horsesMockList;


    @Test
    public void createHippodromeWithValidHorsesList(){
        Mockito.doReturn(10).when(horsesMockList).size();

        assertEquals(10, horsesMockList.size());
    }


    @Test
    public void createHippodromeWithNullHorsesList(){
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));

        assertEquals("Horses cannot be null.", e.getMessage());
    }

    @Test
    public void createHippodromeWithEmptyHorsesList(){
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(Collections.EMPTY_LIST));

        assertEquals("Horses cannot be empty.", e.getMessage());
    }


    @Test
    void checkHorserUnmodifiedAfterGetHorsesMethod() {
        List<Horse> horsesMockList = IntStream.range(0, 30)
                .mapToObj(i -> Mockito.mock(Horse.class))
                .collect(Collectors.toList());


        Hippodrome hippodrome = new Hippodrome(horsesMockList);

        List<Horse> hippodromHorsesList = hippodrome.getHorses();
        int i=0;

        for(Horse horse: hippodromHorsesList){
            Assertions.assertEquals(horse, horsesMockList.get(i));
            i++;
        }
    }

    @Test
    public void checkMoveCallByAllHorsesInList() {
        List<Horse> horsesMockList = IntStream.range(0, 50)
                .mapToObj(i -> Mockito.mock(Horse.class))
                .collect(Collectors.toList());

        Hippodrome hippodrome = new Hippodrome(horsesMockList);

        hippodrome.move();

        for(Horse horseMock : horsesMockList){
            Mockito.verify(horseMock).move();
        }

    }

    @Test
    void getWinnerReturnHorseWithLongDistance() {
        Horse horseMock1 = Mockito.mock(Horse.class);
        Horse horseMock2 = Mockito.mock(Horse.class);
        Horse horseMock3 = Mockito.mock(Horse.class);

        Mockito.when(horseMock1.getDistance()).thenReturn(10.0);
        Mockito.when(horseMock2.getDistance()).thenReturn(20.0);
        Mockito.when(horseMock3.getDistance()).thenReturn(15.0);

        List<Horse> horses = List.of(horseMock1, horseMock2, horseMock3);
        Hippodrome hippodrome = new Hippodrome(horses);

        Horse winner = hippodrome.getWinner();

        assertSame(horseMock2, winner);
        Mockito.verify(horseMock2, Mockito.atLeastOnce()).getDistance();
    }


}
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoSession;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
        Mockito.doReturn(null).when(horsesMockList).size();
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(horsesMockList));

        assertEquals("Horses cannot be null.", e.getMessage());
    }

    @Test
    public void createHippodromeWithEmptyHorsesList(){
        Mockito.doReturn(horsesMockList.isEmpty()).when(horsesMockList).size();
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(horsesMockList));

        assertEquals("Horses cannot be empty.", e.getMessage());
    }


    @Test
    void getHorses() {
    }

    @Test
    void move() {
    }

    @Test
    void getWinner() {
    }
}
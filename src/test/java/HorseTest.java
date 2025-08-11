import com.sun.jdi.connect.Connector;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class HorseTest {


    @Test
    void nameIsNull_ShouldReturnIllegalArgumentException() {

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                ()-> new Horse(null, 5.0, 10.1)
        );

        assertEquals("Name cannot be null.", e.getMessage());
    }


//Аргументы для проверки пустого name
    public static Stream<Arguments> getDataForNameIsBlank() {
        return Stream.of(
                Arguments.arguments(" "),
                Arguments.arguments("\n"),
                Arguments.arguments("\t"),
                Arguments.arguments("   "),
                Arguments.arguments("\n \t  \r ")


        );
    }


    @ParameterizedTest
    @MethodSource("getDataForNameIsBlank")
    void nameIsBlank_ShouldReturnIllegalArgumentException(String name) {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                ()-> new Horse(name, 5.0, 10.1)
        );

        assertEquals("Name cannot be blank.", e.getMessage());
    }

    //Аргументы для проверки пустого name
    public static Stream<Arguments> getDataForNegativeSpeed() {
        return Stream.of(
                Arguments.arguments(-0.0001),
                Arguments.arguments(-0.1),
                Arguments.arguments(-1.0),
                Arguments.arguments(-100.1)
        );
    }


    @ParameterizedTest
    @MethodSource("getDataForNegativeSpeed")
    void speedIsNegative_ShouldReturnIllegalArgumentException(double speed) {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                ()-> new Horse("Sprinter", speed, 10.1)
        );

        assertEquals("Speed cannot be negative.", e.getMessage());
    }

    public static Stream<Arguments> getDataForNegativeDistance() {
        return Stream.of(
                Arguments.arguments(-0.0001),
                Arguments.arguments(-0.1),
                Arguments.arguments(-1.0),
                Arguments.arguments(-100.1)
        );
    }


    @ParameterizedTest
    @MethodSource("getDataForNegativeDistance")
    void distanceIsNegative_ShouldReturnIllegalArgumentException(double speed) {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                ()-> new Horse("Sprinter", speed, 10.1)
        );

        assertEquals("Distance cannot be negative.", e.getMessage());
    }

    @Test
    public void getName_ShouldReturnName(){
        String name = "Sprinter";
        double speed = 5.2;
        double distance = 5.2;

        Horse horse = new Horse(name, speed, distance);

        assertEquals(name, horse.getName());
    }

    @Test
    public void getSpeed_ShouldReturnSpeed(){
        String name = "Sprinter";
        double speed = 5.2;
        double distance = 5.2;

        Horse horse = new Horse(name, speed, distance);

        assertEquals(speed, horse.getSpeed());
    }

    @Test
    public void getName_ShouldReturnDistance(){
        String name = "Sprinter";
        double speed = 5.2;
        double distance = 5.2;

        Horse horse = new Horse(name, speed, distance);

        assertEquals(distance, horse.getDistance());
    }



    @Test
    void move() {
    }

    @Test
    void getRandomDouble() {
    }
}
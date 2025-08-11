import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
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
    void distanceIsNegative_ShouldReturnIllegalArgumentException(double distance) {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                ()-> new Horse("Sprinter", 2.4, distance)
        );

        assertEquals("Distance cannot be negative.", e.getMessage());
    }

    private String name = "Sprinter";
    private double speed = 5.2;
    private double distance = 5.2;
    private Horse horse = new Horse(name, speed, distance);


    @Test
    public void getName_ShouldReturnName(){
        assertEquals(name, horse.getName());
    }

    @Test
    public void getSpeed_ShouldReturnSpeed(){
        assertEquals(speed, horse.getSpeed());
    }

    @Test
    public void getName_ShouldReturnDistance(){
        assertEquals(distance, horse.getDistance());
    }


    @Test
    void move_shouldReturnVerifyGetRandomDouble() {
       try( MockedStatic<Horse> mockStatic =  Mockito.mockStatic(Horse.class)) {
           mockStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.5);

           horse.move();

           mockStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
       }
    }

    public static Stream<Arguments> getDataForGetRandomInt() {
        return Stream.of(
                Arguments.arguments(0.21),
                Arguments.arguments(0.5),
                Arguments.arguments(0.34),
                Arguments.arguments(0.89)
        );
    }

    @ParameterizedTest
    @MethodSource("getDataForGetRandomInt")
    void move_shouldReturnCorrectDistance(double random) {
        try(MockedStatic<Horse> mockStatic =  Mockito.mockStatic(Horse.class)){
            mockStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(random);
            double speed = 2.3;
            double distance = 10.5;

            Horse horse = new Horse("Funtic", speed, distance);

            horse.move();
            double expectedDistance =  distance + speed * random;

            Assertions.assertEquals(expectedDistance,  horse.getDistance() ); ;
        }
    }
}
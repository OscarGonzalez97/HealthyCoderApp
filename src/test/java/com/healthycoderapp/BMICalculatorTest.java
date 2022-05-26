package com.healthycoderapp;

import org.junit.jupiter.api.*;

import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class BMICalculatorTest {

    private final String enviroment = "dev";

    @BeforeAll
    static void beforeAll() {
        System.out.println("Before all unit test passes");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("After all unit test passes");
    }

    @Nested
    class IsDietRecommendedTests {
        @ParameterizedTest(name = "weight={0}, height={1}")
        @CsvFileSource(resources = "/diet-recommended-input-data.csv", numLinesToSkip = 1)
        void shouldReturnTrueWhenDietRecommended(Double coderWeight, Double coderHeight) {
            // given
            double weight = coderWeight;
            double height = coderHeight;
            // when
            boolean recommended = BMICalculator.isDietRecommended(weight, height);
            //then
            assertTrue(recommended);
        }

        @Test
        void shouldReturnFalseWhenDietNotRecommended() {
            // given
            double weight = 50.0;
            double height = 1.92;
            // when
            boolean recommended = BMICalculator.isDietRecommended(weight, height);
            //then
            assertFalse(recommended);
        }

        @Test
        void shouldReturnArithmeticExceptionWhenHeightZero() {
            // given
            double weight = 50.0;
            double height = 0.0;
            // when
            Executable executable = () -> BMICalculator.isDietRecommended(weight, height);
            //then
            assertThrows(ArithmeticException.class, executable);
        }
    }

    @Nested
    @DisplayName("Sample inner class display name")
    class FindCoderWithWorstBMITests {
        @Test
        @Disabled
        void shouldReturnCoderWithWorstBMIIn1MsWhenCoderListHas10000Elements() {
            assumeTrue(BMICalculatorTest.this.enviroment.equals("prod"));
            //given
            List<Coder> coders = new ArrayList<>();
            for (int i = 0; i < 10000; i++) {
                coders.add(new Coder(1.0 + i, 10.0 + 1));
            }
            // when
            Executable executable = () -> BMICalculator.findCoderWithWorstBMI(coders);
            //then
            assertTimeout(Duration.ofMillis(100), executable);
        }

        @Test
        void shouldReturnCoderWithWorstBMIWhenCoderListNotEmpty() {
            // given
            List<Coder> coders = new ArrayList<>();
            coders.add(new Coder(1.80, 60.0));
            coders.add(new Coder(1.82, 98.0));
            coders.add(new Coder(1.82, 64.7));
            // when
            Coder coderWorstBMI = BMICalculator.findCoderWithWorstBMI(coders);
            //then
            assertAll(
                    () -> assertEquals(98.0, coderWorstBMI.getWeight()),
                    () -> assertEquals(1.82, coderWorstBMI.getHeight())
            );
        }

        @Test
        void shouldReturnNullWorstBMIWhenCoderListEmpty() {
            // given
            List<Coder> coders = new ArrayList<>();
            // when
            Coder coderWorstBMI = BMICalculator.findCoderWithWorstBMI(coders);
            //then
            assertNull(coderWorstBMI);
        }

    }

    @Nested
    class GetBMIScoresTests {
        @Test
        void shouldReturnCorrectBMIScoreArrayWhenCoderListNotEmpty() {
            // given
            List<Coder> coders = new ArrayList<>();
            coders.add(new Coder(1.80, 60.0));
            coders.add(new Coder(1.82, 98.0));
            coders.add(new Coder(1.82, 64.7));
            double[] expected = {18.52, 29.59, 19.53};
            // when
            double[] bmiScores = BMICalculator.getBMIScores(coders);
            //then
            assertArrayEquals(expected, bmiScores);
        }
    }

}
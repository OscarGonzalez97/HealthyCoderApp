package com.healthycoderapp;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;


public class DietPlannerTest {
    private DietPlanner dietPlanner;

    @BeforeEach
    void setup() {
        this.dietPlanner = new DietPlanner(20, 30, 50);
    }

    @AfterEach
    void afterEach() {
        System.out.println("A unit test was finished");
    }

    @RepeatedTest(value = 10, name = RepeatedTest.LONG_DISPLAY_NAME)
    void shouldReturnCorrectDietPlanWhenCorrectCoder(){
        //given
        Coder coder = new Coder(1.82, 75, 26, Gender.MALE);
        DietPlan expected = new DietPlan(2202, 110, 73, 275);
        //when
        DietPlan actual = dietPlanner.calculateDiet(coder);
        // then
        assertAll(
                () -> assertEquals(expected.getCalories(), actual.getCalories()),
                () -> assertEquals(expected.getProtein(), actual.getProtein()),
                () -> assertEquals(expected.getFat(), actual.getFat()),
                () -> assertEquals(expected.getCarbohydrate(), actual.getCarbohydrate())
        );
    }
}

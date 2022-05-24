package com.healthycoderapp;

import com.sun.org.apache.bcel.internal.classfile.Code;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BMICalculatorTest {
    @Test
    void shouldReturnTrueWhenDietRecommended() {
        // given
        double weight = 89.0;
        double height = 1.72;
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
                () -> assertEquals(98.0,coderWorstBMI.getWeight()),
                () -> assertEquals(1.82,coderWorstBMI.getHeight())
        );
    }
}
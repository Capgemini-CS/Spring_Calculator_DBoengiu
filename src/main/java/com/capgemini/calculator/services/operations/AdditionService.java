package com.capgemini.calculator.services.operations;

import org.springframework.stereotype.Service;

@Service
public class AdditionService implements Operation {
    @Override
    public double execute(double firstNumber, double secondNumber) {
        return firstNumber + secondNumber;
    }
}

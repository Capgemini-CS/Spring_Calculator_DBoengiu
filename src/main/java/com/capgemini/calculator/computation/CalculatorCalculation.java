package com.capgemini.calculator.computation;

import com.capgemini.calculator.services.operations.AdditionService;
import com.capgemini.calculator.services.operations.DivisionService;
import com.capgemini.calculator.services.operations.MultiplicationService;
import com.capgemini.calculator.services.operations.SubtractionService;
import org.springframework.stereotype.Component;

@Component
public class CalculatorCalculation implements Calculation{

    private final AdditionService additionService;
    private final SubtractionService subtractionService;
    private final MultiplicationService multiplicationService;
    private final DivisionService divisionService;

    public CalculatorCalculation(AdditionService additionService, SubtractionService subtractionService, MultiplicationService multiplicationService, DivisionService divisionService) {
        this.additionService = additionService;
        this.subtractionService = subtractionService;
        this.multiplicationService = multiplicationService;
        this.divisionService = divisionService;
    }

    @Override
    public double doCalculation(double firstNumber, double secondNumber, String operator) {

        double result = 0;
        switch (operator) {
            case "+":
                result = additionService.execute(firstNumber, secondNumber);
                break;
            case "-":
                result = subtractionService.execute(firstNumber, secondNumber);
                break;
            case "*":
                result = multiplicationService.execute(firstNumber, secondNumber);
                break;
            case "/":
                result = divisionService.execute(firstNumber, secondNumber);
                break;
        }
        return result;
    }
}

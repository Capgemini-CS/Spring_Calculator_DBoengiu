package com.capgemini.calculator.services;

import com.capgemini.calculator.computation.CalculatorCalculation;
import com.capgemini.calculator.input.FileInput;
import com.capgemini.calculator.output.CalculatorOutput;
import com.capgemini.calculator.validation.FileDataValidation;
import com.capgemini.calculator.validation.FileNumberValidation;
import com.capgemini.calculator.validation.FileOperatorValidation;
import org.springframework.stereotype.Service;
import org.tinylog.Logger;

@Service
public class CalculatorService implements CalculatorInterface {

    int firstNumber;
    int secondNumber;
    String operator;

    private final FileInput fileInput;
    private final FileDataValidation fileDataValidation;
    private final FileOperatorValidation fileOperatorValidation;
    private final FileNumberValidation fileNumberValidation;
    private final CalculatorCalculation calculatorCalculation;
    private final CalculatorOutput calculatorOutput;

    public CalculatorService(FileInput fileInput, FileDataValidation fileDataValidation, FileOperatorValidation fileOperatorValidation, FileNumberValidation fileNumberValidation, CalculatorCalculation calculatorCalculation, CalculatorOutput calculatorOutput) {
        this.fileInput = fileInput;
        this.fileDataValidation = fileDataValidation;
        this.fileOperatorValidation = fileOperatorValidation;
        this.fileNumberValidation = fileNumberValidation;
        this.calculatorCalculation = calculatorCalculation;
        this.calculatorOutput = calculatorOutput;
    }


    @Override
    public void executeOperation() {
        String fileLine = fileInput.getFileLine();
        String[] fileAttributes = fileLine.split(",");

        try {
            fileDataValidation.checkLength(fileAttributes);
            fileDataValidation.checkIfNull(fileAttributes);
            fileOperatorValidation.checkAllowedOperand(fileAttributes);
            fileNumberValidation.isNotNumeric(fileAttributes[0]);
            fileNumberValidation.isNotNumeric(fileAttributes[1]);
        } catch (RuntimeException e) {
            Logger.error(e.getMessage());
        }


        firstNumber = fileInput.getFirstNumber();
        secondNumber = fileInput.getSecondNumber();
        operator = fileInput.getOperator();

        double result = 0;


        try {
            result = calculatorCalculation.doCalculation(firstNumber, secondNumber, operator);
        } catch (
                ArithmeticException e) {
            Logger.error("You have tried to divide by zero");
        }


        calculatorOutput.showResult(result);


    }
}

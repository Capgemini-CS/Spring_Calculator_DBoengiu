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
public class MasterService implements MasterInterface {

    int firstNumber;
    int secondNumber;
    String operator;

    private FileInput fileInput;
    private final FileDataValidation fileDataValidation;
    private final FileOperatorValidation fileOperatorValidation;
    private final FileNumberValidation fileNumberValidation;
    private CalculatorCalculation calculatorCalculation;
    private final CalculatorOutput calculatorOutput;

    public MasterService(FileInput fileInput, FileDataValidation fileDataValidation, FileOperatorValidation fileOperatorValidation, FileNumberValidation fileNumberValidation, CalculatorCalculation calculatorCalculation, CalculatorOutput calculatorOutput) {
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

        fileDataValidation.checkLength(fileAttributes);
        fileDataValidation.checkIfNull(fileAttributes);
        fileOperatorValidation.checkAllowedOperand(fileAttributes);

        if (fileNumberValidation.isNotNumeric(fileAttributes[0]) || fileNumberValidation.isNotNumeric(fileAttributes[1])) {
            Logger.error("You should have introduced numbers");
            System.exit(0);
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

package com.capgemini.calculator.validation;

import com.capgemini.calculator.exception.WrongOperatorException;
import org.springframework.stereotype.Component;
import org.tinylog.Logger;

import java.util.ArrayList;
import java.util.List;

@Component
public class FileOperatorValidation implements OperatorValidation{
    @Override
    public void checkAllowedOperand(String[] fileAttributes) {
        List<String> allowedOperands = new ArrayList<>(List.of("+", "-", "*", "/"));

        if (!allowedOperands.contains(fileAttributes[2])) {
            throw new WrongOperatorException("You should enter one of these operands {+, -, *, /}");
        }
    }
}

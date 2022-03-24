package com.capgemini.calculator.validation;

import com.capgemini.calculator.exception.InvalidLengthException;
import org.springframework.stereotype.Component;
import org.tinylog.Logger;

import java.util.Objects;

@Component
public class FileDataValidation implements DataValidation{
    @Override
    public void checkLength(String[] fileAttributes) {
        if (fileAttributes.length != 3) {
            throw new InvalidLengthException("The number of requested parameters was not respectred");
        }
    }

    @Override
    public void checkIfNull(String[] fileAttributes) {
        for (String attribute : fileAttributes) {
            if (Objects.isNull(attribute)) {
                throw new NullPointerException("Input was null");
            }
        }
    }
}

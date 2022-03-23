package com.capgemini.calculator.validation;

import org.springframework.stereotype.Component;
import org.tinylog.Logger;

import java.util.Objects;

@Component
public class FileDataValidation implements DataValidation{
    @Override
    public void checkLength(String[] fileAttributes) {
        if (fileAttributes.length != 3) {
            Logger.error("Invalid file");
            System.exit(0);
        }
    }

    @Override
    public void checkIfNull(String[] fileAttributes) {
        for (String attribute : fileAttributes) {
            if (Objects.isNull(attribute)) {
                System.exit(0);
            }
        }
    }
}

package com.capgemini.calculator.validation;

import org.springframework.stereotype.Component;

@Component
public class FileNumberValidation implements NumberValidation{
    @Override
    public boolean isNotNumeric(String stringToNumber) {
        try {
            Integer.parseInt(stringToNumber);
        } catch (NumberFormatException e) {
            return true;
        }
        return false;
    }
}

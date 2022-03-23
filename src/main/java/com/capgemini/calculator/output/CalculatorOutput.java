package com.capgemini.calculator.output;

import org.springframework.stereotype.Component;
import org.tinylog.Logger;

@Component
public class CalculatorOutput implements Output{
    @Override
    public void showResult(double result) {
        Logger.info(result);
    }
}

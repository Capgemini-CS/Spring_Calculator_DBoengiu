package com.capgemini.calculator.input;

import com.capgemini.calculator.exception.InvalidFileException;
import org.springframework.stereotype.Component;
import org.tinylog.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Component
public class FileInput implements Input {


    public String getFileLine() {
        String currentLine;
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/file_to_parse.csv"))) {

            while ((currentLine = br.readLine()) != null) {
                content.append(currentLine);
//                content.append(System.lineSeparator());
            }

        } catch (IOException e) {
            Logger.error("An exception occurred");
            throw new InvalidFileException("The file can't be parsed");
        }

        return content.toString();
    }

    String fileLine = getFileLine();
    String[] fileAttributes = fileLine.split(",");


    @Override
    public String getOperator() {
        return fileAttributes[2];

    }


    @Override
    public int getFirstNumber() {
        return Integer.parseInt(fileAttributes[0]);
    }


    @Override
    public int getSecondNumber() {
        return Integer.parseInt(fileAttributes[1]);
    }


}

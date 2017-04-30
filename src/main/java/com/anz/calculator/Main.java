package com.anz.calculator;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Main program reading inputs from the user
 * 
 *
 * @version $Rev$ $Date$
 */

public class Main {

    public static void main(String args[]) throws Exception {

        Calculator calculator = new Calculator();

        BufferedReader reader =
            new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Please enter parameters and operations as single whitespace separated list \n eg: 5 4 3 + (If you want to quit type 'exit')  ");

        while (true) {
            System.out.println("\n $ >");
            String input = reader.readLine();

            if (input.equals("exit")) {
                return;
            }
            calculator.doCalculation(input);
            calculator.list();     
        }

    }
}

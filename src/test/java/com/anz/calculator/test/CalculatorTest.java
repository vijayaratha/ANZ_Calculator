package com.anz.calculator.test;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.anz.calculator.Calculator;
import com.anz.calculator.CustomException;

public class CalculatorTest {

    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(output));
    }

    @Test
    public void addTest() {
        Calculator calculator = new Calculator();
        try {
            calculator.doCalculation("1 2 +");
            calculator.list();
            
            assertTrue("Stack : 3 ".equals(output.toString()));
        } catch (CustomException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Test
    public void substractTest() {
        Calculator calculator = new Calculator();
        try {
            calculator.doCalculation("1 4 3 -");
            calculator.list();
            
            assertTrue("Stack : 1 1 ".equals(output.toString()));
        } catch (CustomException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    
    @Test
    public void multiplyTest() {
        Calculator calculator = new Calculator();
        try {
            calculator.doCalculation("1 4 3 *");
            calculator.list();
            
            assertTrue("Stack : 1 12 ".equals(output.toString()));
        } catch (CustomException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    
    @Test
    public void divideTest() {
        Calculator calculator = new Calculator();
        try {
            calculator.doCalculation("1 9 3 /");
            calculator.list();
            
            assertTrue("Stack : 1 3 ".equals(output.toString()));
        } catch (CustomException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    
    @Test
    public void undoTest() {
        Calculator calculator = new Calculator();
        try {
            calculator.doCalculation("1 9 3 undo");
            calculator.list();
            
            assertTrue("Stack : 1 9 ".equals(output.toString()));
        } catch (CustomException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    
  
    @Test
    public void sqrtTest() {
        Calculator calculator = new Calculator();
        try {
            calculator.doCalculation("1 9 sqrt");
            calculator.list();
            
            assertTrue("Stack : 1 3.0 ".equals(output.toString()));
        } catch (CustomException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    
    @Test
    public void clearTest() {
        Calculator calculator = new Calculator();
        try {
            calculator.doCalculation("1 9 clear");
            calculator.list();
            
            assertTrue("Stack : ".equals(output.toString()));
        } catch (CustomException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    
    @Test
    public void inSufficientParamTest() {
        Calculator calculator = new Calculator();
        try {
            calculator.doCalculation("1 2 3 * * *");
            calculator.list();       
        } catch (CustomException e) {
            assertTrue(e.getMessage().contains("insufficient parameters"));
        }

    }
    
    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }

}

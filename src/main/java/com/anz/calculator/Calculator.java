package com.anz.calculator;

import java.math.BigDecimal;
import java.util.Stack;

/**
 * Class defines calculator operations
 * 
 *
 * @author ratha
 */

public class Calculator {

    private Stack<BigDecimal> numberStack = new Stack<>();
    private Stack<UnDoOperation> undoStack = new Stack<>();  //Stack to keep track of undo parameters

    private BigDecimal[] numArr;
    private int index = 0; // hold the index of the input


    /**
     * Do calculation based on the input
     * 
     * @param input
     * @throws CustomException
     */
    public void doCalculation(String input) throws CustomException {
        index = 0;
        if (input == null || "".equals(input)) {
            throw new CustomException("Input is null");
        }

        String[] inputs = input.split("\\s");

        for (int i = 0; i < inputs.length; i++) {
            index++;
            try {
                BigDecimal number = new BigDecimal(inputs[i]);
                numberStack.push(number);
            } catch (NumberFormatException ex) {
                int lastIndex = numberStack.lastIndexOf(numberStack.peek());

                UnDoOperation uop = new UnDoOperation();
                uop.setOperation(inputs[i]);
                if (lastIndex > 1) {
                    uop.setPriorElement(numberStack.get(lastIndex - 1));
                }
                if (!inputs[i].equals(CalculatorConstants.UNDO)) {
                    undoStack.push(uop);
                }

                if (inputs[i].equals(CalculatorConstants.PLUS)) {
                    add();
                } else if (inputs[i].equals(CalculatorConstants.MINUS)) {
                    subtract();
                } else if (inputs[i]
                    .equals(CalculatorConstants.MULTIPLICATION)) {
                    multiply();
                } else if (inputs[i].equals(CalculatorConstants.DIVIDE)) {
                    divide();
                } else if (inputs[i].equals(CalculatorConstants.CLEAR)) {
                    clear();
                } else if (inputs[i].equals(CalculatorConstants.SQRT)) {
                    squareroot();
                } else if (inputs[i].equals(CalculatorConstants.UNDO)) {
                    undo();
                } else {
                    throw new CustomException(
                        "Not supported operation " + inputs[i]);
                }
            }
        }

    }

    /**
     * List the number stack for the contents
     */
    public void list() {
        System.out.print("Stack : ");
        if (!numberStack.empty()) {
            for (int i = 0; i < numberStack.size(); i++) {
                System.out.print(numberStack.elementAt(i) + " ");
            }
        }

    }

    /**
     * Add
     * 
     * @return
     * @throws CustomException
     */
    private void add() throws CustomException {
        if (numberStack.size() < 2) {
            throw new CustomException("operator + (position: " + index
                + " : insufficient parameters");
        }
        BigDecimal db1 = numberStack.pop();
        BigDecimal db2 = numberStack.pop();

        numberStack.push(db1.add(db2));

    }

    /**
     * Subtract
     * 
     * @return
     * @throws CustomException
     */
    private void subtract() throws CustomException {
        if (numberStack.size() < 2) {
            throw new CustomException("operator - (position: " + index
                + " : insufficient parameters");
        }
        BigDecimal firstArg = numberStack.pop();
        BigDecimal secondArg = numberStack.pop();
        numberStack.push(secondArg.subtract(firstArg));
    }

    /**
     * Multiply
     * 
     *
     * @return
     * @throws CustomException
     */
    private void multiply() throws CustomException {
        if (numberStack.size() < 2) {
            throw new CustomException("operator  * (position: " + index
                + " : insufficient parameters");
        }
        BigDecimal db1 = numberStack.pop();
        BigDecimal db2 = numberStack.pop();
        numberStack.push(db1.multiply(db2));
    }

    /**
     * Divide
     * 
     * @return
     * @throws CustomException
     */
    private void divide() throws CustomException {
        if (numberStack.size() < 2) {
            throw new CustomException("operator / (position: " + index
                + " : insufficient parameters");
        }
        BigDecimal firstArg = numberStack.pop();
        BigDecimal secondArg = numberStack.pop();
        numberStack.push(secondArg.divide(firstArg));
    }

    /**
     * squareroot
     * 
     * @throws CustomException
     *
     */
    private void squareroot() throws CustomException {
        if (numberStack.size() < 1) {
            throw new CustomException("operator sqrt (position: " + index
                + " : insufficient parameters");
        }
        BigDecimal bd1 = numberStack.pop();
        BigDecimal bd2 =
            BigDecimal.valueOf(java.lang.Math.sqrt(bd1.doubleValue()));
        numberStack.push(bd2);
    }

    /**
     * Clear all history
     * 
     */

    private void clear() {
        numberStack.clear();
        undoStack.clear();
    }

    /**
     * Undo
     */
    
    private void undo() throws CustomException {

        if (undoStack.isEmpty()) {
            numberStack.pop();
        } else {
            UnDoOperation lastOperation = undoStack.pop();

            UnDoOperation udp = new UnDoOperation();

            udp.setOperation(lastOperation.getOperation());
            udp.setTopElement(numberStack.pop());
            udp.setPriorElement(lastOperation.getPriorElement());
            BigDecimal val = udp.undo();

            if (lastOperation.getPriorElement() != null) {
                numberStack.push(lastOperation.getPriorElement());
            }
            numberStack.push(val);

        }
    }

    public Stack<BigDecimal> getNumberStack() {
        return numberStack;
    }

    public void setNumberStack(Stack<BigDecimal> numberStack) {
        this.numberStack = numberStack;
        this.numArr = new BigDecimal[numberStack.size()];
        numberStack.copyInto(numArr);
    }

}

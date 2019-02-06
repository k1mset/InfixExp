/**
 * File: InfixEval.java
 * Date: 8/31/2018
 * Author: Dillan Cobb
 * Purpose: Handles all of the calculations with in the program, as well as 
 * handles what goes in and out of the stacks used for the calculations. Feeds
 * that information back to visually see the result on the screen.
 */

// Imports
import java.util.*;

public class InfixEval<E> {
    
    // checkString method checks the contents of the string that has been entered
    // for calculation, and works that calculation
    public int checkString(String string) {
        // Breaks the string down into an array for use and creates the stacks
        char[] expChars = string.toCharArray();
        Stack operandStack = new Stack();
        Stack operatorStack = new Stack();
        
        // For loop for checking and sorting the contents of the string array
        // expChars
        for (int i=0; i<expChars.length; i++) {
            char currChar = expChars[i];
            
            // if the currChar is a number, then it is pushed into the operand
            // stack
            if (Character.isDigit(currChar)) {
                operandStack.push(currChar);
            }
            
            // if the currChar is a operand for calculation it is pushed into the 
            // operand stack
            else if (currChar == '+' || currChar == '-' || 
                        currChar == '*' || currChar == '/') {
                operatorStack.push(currChar);
            }
            
            // if the currChar is a left parenthesis it is pushed into the 
            // operand stack
            else if (currChar == '(') {
                operatorStack.push(currChar);
            }
            
            // if the currChar is a right parenthesis, the program must search
            // the stacks to figure out what to do next
            else if (currChar == ')') {
                // loads the top and next operators for checking
                char operatorTop = operatorStack.pop().toString().charAt(0);
                char nextOperatorCheck = operatorStack.peek().toString().
                        charAt(0);
                
                // if the next operator up after the top is a left parenthesis
                // calculates the current top of the stack and trashes
                // the left parenthesis
                if (nextOperatorCheck == '(') {
                    int result = calcResult(operandStack, operatorTop);
                    operatorStack.pop().toString().charAt(0);
                        
                    operandStack.push(result);   
                }
                // if the next operator up after the top is not a left parenthesis
                // conduct more calculations
                else {
                    
                    // While the next operator is not a left parenthesis,
                    // calculate contents of equation
                    while (nextOperatorCheck != '(') {
                        int currOperatorOrder;
                        int nextOperatorOrder;
                        
                        // checks the order of operations compared to
                        // their mathematical operator with orderCheck method
                        currOperatorOrder = orderCheck(operatorTop);
                        nextOperatorOrder = orderCheck(nextOperatorCheck);
                        
                        // if operator stack is not empty and the current 
                        // operator has higher priority than the next operator
                        // than conduct calculation
                        if (!operatorStack.isEmpty() && currOperatorOrder >=
                                nextOperatorOrder) {
                            int result = calcResult(operandStack, operatorTop);

                            operandStack.push(result);  
                            operatorTop = operatorStack.pop().toString().
                                    charAt(0);
                            nextOperatorCheck = operatorStack.peek().toString().
                                    charAt(0);
                        }
                    }
                    
                    // the left operator is the next operator in the stack,
                    // so conduct calculation and trash the left parenthesis
                    int result = calcResult(operandStack, operatorTop);
                    operatorStack.pop().toString().charAt(0);
                    
                    operandStack.push(result);
                }
            }
        }
        
        // after the loop thru the array has been complete, calculate the remaining
        // operators in the operator stack
        while(!operatorStack.isEmpty()) {
            
            char operatorTop = operatorStack.pop().toString().charAt(0);
            
            // for checking if the next operands order of operations is correct
            if (!operatorStack.isEmpty()) {
                char nextOperatorCheck = operatorStack.peek().toString().
                        charAt(0);
                
                int currOperatorOrder;
                int nextOperatorOrder;

                currOperatorOrder = orderCheck(operatorTop);
                nextOperatorOrder = orderCheck(nextOperatorCheck);
                
                if (!operatorStack.isEmpty() && currOperatorOrder >=
                    nextOperatorOrder) {
                    int result = calcResult(operandStack, operatorTop);

                    operandStack.push(result);
                }
                else {
                    char tempOperand = operandStack.pop().toString().charAt(0);
                    char tempOperator = operatorTop;
                    operatorTop = operatorStack.pop().toString().charAt(0);
                    
                    int result = calcResult(operandStack, operatorTop);
                    
                    operandStack.push(result);
                    operandStack.push(tempOperand);
                    operatorStack.push(tempOperator);
                }
                
            }
            // if nothing to check, conduct calculation
            else {
                int result = calcResult(operandStack, operatorTop);

                operandStack.push(result);  
            }
        }
        
        // returns the final result, or the last item in the operand stack, 
        // which is the total of the entire calculation
        int finalResult = Integer.parseInt(String.valueOf(operandStack.pop()));
        
        return finalResult;
    }
    
    // orderCheck method that checks the order of operations when attempting
    // a calculation. According to PEMDAS. Returns a int that represents the
    // operands operational order from 3 being division, 2 being multiplication,
    // and 1 being subrtraction and addition
    private int orderCheck(char operator) {
        int operatorOrder;
        if (operator == '+' || operator == '-') {
            operatorOrder = 1;
        }
        else if (operator == '*') {
            operatorOrder = 2;
        }
        else {
            operatorOrder = 3;
        }
        
        return operatorOrder;
    }
    
    // calcResult method that calculates the results of the top two operands
    // and the top operator, returns the results of that calculation
    private int calcResult(Stack operandStack, char operatorType) {
        int result = 0;
        int operandTwo = Integer.parseInt(String.valueOf(operandStack.pop()));
        int operandOne = Integer.parseInt(String.valueOf(operandStack.pop()));
        char operatorPop = operatorType;

        if (operatorPop == '*') {
            result = operandOne * operandTwo;
        }
        // REFUSES TO CALCULATE IF DIVIDE BY ZERO
        else if (operatorPop == '/') {
            if (operandTwo == 0) {
                System.out.print("Unable to divide by zero.\n");
            }
            else {
                result = operandOne / operandTwo;
            }
        }
        else if (operatorPop == '+') {
            result = operandOne + operandTwo;
        }
        else {
            result = operandOne - operandTwo;
        }

        return result;
    }
}

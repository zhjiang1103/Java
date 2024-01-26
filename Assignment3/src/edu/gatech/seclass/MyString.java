package edu.gatech.seclass;

public class MyString implements MyStringInterface {
    String input=null; //Set default null to string

    public String getString() {

        return this.input;               //return input string
    }

    public void setString(String string) {
        this.input=string;              //set the value of the current string
    }

    public int countAlphabeticWords() {
        if(this.input==null) {       //if string is null throw exception
            throw new NullPointerException("Current string is null");
        }

        int count=0;
        boolean inWord = false;

        for(char c : this.input.toCharArray()) {
            if(Character.isLetter(c)) {
                if(!inWord){
                    count ++;
                    inWord = true;
                }
            }
            else {
                inWord = false;
            }
        }

        return count;

    }


    public String encrypt(int arg1, int arg2){
        if(this.input==null){
            throw new NullPointerException("String is null");
        }
        else{
            if(!isCoPrime(arg1) || arg1<0 || arg1>=62 ){
                throw new IllegalArgumentException("arg1 is invalid");
            }
            if(arg2<1 || arg2>=62){
                throw new IllegalArgumentException("arg2 is invalid");
            }
        }
        StringBuilder encryptedString = new StringBuilder();

        for (char c : this.input.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                int value = charToNumericValue(c);
                int encryptedValue = (arg1 * value + arg2) % 62;
                char encryptedChar = numericValueToChar(encryptedValue);
                encryptedString.append(encryptedChar);
            } else {
                encryptedString.append(c); // Non-alphanumeric characters remain unchanged
            }
        }

        return encryptedString.toString();

    }
    private boolean isCoPrime(int a) {
        return a % 2 == 1;
    }
    private int charToNumericValue(char c){
        if (Character.isDigit(c)) {
            return Character.getNumericValue(c);
        } else if (Character.isUpperCase(c)) {
            return c - 'A' + 10;
        } else if (Character.isLowerCase(c)) {
            return c - 'a' + 36;
        } else {
            throw new IllegalArgumentException("Invalid character: " + c);
        }
    }

    private char numericValueToChar(int value){
        if (value >= 0 && value <= 9) {
            return (char) ('0' + value);
        } else if (value >= 10 && value <= 35) {
            return (char) ('A' + value - 10);
        } else if (value >= 36 && value <= 61) {
            return (char) ('a' + value - 36);
        } else {
            throw new IllegalArgumentException("Invalid numeric value: " + value);
        }
    }


    public void convertDigitsToNamesInSubstring(int firstPosition, int finalPosition){
        if(this.input == null){
            throw new NullPointerException("String is null");
        }
        else{
            if(firstPosition >= 1 && firstPosition <= finalPosition) {
                if(finalPosition > this.input.length()) {
                    throw new IndexOutOfBoundsException("finalPosition is out of bounds");
                }
            }
            else{
                throw new IllegalArgumentException("Invalid firstPosition");
            }
        }
        StringBuilder convertedDigitsString = new StringBuilder();
        for (int i =0; i < this.input.length(); i++) {
            char currentChar = this.input.charAt(i);
            if (i + 1 >= firstPosition && i + 1 <= finalPosition && Character.isDigit(currentChar)) {
                String digitName = convertDigitToName(currentChar);
                convertedDigitsString.append(digitName);
            } else {
                convertedDigitsString.append(currentChar);
            }
        }
        // Update the current string with the modified result
        this.input = convertedDigitsString.toString();
    }

    private String convertDigitToName(char digit){
        // Convert a single digit to its name (e.g., '0' to "Zero", '1' to "One", etc.)
        return switch (digit) {
            case '0' -> "Zero";
            case '1' -> "One";
            case '2' -> "Two";
            case '3' -> "Three";
            case '4' -> "Four";
            case '5' -> "Five";
            case '6' -> "Six";
            case '7' -> "Seven";
            case '8' -> "Eight";
            case '9' -> "Nine";
            default -> String.valueOf(digit);
        };
    }
}

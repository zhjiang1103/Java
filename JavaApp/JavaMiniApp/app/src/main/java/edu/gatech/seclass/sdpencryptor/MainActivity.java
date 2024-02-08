package edu.gatech.seclass.sdpencryptor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonEncrypt;
    EditText entryText, arg1, arg2;
    TextView answer;
    int num1 = 1, num2 = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonEncrypt = findViewById(R.id.encryptButtonID);
        entryText = findViewById(R.id.entryTextID);
        arg1 = findViewById(R.id.argInput1ID);
        arg2 = findViewById(R.id.argInput2ID);
        answer = findViewById(R.id.textEncryptedID);
        buttonEncrypt.setOnClickListener(this);
    }

    @Override
    public void onClick (View view){
        num1 = Integer.parseInt(arg1.getText().toString());
        num2 = Integer.parseInt(arg2.getText().toString());
        if (view.getId() == R.id.encryptButtonID) {
            if (isValidInput(entryText.getText().toString(), arg1.getText().toString(), arg2.getText().toString())) {
                String result = encrypt(entryText.getText().toString(), num1, num2);
                answer.setText(result);
            } else {
                answer.setText("");
            }
        }
    }




        private boolean isEmptyOrNonAlphanumeric (String input){
            // Check if the input is empty or contains only non-alphanumeric characters
            return input.isEmpty() || !input.matches(".*[a-zA-Z0-9].*");
        }

        private boolean isValidInput (String input1, String param1, String param2){
            entryText.setError(null);
            arg1.setError(null);
            arg2.setError(null);

            boolean isValid = true;
            // Check and set errors for each input field
            if (isEmptyOrNonAlphanumeric(input1)) {
                entryText.setError("Invalid Entry Text");
                isValid = false;
            }

            if (param1.isEmpty()) {
                arg1.setError("Invalid Arg Input 1");
                isValid = false;
            } else {
                if (num1 % 2 == 0 || num1 < 1 || num1 > 61) {
                    arg1.setError("Invalid Arg Input 1");
                    isValid = false;
                }
            }

            if (param2.isEmpty()) {
                arg2.setError("Invalid Arg Input 2");
                isValid = false;
            } else {
                if (num2 < 1 || num2 > 62) {
                    arg2.setError("Invalid Arg Input 2");
                    isValid = false;
                }

            }
            return isValid;
        }

        public String encrypt (String text,int num1, int num2){
            StringBuilder encryptedString = new StringBuilder();
            for (char c : text.toCharArray()) {
                if (Character.isLetterOrDigit(c)) {
                    int value = charToNumericValue(c);
                    int encryptedValue = (num1 * value + num2) % 62;
                    char encryptedChar = numericValueToChar(encryptedValue);
                    encryptedString.append(encryptedChar);
                } else {
                    encryptedString.append(c); // Non-alphanumeric characters remain unchanged
                }
            }

            return encryptedString.toString();

        }

        private int charToNumericValue ( char c){
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

        private char numericValueToChar ( int value){
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


    }


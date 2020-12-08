package com.example.week3_code_challenge_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import org.apache.commons.lang3.StringUtils;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText emailEditText = findViewById(R.id.emailEditText);
        EditText phoneEditText = findViewById(R.id.phoneEditText);
        Button submitButton = findViewById(R.id.submitButton);
        CheckBox terms = findViewById(R.id.termsCheckbox);

        if (!areFieldsNotBlank(emailEditText.getText().toString(), phoneEditText.getText().toString(), terms))
            submitButton.setEnabled(false);

        terms.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                submitButton.setEnabled(areFieldsNotBlank(emailEditText.getText().toString(), phoneEditText.getText().toString(), terms) && validFields(emailEditText.getText().toString(), phoneEditText.getText().toString()));
            }
        });

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkFieldsRequired(emailEditText, phoneEditText, terms, submitButton);
            }

            @Override
            public void afterTextChanged(Editable s) {
                checkFieldsRequired(emailEditText, phoneEditText, terms, submitButton);
                if (validFields(emailEditText.getText().toString(), phoneEditText.getText().toString()) && terms.isChecked()) {
                    submitButton.setEnabled(true);
                } else {
                    submitButton.setEnabled(false);
                    if (!isEmailValid(emailEditText.getText().toString())) {
                        emailEditText.setError("Fill the input with a valid email address");
                    } else {
                        phoneEditText.setError("Fill the input with a valid email phone number");
                    }
                }
            }
        };

        emailEditText.addTextChangedListener(textWatcher);
        emailEditText.addTextChangedListener(textWatcher);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    private void checkFieldsRequired(EditText emailEditText, EditText phoneEditText, CheckBox terms, Button submitButton) {
        if (!areFieldsNotBlank(emailEditText.getText().toString(), phoneEditText.getText().toString(), terms)) {
            setErrorMessage(emailEditText, phoneEditText, "This field cannot be empty!");
        }
    }

    private void setErrorMessage(EditText email, EditText phone, String message) {
        if (!isEmailNotBlank(email.getText().toString()))
            email.setError(message);
        if (!isPhoneNotBlank(phone.getText().toString()))
            phone.setError(message);

    }

    private boolean areFieldsNotBlank(String email, String phone, CheckBox terms) {
        return isEmailNotBlank(email) && isPhoneNotBlank(phone) && terms.isChecked();
    }

    private boolean isEmailNotBlank(String email) {
        return StringUtils.isNotBlank(email);
    }

    private boolean isPhoneNotBlank(String phone) {
        return StringUtils.isNotBlank(phone);
    }

    private boolean validFields(String email, String phone) {
        return isEmailValid(email) && isPhoneValid(phone);
    }

    private boolean isEmailValid(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isPhoneValid(String phone) {
        return Patterns.PHONE.matcher(phone).matches();
    }

}
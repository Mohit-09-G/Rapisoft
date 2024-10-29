package com.example.rapisoft.Login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

import com.example.rapisoft.Database.TestDatabase;
import com.example.rapisoft.R;

public class Sign_up_Activity extends AppCompatActivity {
    public TextView logintext;
    public AppCompatButton register_button;
    public AutoCompleteTextView role_spiner, cate_spiner;
    public EditText name, phone, password, email;
    private TestDatabase testDatabase;
    private LinearLayout category_linerlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getWindow().setStatusBarColor(ContextCompat.getColor(Sign_up_Activity.this,R.color.olive));
        views();

        testDatabase = new TestDatabase(this);

        setupSpinner(role_spiner, new String[]{"Worker", "Employee"});
        setupSpinner(cate_spiner, new String[]{"Electrician", "Carpenter", "Plumber"});

        role_spiner.setOnItemClickListener((parent, view, position, id) -> {
            String selectedRole = role_spiner.getText().toString().trim();
            if (selectedRole.equals("Employee")) {
                category_linerlay.setVisibility(View.GONE);
                cate_spiner.setText("");
            } else {
                category_linerlay.setVisibility(View.VISIBLE);
            }
        });

        logintext.setOnClickListener(v -> startActivity(new Intent(Sign_up_Activity.this, Sign_in_Activity.class)));
        register_button.setOnClickListener(v -> registrationData());
    }

    public void views() {
        logintext = findViewById(R.id.already_account_text);
        role_spiner = findViewById(R.id.role_spinner);
        cate_spiner = findViewById(R.id.category_spinner);
        //edit texts
        email = findViewById(R.id.email_input);
        phone = findViewById(R.id.phone_input);
        password = findViewById(R.id.password_input);
        name = findViewById(R.id.name_input);
        register_button = findViewById(R.id.register_button);
        category_linerlay = findViewById(R.id.category_layout);
    }

    private void setupSpinner(AutoCompleteTextView spinner, String[] items) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void registrationData() {
        TextView loginText = findViewById(R.id.login_text);

        String ed_name = name.getText().toString().trim();
        String ed_phone = phone.getText().toString().trim();
        String ed_email = email.getText().toString().trim();
        String ed_password = password.getText().toString().trim();
        String ed_role = role_spiner.getText().toString().trim();
        String ed_category = cate_spiner.equals("Employee") ? null : cate_spiner.getText().toString().trim();
        String status;

        if (ed_name.isEmpty()) {
            Toast.makeText(this, "Please fill in the name field", Toast.LENGTH_SHORT).show();
            return;
        }
        if (ed_phone.isEmpty()) {
            Toast.makeText(this, "Please fill in the phone field", Toast.LENGTH_SHORT).show();
            return;
        }
        if (ed_email.isEmpty()) {
            Toast.makeText(this, "Please fill in the email field", Toast.LENGTH_SHORT).show();
            return;
        }
        if (ed_password.isEmpty()) {
            Toast.makeText(this, "Please fill in the password field", Toast.LENGTH_SHORT).show();
            return;
        }

        String namePattern = "^[a-zA-Z\\s]+$";
        String phonePattern = "^[6-9]\\d{9}$";
        String emailPattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        String passwordPattern = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";

        if (!ed_name.matches(namePattern)) {
            name.setError("Name should contain only letters and spaces.");
            return;
        }
        if (!ed_phone.matches(phonePattern)) {
            phone.setError("Enter a valid 10-digit phone number starting with 6, 7, 8, or 9.");
            return;
        }
        if (!ed_email.matches(emailPattern)) {
            email.setError("Enter a valid email address.");
            return;
        }
        if (!ed_password.matches(passwordPattern)) {
            password.setError("Password must be at least 8 characters long with at least one letter and one number.");
            return;
        }

        if (testDatabase.checkEmail(ed_email)) {
            email.setError("Email number already registered.");
            return;
        }

        boolean isInserted = testDatabase.insertLoginData(ed_phone, ed_name,ed_email, ed_password,  ed_role, ed_category);
        if (isInserted) {
            status = "Registration successful!";
            Log.d("DatabaseInsert", "Data inserted successfully.");
            startActivity(new Intent(this, Sign_in_Activity.class));
            finish();
        } else {
            status = "Registration failed.";
            Log.d("DatabaseInsert", "Data inserted unsuccessfully.");
        }

        loginText.setText(status);
        Toast.makeText(this, status, Toast.LENGTH_SHORT).show();
    }
}

package com.example.rapisoft.Login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

import com.example.rapisoft.Database.SessionManager;
import com.example.rapisoft.Database.TestDatabase;
import com.example.rapisoft.Employee.MainActivity;
import com.example.rapisoft.R;
import com.example.rapisoft.Worker_Activity;

public class Sign_in_Activity extends AppCompatActivity {

    private TextView registerText;
    public EditText email,password;
    private TestDatabase testDatabase;
    private AppCompatButton loginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        getWindow().setStatusBarColor(ContextCompat.getColor(Sign_in_Activity.this,R.color.olive));

        initializeViews();
        setListeners();


      loginButton.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              loginData1();
          }
      });
    }

    private void initializeViews() {
        testDatabase = new TestDatabase(this);
        registerText = findViewById(R.id.register_text);
        email=findViewById(R.id.email_input);
        loginButton=findViewById(R.id.login_button);
        password=findViewById(R.id.password_input);
    }

    private void setListeners() {
        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(Sign_in_Activity.this, Sign_up_Activity.class));
            }
        });
    }


    private void loginData() {
        String ed_email = email.getText().toString().trim();
        String ed_password = password.getText().toString().trim();

        if (ed_email.isEmpty()) {
            email.setError("Please fill in the email field");
            email.requestFocus();
            Log.w("LoginData", "Email field is empty.");
            return;
        }
        if (ed_password.isEmpty()) {
            password.setError("Please fill in the password field");
            password.requestFocus();
            Log.w("LoginData", "Password field is empty.");
            return;
        }
        if (!ed_email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            email.setError("Please enter a valid email address");
            email.requestFocus();
            Log.w("LoginData", "Invalid email format: " + ed_email);
            return;
        }

        // Check user credentials
        if (testDatabase.checkUserCredentialsWithRole(ed_email, ed_password)) {
            String userRole = testDatabase.getUserRole(ed_email, ed_password);
            if (userRole != null) {
                Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
                Log.i("LoginData", "User logged in successfully: " + ed_email + ", Role: " + userRole);

                if (userRole.equals("Employee")) {
                    startActivity(new Intent(Sign_in_Activity.this, MainActivity.class));
                } else if (userRole.equals("Worker")) {
                    startActivity(new Intent(Sign_in_Activity.this, Worker_Activity.class));
                }
                finish();
            } else {
                Log.e("LoginData", "User role retrieval failed for: " + ed_email);
            }
        } else {
            Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show();
            Log.w("LoginData", "Invalid credentials for email: " + ed_email);
        }
    }
    private void loginData1() {
        String ed_email = email.getText().toString().trim();
        String ed_password = password.getText().toString().trim();

        // Validate email input
        if (ed_email.isEmpty()) {
            email.setError("Please fill in the email field");
            email.requestFocus();
            Log.w("LoginData", "Email field is empty.");
            return;
        }

        // Validate password input
        if (ed_password.isEmpty()) {
            password.setError("Please fill in the password field");
            password.requestFocus();
            Log.w("LoginData", "Password field is empty.");
            return;
        }

        // Validate email format
        if (!ed_email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            email.setError("Please enter a valid email address");
            email.requestFocus();
            Log.w("LoginData", "Invalid email format: " + ed_email);
            return;
        }


        String userRole = testDatabase.checkUserCredentialsAndRole(ed_email, ed_password);
        if (userRole != null) {

             String userName = testDatabase.getUserName(ed_email);


            SessionManager sessionManager = new SessionManager(this);
            sessionManager.createLoginSession(userName, ed_email);
            Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
            Log.i("LoginData", "User logged in successfully: " + ed_email + ", Role: " + userRole);


            if (userRole.equals("Employee")) {
                startActivity(new Intent(Sign_in_Activity.this, MainActivity.class));
            } else if (userRole.equals("Worker")) {
                startActivity(new Intent(Sign_in_Activity.this, Worker_Activity.class));
            }
            finish();
        } else {

            Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show();
            Log.w("LoginData", "Invalid credentials for email: " + ed_email);
        }
    }





}

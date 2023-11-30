package com.example.imhungry.gui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.imhungry.R;

public class LogInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        Button buttonLogin = findViewById(R.id.button_login);
        buttonLogin.setOnClickListener(v->{
            Log.d("ActivityMain", "haciendio click");

                EditText usuarioET = findViewById(R.id.text_view_username);
                EditText passwordET = findViewById(R.id.text_view_password);

                String usuario = usuarioET.getText().toString();
                String password = passwordET.getText().toString();

                if(TextUtils.isEmpty(usuario)){

                    Toast.makeText(LogInActivity.this, "Debes ingresar un usuario", Toast.LENGTH_SHORT).show();

                }else if(TextUtils.isEmpty(password)){

                    Toast.makeText(LogInActivity.this, "Debes ingresar la contraseña", Toast.LENGTH_SHORT).show();

                }else{

                    if(usuario.equals("Rau") && password.equals("123")){

                        Toast.makeText(LogInActivity.this, "Exito", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(this, MainMenuActivity.class);
                        startActivity(intent);

                    }else{

                        Toast.makeText(LogInActivity.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();

                    }
                }
        });

    }
}
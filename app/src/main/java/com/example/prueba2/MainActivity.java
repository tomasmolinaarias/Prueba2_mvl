package com.example.prueba2;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    private EditText etRut, etNombre, etApellido, etFecha;
    private Button btnEnviar, btnCerrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        referencias();
    }
    private void referencias(){
        etRut = findViewById(R.id.etRut);
        etNombre = findViewById(R.id.etNombre);
        etApellido = findViewById(R.id.etApellido);
        etFecha = findViewById(R.id.etFecha);
        btnEnviar = findViewById(R.id.btnEnviar);
        btnCerrar = findViewById(R.id.btnCerrar);
    }
}
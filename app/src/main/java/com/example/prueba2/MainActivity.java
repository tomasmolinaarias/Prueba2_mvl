package com.example.prueba2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Calendar;
import java.util.Locale;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText etRut, etNombre, etApellido, etFecha;
    private Button btnEnviar, btnCerrar;
    private TextView tvTerminos;
    private int edad = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        referencias();

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarCamposVacios();
                String fechaNac = etFecha.getText().toString();
                validarFechaNacimiento(fechaNac);
                if (etRut.getError() == null
                        && etNombre.getError() == null
                        &&  etApellido.getError() == null &&
                        etFecha.getError() == null) {
                    enviarDatos();
                }
            }
        });
        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tvTerminos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, terminos_condiciones.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        limpiarCampos();
    }


    // referencias
    private void referencias(){
        etRut = findViewById(R.id.etRut);
        etNombre = findViewById(R.id.etNombre);
        etApellido = findViewById(R.id.etApellido);
        etFecha = findViewById(R.id.etFecha);
        btnEnviar = findViewById(R.id.btnEnviar);
        btnCerrar = findViewById(R.id.btnCerrar);
        tvTerminos = findViewById(R.id.tvTerminos);
    }
    // Validación de campos vacíos
    private void validarCamposVacios(){
        if (etRut.getText().toString().isEmpty()) {
            etRut.setError(getString(R.string.error_rut));
        }

        if (etNombre.getText().toString().isEmpty()) {
            etNombre.setError(getString(R.string.error_nombre));
        }

        if (etApellido.getText().toString().isEmpty()) {
            etApellido.setError(getString(R.string.error_apellido));
        }

        if (etFecha.getText().toString().isEmpty()) {
            etFecha.setError(getString(R.string.error_fecha));
        }
    }
    // Validación de la fecha de nacimiento
    private void validarFechaNacimiento(String fechaNac) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        Date dateOfBirth = null;

        try {
            dateOfBirth = sdf1.parse(fechaNac);
        } catch (ParseException e) {
            try {
                dateOfBirth = sdf2.parse(fechaNac);
            } catch (ParseException ex) {
                etFecha.setError(getString(R.string.error_fecha_formato));
                return;
            }
        }
        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();
        dob.setTime(dateOfBirth);

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }

        edad = age;

        Log.d("EdadCalculada", "Edad: " + edad);

        if (edad < 0 || edad > 150) {
            etFecha.setError(getString(R.string.error_edad_invalida));
        }

        else if (edad < 18) {
            etFecha.setError(getString(R.string.error_menor_edad));
        }
    }
    // Enviar Datos a resultados
    private void enviarDatos() {
        Intent intent = new Intent(MainActivity.this, Resultados.class);
        intent.putExtra("rut", etRut.getText().toString());
        intent.putExtra("nombre", etNombre.getText().toString());
        intent.putExtra("apellido", etApellido.getText().toString());
        intent.putExtra("fechaNacimiento", etFecha.getText().toString());
        intent.putExtra("edad", edad); // Enviar la edad calculada
        startActivity(intent);
    }
    // Método para limpiar los campos EditText
    private void limpiarCampos() {
        etRut.setText("");
        etNombre.setText("");
        etApellido.setText("");
        etFecha.setText("");
    }
}

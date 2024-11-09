package com.example.prueba2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Resultados extends AppCompatActivity {
    private TextView tvRut, tvNombre, tvEdad, tvValidoHasta;
    private Button btSuscripcion, btnCancelar,btnConfirmar ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);

        referencias();
        obtener_mostrar_datos();
        btSuscripcion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                establecerFechaSuscripcion();
            }
        });
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Resultados.this, "Cliente ingresado", Toast.LENGTH_SHORT).show();
                finish(); //vuelve main
            }
        });
    }
    // Referencias a los TextView
    private void referencias(){
        tvRut = findViewById(R.id.tvRut);
        tvNombre = findViewById(R.id.tvNombre);
        tvEdad = findViewById(R.id.tvEdad);
        tvValidoHasta = findViewById(R.id.tvValidoHasta);
        btSuscripcion = findViewById(R.id.btSuscripcion);
        btnCancelar = findViewById(R.id.btnCancelar);
        btnConfirmar = findViewById(R.id.btnConfirmar);
    }
    private void obtener_mostrar_datos(){
        String rut = formatearRut(getIntent().getStringExtra("rut"));
        String nombre = getIntent().getStringExtra("nombre") + " " + getIntent().getStringExtra("apellido");
        int edad = getIntent().getIntExtra("edad", -1);
        String fechaNacimiento = getIntent().getStringExtra("fechaNacimiento");

        tvRut.setText(rut);
        tvNombre.setText(nombre);
        tvEdad.setText(String.valueOf(edad));
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 1);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String fechaCaducidad = sdf.format(calendar.getTime());
        tvValidoHasta.setText(fechaCaducidad);
    }
    private void establecerFechaSuscripcion() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 3);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String nuevaFechaCaducidad = sdf.format(calendar.getTime());

        Toast.makeText(this, "La fecha de suscripción ha sido cambiada a " + nuevaFechaCaducidad, Toast.LENGTH_SHORT).show();
    }
    private String formatearRut(String rut) {
        if (rut.length() < 8) {
            return rut;
        }
        String rutFormateado = rut.substring(0, rut.length() - 7) + "." +
                rut.substring(rut.length() - 7, rut.length() - 4) + "." +
                rut.substring(rut.length() - 4, rut.length() - 1) + "-" +
                rut.substring(rut.length() - 1);
        return rutFormateado;
    }

}
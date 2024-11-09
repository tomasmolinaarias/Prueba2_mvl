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
        // Obtener los datos enviados desde el MainActivity
        String rut = formatearRut(getIntent().getStringExtra("rut"));
        String nombre = getIntent().getStringExtra("nombre") + " " + getIntent().getStringExtra("apellido");
        int edad = getIntent().getIntExtra("edad", -1); // Valor por defecto -1 si no se recibe
        String fechaNacimiento = getIntent().getStringExtra("fechaNacimiento");

        // Mostrar los datos en los TextView
        tvRut.setText(rut);
        tvNombre.setText(nombre);
        tvEdad.setText(String.valueOf(edad));
        // Calcular la fecha de caducidad (un año a partir de hoy)
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 1); // Añadir un año a la fecha actual
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String fechaCaducidad = sdf.format(calendar.getTime());

        // Mostrar la fecha de caducidad
        tvValidoHasta.setText(fechaCaducidad);
    }
    private void establecerFechaSuscripcion() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 3); // Añadir 3 meses a la fecha actual
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String nuevaFechaCaducidad = sdf.format(calendar.getTime());

        // Actualizar el TextView con la nueva fecha de caducidad
        tvValidoHasta.setText(nuevaFechaCaducidad);

        // Mostrar el mensaje Toast
        Toast.makeText(this, "La fecha de suscripción ha sido cambiada a " + nuevaFechaCaducidad, Toast.LENGTH_SHORT).show();
    }
    private String formatearRut(String rut) {
        // Asegurarnos de que el rut tiene al menos 8 caracteres
        if (rut.length() < 8) {
            return rut; // Devuelve el rut sin formato si es muy corto
        }
        // Agregar puntos y guión al rut
        String rutFormateado = rut.substring(0, rut.length() - 7) + "." +
                rut.substring(rut.length() - 7, rut.length() - 4) + "." +
                rut.substring(rut.length() - 4, rut.length() - 1) + "-" +
                rut.substring(rut.length() - 1);
        return rutFormateado;
    }

}
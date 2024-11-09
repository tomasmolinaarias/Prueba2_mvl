package com.example.prueba2;

import android.os.Bundle;
import android.widget.TextView;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);

        referencias();
        obtener_mostrar_datos();
    }
    // Referencias a los TextView
    private void referencias(){
        tvRut = findViewById(R.id.tvRut);
        tvNombre = findViewById(R.id.tvNombre);
        tvEdad = findViewById(R.id.tvEdad);
        tvValidoHasta = findViewById(R.id.tvValidoHasta);
    }
    private void obtener_mostrar_datos(){
        // Obtener los datos enviados desde el MainActivity
        String rut = getIntent().getStringExtra("rut");
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
}
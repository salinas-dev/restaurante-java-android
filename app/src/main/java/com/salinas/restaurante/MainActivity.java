package com.salinas.restaurante;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.salinas.restaurante.model.OrdenRestaurante;

public class MainActivity extends AppCompatActivity {

    private Spinner spinnerMenu;
    private TextInputEditText editTextCantidad;
    private Button buttonCalcular;
    private Button buttonNuevo;
    private Button buttonSalir;
    private TextView textViewPrecioTotal;

    private OrdenRestaurante orden;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Asignar vistas
        spinnerMenu = findViewById(R.id.spinnerMenu);
        editTextCantidad = findViewById(R.id.editTextCantidad);
        buttonCalcular = findViewById(R.id.buttonCalcular);
        buttonNuevo = findViewById(R.id.buttonNuevo);
        buttonSalir = findViewById(R.id.buttonSalir);
        textViewPrecioTotal = findViewById(R.id.textPrecioTotak);


        // Configurar opciones del Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.menu_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMenu.setAdapter(adapter);

        // Configurar botón Calcular
        buttonCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tipoMenu = spinnerMenu.getSelectedItem().toString();
                int cantidad = Integer.parseInt(editTextCantidad.getText().toString());
                double precioUnitario = obtenerPrecioUnitario(tipoMenu);
                orden = new OrdenRestaurante(tipoMenu, cantidad, precioUnitario);
                textViewPrecioTotal.setText(String.format("Precio total: $%.2f", orden.getPrecioTotal()));
            }
        });

        // Configurar botón Nuevo
        buttonNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spinnerMenu.setSelection(0);
                editTextCantidad.setText("");
                textViewPrecioTotal.setText("");
            }
        });

        // Configurar botón Salir
        buttonSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private double obtenerPrecioUnitario(String tipoMenu) {
        switch (tipoMenu) {
            case "Menú del día":
                return 15.0;
            case "Menú infantil":
                return 10.0;
            case "Menú vegetariano":
                return 12.0;
            default:
                return 0.0;
        }
    }
}
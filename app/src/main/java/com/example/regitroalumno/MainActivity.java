package com.example.regitroalumno;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.regitroalumno.Modelo.Alumno;
import com.example.regitroalumno.database.Database;

public class MainActivity extends AppCompatActivity {
    ListView listaAlumnos;
    EditText edtId;
    Database database;

    // EL on create Se ejecuta al iniciar la aplicacion
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Instanseamos la clase Database
        database = new Database(this);
        // Creamos los enlaces con los componentes de nuestra activity_main.xml
        listaAlumnos = findViewById(R.id.lista);
        edtId = findViewById(R.id.txtId);
        // llamamos al metodo listarAlumnos
        listarAlumnos();
    }

    // Se ejecuta cuando se regresa a la actividad (Vuelve a la pantalla)
    @Override
    protected void onResume() {
        super.onResume();
        // llamamos al metodo listarAlumnos
        listarAlumnos();
    }

    // Este metodo lista lis alumnos en el ListView
    public void listarAlumnos() {
        // Creamos un ArrayAdapter para pasarlo al list view
        // El array recibe la clase donde se crea el tipo de lista y la lista de datos
        // de la Clase Database llamamos listar alumnos que nos devuelve una lista y eso lo pasamos
        ArrayAdapter<Alumno> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, database.listarAlumnos());
        // La lista recibe un adapter para poder generar los datos en pantalla
        // le pasamos el adapter creado anteriormente
        listaAlumnos.setAdapter(adapter);
    }

    // Nos traslada a la pantalla de registro
    public void irRegistrar(View view) {
        // Creamos un intent para movernos a la otra Activity
        // El intent recibe dos parametros al ser instanseado
        // La clase donde se encuentra y la clase a la que se dirige
        Intent registro = new Intent(this, RegistroActivity.class);
        // Indicamos que inici la actividad osea que cambie de pantalla
        startActivity(registro);
    }

    // elimina un registro
    public void eliminar(View view) {
        // creamos la variable entera y traemos los datos que el usuario ingreso
        // los datos vienen por defecto de tipo Texto y tenemos que convertirlo a entero parseando
        int id = Integer.parseInt(edtId.getText().toString());
        // de la clase databae llamamos el metodo eliminar alumno
        // este recibe el id del alumno y se lo pasamos
        database.eliminarAlmuno(id);
        listarAlumnos();
        // limpiamos la caja de texto
        edtId.setText("");
    }

}
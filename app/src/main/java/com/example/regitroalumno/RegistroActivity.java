package com.example.regitroalumno;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.regitroalumno.Modelo.Alumno;
import com.example.regitroalumno.database.Database;

public class RegistroActivity extends AppCompatActivity {
    EditText edtNombre,edtApellido,edtNota1,edtNota2;
    Database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        edtNombre = findViewById(R.id.txtNombre);
        edtApellido = findViewById(R.id.txtApellido);
        edtNota1 = findViewById(R.id.txtNota1);
        edtNota2 = findViewById(R.id.txtNota2);
        database = new Database(this);
    }

    public String obtenerValor(EditText edt){
        return edt.getText().toString();
    }

    public void procesarRegistro(View view) {

        String nombre = obtenerValor(edtNombre);
        String apellido = obtenerValor(edtApellido);
        int nota1 = Integer.parseInt(obtenerValor(edtNota1));
        int nota2 = Integer.parseInt(obtenerValor(edtNota2));
        double promedio = (nota1+nota2)/2;

        Alumno alumno = new Alumno(nombre,apellido,nota1,nota2,promedio);
        database.registrarAlumno(alumno);
        edtNombre.setText("");
        edtApellido.setText("");
        edtNota1.setText("");
        edtNota2.setText("");
    }
}
package com.example.regitroalumno.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.regitroalumno.Modelo.Alumno;

import java.util.ArrayList;
import java.util.List;

// En nuestra clase extendemos de SQLiteOpenHelper que es una clase con lo necesario para
// trabajar con bases de datos
public class Database extends SQLiteOpenHelper {
    // Declaracion de variables
    private final Context contexto;
    private static final String DATABASE_NAME = "ucv_semana9.db";
    private static final int DATABASE_VERSION = 1;
    // constructor de la Clase
    public Database(Context context) {
        // la palabra
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.contexto = context;
    }
    // Esto se ejecuta una sola ves
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Creamos una un String que contiene la sentencia SQL en este caso
        // la de crear tabla con lo necesario
        String query = "CREATE TABLE Alumno (\n" +
                "    id       INTEGER        PRIMARY KEY AUTOINCREMENT,\n" +
                "    nombre   VARCHAR (50)   NOT NULL,\n" +
                "    apellido VARCHAR (50)   NOT NULL,\n" +
                "    nota_a   INTEGER        NOT NULL,\n" +
                "    nota_b   INTEGER        NOT NULL,\n" +
                "    promedio NUMBER        NOT NULL\n" +
                ");\n";
        // ejecutamos la consulta
        db.execSQL(query);
    }
    // se ejecuta mas de ua ves
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // en caso exista una tabla anterior con el mismo nombre la elimina
        String query = "DROP TABLE IF EXISTS Alumno";
        db.execSQL(query);
        onCreate(db);
    }
    // Metodo para poder registar un alumno
    // Le pasamos por parametros el Objeto Alumno
    public void registrarAlumno(Alumno alumno){
        // creamos un objeto dababase y de la clase actual llammos al metodo getWritableDatabase
        // este nos proporcionara funciones para escribir a nuestro objeto
        SQLiteDatabase database =this.getWritableDatabase();
        // Creamos un contendor de valores
        ContentValues cv = new ContentValues();
        cv.put("nombre",alumno.getNombre());
        cv.put("apellido",alumno.getApellido());
        cv.put("nota_a",alumno.getNota_a());
        cv.put("nota_b",alumno.getNota_b());
        cv.put("promedio",alumno.getPromedio());
        // try catch lo usamos para evaluar si se genera un error y que no se rompa la aplicacion
        try {

            //ejecutamos el metodo insert de database
            // Este recive de parametros el nombre de la tabla y column hack qe de momento no es
            // necesario y el contendor de valores que vamos ingresar
            long result = database.insert("Alumno",null,cv);
            if (result == -1){
                Toast.makeText(contexto, "Algo fallo", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(contexto, "Alumno registrado", Toast.LENGTH_SHORT).show();
            }
        }catch (SQLException error){
            Toast.makeText(contexto, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    // Listar los alumnos - este metodo nos retorna una lista del objeto persona
    public List<Alumno> listarAlumnos(){
        // Creamos una cadena con la sentencia SQL a ejecutar
        String query = "SELECT * FROM Alumno";
        // creamos un objeto dababase y de la clase actual llammos al metodo getReadableDatabase
        // este nos proporcionara funciones de lectura
        SQLiteDatabase database = this.getReadableDatabase();
        // Creamos un objeto cursor que contendra todos los elementos
        // del objeto database ejecutamos el rawQquery que recibe la consulta
        // y en caso de que se use un where los argumentos para este caso no necesitamos y ponemos null
        Cursor cursor = database.rawQuery(query,null);
        // Creamos una lista de alumnos que es donde rellenaremos los registros
        List<Alumno> alumnos = new ArrayList<>();
        // while es un bucle que la condicion evalua una condicion antes de iniciar
        // en este caso le decimos si el cursor se puede mover hacia algun elemento
        // empieza el recorrido de esta forma nos aseguramos de que recorra siempre en cuado hayan
        // registros en la base de datos
        while (cursor.moveToNext()){
            // creamos un objeto alumno
            Alumno alumno = new Alumno();
            // le pasamos los datos del cursor
            alumno.setId(Integer.parseInt(cursor.getString(0)));
            alumno.setNombre(cursor.getString(1));
            alumno.setApellido(cursor.getString(2));
            alumno.setNota_a(Integer.parseInt(cursor.getString(3)));
            alumno.setNota_b(Integer.parseInt(cursor.getString(4)));
            alumno.setPromedio(Double.parseDouble(cursor.getString(5)));
            // le pasamos a la lista el objeto con los datos
            alumnos.add(alumno);
        }
        // retornamos la lista de alumnos
        return alumnos;
    }

    // Metodo para eliminar un alumno recibe de parametro el id en valor entero
    public void eliminarAlmuno(int id){
        try {
            SQLiteDatabase database =this.getWritableDatabase();
            // ejecutamos el metodo delete que recibe el nombre de la tabla
            // la condicion y el parametro
            database.delete("Alumno","id=?",new String[]{String.valueOf(id)});
            database.close();
        }catch (SQLException error) {
            Toast.makeText(contexto, ""+ error.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}

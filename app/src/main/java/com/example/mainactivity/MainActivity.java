package com.example.mainactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button permitir;
    //Atributo de tipo private que pertenece al EditText
    private EditText et_codigo, et_descripcion, et_precio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        

        //datos que  van a recibir el Id de la clase activity main
        et_codigo = (EditText) findViewById(R.id.txt_codigo);
        et_descripcion = (EditText) findViewById(R.id.txt_descripcion);
        et_precio = (EditText) findViewById(R.id.txt_precio);
    }

    //Métotdo para guardar lugares en al base de datos
    public void Registrar(View view) {

        //Objeto para crear el nombre de la base de datos con la version 1
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper
                (this, "administracion", null, 1);

        //Se abre la base de datos de tipo lectura y escritura
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String codigo = et_codigo.getText().toString();
        String descripcion = et_descripcion.getText().toString();
        String precio = et_precio.getText().toString();

        //Se creo un if para comparar los datos de registrados
        if (!codigo.isEmpty() && !descripcion.isEmpty() && !precio.isEmpty()) {
            ContentValues registro = new ContentValues();

            //Ingreso de los datos
            registro.put("codigo", codigo);
            registro.put("descripcion", descripcion);
            registro.put("precio", precio);

            //Insertamos los datos en la tabla articulos
            BaseDeDatos.insert("articulos", null, registro);

            //Cerrando base datos
            BaseDeDatos.close();
            et_codigo.setText("");
            et_descripcion.setText("");
            et_precio.setText("");

            //Si cumple la condicion mostrara un mensaje de "Registro existo" datos gurdados
            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_LONG).show();
        } else {

            //Si los datos no son ingresados muestra un mensaje de alerta
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_LONG).show();
        }
    }

    //Método para consultar los datos registrados en la base datos
    public void Buscar(View view) {

        //Objeto para crear el nombre de la base de datos con la version 1
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper
                (this, "administracion", null, 1);

        //Se abre la base de datos de tipo lectura y escritura
        SQLiteDatabase BaseDeDatabase = admin.getWritableDatabase();

        //Ingresar un id para la busqueda de datos en la base de datos
        String codigo = et_codigo.getText().toString();

        //
        if (!codigo.isEmpty()) {

            //Objeto para consulatar
            Cursor fila = BaseDeDatabase.rawQuery

                    //Mostrar los datos intrudiciendo un codigo regsitrado anteriormente
                            ("select descripcion, precio from articulos where codigo =" + codigo, null);

            //Arreglo
            if (fila.moveToFirst()) {
                et_descripcion.setText(fila.getString(0));
                et_precio.setText(fila.getString(1));
                BaseDeDatabase.close();
            } else {

                //Si el lugar no ha sido registrado se mostrara este mensaje
                Toast.makeText(this, "No existe el lugar", Toast.LENGTH_LONG).show();

                //Se cierra la base de datos
                BaseDeDatabase.close();
            }

        } else {

            //Este mesaje saldra si no se registro ningun dato antes de la consulta
            Toast.makeText(this, "Debes introducir el id del lugar", Toast.LENGTH_LONG).show();
        }
    }

    //Método para eliminar un lugar registrado de la base de datos
    public void Eliminar(View view) {

        //Objeto para crear el nombre de la base de datos con la version 1
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper
                (this, "administracion", null, 1);

        //Se abre la base de datos de tipo lectura y escritura
        SQLiteDatabase BaseDatabase = admin.getWritableDatabase();

        //Variable para solicitar el codigo y proceder a eliminar
        String codigo = et_codigo.getText().toString();

        //Condicion
        if (!codigo.isEmpty()) {

            //Se elimina de la tabla articulos con los datos.
            int cantidad = BaseDatabase.delete("articulos", "codigo=" + codigo, null);
            BaseDatabase.close();

            //Retornan dato vacios
            et_codigo.setText("");
            et_descripcion.setText("");
            et_precio.setText("");

            //Condicion para comparar los datos si se elinaron o no
            if (cantidad == 1) {

                //Se se eliminaron los datos se mostrara un mensaje confirmado
                Toast.makeText(this, "Lugar eliminado exitosamente", Toast.LENGTH_SHORT).show();
            } else {

                //Si no se eliminaron los datos significa que el lugar no esta registrdo o esta ingrsando mal el id
                Toast.makeText(this, "El lugar registrado no existe", Toast.LENGTH_SHORT).show();
            }

        } else {

            //No se a introducido un codigo del lugar
            Toast.makeText(this, "Debes de introducir el código del Lugar", Toast.LENGTH_SHORT).show();
        }
    }

    //Método para modificar un lugar registrado en la base de datos
    public void Modificar(View view) {

        //Objeto para crear el nombre de la base de datos con la version 1
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);

        //Se abre la base de datos de tipo lectura y escritura
        SQLiteDatabase BaseDatabase = admin.getWritableDatabase();

        //Sirven para cambiar en cualquier campo que se desee modificar
        String codigo = et_codigo.getText().toString();
        String descripcion = et_descripcion.getText().toString();
        String precio = et_precio.getText().toString();

        //Condicion
        if (!codigo.isEmpty() && !descripcion.isEmpty() && !precio.isEmpty()) {

            //Atributos para modificar en que campo se desee
            ContentValues registro = new ContentValues();
            registro.put("codigo", codigo);
            registro.put("descripcion", descripcion);
            registro.put("precio", precio);

            //Se se modifica en la tabla articulos con los datos.
            int cantidad = BaseDatabase.update("articulos", registro, "codigo=" + codigo, null);

            //Se cierra la base  detos
            BaseDatabase.close();

            //Condicion para modificar los datos ya modificados
            if (cantidad == 1) {

                //Si el dato fue modificado se mostrara un mensaje de confirmacion
                Toast.makeText(this, "Lugar modificado correctamente", Toast.LENGTH_LONG).show();
            } else {

                //Si el dato no fue modificado signidfica que le lugar  no esta registrado
                Toast.makeText(this, "El lugar no existe", Toast.LENGTH_LONG).show();
            }

        } else {

            //Se debe llenar los campos para proceder con el registro
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_LONG).show();
        }
    }

    //Metodo para ir a la siguiente clase
    public void Siguiente(View view) {
        Intent siguiente = new Intent(this, Mimapa.class);
        startActivity(siguiente);
    }
}

package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sqlite.bdd.BDHelper;

public class MainActivity extends AppCompatActivity {
    EditText et_cedula,et_nombre,et_apellido,et_correo;
    Button btnCrear;    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_cedula=findViewById(R.id.txtCedula);
        et_nombre=findViewById(R.id.txtNombre);
        et_apellido=findViewById(R.id.txtApellidos);
        et_correo=findViewById(R.id.txtCorreo);

    }
    //MÉTODO PARA CREAR+
    public void registrar(View view) {
        BDHelper admin = new BDHelper(this, "gestion.db", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String cedula=et_cedula.getText().toString();
        String nombre = et_nombre.getText().toString();
        String apellido = et_apellido.getText().toString();
        String correo = et_correo.getText().toString();
        //diferente de vacío
        if (!cedula.isEmpty() && !nombre.isEmpty() && !apellido.isEmpty() && !correo.isEmpty()) {
            ContentValues registro = new ContentValues();
            registro.put("usu_cedula",cedula);
            registro.put("usu_nombre", nombre);
            registro.put("usu_apellido", apellido);
            registro.put("usu_correo_electronico", correo);
            bd.insert("tblUsuarios", null, registro);
            bd.close();
            et_cedula.setText("");
            et_nombre.setText("");
            et_apellido.setText("");
            et_correo.setText("");
            Toast.makeText(this, "Registro Exitoso", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

    public void buscar(View view){
        BDHelper admin=new BDHelper(this,"gestion.db",null,1);
        //abrir la bdd modo lectura y escritura
        SQLiteDatabase db=admin.getWritableDatabase();

        String cedula=et_cedula.getText().toString();

        if(!cedula.isEmpty()){
            //consulta sql
            Cursor fila=db.rawQuery("select usu_nombre, usu_apellido, usu_correo_electronico from tblUsuarios where usu_cedula ="+cedula,null);
            //comprobar si la consulta dio resultado
            if(fila.moveToFirst()){
                et_nombre.setText(fila.getString(0));
                et_apellido.setText(fila.getString(1));
                et_correo.setText(fila.getString(2));
                db.close();
            }else{
                Toast.makeText(this,"El Usuario no existe",Toast.LENGTH_SHORT).show();
                db.close();
            }

        }else{
            Toast.makeText(this,"Ingrese un parámetro de búsqueda",Toast.LENGTH_SHORT).show();
        }


    }
}


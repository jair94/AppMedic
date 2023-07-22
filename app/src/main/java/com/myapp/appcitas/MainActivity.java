package com.myapp.appcitas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.myapp.appcitas.Model.User;
import com.myapp.appcitas.conection.Database_Administrator;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText txtEmail;
    private EditText txtPassword;
    private Button btnIniciar;
    private TextView txtRegistar;
    private Database_Administrator db;
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicialize();
        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validarEmpty()){
                    if(db.connectSQL()){

                        if(validarUsuarioOk()){
                            Toast.makeText(MainActivity.this,  "Bienvenido, "+txtEmail.getText().toString() , Toast.LENGTH_LONG ).show();
                            Intent intent_login = new Intent(MainActivity.this, Consult.class);
                            startActivity(intent_login);

                        }else {
                            Toast.makeText(MainActivity.this,  "Usuario y/Contraseña no Validos " , Toast.LENGTH_LONG ).show();
                        }
                    }else {
                        Toast.makeText(MainActivity.this,  "No hay conexión al servidor" , Toast.LENGTH_LONG ).show();
                    }

                }

            }
        });

        txtRegistar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_Register = new Intent(MainActivity.this, Register.class);
                startActivity(intent_Register);
            }
        });
    }

    private boolean validarUsuarioOk() {
        User user = db.consultTable(txtEmail.getText().toString(), txtPassword.getText().toString());
        if (user != null){
            return true;
        }else {
            return false;
        }
    }

    private boolean validarEmpty() {
        if(txtEmail.getText().toString().isEmpty() || txtPassword.getText().toString().isEmpty()){
            txtEmail.setError("Ingrese la Información solicitada");
            txtPassword.setError("Ingrese la Información solicitada");
            return false;
        }else{
            return true;
        }
    }

    private  void inicialize(){
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        btnIniciar = (Button)  findViewById(R.id.btnIniciar);
        txtRegistar = (TextView) findViewById(R.id.txtRegistrar);
        db = new Database_Administrator();
        user = new User();
    }



}
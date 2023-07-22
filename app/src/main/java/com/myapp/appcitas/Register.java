package com.myapp.appcitas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.myapp.appcitas.Model.User;
import com.myapp.appcitas.conection.Database_Administrator;

import java.util.ArrayList;

public class Register extends AppCompatActivity {

    private EditText txtNombres;
    private EditText txtApellidos;
    private Spinner txtTipoDocumento;

    private ArrayAdapter<String> listaTipyDocument;
    private EditText txtNumeroDocumento;
    private EditText txtEmail;

    private EditText txtPhone;
    private EditText txtFechanacimiento;

    private Button btnContinuar;

    private int itemSeleccionado;

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        inicialize();
        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Register.this,  "Cree una Contraseña para el usuario, "+txtEmail.getText()  , Toast.LENGTH_LONG ).show();
                Intent intent_login = new Intent(Register.this, Register2.class);
                listaFormulario(intent_login);
                intent_login.putExtra("user", user);
                startActivity(intent_login);
            }
        });
    }

    private void listaFormulario(Intent intent_login) {
         user = new User(txtNombres.getText().toString(), txtApellidos.getText().toString(),itemSeleccionado,
                Integer.parseInt(txtNumeroDocumento.getText().toString()),txtEmail.getText().toString(),
                Integer.parseInt(txtPhone.getText().toString()),txtFechanacimiento.getText().toString(),"");

    }

    private  void inicialize(){
        txtNombres = (EditText) findViewById(R.id.txtNombre);
        txtApellidos = (EditText) findViewById(R.id.txtApellidos);
        txtTipoDocumento = (Spinner) findViewById(R.id.spinner);
        txtTipoDocumento.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Register.this,  "Selecciono: , "+ parent.getItemAtPosition(position).toString() , Toast.LENGTH_LONG ).show();
                itemSeleccionado = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        txtNumeroDocumento = (EditText) findViewById(R.id.txtDocument);
        txtEmail = (EditText) findViewById(R.id.txtEmailRegister);
        txtFechanacimiento = (EditText) findViewById(R.id.txtDate);
        txtPhone = (EditText) findViewById(R.id.txtPhone);
        btnContinuar = (Button)  findViewById(R.id.btnContinuar);

    }
}
package com.myapp.appcitas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.myapp.appcitas.Model.User;
import com.myapp.appcitas.conection.Database_Administrator;

public class Register2 extends AppCompatActivity {

    private EditText txtPassword;
    private EditText txtPasswordConfirm;
    private CheckBox checkBox;

    private User user;

    private Bundle bundle;

    private Button btnConfirmar;

    private Database_Administrator db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        inicialize();
        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validarPasword()){
                    if(db.connectSQL()){
                        boolean confirmRegistro = db.insertRecord(user.getNombres(), user.getApellidos(), user.getTipoDoc(), user.getNumDoc(),
                                user.getEmail(), user.getPhone(), user.getFechanacimiento(),user.getPwd());
                        if(confirmRegistro){
                            Toast.makeText(Register2.this,  "Usuario Registrado, Bienvenido " + user.getNombres() , Toast.LENGTH_LONG ).show();
                            Intent intent_login = new Intent(Register2.this, Consult.class);
                            intent_login.putExtra("user", user);
                            startActivity(intent_login);
                        }else {
                            Toast.makeText(Register2.this,  "No ha sido posible Registrar al Usuario " , Toast.LENGTH_LONG ).show();
                        }
                    }else {
                        Toast.makeText(Register2.this,  "No hay conexión al servidor" , Toast.LENGTH_LONG ).show();
                    }

                }


            }
        });

    }

    private boolean validarPasword() {
        if (txtPassword.getText().toString().equals(txtPasswordConfirm.getText().toString())){
            Toast.makeText(Register2.this,  "Contraseña valida" , Toast.LENGTH_LONG ).show();
            user.setPwd(txtPassword.getText().toString());
            return true;
        }else {
            txtPasswordConfirm.setError("Las Contraseñas no coinciden");
            return false;

        }

    }

    private  void inicialize(){
        txtPassword = (EditText) findViewById(R.id.txtpaswordRegistro);
        txtPasswordConfirm = (EditText) findViewById(R.id.txtpaswordConfirm);
        checkBox = (CheckBox) findViewById(R.id.checkBox);

        bundle = getIntent().getExtras();
        user = (User) bundle.getSerializable("user");

        btnConfirmar = (Button)  findViewById(R.id.btnConfirmarRegistro);
        db = new Database_Administrator();
    }
    public void datosUsuario(){

    }
}
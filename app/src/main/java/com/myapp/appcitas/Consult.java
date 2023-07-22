package com.myapp.appcitas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Consult extends AppCompatActivity {

    private ImageView imgSolicitar;
    private ImageView imgConsultar;
    private ImageView imgCancelar;
    private ImageView imgEspecializacion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult);
        inicialize();
        imgSolicitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Consult.this,  "Solicitudes"  , Toast.LENGTH_LONG ).show();
                Intent intent_login = new Intent(Consult.this, SolicitarCitas.class);
                startActivity(intent_login);
            }
        });
        imgConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Consult.this,  "Consultas"  , Toast.LENGTH_LONG ).show();
                Intent intent_login = new Intent(Consult.this, ConsultarCitas.class);
                startActivity(intent_login);
            }
        });
        imgCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Consult.this,  "Cancelar"  , Toast.LENGTH_LONG ).show();
                Intent intent_login = new Intent(Consult.this, CancelarCitas.class);
                startActivity(intent_login);
            }
        });
        imgEspecializacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Consult.this,  "Especialidades"  , Toast.LENGTH_LONG ).show();
                Intent intent_login = new Intent(Consult.this, ConsultarEspecializacion.class);
                startActivity(intent_login);
            }
        });
    }

    private  void inicialize(){
        imgSolicitar=(ImageView) findViewById(R.id.imgRegistrarCita);
        imgConsultar=  (ImageView) findViewById(R.id.imgConsultarCita);
        imgCancelar= (ImageView) findViewById(R.id.imgCancelarCita);
        imgEspecializacion= (ImageView) findViewById(R.id.imgEspecializaciones);
    }
}
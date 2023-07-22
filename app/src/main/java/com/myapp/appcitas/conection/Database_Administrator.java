package com.myapp.appcitas.conection;

import android.os.StrictMode;
import android.util.Log;

import com.myapp.appcitas.Model.User;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Database_Administrator {

    Connection connect;
    //private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://34.71.169.233:3306/appmedic";
    private String user ="jair";
    private String pwd ="12345";

    String instructionSQL;

    public boolean connectSQL() {
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(url, user, pwd);
            Log.d("myTag", "Succesfull connection");
            return true;

        } catch (Exception err) {
            Log.d("myTag", err.toString());
            return false;
        }
    }

    public boolean insertRecord (String nombres, String apellidos, int tipoDoc, int cc, String email, int phone, String date, String pasword) {
        instructionSQL = "INSERT INTO users (Nombres, Apellidos, idTipoDocumento, numeroDocumento, correo, telefono, fechaNacimiento, contrasena) VALUES ('" + nombres + "', '" + apellidos + "', '"+ tipoDoc + "', '"
                + cc
                + "', '" + email + "', '" + phone + "', '" + date + "', '" + pasword +  "')";
        try {
            connect.prepareStatement(instructionSQL).execute();
            Log.d("myTag", "Data Registered");
            return true;
        } catch (Exception err) {
            Log.d("myTag", err.toString());
            return false;
        }
    }

    public User consultTable (String email, String pwd) {
        instructionSQL = "SELECT * FROM users where correo = '"+email+"' and contrasena = '"+pwd+"'";
        User datos = new User();
        boolean volidate = false;
        try {
            ResultSet results = connect.prepareStatement(instructionSQL).executeQuery();
            while (results.next()) {

                datos.setNombres(results.getString("Nombres"));
                datos.setApellidos(results.getString("Apellidos"));
                datos.setEmail(results.getString("correo") );
                datos.setNumDoc(Integer.parseInt(results.getString("numeroDocumento")));
                volidate = true;
                Log.d("MyUsuario",datos.getNombres());
            }
        } catch (Exception err) {
            Log.d("myTag", err.toString());
        }if(volidate){
            return datos;
        }else {
            return datos = null;
        }

    }
    public ArrayList<String> consultTableEspecialidades () {
        instructionSQL = "SELECT * FROM especialidades";
        ArrayList<String> datos = new ArrayList <String>();
        try {
            ResultSet results = connect.prepareStatement(instructionSQL).executeQuery();
            while (results.next()) {
                datos.add(results.getString("idEspecialidades") + " " + results.getString("nombreEspecialidad"));
            }
        } catch (Exception err) {
            Log.d("myTag", err.toString());
        }
        return datos;
    }
}

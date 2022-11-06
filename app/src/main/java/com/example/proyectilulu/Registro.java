package com.example.proyectilulu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.proyectilulu.Json.MyInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Registro extends AppCompatActivity {
    private Button conti;

    private EditText Usuario,Contraseña,Correo,Telefono,Fecha,edad;
    private RadioButton generom,generoh;
    private Switch switch1;
    private static final String TAG = "MainActivity";
    public static final String archivo = "archivo.json";
    String json = null;
    public static String usu,password,ecor,tel,dat,ed;
    public static boolean sw= false;
    public static boolean on;
    public static List<MyInfo> list =new ArrayList<MyInfo>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);


        conti = findViewById(R.id.continuarlogid);
        Button inicio = findViewById(R.id.inicio);
        Usuario = findViewById(R.id.usuariologid);
        Contraseña = findViewById(R.id.contralogid);
        Correo = findViewById(R.id.emailid);
        Telefono = findViewById(R.id.numeroid);
        Fecha = findViewById(R.id.fechaid);
        edad = findViewById(R.id.edadid);
        generom = findViewById(R.id.mujerid);
        generoh = findViewById(R.id.hombreid);
        switch1 = findViewById(R.id.switchid);
        Read();
        json2List(json);
        inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Registro.this, Login.class);
                startActivity(intent);
            }
        });

        conti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyInfo info= new MyInfo();

                usu= String.valueOf(Usuario.getText());
                password = String.valueOf(Contraseña.getText());
                ecor= String.valueOf(Correo.getText());
                tel = String.valueOf(Telefono.getText());
                dat = String.valueOf(Fecha.getText());
                ed = String.valueOf(edad.getText());

                if(generom.isChecked()==true){
                    on=true;
                }
                if(generoh.isChecked()==true){
                    on=true;
                }
                if(switch1.isChecked()){
                    sw= true;
                }

                if(usu.equals("")||password.equals("")||ecor.equals("")){
                    Log.d(TAG,"vacio");
                    Log.d(TAG,usu);
                    Log.d(TAG,password);
                    Log.d(TAG,ecor);
                    Toast.makeText(getApplicationContext(), "Completa los campos ><", Toast.LENGTH_LONG).show();
                }else{
                    if(Cluster.validarEmail(ecor)){
                        if(list.isEmpty()){
                            Log.d(TAG,"lleno");
                            Cluster.fillInfo(info);
                            List2Json(info,list);
                        }else{
                            if(Cluster.usuar(list,usu)){
                                Log.d(TAG,"Ya existe alguien más");
                                Toast.makeText(getApplicationContext(), "enserio, ¿Tienen la misma edad?", Toast.LENGTH_LONG).show();
                            }else{
                                Cluster.fillInfo(info);
                                List2Json(info,list);
                            }
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "Verifica el correo", Toast.LENGTH_LONG).show();
                    }

                }
            }
        });
    }
    public void List2Json(MyInfo info,List<MyInfo> list){
        Gson gson =null;
        String json= null;
        gson =new Gson();
        list.add(info);
        json =gson.toJson(list, ArrayList.class);
        if (json == null)
        {
            Log.d(TAG, "Error en json");
        }
        else
        {
            Log.d(TAG, json);
            writeFile(json);
        }
        Toast.makeText(getApplicationContext(), "Todo bien", Toast.LENGTH_LONG).show();
    }
    private boolean writeFile(String text){
        File file =null;
        FileOutputStream fileOutputStream =null;
        try{
            file=getFile();
            fileOutputStream = new FileOutputStream( file );
            fileOutputStream.write( text.getBytes(StandardCharsets.UTF_8) );
            fileOutputStream.close();
            Log.d(TAG, "Bienvenido ><");
            return true;
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return false;
    }
    private File getFile(){
        return new File(getDataDir(),archivo);
    }
    public boolean Read(){
        if(!isFileExits()){
            return false;
        }
        File file = getFile();
        FileInputStream fileInputStream = null;
        byte[] bytes = null;
        bytes = new byte[(int)file.length()];
        try {
            fileInputStream = new FileInputStream(file);
            fileInputStream.read(bytes);
            json=new String(bytes);
            Log.d(TAG,json);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
    private boolean isFileExits( )
    {
        File file = getFile( );
        if( file == null )
        {
            return false;
        }
        return file.isFile() && file.exists();
    }
    public void json2List( String json)
    {
        Gson gson = null;
        String mensaje = null;
        if (json == null || json.length() == 0)
        {
            Toast.makeText(getApplicationContext(), "Error json null or empty", Toast.LENGTH_LONG).show();
            return;
        }
        gson = new Gson();
        Type listType = new TypeToken<ArrayList<MyInfo>>(){}.getType();
        list = gson.fromJson(json, listType);
        if (list == null || list.size() == 0 )
        {
            Toast.makeText(getApplicationContext(), "Error list is null or empty", Toast.LENGTH_LONG).show();
            return;
        }
    }
}
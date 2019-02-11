package com.example.dam2a16.killerremote;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class KillerActivity extends AppCompatActivity {

    private EditText ip,puerto,msg;
    private Button conect;
    private String message;
    private Socket cliente;
    private PrintWriter pw;
    private int port = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_killer);

        ip = (EditText) findViewById(R.id.dirip);
        puerto = (EditText) findViewById(R.id.puerto);
        msg = (EditText) findViewById(R.id.mensaje);
        conect = (Button) findViewById(R.id.conect);
    }

    public void enviar(View vw){

        message = msg.getText().toString();
        port = Integer.parseInt(puerto.getText().toString());
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    cliente = new Socket(ip.getText().toString(),port);
                    pw = new PrintWriter(cliente.getOutputStream());
                    pw.write(message);
                    pw.flush();
                    pw.close();
                    cliente.close();
                }
                catch(IOException ex){
                    Log.d("Error","No se pudo encontrar el socket");

                }
            }
        }).start();
    }
}

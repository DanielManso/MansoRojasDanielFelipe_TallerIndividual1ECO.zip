package com.example.mansot1;

import android.util.Log;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.Socket;

class Cliente extends Thread {

    Socket s;
    public static Cliente reference;

    private Cliente (){

    }

    @Override
    public void run() {

        try{

            s = new Socket("10.0.2.2", 5000);

        } catch (Exception e){

            e.printStackTrace();
        }
    }

    public void enviar (final String msj) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {

                    OutputStream os = s.getOutputStream();
                    Log.e("ENVIAR", "ME PREPARO PARA ENVIAR UN MENSAJE");

                    PrintWriter mensaje = new PrintWriter( new OutputStreamWriter(os));
                    mensaje.println(msj);

                    mensaje.flush();

                } catch ( Exception e ) {

                    e.printStackTrace();
                }
            }
        }).start();

    }

    public synchronized static Cliente getInstance(){
        if(reference==null){
            reference = new Cliente();
            reference.start();
        }
        return reference;
    }
}

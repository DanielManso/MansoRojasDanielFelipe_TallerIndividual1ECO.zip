package com.example.mansot1;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Cliente c;
    Button btnMensajeDos;
    Button btnMensajeCuatro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        c = Cliente.getInstance();


        btnMensajeDos = findViewById(R.id.btn_mensaje0);
        btnMensajeDos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                c.enviar("2");
            }
        });

        btnMensajeCuatro = findViewById(R.id.btn_mensaje1);
        btnMensajeCuatro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                c.enviar("4");
            }
        });
    }
}

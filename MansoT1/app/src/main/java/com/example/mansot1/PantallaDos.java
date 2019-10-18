package com.example.mansot1;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PantallaDos extends AppCompatActivity {

    Cliente c;
    Button btnJugar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_dos);

        //Intent i = this.getIntent();
        ///Bundle b = i.getExtras();
        //c = (Cliente) b.getSerializable("cliente");
        c = Cliente.getInstance();

        btnJugar = findViewById(R.id.btn_jugar);
        btnJugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                c.enviar("7");

                Intent intento = new Intent(getApplicationContext(), MainActivity.class);
                //Bundle bundle = new Bundle();
                //bundle.putSerializable("cliente", (Serializable) c);
                //intento.putExtras(bundle);
                startActivity(intento);
            }
        });
    }
}

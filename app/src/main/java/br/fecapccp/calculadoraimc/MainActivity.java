package br.fecapccp.calculadoraimc;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button startButton; //Botão para direcionar o usuário à calculadora

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        startButton = findViewById(R.id.startButton); //instanciando a variável com seu respectivo elemento

        startButton.setOnClickListener(view ->{ //Criando um listener para o botão
            Intent intent = new Intent(getApplicationContext(), CalculoIMCActivity.class);
            startActivity(intent); //Direcionando o usuário para a Activity da calculadora
        });

    }
}
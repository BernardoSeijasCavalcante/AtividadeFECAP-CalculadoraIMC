package br.fecapccp.calculadoraimc;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AbaixoDoPesoActivity extends AppCompatActivity {

    //Variáveis correspondentes aos elementos da Activity
    private TextView values;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_abaixo_do_peso);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Instanciando variáveis com seus respectivos elementos no XML

        values = findViewById(R.id.values);
        backButton = findViewById(R.id.backButton);

        //Resgatando os dados transferidos pela intent

        Bundle bundle = getIntent().getExtras();

        Double peso = bundle.getDouble("peso");
        Double altura = bundle.getDouble("altura");
        Double resultadoIMC = bundle.getDouble("resultadoIMC");

        //Exibindo dados na tela

        String resultado =
                "Seu Peso: " + peso + "\n"
                +"Sua Altura: " + altura + "\n"
                +"Seu IMC: " + String.format("%.2f",resultadoIMC) + ".";

        values.setText(resultado);

        backButton.setOnClickListener(view ->{ //Criando um listener para o botão Fechar
            finish();//Fecha a Activity atual
        });
    }
}
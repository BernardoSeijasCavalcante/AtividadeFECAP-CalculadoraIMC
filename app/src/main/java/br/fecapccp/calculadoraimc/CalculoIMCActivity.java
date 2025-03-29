package br.fecapccp.calculadoraimc;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.ParseException;

public class CalculoIMCActivity extends AppCompatActivity {

    //Variáveis correspondentes aos elementos da Activity

    private EditText pesoEditText;
    private EditText alturaEditText;

    private Button calcButton;
    private Button resetButton;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calculo_imcactivity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Instanciando variáveis com seus respectivos elementos no XML

        pesoEditText = findViewById(R.id.pesoEditText);
        alturaEditText = findViewById(R.id.alturaEditText);

        calcButton = findViewById(R.id.calcButton);
        resetButton = findViewById(R.id.resetButton);
        backButton = findViewById(R.id.backButton);

        calcButton.setOnClickListener(view ->{//Criando um listener para o botão Calcular IMC
            calcularIMC();//Função que efetua a operação de cálculo
        });

        resetButton.setOnClickListener(view ->{ //Criando um listener para o botão Limpar
            pesoEditText.setText(""); //Limpa o EditText pesoEditText
            alturaEditText.setText(""); //Limpa o EditText alturaEditText
        });

        backButton.setOnClickListener(view ->{ //Criando um listener para o botão Fecha
            finish();//Finaliza a Activity atual
        });
    }

    public void calcularIMC(){ //Função que efetua a operação de cálculo
        try {
            Double peso = Double.parseDouble(pesoEditText.getText().toString()); //Coletando o dado do campo peso
            Double altura = Double.parseDouble(alturaEditText.getText().toString()); //Coletando o dado do campo altura

            double resultadoIMC = peso / (altura * altura); //Calculando IMC

            if(resultadoIMC <= 0){ //Se o IMC for negativo ou igual a zero
                throw new NumberFormatException(); //Lança uma exceção para valores inválidos
            }

            if(resultadoIMC < 18.5){ //Abaixo do Peso

                changeActivity(AbaixoDoPesoActivity.class, peso, altura, resultadoIMC); //Muda para a Activity de Abaixo do Peso

            }else if(resultadoIMC >= 18.5 && resultadoIMC < 25.0){//Peso Normal

                changeActivity(PesoNormalActivity.class, peso, altura, resultadoIMC); //Muda para a Activity de Peso Normal

            }else if(resultadoIMC >= 25.0 && resultadoIMC <30.0){//Sobrepeso

                changeActivity(SobrepesoActivity.class, peso, altura, resultadoIMC); //Muda para a Activity de Sobrepeso

            }else if(resultadoIMC >= 30.0 && resultadoIMC < 35){//Obesidade 1

                changeActivity(Obesidade1Activity.class, peso, altura, resultadoIMC); //Muda para a Activity de Obesidade 1

            }else if(resultadoIMC >= 35 && resultadoIMC < 40){//Obesidade 2

                changeActivity(Obesidade2Activity.class, peso, altura, resultadoIMC); //Muda para a Activity de Obesidade 2

            }else if(resultadoIMC >= 40){//Obesidade 3

                changeActivity(Obesidade3Activity.class, peso, altura, resultadoIMC); //Muda para a Activity de Obesidade 3

            }
        }catch(NumberFormatException e){ //Caso ocorra a exceção de valores inválidos inseridos nos campos
            Toast.makeText(getApplicationContext(),"Digite valores válidos para os campos! (Utilize pontos (.) ao invés de vírgulas (,)).",Toast.LENGTH_LONG).show();
        }
    }

    public void changeActivity(Class<?> cls, Double peso, Double altura, Double resultadoIMC){ //Função para mudar de Activity
        Intent intent = new Intent(getApplicationContext(), cls); //Declara a intenção

        //Envia os dados do cálculo de IMC para a intenção

        intent.putExtra("peso", peso);
        intent.putExtra("altura", altura);
        intent.putExtra("resultadoIMC", resultadoIMC);

        startActivity(intent); //Abre a intenção
    }
}
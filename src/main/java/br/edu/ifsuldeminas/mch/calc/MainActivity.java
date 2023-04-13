package br.edu.ifsuldeminas.mch.calc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import java.util.function.Function;

import de.congrace.exp4j.Calculable;
import de.congrace.exp4j.ExpressionBuilder;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "ifsuldeminas.mch.calc";
    private Button buttonIgual;
    private TextView txtResultado;
    private TextView txtExpressao;
    private Button numeroZero,numeroUm,numeroDois,numeroTres,numeroQuatro,numeroCinco,numeroSeis,numeroSete,numeroOito,numeroNove,ponto,soma,subtracao,multiplicacao,divisao,porcentagem,igual,botao_limpar,delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicialização dos componentes da interface gráfica
        IniciarComponentes();
        getSupportActionBar().hide();

        // Configuração dos botões para receberem eventos de clique
        numeroZero.setOnClickListener(this);
        numeroUm.setOnClickListener(this);
        numeroDois.setOnClickListener(this);
        numeroTres.setOnClickListener(this);
        numeroQuatro.setOnClickListener(this);
        numeroCinco.setOnClickListener(this);
        numeroSeis.setOnClickListener(this);
        numeroSete.setOnClickListener(this);
        numeroOito.setOnClickListener(this);
        numeroNove.setOnClickListener(this);
        ponto.setOnClickListener(this);
        soma.setOnClickListener(this);
        subtracao.setOnClickListener(this);
        multiplicacao.setOnClickListener(this);
        divisao.setOnClickListener(this);
        porcentagem.setOnClickListener(this);

        // Configuração do botão botao_limpar para remover os dados da expressão e do resultado
        botao_limpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtExpressao.setText("");
                txtResultado.setText("");
            }
        });

        // Configuração do botão delete para remover o último caractere da expressão
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 {
                    // Obtém a expressão atual
                    String string = txtExpressao.getText().toString();
                    // Verifica se a expressão não está vazia
                    if (!string.isEmpty()) {
                        // Remove o último caractere da expressão
                        String txtExpressaoo = string.substring(0, string.length() - 1);
                        txtExpressao.setText(txtExpressaoo);
                    }
                    // Limpa o resultado
                    txtResultado.setText("");
                }

                }
        });

        // Configuração do botão porcentagem para adicionar o caractere de porcentagem à expressão
        porcentagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtém a expressão atual
                String expressao = txtExpressao.getText().toString();
                // Adiciona o caractere de porcentagem à expressão
                String novaExpressao = expressao + "%";
                txtExpressao.setText(novaExpressao);
            }
        });

        // Configuração do botão igual para calcular o resultado da expressão
        igual.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View view) {

                // Obtém a expressão atual
                String expressao = txtExpressao.getText().toString();

                // Remove o caractere de porcentagem, se houver
                if (expressao.contains("%")) {
                    expressao = expressao.replace("%", "/100");
                }

                Calculable avaliadorExpressao = null;
                try {
                    // Cria um objeto ExpressionBuilder para avaliar a expressão
                    avaliadorExpressao = (Calculable) new ExpressionBuilder(expressao).build();
                    // Avalia a expressão e obtém o resultado
                    double resultado = avaliadorExpressao.calculate();
                    // Arredonda o resultado para baixo, caso seja um número inteiro
                    long longResult = (long) resultado;
                    txtExpressao.setText("");
                    // Verifica se o resultado é um número inteiro
                    if (resultado == (double)longResult){
                        // Exibe o resultado como inteiro
                        txtExpressao.setText((CharSequence) String.valueOf(longResult));
                    }else{
                        // Exibe o resultado com casas decimais
                        txtResultado.setText((CharSequence) String.valueOf(resultado));
                    }
                } catch (Exception e) {
                    // Em caso de erro, exibe uma mensagem no log do sistema
                    Log.d(TAG, e.getMessage());
                }
            }
        });
    }




    private void IniciarComponentes(){
        //Inicialização dos botões numéricos
        numeroZero = findViewById(R.id.buttonZeroID);
        numeroUm = findViewById(R.id.buttonUmID);
        numeroDois = findViewById(R.id.buttonDoisID);
        numeroTres = findViewById(R.id.buttonTresID);
        numeroQuatro = findViewById(R.id.buttonQuatroID);
        numeroCinco = findViewById(R.id.buttonCincoID);
        numeroSeis = findViewById(R.id.buttonSeisID);
        numeroSete = findViewById(R.id.buttonSeteID);
        numeroOito = findViewById(R.id.buttonOitoID);
        numeroNove = findViewById(R.id.buttonNoveID);
        //Inicialização dos botões de operações
        ponto = findViewById(R.id.buttonVirgulaID);
        soma = findViewById(R.id.buttonSomaID);
        subtracao = findViewById(R.id.buttonSubtracaoID);
        multiplicacao = findViewById(R.id.buttonMultiplicacaoID);
        divisao = findViewById(R.id.buttonDivisaoID);
        porcentagem = findViewById(R.id.buttonPorcentoID);
        igual = findViewById(R.id.buttonIgualID);
        //Inicialização do botão de limpar
        botao_limpar = findViewById(R.id.buttonResetID);
        //Inicialização dos textViews de expressão e resultado
        txtExpressao = findViewById(R.id.textViewUltimaExpressaoID);
        txtResultado = findViewById(R.id.textViewResultadoID);
        //Inicialização do botão de deletar
        delete = findViewById(R.id.buttonDeleteID);
    }

    public void AcrescentarUmaExpressao(String string, boolean limpar_dados){

        if (txtResultado.getText().equals("")){
            txtExpressao.setText(" ");
        }
        if (limpar_dados){
            //Caso o usuário esteja começando a digitar uma nova expressão
            txtResultado.setText(" ");
            txtExpressao.append(string);
        }else{
            //Caso o usuário esteja adicionando operações à expressão já existente
            txtExpressao.append(txtResultado.getText());
            txtExpressao.append(string);
            txtResultado.setText(" ");
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonZeroID:
                //true a expressão atual é limpa e o novo caractere é adicionado
                AcrescentarUmaExpressao("0", true);
                break;

            case R.id.buttonUmID:
                AcrescentarUmaExpressao("1", true);
                break;

            case R.id.buttonDoisID:
                AcrescentarUmaExpressao("2", true);
                break;

            case R.id.buttonTresID:
                AcrescentarUmaExpressao("3", true);
                break;

            case R.id.buttonQuatroID:
                AcrescentarUmaExpressao("4", true);
                break;

            case R.id.buttonCincoID:
                AcrescentarUmaExpressao("5", true);
                break;

            case R.id.buttonSeisID:
                AcrescentarUmaExpressao("6", true);
                break;

            case R.id.buttonSeteID:
                AcrescentarUmaExpressao("7", true);
                break;

            case R.id.buttonOitoID:
                AcrescentarUmaExpressao("8", true);
                break;

            case R.id.buttonNoveID:
                AcrescentarUmaExpressao("9", true);
                break;

            case R.id.buttonVirgulaID:
                AcrescentarUmaExpressao(".", true);
                break;

            case R.id.buttonSomaID:
                //false a expressão atual não é limpa e o novo caractere é adicionado à expressão existente
                AcrescentarUmaExpressao("+", false);
                break;

            case R.id.buttonSubtracaoID:
                AcrescentarUmaExpressao("-", false);
                break;

            case R.id.buttonMultiplicacaoID:
                AcrescentarUmaExpressao("*", false);
                break;

            case R.id.buttonDivisaoID:
                AcrescentarUmaExpressao("/", false);
                break;

            case R.id.buttonPorcentoID:
                AcrescentarUmaExpressao("%", false);
                break;
        }
    }
}


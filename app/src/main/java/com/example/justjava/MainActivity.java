package com.example.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.justjava.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    //Variavel binding do data binding nova forma para se usar o binding no android
    ActivityMainBinding binding;

    public int qtdCafe = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    /**
     * Este metodo é responsavel por realizar um pedido
     *
     * @param view
     */
    public void fazerPedido(View view) {
        criarResumoPedido();
    }

    /**
     * Este metodo é responsavel por criar um resumo do pedido.
     *
     * @return
     */
    private void criarResumoPedido() {
        Boolean checkChantilly = binding.checkboxChantilly.isChecked();
        Boolean checkChocolate = binding.checkboxChocolate.isChecked();
        String mensagemPedido = "";

        if (qtdCafe > 0) {
            mensagemPedido = "Nome: " + binding.editNome.getText() + " \n" +
                    (checkChantilly ? "Quer chantilly no café \n" : "") +
                    (checkChocolate ? "Quer chocolate no café \n" : "") +
                    "Quantidade: " + qtdCafe + " \n" +
                    "Total: R$" + calculaPrecoPedido(qtdCafe, 5, checkChantilly, checkChocolate) + "\n" +
                    "Obrigado";

            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:")); //  somente apps de email iram responder a essa intent
            intent.putExtra(Intent.EXTRA_SUBJECT, "Pedido feito no App Just Java para: " + binding.editNome.getText());
            intent.putExtra(Intent.EXTRA_TEXT, mensagemPedido);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }

        } else {
            Toast.makeText(this, "Por favor adicione ao menos um café!", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Calcula preço do pedido pela quantidade passada por parametro
     *
     * @param quantidade
     * @param preco
     * @param checkChantilly
     * @param checkChocolate
     * @return
     */
    private int calculaPrecoPedido(int quantidade, int preco, boolean checkChantilly, boolean checkChocolate) {
        int precoBase = preco;

        if (checkChantilly) {
            precoBase += 1;
        }

        if (checkChocolate) {
            precoBase += 2;
        }

        return quantidade * precoBase;
    }



    private void exibirQuantidadeCafe(int quantidade) {
        binding.tvQuantidade.setText("" + quantidade);
    }

    public void addCafe(View view) {
        qtdCafe += 1;
        exibirQuantidadeCafe(qtdCafe);
    }

    public void removeCafe(View view) {
        if (qtdCafe != 0) {
            qtdCafe -= 1;
            exibirQuantidadeCafe(qtdCafe);
        } else {
            Toast.makeText(this, "Não pode pedir café negativo, caro gafanhoto", Toast.LENGTH_SHORT).show();
        }
    }
}
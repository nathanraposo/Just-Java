package com.example.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
     * @param view
     */
    public void fazerPedido(View view) {
        exibirMensagem(criarResumoPedido());
    }

    /**
     * Este metodo é responsavel por criar um resumo do pedido.
     *
     * @return
     */
    private String criarResumoPedido() {
        String mensagemPedido = "Nome: Nathan Raposo Olivo \n" +
                "Quantidade: " + qtdCafe + " \n" +
                "Total: R$" + calculaPrecoPedido(qtdCafe, 5) + "\n" +
                "Obrigado";

        return mensagemPedido;
    }

    /**
     * Calcula preço do pedido pela quantidade passada por parametro
     *
     * @param quantidade
     * @param preco
     * @return
     */
    private int calculaPrecoPedido(int quantidade, int preco) {
        return quantidade * preco;
    }

    private void exibirMensagem(String message) {
        binding.tvResumoPedido.setText(message);
    }

    private void exibirQuantidadeCafe(int quantidade) {
        binding.tvQuantidade.setText("" + quantidade);
    }

    public void addCafe(View view) {
        exibirMensagem("AGUARDANDO PEDIDO");
        qtdCafe += 1;
        exibirQuantidadeCafe(qtdCafe);
    }

    public void removeCafe(View view) {
        exibirMensagem("AGUARDANDO PEDIDO");
        if (qtdCafe != 0) {
            qtdCafe -= 1;
            exibirQuantidadeCafe(qtdCafe);
        } else {
            Toast.makeText(this, "Não pode pedir café negativo, caro gafanhoto", Toast.LENGTH_SHORT).show();
        }
    }
}
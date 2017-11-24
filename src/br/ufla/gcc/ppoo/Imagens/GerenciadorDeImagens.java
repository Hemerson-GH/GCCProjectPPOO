package br.ufla.gcc.ppoo.Imagens;

import javax.swing.ImageIcon;

public class GerenciadorDeImagens {
    
    public static final ImageIcon ESTRELA_1 = carregarImagem("sair.png");
    public static final ImageIcon ESTRELA_2 = carregarImagem("logout.png");
    public static final ImageIcon ESTRELA_3 = carregarImagem("entrar.png");
    public static final ImageIcon ESTRELA_4 = carregarImagem("novo.png");
    public static final ImageIcon ESTRELA_5 = carregarImagem("editar.png");
    public static final ImageIcon DELETAR = carregarImagem("deletar.png");
    public static final ImageIcon BANDEIRA_BR = carregarImagem("bandeira-br.png");
    public static final ImageIcon BANDEIRA_GB = carregarImagem("bandeira-gb.png");
    public static final ImageIcon CADASTRAR_USUARIO = carregarImagem("cadastrar-usuario.png");
    public static final ImageIcon OK = carregarImagem("btn-ok.png");
    public static final ImageIcon CANCELAR = carregarImagem("btn-cancelar.png");
    public static final ImageIcon SOBRE = carregarImagem("sobre.png");
    public static final ImageIcon LISTA = carregarImagem("icone-lista.png");

    private static ImageIcon carregarImagem(String caminho) {
        return new ImageIcon(GerenciadorDeImagens.class.getResource(caminho));
    }
}

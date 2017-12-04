package br.ufla.gcc.ppoo.Imagens;

import javax.swing.ImageIcon;

public class GerenciadorDeImagens {
    
	public static final ImageIcon ENTRAR = carregarImagem("entrar.png");
    public static final ImageIcon SAIR = carregarImagem("sair.png");
    public static final ImageIcon LOGOUT = carregarImagem("logout.png");
    public static final ImageIcon NOVO = carregarImagem("novo.png");
    public static final ImageIcon NOVO_ITEM = carregarImagem("novo_item.png");
    public static final ImageIcon EDITAR = carregarImagem("editar.png");
    public static final ImageIcon FILME = carregarImagem("filmes.png");
    public static final ImageIcon OK = carregarImagem("btn-ok.png");
    public static final ImageIcon CANCELAR = carregarImagem("btn-cancelar.png");
    public static final ImageIcon PROCURAR = carregarImagem("procurar.png");
    public static final ImageIcon PROCURAR_GRANDE = carregarImagem("icon-procurar-grande.png");
    public static final ImageIcon USUARIO = carregarImagem("icone-usuario.png");
    public static final ImageIcon EXIT = carregarImagem("icone-exit.png");
    public static final ImageIcon DELETAR = carregarImagem("deletar.png");
    public static final ImageIcon SOBRE = carregarImagem("sobre.png");
    public static final ImageIcon LISTA = carregarImagem("icone-lista.png");
    public static final ImageIcon LISTA_OK = carregarImagem("lista.jpg");
    public static final ImageIcon CADASTRAR_USUARIO = carregarImagem("cadastrar-usuario.png");
    public static final ImageIcon CADASTRO_USUARIO = carregarImagem("cadastro-usuario.jpg");
    public static final ImageIcon ESTRELA_1 = carregarImagem("estrela-1.png");
    public static final ImageIcon ESTRELA_2 = carregarImagem("estrela-2.png");
    public static final ImageIcon ESTRELA_3 = carregarImagem("estrela-3.png");
    public static final ImageIcon ESTRELA_4 = carregarImagem("estrela-4.png");
    public static final ImageIcon ESTRELA_5 = carregarImagem("estrela-5.png");
    public static final ImageIcon COMENTARIO = carregarImagem("comentario.png");
    
    private static ImageIcon carregarImagem(String caminho) {
        return new ImageIcon(GerenciadorDeImagens.class.getResource(caminho));
    }
}

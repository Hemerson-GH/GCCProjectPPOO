package br.ufla.gcc.ppoo.Imagens;

import javax.swing.ImageIcon;

public class GerenciadorDeImagens {
    
//	public static final ImageIcon ENTRAR = CarregarIcone("entrar.png");
    public static final ImageIcon SAIR = CarregarIcone("sair.png");
    public static final ImageIcon LOGOUT = CarregarIcone("logout.png");
    public static final ImageIcon NOVO = CarregarIcone("novo.png");
//    public static final ImageIcon NOVO_ITEM = CarregarIcone("novo_item.png");
    public static final ImageIcon EDITAR = CarregarIcone("editar.png");
    public static final ImageIcon FILME = CarregarIcone("filmes.png");
    public static final ImageIcon OK = CarregarIcone("btn-ok.png");
    public static final ImageIcon CANCELAR = CarregarIcone("btn-cancelar.png");
    public static final ImageIcon PROCURAR = CarregarIcone("procurar.png");
    public static final ImageIcon PROCURAR_GRANDE = CarregarIcone("icon-procurar-grande.png");
    public static final ImageIcon USUARIO = CarregarIcone("icone-usuario.png");
//    public static final ImageIcon EXIT = CarregarIcone("icone-exit.pjg");
    public static final ImageIcon DELETAR = CarregarIcone("deletar.png");
//    public static final ImageIcon SOBRE = CarregarIcone("sobre.png");
    public static final ImageIcon LISTA = CarregarIcone("icone-lista.png");
    public static final ImageIcon LISTA_OK = CarregarIcone("Lista.jpg");
//    public static final ImageIcon CADASTRAR_USUARIO = CarregarIcone("cadastrar-usuario.png");
//    public static final ImageIcon CADASTRO_USUARIO = CarregarIcone("cadastro-usuario.jpg");
//    public static final ImageIcon ESTRELA_1 = CarregarIcone("estrela-1.pjg");
//    public static final ImageIcon ESTRELA_2 = CarregarIcone("estrela-2.pjg");
//    public static final ImageIcon ESTRELA_3 = CarregarIcone("estrela-3.pjg");
//    public static final ImageIcon ESTRELA_4 = CarregarIcone("estrela-4.pjg");
//    public static final ImageIcon ESTRELA_5 = CarregarIcone("estrela-5.pjg");
//    public static final ImageIcon COMENTARIO = CarregarIcone("comentario.png");
    
    private static ImageIcon CarregarIcone(String caminho) {
        return new ImageIcon(GerenciadorDeImagens.class.getResource(caminho));
    }
}

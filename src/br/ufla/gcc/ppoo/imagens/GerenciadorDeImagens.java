package br.ufla.gcc.ppoo.imagens;

import javax.swing.ImageIcon;

public class GerenciadorDeImagens {
    
    public static final ImageIcon SAIR = CarregarIcone("sair.png");
    public static final ImageIcon LOGOUT = CarregarIcone("logout.png");
    public static final ImageIcon NOVO = CarregarIcone("novo.png");
    public static final ImageIcon NOVO_ITEM = CarregarIcone("novo_item.png");
    public static final ImageIcon EDITAR = CarregarIcone("editar.png");
    public static final ImageIcon FILME = CarregarIcone("filmes.png");
    public static final ImageIcon OK = CarregarIcone("btn-ok.png");
    public static final ImageIcon CANCELAR = CarregarIcone("btn-cancelar.png");
    public static final ImageIcon PROCURAR = CarregarIcone("procurar.png");
    public static final ImageIcon PROCURAR_GRANDE = CarregarIcone("icon-procurar-grande.png");
    public static final ImageIcon DELETAR = CarregarIcone("deletar.png");
    public static final ImageIcon LISTA_OK = CarregarIcone("lista.jpg");
    public static final ImageIcon ESTRELA_1 = CarregarIcone("estrela-1.jpg");
    public static final ImageIcon ESTRELA_2 = CarregarIcone("estrela-2.jpg");
    public static final ImageIcon ESTRELA_3 = CarregarIcone("estrela-3.jpg");
    public static final ImageIcon ESTRELA_4 = CarregarIcone("estrela-4.jpg");
    public static final ImageIcon ESTRELA_5 = CarregarIcone("estrela-5.jpg");
    public static final ImageIcon COMENTARIO = CarregarIcone("comentario.png");

    
    private static ImageIcon CarregarIcone(String caminho) {
        return new ImageIcon(GerenciadorDeImagens.class.getResource(caminho));
    }
}

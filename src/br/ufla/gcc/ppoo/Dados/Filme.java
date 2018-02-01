package br.ufla.gcc.ppoo.dados;

import java.util.ArrayList;

import br.ufla.gcc.ppoo.exceptions.FilmesException;

public class Filme {
	
	private String nome;
	private String data;
	private String descricao;
	private String wordKeys;
	private String genero;
	private String duracaoFilme;
	private String diretor;
	private Long pontos;
	private Long id_user;
	private Long id_filme;
	
	public Filme(String nome, String data, String descricao, String wordKeys,
									String genero, String duracaoFilme, String diretor){
		this.nome = nome;
		this.data = data;
		this.descricao = descricao;
		this.wordKeys = wordKeys;
		this.genero = genero;
		this.duracaoFilme = duracaoFilme;
		this.diretor = diretor;
	}
	
	public Filme(String nome, String data, String descricao, String wordKeys, String genero, 
									String duracaoFilme, String diretor, Long pontos, Long id_user, Long id_filme){
		this.nome = nome;
		this.data = data;
		this.descricao = descricao;
		this.wordKeys = wordKeys;
		this.genero = genero;
		this.duracaoFilme = duracaoFilme;
		this.diretor = diretor;
		this.pontos = pontos;
		this.id_user = id_user;
		this.id_filme = id_filme;
	}
	
	public Filme(Filme filme) throws FilmesException{
		if (filme == null) {
			throw new FilmesException("Não foi possível encontrar esse filme", "Filme Inexistente");
		}
		
		this.setNome(filme.getNome());
		this.setData(filme.getData());
		this.setDescricao(filme.getDescricao());
		this.setWordKeys(filme.getWordKeys());
		this.setGenero(filme.getGenero());
		this.setDuracaoFilme(filme.getDuracaoFilme());
		this.setDiretor(filme.getDiretor());
		this.setPontos(filme.getPontos());
		this.setId_filme(filme.getId_filme());
		this.setId_user(filme.getId_user());
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getWordKeys() {
		return wordKeys;
	}

	public void setWordKeys(String wordKeys) {
		this.wordKeys = wordKeys;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getDuracaoFilme() {
		return duracaoFilme;
	}

	public void setDuracaoFilme(String duracaoFilme) {
		this.duracaoFilme = duracaoFilme;
	}

	public Long getPontos() {
		return pontos;
	}

	public void setPontos(Long pontos) {
		this.pontos = pontos;
	}

	public String getDiretor() {
		return diretor;
	}

	public void setDiretor(String diretor) {
		this.diretor = diretor;
	}

	public Long getId_user() {
		return id_user;
	}

	public void setId_user(Long id_user) {
		this.id_user = id_user;
	}

	public Long getId_filme() {
		return id_filme;
	}

	public void setId_filme(Long id_filme) {
		this.id_filme = id_filme;
	}
	
	public static boolean comparaFilmeBool(ArrayList<Filme> listFilmes, String nomeFilme){
		
		for (Filme filme : listFilmes) {
			if (filme.getNome().equals(nomeFilme)) {
				return true;
			}
		}
		
		return false;
	}
	
	public static ArrayList<String> separaStrings(String wordKeyCompleta){
		String[] wordKeyHifen = wordKeyCompleta.split("-");
		ArrayList<String> listWordKeys = new ArrayList<>();
		
		for (String wordKey : wordKeyHifen) {
			listWordKeys.add(wordKey);
		}
		
		return listWordKeys;
	}
	
	public static ArrayList<Filme> pesquisaFilme(ArrayList<Filme> listaFilme, String wordKey) {
		ArrayList<Filme> filmesEncontrados = new ArrayList<>();
		ArrayList<String> wordKeys = new ArrayList<>();
		Long filmeConf = null;
		
		for (Filme filme : listaFilme) {
			
			wordKeys = separaStrings(filme.getWordKeys());
			wordKeys.add(filme.getNome());
			
			for (String string : wordKeys) {
					if (string.toUpperCase().contains(wordKey.toUpperCase()) && filmeConf != filme.getId_filme()) {	
						filmesEncontrados.add(filme);
						filmeConf = filme.getId_filme();	
				}
			}
		}
		
		return filmesEncontrados;
	}
	
	public static ArrayList<Filme> pesquisaRecomendacao(ArrayList<Filme> listaTodosFilmes, ArrayList<Filme> listaFilmes) {
		ArrayList<Filme> filmesEncontrados = new ArrayList<>();
		ArrayList<Filme> generoFilmesRecomendados = new ArrayList<>();
		Long filmeConf = null;

		for (Filme filme : listaFilmes) {
			generoFilmesRecomendados.add(filme);
		}
		
		for (Filme filme : listaTodosFilmes) {
			for (Filme filmeRecomendado : generoFilmesRecomendados) {	
				if (filmeRecomendado.getGenero().toUpperCase().contains(filme.getGenero().toUpperCase()) && filmeConf != filme.getId_filme() ) {	
					if (!Filme.comparaFilmeBool(listaFilmes, filme.getNome() ) ) {
						filmesEncontrados.add(filme);
						filmeConf = filme.getId_filme();
					}					
				}				
			}
		}	
		
		return filmesEncontrados;
	}

//	public static String converteTexto(String wordKeys) {
//		
//		String[] wordKeyHifen = wordKeys.split("-");
//		String listWordKeys = "";
//		
//		for (int i = 0; i < wordKeyHifen.length; i++) {
//			if (i < wordKeyHifen.length-1) {
//				listWordKeys += wordKeyHifen[i] + ", ";
//			} else {
//				listWordKeys += wordKeyHifen[i];
//			}
//		}
//		
//		return listWordKeys;
//	}
	
	public static String converteTexto(String wordKeys) {
		return wordKeys.replaceAll("-", ", ");
	}
}

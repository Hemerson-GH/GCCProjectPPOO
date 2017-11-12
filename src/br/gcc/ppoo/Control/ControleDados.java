package br.gcc.ppoo.Control;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import br.gcc.ppoo.BancoDeDados.BancoDeDados;
import br.gcc.ppoo.Dados.DadosLogin;

public class ControleDados {
	
	BancoDeDados bd = new BancoDeDados();
	
	public void salvar(DadosLogin dados){
		bd.conexao();
		
		try {
			PreparedStatement pst = bd.con.prepareStatement("insert into dados_usuarios(nome,email,senha) values(?,?,md5(?))");
			pst.setString(1, dados.getNome());		
			pst.setString(2, dados.getEmail());
			pst.setString(3, dados.getSenha());	
			pst.execute();
			JOptionPane.showMessageDialog(null, "Cadastro Sucesso");
			
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Erro no cadastro: \n " + ex);
			ex.printStackTrace();
		}
		
		bd.desconecta();
	}
	
	public String convertMD5(){
		String s="teste";
		MessageDigest m = null;
		
		try {
			m = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	       m.update(s.getBytes(),0,s.length());
	       
//	       System.out.println("MD5: "+ new BigInteger(1,m.digest()).toString(16));
	       
	       return new BigInteger(1,m.digest()).toString(16);
	}
	
}

package br.ufla.gcc.ppoo.RecuperacaoSenha;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JOptionPane;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class RecuperaSenha {
	
//  Host mail of server from email that be used to send
    private String hostName = "smtp.gmail.com";
    private String nomeRemetente = "AnnotFilm's";
    private String remetente = "gcc178project@gmail.com";
    private String destinatarios; 
    private String assuntoEmail;
    private String mensagemEmail;
    private String usuarioEmail = "gcc178project@gmail.com";
    private String senhaEmail = "PauloGcc178";
    private int portaEnvio = 465;
	
    public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public String getNomeRemetente() {
		return nomeRemetente;
	}
	public void setNomeRemetente(String nomeRemetente) {
		this.nomeRemetente = nomeRemetente;
	}
	public String getRemetente() {
		return remetente;
	}
	public void setRemetente(String remetente) {
		this.remetente = remetente;
	}
	public String getDestinatarios() {
		return destinatarios;
	}
	public void setDestinatarios(String destinatarios) {
		this.destinatarios = destinatarios;
	}
	public String getAssuntoEmail() {
		return assuntoEmail;
	}
	public void setAssuntoEmail(String assuntoEmail) {
		this.assuntoEmail = assuntoEmail;
	}
	public String getMensagemEmail() {
		return mensagemEmail;
	}
	public void setMensagemEmail(String mensagemRmail) {
		this.mensagemEmail = mensagemRmail;
	}
	public String getUsuarioEmail() {
		return usuarioEmail;
	}
	public void setUsuarioEmail(String usuarioEmail) {
		this.usuarioEmail = usuarioEmail;
	}
	public String getSenhaEmail() {
		return senhaEmail;
	}
	public void setSenhaEmail(String senhaEmail) {
		this.senhaEmail = senhaEmail;
	}
	public int getPortaEnvio() {
		return portaEnvio;
	}
	public void setPortaEnvio(int portaEnvio) {
		this.portaEnvio = portaEnvio;
	}
	
	public RecuperaSenha(String destinatario, String assunto, String mensagem) {
		this.destinatarios = destinatario;
        this.assuntoEmail = assunto;
        this.mensagemEmail = mensagem;
	}
    
	@SuppressWarnings("deprecation")
	public void enviarEmail(String destinatario, String assunto, String mensagem) throws EmailException{
		SimpleEmail email = new SimpleEmail();
		
//    Settings o host name
      email.setHostName(getHostName());
      
//    Settings shipping port
      email.setSmtpPort(portaEnvio);
      
//    Settings o email do sender
      email.setFrom(remetente, nomeRemetente);
      
//    Add the e-mails of destination
      email.addTo(destinatario);
      
//    Add o subject matter
      email.setSubject(assunto);
      
//    Message do email
      email.setMsg(mensagem);
      
//    Authentic no server
      email.setSSL(true);
      
//    User and password from sender
      email.setAuthentication(usuarioEmail, senhaEmail);
      email.send();

      JOptionPane.showMessageDialog(null, "Email enviado com sucesso.");
    }
    public static void main(String args[]) throws InterruptedException, FileNotFoundException, IOException, EmailException {
        
    	String destinatario = "hemersonel@gmail.com";
        String assunto = "Recuperação de Senha";
        String mensagem = "Este email serve para recuperar sua senha.";
        
        RecuperaSenha RP = new RecuperaSenha(destinatario, assunto, mensagem);
        
    	RP.enviarEmail(destinatario, assunto, mensagem);
    }
}
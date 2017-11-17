package br.ufla.gcc.ppoo.RecuperacaoSenha;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class RecuperaSenha {
    //hostmail do servidor de email que será usado para envio
    static String hostName = "smtp.gmail.com";
    static String nomeRemetente = "Eu";
    static String remetente = "gcc178project@gmail.com";
    static String destinatarios = "raydsonferreira@gmail.com"; //"hemersonel@gmail.com";
    static String assuntoEmail = "teste email";
    static String mensagemRmail = "Aprendi enviar email e agora consigo likar isso para recurar sua senha no meu sistema";//"testando envio de email utilizando o JAVA";
    static String usuarioEmail = "gcc178project@gmail.com";
    static String senhaEmail = "PauloGcc178";
    static int portaEnvio = 465; //465;
    
    @SuppressWarnings("deprecation")
	public static void enviarEmail() throws EmailException{
        SimpleEmail email = new SimpleEmail();
        //configura o hostname
        email.setHostName(hostName);
        //configura porta de envio
        email.setSmtpPort(portaEnvio);
        //configura o email do remetente
        email.setFrom(remetente, nomeRemetente);
      //adiciona os emails de destino
        email.addTo(destinatarios);
        //adiciona o assunto
        email.setSubject(assuntoEmail);
        //mensagem do email
        email.setMsg(mensagemRmail);
        //autenticacao no servidor
        email.setSSL(true);
//        email.setTLS(true);
        //usuario e senha do remetente
        email.setAuthentication(usuarioEmail, senhaEmail);
        email.send();
        System.out.println("Enviado");
    }
    public static void main(String args[]) throws InterruptedException, FileNotFoundException, IOException, EmailException {
        RecuperaSenha.enviarEmail();
    }
}
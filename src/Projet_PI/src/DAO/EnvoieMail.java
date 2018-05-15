package DAO;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EnvoieMail 
{
	private String host;
	private String exp;
	private String mdp;
    private int port;

    private Properties props;
    private Session session;
    private Message message;
    
    public EnvoieMail()
    {
    	host = "smtp-mail.outlook.com";
    	exp = "gestionEventHERS@hotmail.com";
    	mdp = "naming-hers6";
        port = 587;
        
        props = new Properties();
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.debug", "true");
        
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", exp);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.password", mdp);
        
        session = Session.getDefaultInstance(props, new javax.mail.Authenticator()
        											{
        	        									protected PasswordAuthentication getPasswordAuthentication()
        	        									{
        	        										return new PasswordAuthentication(exp, mdp);// Specify the Username and the PassWord
        	        									}
        											});
        
    }
    
    public boolean send(String[] tabDest, String subject, String text)
    {
    	message = new MimeMessage(session);
        try 
        {
        	InternetAddress[] tabAdr = new InternetAddress[tabDest.length];
        	for(int i = 0; i < tabAdr.length; i++) tabAdr[i] = new InternetAddress(tabDest[i]);
    		message.setFrom(new InternetAddress(exp));
    		message.setRecipients(Message.RecipientType.TO, tabAdr);
            message.setSubject(subject);
            message.setText(text); 
    	} 
        catch (MessagingException ex)
        {
        	System.out.println("Erreur EnvoieMail: edition du message !");
    		ex.printStackTrace();
    		return false;
    	} 

        Transport transport = null;
        try
        {
            transport = session.getTransport("smtp");
            transport.connect(host, port, exp, mdp);
            Transport.send(message);
    	} 
        catch (MessagingException ex)
        {
        	System.out.println("Erreur EnvoieMail: envoie du message !");
    		ex.printStackTrace();
    		return false;
    	} 
        finally 
        {
            try 
            {
                if (transport != null) 
                {
                    transport.close();
                }
            } 
            catch (MessagingException e)
            {
            	System.out.println("Erreur EnvoieMail: transport.close() !");
                e.printStackTrace();
                return false;
            }
        }
        return true;
    } 
}

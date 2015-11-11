/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emailclient;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import java.io.*;
import java.util.*;
import javax.mail.*;
import javax.mail.Flags.Flag;
import javax.mail.search.FlagTerm;

import com.sun.mail.pop3.POP3Store;

/**
 *
 * @author Administrator
 */
public class emailalgorithm {
    private String user = "12738testtesttest@gmail.com";
    private String password = "whatapassword" ;
    static String subject;
    static Multipart  content;
    static int emailnumber;
    static Address from;
    static BodyPart bp;
    static Object yo;
    static List all2;
    public void sendemail(String to,String sub,String msg){
        Properties prop = new Properties();
        prop.put("mail.smtp.ssl.trust","smtp.gmail.com");
        prop.put("mail.smtp.auth",true);
        prop.put("mail.smtp.starttls.enable",true);
        prop.put("mail.smtp.host","smtp.gmail.com");
        prop.put("mail.smtp.port","587");
        
        Session session = Session.getInstance(prop,new javax.mail.Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication(){
              return new javax.mail.PasswordAuthentication(user,password);
            }
        });
        
        try{
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("no-reply@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
            message.setSubject(sub);
            message.setText(msg);
            
            Transport.send(message);
            System .out.println("mail sent");
            
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    /*public void dumpPart(Part p) throws Exception {
        // Dump input stream ..
        InputStream is = p.getInputStream();
         List<String> list=new ArrayList<String>();
        // If "is" is not already buffered, wrap a BufferedInputStream
        // around it.
        if (!(is instanceof BufferedInputStream)) {
            is = new BufferedInputStream(is);
        }
        int c;
        System.out.println("Message : ");
        while ((c = is.read()) != -1) {
            list.add(c);
        }
    }*/
    public void receiveemail(String pop3Host, String storeType) throws Exception
    {
        ArrayList<LinkedList<String>>  alll = new ArrayList<LinkedList<String>>();  
         
        try {
			Properties properties = new Properties();
			properties.put("mail.pop3.host", pop3Host);
                         properties.put("mail.pop3.port", "995");
                         properties.put("mail.pop3.starttls.enable", "true");
			Session emailSession = Session.getDefaultInstance(properties);

			POP3Store emailStore = (POP3Store) emailSession.getStore("pop3s");
			emailStore.connect(pop3Host,user, password);

			Folder emailFolder = emailStore.getFolder("INBOX");
			emailFolder.open(Folder.READ_ONLY);

			Message[] messages = emailFolder.getMessages();
			for (int i = 0; i < messages.length; i++) {
				Message message = messages[i];
				LinkedList<String> ll = new LinkedList<String>(); 
				emailnumber = i+1 ;
				subject = message.getSubject();
				from =  message.getFrom()[0];
                                ll.add(Integer.toString(emailnumber));
                                ll.add(subject);
                                ll.add(String.valueOf(from));
                                alll.add(ll);
                                /*content = (Multipart) message.getContent();
                                bp = content.getBodyPart(0);
                                 yo = bp.getContent();
                                /*int count = content.getCount();
            for (int i = 0; i < count; i++) {
                dumpPart(content.getBodyPart(i));
            }*/
				
                                
			}
                        emailFolder.close(false);
			emailStore.close();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} /*catch (IOException e) {
			e.printStackTrace();
		}*/
           all2 = alll;
    }
    public void getcontent(String pop3Host, String storeType, int emailnum1) throws Exception
    {
        ArrayList<LinkedList<String>>  alll = new ArrayList<LinkedList<String>>();  
         
              try {
			Properties properties = new Properties();
			properties.put("mail.pop3.host", pop3Host);
                         properties.put("mail.pop3.port", "995");
                         properties.put("mail.pop3.starttls.enable", "true");
			Session emailSession = Session.getDefaultInstance(properties);

			POP3Store emailStore = (POP3Store) emailSession.getStore("pop3s");
			emailStore.connect(pop3Host,user, password);

			Folder emailFolder = emailStore.getFolder("INBOX");
			emailFolder.open(Folder.READ_ONLY);

			Message[] messages = emailFolder.getMessages();
			//for (int i = 0; i < messages.length; i++) {
                        
				Message message = messages[emailnum1];
				LinkedList<String> ll = new LinkedList<String>(); 
				emailnumber = emailnum1+1 ;
				subject = message.getSubject();
				from =  message.getFrom()[0];
                                ll.add(Integer.toString(emailnumber));
                                ll.add(subject);
                                ll.add(String.valueOf(from));
                                alll.add(ll);
                                content = (Multipart) message.getContent();
                                bp = content.getBodyPart(0);
                                 yo = bp.getContent();
                                /*int count = content.getCount();
            for (int i = 0; i < count; i++) {
                dumpPart(content.getBodyPart(i));
            }*/
				
                                
			//}
                        emailFolder.close(false);
			emailStore.close();
               } catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} /*catch (IOException e) {
			e.printStackTrace();
		}*/
           all2 = alll;
    }

}
 



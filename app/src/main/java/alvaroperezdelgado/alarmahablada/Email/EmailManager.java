package alvaroperezdelgado.alarmahablada.Email;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import alvaroperezdelgado.alarmahablada.Model.Emails;

/**
 * Created by perez on 10/5/16.
 */
public class EmailManager {
    private String stmpHost = "smtp.gmail.com";
    private String mailServer = "imap.gmail.com";
    private EmailAccount account;
    private Session smtpSession;
    private Session imapSession;

    private Folder inbox;
    private Store store;
    public EmailManager(String username, String password, String urlServer, String stmpHost, String mailServer) {
        account = new EmailAccount(username, password, urlServer);
        this.stmpHost = stmpHost;
        this.mailServer = mailServer;
        initProtocol();
    }
    private void initProtocol() {
        EmailAuthenticator authenticator = new EmailAuthenticator(account);

        Properties props1 = new Properties();
        props1.setProperty("mail.transport.protocol", "smtps");
        props1.setProperty("mail.host", stmpHost);
        props1.put("mail.smtp.auth", "true");
        props1.put("mail.smtp.port", "465");
        props1.put("mail.smtp.socketFactory.port", "465");
        props1.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props1.put("mail.smtp.socketFactory.fallback", "false");
        props1.setProperty("mail.smtp.quitwait", "false");
        smtpSession = Session.getDefaultInstance(props1, authenticator);

        Properties props2 = new Properties();
        props2.setProperty("mail.store.protocol", "imaps");
        props2.setProperty("mail.imaps.host", mailServer);
        props2.setProperty("mail.imaps.port", "993");
        props2.setProperty("mail.imaps.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props2.setProperty("mail.imaps.socketFactory.fallback", "false");
        imapSession = Session.getInstance(props2);
    }
    public Message[] getMails() throws MessagingException {
        Emails emails = Emails.getInstance();

        store = imapSession.getStore("imaps");
        store.connect(mailServer, account.username, account.password);
        inbox = store.getFolder("Inbox");
        inbox.open(Folder.READ_ONLY);
        Message[] result = inbox.getMessages();
        for (int i = 0, n = result.length; i < n; i++) {
            Message message = result[i];
            emails.addEmail(message);
            System.out.println("asunto " + message.getSubject());
            System.out.println("enviado por " + message.getFrom()[0]);
        }
        return result;
    }
    public void close() {
        //Close connection
        try {
            inbox.close(false);
            store.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public synchronized void sendMail(String subject, String body, String sender, String recipients) throws Exception {
        MimeMessage message = new MimeMessage(smtpSession);
        DataHandler handler = new DataHandler(new ByteArrayDataSource(body.getBytes(), "text/plain"));
        message.setSender(new InternetAddress(sender));
        message.setSubject(subject);
        message.setDataHandler(handler);
        if (recipients.indexOf(',') > 0)
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipients));
        else
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipients));
        Transport.send(message);
    }
}


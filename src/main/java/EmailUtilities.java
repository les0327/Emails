import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailUtilities {

    private static Properties props;

    static {
        props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
    }


    /**
     * Send email via Gmail
     *
     * @param from     - sender's email address
     * @param to       - recipient's email address
     * @param title    - subject of email
     * @param content  - text of email
     * @param password - password of sender gmail account
     * @throws MessagingException if problems with connection or authentication
     */
    public static void sendEmail(String from, String to, String title, String content, String password) throws MessagingException {
        Message message = new MimeMessage(getSession(from, password));

        message.setFrom(new InternetAddress(from));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(title);
        message.setText(content);

        Transport.send(message);
    }

    /**
     * Send html email via Gmail
     *
     * @param from     - sender's email address
     * @param to       - recipient's email address
     * @param title    - subject of email
     * @param htmlText - html email
     * @param password - password of sender gmail account
     * @throws MessagingException if problems with connection or authentication
     */
    public static void sendHTMLEmail(String from, String to, String title, String htmlText, String password) throws MessagingException {
        Message message = new MimeMessage(getSession(from, password));

        message.setFrom(new InternetAddress(from));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(title);
        message.setContent(htmlText, "text/html");

        Transport.send(message);
    }

    private static Session getSession(String from, String password) {
        return Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from.substring(0, from.indexOf("@")), password);
                    }
                });
    }
}

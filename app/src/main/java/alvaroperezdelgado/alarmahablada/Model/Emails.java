package alvaroperezdelgado.alarmahablada.Model;


import java.util.ArrayList;
import java.util.List;

import javax.mail.Message;
import javax.mail.MessagingException;

/**
 * Esta clase sirve para poder obtener cadenas de caracteres de los mails recibidos.
 */
public class Emails {
    //Para saber si hay algo añadido
    private Boolean isAdd = false;
    //Lista de Emails
    private List<String[]> emailList = new ArrayList<String[]>();
    private String[] fromSubject = null;
    private String speechMail = "No tienes ningún Mail en tu bandeja de entrada";
    private static Emails emails;
    //Utilizaremos esta cadena para cuando no tengamos ningun Mail que mostrar
    private static String[] empty = new String[2];

    /**
     * Método que obtiene una instancia de la clase Emails.
     * @return emails
     */
    public static Emails getInstance() {

        if (emails == null) {
            emails = new Emails();
            empty[0] = "No hay emails disponibles";
            empty[1] = "";
        }

        return emails;
    }

    /**
     * Este metodo recive un Message y lo separa en remitente y asunto.
     *
     * @param message
     */
    public void addEmail(Message message) {
        isAdd = true;
        try {
            fromSubject = new String[2];
            fromSubject[0] = message.getFrom()[0].toString();
            fromSubject[1] = message.getSubject().toString();
            fromSubject[0] = fixString(fromSubject[0]);
            emailList.add(fromSubject);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método que nos devuelve la lista completa de los Emails almacenados.
     * @return emailList
     */
    public List<String[]> getEmailList() {
        return emailList;
    }

    /**
     * Método que nos devuelve un mail por índice.
     * @param index
     * @return
     */
    public String[] getEmail(int index) {

        if (emailList.size() < index || index <= 0) {
            return empty;
        }
        return emailList.get(index);
    }

    /**
     * Nos devuelve la cadena que vamos a leer.
     * @return speechMail
     */
    public String getSpeechMail() {
        return speechMail;
    }

    /**
     * Método que nos devuelve el tamaño de la lista de los Mails.
     * @return emailList.size()
     */
    public int size() {
        return emailList.size();
    }




    /**
     *Método que sirve para eliminar por completo nuestra lista de Emails.
     *Lo utilizaremos cada vez que queramos cargar de nuevo nuestros Emails, para que no esten por duplicado.
     */
    public void removeEmailList() {
        isAdd = false;
        emailList.removeAll(emailList);
    }


    /**
     * Método que nos devuelve true si hemos añadido algun mail a la lista.
     * @return
     */
    public Boolean getIsAdd() {
        return isAdd;
    }

    /**
     * Este metodo sirve para arreglar el problema de que la api nos proporciona mal el remitente, por
     * el tema de las tildes.
     * Y en el caso de que nos lo proporcione bien que solo nos muestre el nombre.
     */
    public String fixString(String string) {
        //si viene con acentos el primer caractér será un =
        Boolean isAccent = false;
        int start = 0, end = string.length() - 1;
        if (string.charAt(0) == '=') {
            isAccent = true;
        }
        //si tiene el igual que nos muestre solo la dirección de correo
        if (isAccent) {
            for (int i = 0; i < string.length(); i++) {
                if (string.charAt(i) == '<') {
                    start = i + 1;
                }
                if (string.charAt(i) == '>') {
                    end = i;
                }
            }
        }//sino que nos muestre solo el nombre
        else {
            start = 0;
            for (int i = 0; i < string.length(); i++) {
                if (string.charAt(i) == '<') {
                    end = i;
                }
            }
        }
        string = string.substring(start, end);
        return string;
    }


    /**
     * Método que sirve para crear una cadena con lo que vamos a decir hablando.
     */
    public void setSpeechMail() {
        String aux = "";
        if (size() < 1) {
            this.speechMail = "No tienes ningún Mail en tu bandeja de entrada";
        } else {
            this.speechMail = "Tienes " + size() + " emails en tu bandeja de entrada.";
            for (int i = size() - 1; i > size() - 4; i--) {

                aux = "Tienes un Email de " + emailList.get(i)[0] + ", con asunto:" + emailList.get(i)[1] + ". ";
                this.speechMail = this.speechMail.concat(aux);
            }
        }
    }
}

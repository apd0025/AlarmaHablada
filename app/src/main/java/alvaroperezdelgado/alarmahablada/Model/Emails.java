package alvaroperezdelgado.alarmahablada.Model;


import java.util.ArrayList;
import java.util.List;

import javax.mail.Message;
import javax.mail.MessagingException;

/**
 * Esta clase servira para poder obtener cadenas de caracteres de los mails recividos
 */
public class Emails {
    private Boolean isAdd=false;
    private List<String> emailList=new ArrayList<String>();
    private String aux;
    private static Emails emails;
    //Utilizaremos esta cadena para cuando no tengamos ningun Mail que mostrar
    private String empty="No hay Mails en la bandeja de entrada";

    public static Emails getInstance() {
        if (emails == null) {
            emails = new Emails();
        }

        return emails;
    }

    /**
     * Este metodo recive un Message y lo separa en remitente y asunto
     * @param message
     */
    public void addEmail(Message message){
        isAdd=true;
        try {
            /*String string=(String)message.getFrom()[0].toString();
            string = Normalizer.normalize(string, Normalizer.Form.NFD);
            string = string.replaceAll("[^\\p{ASCII}]", "");
            System.out.println(string);
            string=string.toLowerCase();*/
            aux="Mail enviado por: "+(String)message.getFrom()[0].toString()+" Asunto: "+message.getSubject().toString();
            emailList.add(aux);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public List<String> getEmailList() {
        return emailList;
    }

    public String getEmail(int index) {

        if (emailList.size()<index||index<=0){
            return empty;
        }
        return emailList.get(index);
    }
    public int size(){

        return emailList.size();
    }

    public void removeEmailList(){
        emailList.removeAll(emailList);
    }

    public Boolean getIsAdd() {
        return isAdd;
    }
}

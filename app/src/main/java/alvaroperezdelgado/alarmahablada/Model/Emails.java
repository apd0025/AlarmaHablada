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
    private List<String[]> emailList=new ArrayList<String[]>();
    private String[] fromSubject=null;
    private static Emails emails;
    //Utilizaremos esta cadena para cuando no tengamos ningun Mail que mostrar
    private static String[] empty=new String[2];



    public static Emails getInstance() {

        if (emails == null) {
            emails = new Emails();
            empty[0]="No hay emails disponibles";
            empty[1]="";
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
            fromSubject=new String[2];
            fromSubject[0]=message.getFrom()[0].toString();
            fromSubject[1]=message.getSubject().toString();
            fromSubject[0]=fixString(fromSubject[0]);
            emailList.add(fromSubject);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public List<String[]> getEmailList() {
        return emailList;
    }

    public String[] getEmail(int index) {

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
    /**
     *Este metodo sirve para arreglar el problema de que la api nos proporciona mal el remitente, por
     * el tema de las tildes
     * y en el caso de que nos lo proporcione bien que solo nos muestre el nombre
     * */
    public String fixString(String string){
        //si viene con acentos el primer caractér será un =
        Boolean isAccent=false;
        int start=0,end=string.length()-1;
        if(string.charAt(0)=='='){
            isAccent=true;
        }

        //si tiene el igual que nos muestre solo la dirección de correo
        if(isAccent) {
            for (int i = 0; i < string.length(); i++) {
                if (string.charAt(i) == '<') {
                    start = i+1;
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
        string=string.substring(start,end);
        return string;
    }
}

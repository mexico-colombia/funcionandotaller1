package Model;

import java.io.File;

/**
 * Clase container
 * sirve para guardar los Strings que va a leer nuestra alarma
 */
public class Container {
    //guarda el string con el mensaje customizado
    private String customMessage="Este es un mensaje personalizado";
    //guarda el string con los mails que vayamos a leer
    private String ubuMailString=null;
    //guarda el string con lo que queremos leer del calendario
    private String ubuCalendarString=null;
    //guarda un String con los mensajes de twiter que queremos que lea
    private String twitterString=null;
    //guarda un string con la informacion metereologica
    private String weatherString=null;
    //guarda la cancion que queramos reproducir
    private File song=null;
    //guarda un objeto contenedor
    private static Container container;

    public static Container getInstance(){
        if(container==null){
            container=new Container();
        }
        return container;
    }

    public String getCustomMessage() {
        return customMessage;
    }

    public void setCustomMessage(String customMessage) {
        this.customMessage = customMessage;
    }

    public String getUbuMailString() {
        return ubuMailString;
    }

    public void setUbuMailString(String ubuMailString) {
        this.ubuMailString = ubuMailString;
    }

    public String getUbuCalendarString() {
        return ubuCalendarString;
    }

    public void setUbuCalendarString(String ubuCalendarString) {
        this.ubuCalendarString = ubuCalendarString;
    }

    public String getTwitterString() {
        return twitterString;
    }

    public void setTwitterString(String twitterString) {
        this.twitterString = twitterString;
    }

    public String getWeatherString() {
        return weatherString;
    }

    public void setWeatherString(String weatherString) {
        this.weatherString = weatherString;
    }

    public File getSong() {
        return song;
    }

    public void setSong(File song) {
        this.song = song;
    }

}

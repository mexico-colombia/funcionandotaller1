package Model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by perez on 18/2/16.
 * Esta clase contendra toda la informacion que podemos tener en un objeto alarma.
 * La utilizaremos para acceder de una forma mas sencilla a nuestra informacion.
 * Esta clase sera singleton ya que solo queremos una alarma
 */

public class Alarm {
    int id=1;
    private int hour;
    private int min;
    private Calendar calendar;
    private enum week{mon, tue, wed,thu,fri,sat,sun};
    //0-Mon,1-tue,2-wed,3-thu,4-fri,5-sat,6-sun
    private static List <Boolean> alarmDays=new ArrayList<Boolean>(7);
    //0-customMessage, 1-ubuMail, 2-ubuCalendar, 3-twitter, 4-weather, 5-music
    private static List <Boolean> choosenoptions=new ArrayList<Boolean>(6);
    private String description;
    private static Alarm alarm;

    //con este metodo obtenemos la instancia de alarm
    public static Alarm getInstance(){
        if(alarm==null) {
            alarm = new Alarm();
            //quiero forzar que de primeras todos los dias salgan activados
            for (int i = 0; i < 7; i++) {
                alarmDays.add(true);

            }
        }
        return alarm;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }



    public List<Boolean> getAlarmDays() {
        return alarmDays;
    }

    public void setAlarmDays(int index,boolean value) {
        switch (index){
            case 1:
                alarmDays.add(0,value);
                break;
            case 2:
                alarmDays.add(1,value);
                break;
            case 3:
                alarmDays.add(2,value);
                break;
            case 4:
                alarmDays.add(3,value);
                break;
            case 5:
                alarmDays.add(4,value);
                break;
            case 6:
                alarmDays.add(5,value);
                break;
            case 7:
                alarmDays.add(6,value);
                break;
            default:
                break;
        }
    }

    public void deleteListDays(){

        alarmDays.removeAll(alarmDays);
    }

    public List<Boolean> getChoosenoptions() {
        return choosenoptions;
    }

    public void setChoosenoptions(int index,boolean value) {
        switch (index){
            case 1:
                choosenoptions.add(0, value);
                break;
            case 2:
                choosenoptions.add(1,value);
                break;
            case 3:
                choosenoptions.add(2,value);
                break;
            case 4:
                choosenoptions.add(3,value);
                break;
            case 5:
                choosenoptions.add(4,value);
                break;
            case 6:
                choosenoptions.add(5,value);
                break;
            case 7:
                choosenoptions.add(6,value);
                break;
            default:
                break;
        }
    }

    public void deleteListChoosenoptions(){
        choosenoptions.removeAll(choosenoptions);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }




}

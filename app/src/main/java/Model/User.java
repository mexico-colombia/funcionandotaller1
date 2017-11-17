package Model;

/**
 * Created by perez on 22/2/16.
 * Esta clase se encarga de guardar y tratar los datos propios del usuario
 * Como solo habra una instancia de usuario y no queremos que se creen mas
 * esta clase sera singleton
 */
public class User{

    private int id=1;
    private String ubuUser;
    private String ubuPass;
    private String twitterUser;
    private String twitterPass;
    private String zipCode="Burgos";
    private static User user;

    public static User getInstance(){
        if(user==null){
            user=new User();
        }
        return user;
    }

    public String getUbuUser() {
        return ubuUser;
    }

    public void setUbuUser(String ubuUser) {
        this.ubuUser = ubuUser;
    }

    public String getUbuPass() {
        return ubuPass;
    }

    public void setUbuPass(String ubuPass) {
        this.ubuPass = ubuPass;
    }

    public String getTwitterUser() {
        return twitterUser;
    }

    public void setTwitterUser(String twitterUser) {
        this.twitterUser = twitterUser;
    }

    public String getTwitterPass() {
        return twitterPass;
    }

    public void setTwitterPass(String twitterPass) {
        this.twitterPass = twitterPass;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String city) {
        this.zipCode = city;
    }
}

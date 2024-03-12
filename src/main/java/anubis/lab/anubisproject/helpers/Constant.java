package anubis.lab.anubisproject.helpers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Constant {
    public static final String PHOTO_DIRECTORY = System.getProperty("resources")+"/profilUser";
    public static final String X_REQUESTED_WITH = "X-Requested-with";

    public String dateFormated(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        return now.format(formatter);
    }
}

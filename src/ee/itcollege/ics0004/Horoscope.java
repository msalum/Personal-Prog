package ee.itcollege.ics0004;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Horoscope {

    String[] zodiacSigns = new String[]

            {
                    "Capricorn", "Aquarius", "Pisces", "Aries", "Taurus", "Gemini",
                    "Cancer", "Leo", "Virgo", "Libra",
                    "Scorpio", "Sagittarius"
            };

    public static void main(String[] args) {

            Scanner s = new Scanner(System.in);
            Horoscope x = new Horoscope();


        System.out.println("___      ___        ___    ___   ___    __    ___  ___   ___   ___ ");
        System.out.println(" |  |__||__   |__| |   |  | __| |   |  |__   |    |   | |___| |___ ");
        System.out.println(" |  |  ||___  |  | |___|  |   | |___|  ___|  |___ |___| |     |___ ");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("WELCOME TO THE HOROSCOPE");
        System.out.println("");


        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM");
        System.out.printf("Enter Your Birthday dd/mm: ");
        String input = s.nextLine();
        Calendar cal = Calendar.getInstance();
        cal.setLenient(false);
        try
        {
            Date date = sdf.parse(input);
            cal = new GregorianCalendar();
            cal.setTime(date);
        }
        catch (ParseException pe)
        {
            pe.printStackTrace();
        }

        // write your code here







}

}
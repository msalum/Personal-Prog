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


        System.out.println("___      ___        ___    ___   ___    ___   ___  ___   ___   ___ ");
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

        System.out.printf("Your Zodiac Sign is: %s\n",
                x.getZodiac(cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)));


        String text=("   The Tip of the day for (SIGN) is: " +
                "Tensions with money, ownership, or workload and shared " +
                "responsibilities are mounting now,(SIGN). While this may take another" +
                "day or two to play out,(SIGN) likely to confront some of these matters" +
                "today, even if this happens indirectly. An awkward angle between Mars" +
                "and Chiron points to the need to make adjustments rather than bold moves." +
                "Others may not be clear with (SIGN) today, but consider that they may " +
                "not be in touch with what it is they truly desire.(SIGN), try not to " +
                "worry about mixed signals and instead wait until others have sorted " +
                "things out. Asserting yourself may have awkward results since this is " +
                "not the best day to understand your own desires, either. This also" +
                "applies to material desires, so you might want to put off making larger" +
                "purchases just for now. (SIGN), do not worry,tomorrow will be a better day!");

        System.out.printf("Do you want Horoscope of the day?(Yes/No): ");
        Scanner in = new Scanner(System.in);
        String exerciseQuestion;
        while (true){
            //get the user input
            exerciseQuestion = in.nextLine();
            //check if user input is yes or no
            if((exerciseQuestion.equalsIgnoreCase("yes")) ||
                    exerciseQuestion.equalsIgnoreCase("no"))
                //if yes break and continue with your code
                break;
            else
                //else loop back to get user input until answer is yes/no
                System.out.println("Please answer with YES or NO only:");

        }//while . i.e answer not yes or no

        if(exerciseQuestion.equalsIgnoreCase("yes")){
            //System.out.println(text);
            //do your code

            text=text.replace("(SIGN)","...");
            System.out.println(text);
        }
        else{
            System.out.println("Thank you for using The Horoscope!");
            //do your not exercise code here

        }


        /*String zodiac = "Your Zodiac Sign is: %s\n";

        if ( text.contains("(SIGN)")) {
            String zodiac = text.replaceFirst(String text, String zodiac);
            System.out.println(zodiac);


        }else{
            System.out.println("not found!");
        }
          */



    }

        // Capricorn December 22 - January 19            jan=0
        // Aquarius January 20 - February 18             feb=1
        // February 19 - March 20 pisces                 mar=2
        // Aries March 21 - April 19                     apr=3
        // Taurus April 20 - May 20                      may=4
        // Gemini May 21 - June 20                       jun=5
        // Cancer June 21 - July 22                      jul=6
        // Leo July 23 - August 22                       aug=7
        // Virgo August 23 - September 22                sep=8
        // Libra September 23 - October 22               oct=9
        // Scorpio October 23 - November 21              nov=10
        // Sagittarius November 22 - December 21         dec=11

    public String getZodiac(int month, int day)
    {
        if((month == 11) && ( day>= 22) || (month == 0) && (day <= 19)) {
            return zodiacSigns[0];
        }else if((month == 0) && (day >= 20)  || (month == 1) && (day <= 18)) {
            return zodiacSigns[1];
        } else if((month == 1) && (day >= 19)  || (month == 2) && (day <= 20)) {
            return zodiacSigns[2];
        } else if((month == 2) && (day >= 21)  || (month == 3) && (day <= 19)) {
            return zodiacSigns[3];
        } else if((month == 3) && (day >= 20)  || (month == 4) && (day <= 20)) {
            return zodiacSigns[4];
        } else if((month == 4) && (day >= 21)  || (month == 5) && (day <= 20)) {
            return zodiacSigns[5];
        } else if((month == 5) && (day >= 21)  || (month == 6) && (day <= 22)) {
            return zodiacSigns[6];
        } else if((month == 6) && (day >= 23)  || (month == 7) && (day <= 22)) {
            return zodiacSigns[7];
        } else if((month == 7) && (day >= 23)  || (month == 8) && (day <= 21)) {
            return zodiacSigns[8];
        } else if((month == 8) && (day >= 22) || (month == 9) && (day <= 21)) {
            return zodiacSigns[9];
        } else if((month == 9) && (day >= 24)  || (month == 10) && (day <= 22)) {
            return zodiacSigns[10];
        } else if((month == 10) && (day >= 23)  || (month == 11) && (day <= 21)) {
            return zodiacSigns[11];
        } else {
            return null;
        }

        /*while(month > 10) {
            System.out.println("Try again!"); //prints when month > 13
            month ++;
        }*/




    // write your code here







}
}

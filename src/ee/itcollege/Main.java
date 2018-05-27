package ee.itcollege;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.text.ParseException;           // occurs whe String does not meet the standards
import java.text.SimpleDateFormat;         // date format MM/dd/yyyy
import java.util.Calendar;
import java.util.Date;                     // dates
import java.util.GregorianCalendar;        // most common calendar format, subclass of calendar
import java.util.Scanner;                  // produces input values
import java.util.concurrent.TimeUnit;

public class Main extends Application {
    private Stage window;
    private Stage dialog = new Stage();
    private TextField enterDate = new TextField();
    private TextField enterYesno = new TextField();
    private Button submitButton = new Button("Get Zodiac");
    private Label yourZodiac = new Label();
    private Text errorText = new Text();
    private Label exitMessage = new Label();
    private Label horoscopeContent = new Label();
    private String theSign = "";


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        window.setTitle("The Horoscope");

        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(window);


        BorderPane border = new BorderPane();

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 10, 0, 10));

        Text heading = new Text("Welcome to the Horoscope");
        heading.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        grid.add(heading, 1, 0);

        Text birthDay = new Text("Enter Your Birthday dd/mm: ");
        birthDay.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        grid.add(birthDay, 1, 1);

        enterDate.setPromptText("Enter date");
        grid.add(enterDate, 1, 2);

        //Text zodiac = new Text("Your Zodiac Sign is: ");
        //zodiac.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        //grid.add(zodiac, 1, 4);

        //yourZodiac.setFont(Font.font("Arial", FontWeight.NORMAL, 15));
        //grid.add(yourZodiac, 1, 5);

        grid.add(submitButton, 1, 3);

        grid.add(horoscopeContent, 1,4);

        errorText.setFont(Font.font("Arial", FontWeight.NORMAL, 15));
        grid.add(errorText, 2, 2);


        submitButton.setOnAction(e -> handleSubmit());


        Scene scene = new Scene(grid, 800, 400);
        window.setScene(scene);

        window.show();

    }
    private void handleSubmit(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM");   // custom date format



        // while loop ends calculation, otherwise program would calculate endless possibilities
        String input = "";
        String inputTemp = enterDate.getText();

        try {
            String[] splitData = inputTemp.split("/");

            // Here code controls dates. NOTE! All monthts are 31 days, which is wrong, but
            // since it does not change the truth of output, then its correct

            if ((Integer.parseInt(splitData[0]) > 0 && Integer.parseInt(splitData[0]) <= 31)
                    && (Integer.parseInt(splitData[1]) <= 12 && Integer.parseInt(splitData[1]) > 0)) {
                input = inputTemp;
                openDialog();
            } else {
                // if entered input is out of range ( date higher than 31, month hirgher than 12
                errorText.setText("Error! Please try again! \n");

            }
        } catch (NumberFormatException e) {
            // if date etered in other format than dd/mm
            errorText.setText("Wrong format! Please enter date as dd/mm. \n");
        }

    }

    private void openDialog() {

        VBox dialogVbox = new VBox(20);

        Label zodiac = new Label("Your Zodiac Sign is: ");
        zodiac.setMinWidth(300);
        zodiac.setAlignment(Pos.CENTER);

        theSign = getZodiac();
        yourZodiac.setText(theSign);
        yourZodiac.setMinWidth(300);
        yourZodiac.setAlignment(Pos.CENTER);

        Label label = new Label("Do you want Horoscope of the day?(Yes/No): ");
        label.setMinWidth(300);
        label.setAlignment(Pos.CENTER);

        HBox hbox = new HBox();
        hbox.setMinWidth(300);
        hbox.setPrefWidth(500);
        hbox.setMaxWidth(1000000);
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        //hbox.setStyle("-fx-background-color: #336699;");


        Button yesButton = new Button("YES");
        yesButton.setPrefSize(100, 20);

        Button noButton = new Button("NO");
        noButton.setPrefSize(100, 20);

        Pane spacer = new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        spacer.setMinSize(10, 1);

        exitMessage.setMinWidth(300);
        exitMessage.setAlignment(Pos.CENTER);

        hbox.getChildren().addAll(yesButton, spacer, noButton);
        dialogVbox.getChildren().addAll(zodiac, yourZodiac, label, hbox, exitMessage);

        Scene dialogScene = new Scene(dialogVbox, 300, 250);
        dialog.setScene(dialogScene);
        dialog.show();

        yesButton.setOnAction(e -> yesSubmit());
        noButton.setOnAction(e -> noSubmit());
    }

    private void yesSubmit() {
        dialog.hide();

        String text = ("   The Tip of the day for (SIGN) is: " +
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


        text = text.replace("(SIGN)", theSign);
        //horoscopeContent.setDisable(true);
        horoscopeContent.setWrapText(true);
        horoscopeContent.setText(text);

    }

    private void noSubmit() {
        exitMessage.setText("Thank you for using The Horoscope!");
    }

    private String getZodiac() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM");

        String[] zodiacSigns = new String[]

                {
                        "Capricorn", "Aquarius", "Pisces", "Aries", "Taurus", "Gemini",
                        "Cancer", "Leo", "Virgo", "Libra",
                        "Scorpio", "Sagittarius"
                };

        Calendar cal = Calendar.getInstance();
        cal.setLenient(false);
        try {
            Date date = sdf.parse(enterDate.getText());
            cal = new GregorianCalendar();
            cal.setTime(date);
        } catch (ParseException pe) {
            pe.printStackTrace();
        }

        int month = Calendar.MONTH;
        int day = Calendar.DAY_OF_MONTH;

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


        if ((month == 11) && (day >= 22) || (month == 0) && (day <= 19)) {
            return zodiacSigns[0];
        } else if ((month == 0) && (day >= 20) || (month == 1) && (day <= 18)) {
            return zodiacSigns[1];
        } else if ((month == 1) && (day >= 19) || (month == 2) && (day <= 20)) {
            return zodiacSigns[2];
        } else if ((month == 2) && (day >= 21) || (month == 3) && (day <= 19)) {
            return zodiacSigns[3];
        } else if ((month == 3) && (day >= 20) || (month == 4) && (day <= 20)) {
            return zodiacSigns[4];
        } else if ((month == 4) && (day >= 21) || (month == 5) && (day <= 20)) {
            return zodiacSigns[5];
        } else if ((month == 5) && (day >= 21) || (month == 6) && (day <= 22)) {
            return zodiacSigns[6];
        } else if ((month == 6) && (day >= 23) || (month == 7) && (day <= 22)) {
            return zodiacSigns[7];
        } else if ((month == 7) && (day >= 23) || (month == 8) && (day <= 21)) {
            return zodiacSigns[8];
        } else if ((month == 8) && (day >= 22) || (month == 9) && (day <= 21)) {
            return zodiacSigns[9];
        } else if ((month == 9) && (day >= 24) || (month == 10) && (day <= 22)) {
            return zodiacSigns[10];
        } else if ((month == 10) && (day >= 23) || (month == 11) && (day <= 21)) {
            return zodiacSigns[11];
        } else {
            return "";
        }



    }







    /*String[] zodiacSigns = new String[]    // results

            {
                    "Capricorn", "Aquarius", "Pisces", "Aries", "Taurus", "Gemini",
                    "Cancer", "Leo", "Virgo", "Libra",
                    "Scorpio", "Sagittarius"
            };*/

    /*public static void main(String[] args) {

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


        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM");   // custom date format

        String input = "";


        // while loop ends calculation, otherwise program would calculate endless possibilities

        while (true) {
            System.out.println("Enter Your Birthday dd/mm: ");
            String inputTemp = s.nextLine();

            try {
                String[] splitData = inputTemp.split("/");

                // Here code controls dates. NOTE! All monthts are 31 days, which is wrong, but
                // since it does not change the truth of output, then its correct

                if ((Integer.parseInt(splitData[0]) > 0 && Integer.parseInt(splitData[0]) <= 31)
                        && (Integer.parseInt(splitData[1]) <= 12 && Integer.parseInt(splitData[1]) > 0)) {
                    input = inputTemp;
                    break;
                } else {
                    // if entered input is out of range ( date higher than 31, month hirgher than 12
                    System.out.print("Error! Please try again! \n");

                }
            } catch (NumberFormatException e) {
                // if date etered in other format than dd/mm
                System.out.print("Wrong format! Please enter date as dd/mm. \n");
            }

        }

        // java Calendar utility

        Calendar cal = Calendar.getInstance();
        cal.setLenient(false);
        try {
            Date date = sdf.parse(input);
            cal = new GregorianCalendar();
            cal.setTime(date);
        } catch (ParseException pe) {
            pe.printStackTrace();
        }

        // if input is entered correctly than output will be correct Zodiac

        String myzodiac = x.getZodiac(cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));

        System.out.printf("Your Zodiac Sign is: %s\n", myzodiac);

        // Daily Horoscope

        String text = ("   The Tip of the day for (SIGN) is: " +
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

        System.out.println("Do you want Horoscope of the day?(Yes/No): ");

        // only yes/no input, if yes printout text, if no printout else value

        Scanner in = new Scanner(System.in);
        String exerciseQuestion;

        // if users aswers anything else than yes or no
        while (true) {
            exerciseQuestion = in.nextLine();

            if ((exerciseQuestion.equalsIgnoreCase("yes")) ||
                    exerciseQuestion.equalsIgnoreCase("no"))
                break;

            else {
                System.out.println("Please answer with YES or NO only:");

            }
        }

        // if user enters yes, program will print out upper text replacing (SIGN) with correct Zodiac
        if (exerciseQuestion.equalsIgnoreCase("yes")) {
            text = text.replace("(SIGN)", myzodiac);
            System.out.println(text);
        }

        // if user does not want to read Horoscope of the day, than program will exit with nice wish
        else {
            System.out.println("Thank you for using The Horoscope!");

        }

    }



 // Principle for calculating Zodiacs.


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

    public String getZodiac(int month, int day) {
        if ((month == 11) && (day >= 22) || (month == 0) && (day <= 19)) {
            return zodiacSigns[0];
        } else if ((month == 0) && (day >= 20) || (month == 1) && (day <= 18)) {
            return zodiacSigns[1];
        } else if ((month == 1) && (day >= 19) || (month == 2) && (day <= 20)) {
            return zodiacSigns[2];
        } else if ((month == 2) && (day >= 21) || (month == 3) && (day <= 19)) {
            return zodiacSigns[3];
        } else if ((month == 3) && (day >= 20) || (month == 4) && (day <= 20)) {
            return zodiacSigns[4];
        } else if ((month == 4) && (day >= 21) || (month == 5) && (day <= 20)) {
            return zodiacSigns[5];
        } else if ((month == 5) && (day >= 21) || (month == 6) && (day <= 22)) {
            return zodiacSigns[6];
        } else if ((month == 6) && (day >= 23) || (month == 7) && (day <= 22)) {
            return zodiacSigns[7];
        } else if ((month == 7) && (day >= 23) || (month == 8) && (day <= 21)) {
            return zodiacSigns[8];
        } else if ((month == 8) && (day >= 22) || (month == 9) && (day <= 21)) {
            return zodiacSigns[9];
        } else if ((month == 9) && (day >= 24) || (month == 10) && (day <= 22)) {
            return zodiacSigns[10];
        } else if ((month == 10) && (day >= 23) || (month == 11) && (day <= 21)) {
            return zodiacSigns[11];
        } else {
            return null;
        }



    }*/
}


                      //The End
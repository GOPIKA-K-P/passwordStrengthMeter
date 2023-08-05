package com.example.passwordmeter;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordMeterFX extends Application {
    private PasswordMeter passwordMeter;
    private Label resultLabel;
    @Override
    public void start(Stage primaryStage) {
        passwordMeter = new PasswordMeter();
        Label titleLabel = new Label("Password Strength Checker");
        titleLabel.setStyle("-fx-font-size: 16pt; -fx-font-weight: bold;");
        Label passwordLabel = new Label("Enter the Password:");
        PasswordField passwordField = new PasswordField();
        Button checkButton = new Button("Check");
        checkButton.setOnAction(e -> checkPassword(passwordField.getText()));
        resultLabel = new Label();
        resultLabel.setWrapText(true);
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20));
        vbox.getChildren().addAll(titleLabel, passwordLabel, passwordField, checkButton, resultLabel);
        Scene scene = new Scene(vbox, 400, 200);
        primaryStage.setTitle("Password Strength Checker");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private void checkPassword(String pwd) {
        boolean strengthFlag = passwordMeter.strength(pwd);
        boolean isPresent = passwordMeter.check(pwd);
        if (strengthFlag && isPresent) {
            resultLabel.setText("Your password is strong and it is not in the 50K passwords list.");
        } else if (strengthFlag && !isPresent) {
            resultLabel.setText("Your password is present in the 50K passwords list.");
        } else if (!strengthFlag && isPresent) {
            resultLabel.setText("Your password is weak and it is in the 50K passwords list.");
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}
class PasswordMeter {
    boolean check(String pwd)
    {
        String filePath="C:\\Users\\GOPIKA\\Desktop\\SoftwareAG\\PasswordMeter\\src\\main\\java\\com\\example\\passwordmeter\\passwords.txt";
        try {
            FileReader fileIn = new FileReader(filePath);
            BufferedReader br= new BufferedReader(fileIn);
            String line;
            while((line=br.readLine())!=null) {
                if((line.contains(pwd)))
                    return false;
            }}
        catch (IOException e){
            System.out.println(e);
        }
        return true;
    }
    boolean strength(String pwd)
    {
        boolean flag=true;
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
        if(pwd.length()<12)
        {
            flag=false;
            System.out.println("Your Password length is less than 12");
        }
        if(!Character.isLetter(pwd.charAt(0)))
        {
            flag=false;
            System.out.println("Your Password must start with a Character");
        }
        Matcher matcher = pattern.matcher(pwd);
        if(!matcher.find())
        {
            flag=false;
            System.out.println("Your Password should contain atleast a Special Character");
        }
        if(pwd.equals(pwd.toLowerCase()))
        {
            flag=false;
            System.out.println("Your Password should contain atleast Capital Letter");
        }
        if(!pwd.matches(".*\\d.*"))
        {
            flag=false;
            System.out.println("Your Password should contain atleast a Digit");
        }
        return flag;
    }
}

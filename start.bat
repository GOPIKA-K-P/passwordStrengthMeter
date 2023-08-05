@echo off
set PATH_TO_FX="C:\Users\GOPIKA\Downloads\javafx-sdk-20.0.1\lib"
javac --module-path %PATH_TO_FX% --add-modules javafx.controls,javafx.fxml src\main\java\com\example\passwordmeter\PasswordMeterFX.java -d out
java --module-path %PATH_TO_FX% --add-modules javafx.controls,javafx.fxml -cp out com.example.passwordmeter.PasswordMeterFX
pause

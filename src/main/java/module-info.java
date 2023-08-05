module com.example.passwordmeter {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.passwordmeter to javafx.fxml;
    exports com.example.passwordmeter;
}
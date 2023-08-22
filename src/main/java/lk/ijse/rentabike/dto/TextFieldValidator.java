package lk.ijse.rentabike.dto;
import javafx.scene.control.TextField;

public class TextFieldValidator {

    private TextField textField;
    private String regex;
    private String message;

    public TextFieldValidator(TextField textField, String regex, String message) {
        this.textField = textField;
        this.regex = regex;
        this.message = message;
    }

    public boolean validate() {
        String text = textField.getText();
        if (!text.matches(regex)) {
            textField.setStyle("-fx-text-fill: red;");
            textField.setPromptText(message);
        } else {
            textField.setStyle("-fx-text-fill: green;");
            textField.setPromptText("");
        }
        return false;
    }
}

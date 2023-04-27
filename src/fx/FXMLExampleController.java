package fx;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
public class FXMLExampleController {
    public Text actionTarget;
    public void handleSubmitButtonAction(ActionEvent event){
        actionTarget.setText("Sign in button pressed");
    }
}

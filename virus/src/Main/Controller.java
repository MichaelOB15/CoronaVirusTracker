package Main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class Controller {

    @FXML
    WebView webView;

    @FXML
    public void onLoadHelpFile(ActionEvent event) {
        System.out.println("onLoadHelpFile clicked" + webView);
        WebEngine webEngine = webView.getEngine();
        webEngine.load(getClass().getResource("hi.html").toExternalForm());
    }
}
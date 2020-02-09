package Main;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import ComboBoxSearcher.ComboBoxSearcher;
import Scrapper.WebScrapper;
import gui.MainScreen;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.geometry.Pos;
import javafx.scene.control.Tooltip;
import java.lang.String;


public class Main extends Application {
	Stage window;
    @Override
    public void start(Stage primaryStage) {
    	window = primaryStage;
        window.setTitle("CoronaVirus Tracker");
        window.setMaximized(true);
        window.setResizable(false);
        LoadStartPage();
        window.show();
    }

    public void LoadStartPage() {
    	
    	Pane mainroot = new Pane();
    	MainScreen root = new MainScreen(this);
		
		// Text Styles for the Screen
		String MainTextStyle = "-fx-font: 100px Tahoma;"+
				"-fx-fill: blue;"+
			    "-fx-font-family: Courier New;"+
			    "-fx-font-weight: bold;"+
			    "-fx-font-size: 100;";
		
		String BaseButtonStyle ="-fx-text-fill: blue;"+
				"-fx-background-color:transparent;"+
				"-fx-border-color: blue;"+
			    "-fx-font: Courier New;"+
			    "-fx-font-family: Courier New;"+
			    "-fx-font-weight: bold;"+
			    "-fx-border-radius: 18;"+
			    "-fx-font-size: 40;";
		
		String HoverButtonStyle = "-fx-text-fill: grey;"+
			    "-fx-background-color: transparent;"+
			    "-fx-font: Courier New;"+
			    "-fx-border-color: grey;"+
			    "-fx-font-family: Courier New;"+
			    "-fx-font-weight: bold;"+
			    "-fx-border-radius: 18;"+
			    "-fx-font-size: 40;";
		
		// Main Root in the Scene
		VBox root1 = new VBox(40);
		root1.setAlignment(Pos.CENTER);
		// Button Root which will go within the main root
		HBox btns = new HBox(10);
		btns.setAlignment(Pos.CENTER);
		
		// Header Text
		Text StartText = new Text("Coronavirus Tracker");
		StartText.setStyle(MainTextStyle);
		
		double InfoButtonSizeX = 1000,InfoButtonButtonSizeY = 60;
		Button InfoButton = new Button();
		InfoButton = new Button("Info");
		InfoButton.setPrefSize(InfoButtonSizeX,InfoButtonButtonSizeY);
		InfoButton.setStyle(BaseButtonStyle);
		InfoButton.setOnAction(e -> {
			try {
				LoadInfoPage();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		HoverListener(InfoButton,BaseButtonStyle, HoverButtonStyle);

		btns.getChildren().add(InfoButton);
		
		root1.getChildren().addAll(StartText,btns);
		root1.setLayoutX(123);
		root1.setLayoutY(200);

		mainroot.getChildren().addAll(root,root1);
		window.setScene(new Scene(mainroot,2560,1600));
		window.setOnCloseRequest(e -> {root.stopGameLoop(); });

	}
    
    public void LoadInfoPage() throws IOException {
    	
    	String MainTextStyle = "-fx-font: 100px Tahoma;"+
				"-fx-fill: white;"+
			    "-fx-font-family: Courier New;"+
			    "-fx-font-weight: bold;"+
			    "-fx-font-size: 75;";

    	String SubTextStyle = "-fx-font: 100px Tahoma;"+
				"-fx-fill: white;"+
			    "-fx-font-family: Courier New;"+
			    "-fx-font-size: 20;";

    	String BoxBorder = "-fx-padding: 10;" +
                "-fx-border-style: solid inside;" + 
                "-fx-border-width: 3;" +
                "-fx-border-insets: 5;" + 
                "-fx-border-radius: 5;" + 
                "-fx-border-color: white;";
    	
    	String BaseButtonStyle ="-fx-text-fill: white;"+
				"-fx-background-color:transparent;"+
				"-fx-border-color:white;"+
			    "-fx-font: Courier New;"+
			    "-fx-font-family: Courier New;"+
			    "-fx-border-radius: 18;"+
			    "-fx-font-size: 15;";
		
		String HoverButtonStyle = "-fx-text-fill: grey;"+
			    "-fx-background-color: transparent;"+
			    "-fx-font: Courier New;"+
			    "-fx-border-color: grey;"+
			    "-fx-font-family: Courier New;"+
			    "-fx-border-radius: 18;"+
			    "-fx-font-size: 15;";


		BorderPane root = new BorderPane();

    	root.setStyle("-fx-background-color: black");
    	
    	VBox TopBox = new VBox();
    	Text toptext = new Text("Coronavirus Tracker");
    	toptext.setStyle(MainTextStyle);
    	TopBox.setStyle(BoxBorder);
    	TopBox.getChildren().add(toptext);
    	TopBox.setAlignment(Pos.CENTER);
    	root.setTop(TopBox);



    	VBox Left = new VBox(5);
    	VBox VLeftUp = new VBox(5);
    	VLeftUp.setStyle(BoxBorder);
    	VBox VLeftMid = new VBox(15);
    	Text CountryText = new Text("Select A Country to View Stats:");
    	CountryText.setStyle(SubTextStyle);
		VLeftMid.getChildren().add(CountryText);


		Text Mike = new Text(WebScrapper.MasScrape());
		Mike.setStyle(SubTextStyle);
		VLeftUp.getChildren().add(Mike);
        
    	HBox BS = new HBox(15);
        ComboBox<String> Places = new ComboBox<>();
        Places.setTooltip(new Tooltip());
        String[][] CoronaInfo = WebScrapper.Scrape();
        for (int i = 0; i < WebScrapper.Scrape().length; i++) {
        	Places.getItems().add(CoronaInfo[i][0]);
        }
        new ComboBoxSearcher<String>(Places);
		Places.getSelectionModel().selectFirst();

		VBox info = new VBox(20);
		int sbmtSizeX = 125,sbmtSizeY = 50;
		Button sbmt = new Button("Submit");
		sbmt.setStyle(BaseButtonStyle);
		sbmt.setPrefSize(sbmtSizeX, sbmtSizeY);
		HoverListener(sbmt,BaseButtonStyle,HoverButtonStyle);
		sbmt.setOnAction(e -> UpdateInfo(info,CoronaInfo,Places.getValue()));
		BS.getChildren().addAll(Places,sbmt);

		VLeftMid.getChildren().addAll(BS,info);
		VLeftMid.setStyle(BoxBorder);

        VBox VLeftDown = new VBox();
		VLeftDown.setStyle(BoxBorder);
        Text MM = new Text("Prediction:\n" +
				"Average Growth in China: 1415\n" +
				"Average Growth in Hubei Province: 855\n" +
				"Average Growth in Guangdong Province:  50.5\n" +
				"Average Growth in Zhejiang: 76.3");
        MM.setStyle(SubTextStyle);
        VLeftDown.getChildren().add(MM);

        Left.getChildren().addAll(VLeftUp,VLeftMid,VLeftDown);

		root.setLeft(Left);


		try {
			//root = FXMLLoader.load(getClass().getResource("sample.fxml"));
			root.setCenter(FXMLLoader.load(getClass().getResource("sample.fxml")));


		} catch(Exception e) {
			e.printStackTrace();
		}

        
    	// Maybe add prediction 
    	
    	window.setScene(new Scene(root));
    }

    public static void UpdateInfo(VBox info, String[][] CoronaInfo, String Country){
		String SubTextStyle = "-fx-font: 100px Tahoma;"+
				"-fx-fill: white;"+
				"-fx-font-family: Courier New;"+
				"-fx-font-size: 20;";

    	info.getChildren().clear();

    	int num = 0;
    	for (int i = 0; i < CoronaInfo.length; i++) {
    		if (CoronaInfo[i][0].equals(Country))
    			num = i;
		}


		Text TC = new Text("Total Cases in region: "+CoronaInfo[num][1]);
		TC.setStyle(SubTextStyle);
		Text TD = new Text("Total Deaths in region: "+CoronaInfo[num][3]);
		TD.setStyle(SubTextStyle);
		Text TR = new Text("Total Recovered in region: "+CoronaInfo[num][5]);
		TR.setStyle(SubTextStyle);

		info.getChildren().addAll(TC,TD,TR);

	}


	public static void HoverListener(Button button, String BaseStyle, String HoverStyle) {
		button.hoverProperty().addListener((observable, oldValue, newValue) -> {
		if (newValue) {
			button.setStyle(HoverStyle);
		} else {
			button.setStyle(BaseStyle);
		}
		});
	}

    public static void main(String[] args) {
        launch(args);
    }
    
}
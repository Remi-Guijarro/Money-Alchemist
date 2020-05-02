package test.dev.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import test.dev.Main;
import test.dev.model.services.alert.AlertService;
import test.dev.model.services.validators.ValidatorService;
import test.dev.model.services.validators.ValidatorService.VerificationType;

public class LoginController implements Initializable{

	@FXML
	private Circle btnClose;
	
	@FXML
	private Circle btnMin;
	
	@FXML
	private Label alertZoneSignUp;
	
	@FXML
	private Label alertZoneLogin;
	
	@FXML
	private TextField signInMailTextField;
	
	@FXML
	private TextField signInPasswordTextField;
	
	@FXML
	private TextField signUpMailTextField;
	
	@FXML
	private TextField signUpPasswordTextField;
	
	@FXML
	private Label forgotPasswordBtn;
	
	@FXML
	private Button loginBtn; 
	
	@FXML
	private Button createAccountBtn;
	
	@FXML
	private AnchorPane logInPage;
	
	@FXML
	private AnchorPane signInPage;
	
	@FXML
	private Button signInBtn;
	
	@FXML
	private ImageView btnBack;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML 
	private void handleForgotPassword() {
		
	}
	
	@FXML
	private void handleCreateAccount() {
		signInPage.toFront();
	}
	
	@FXML
	private void handleSignUp() {
		if(ValidatorService.checkTextField(signUpMailTextField, VerificationType.LOGIN_TEXTFIELD,alertZoneSignUp)
			&& ValidatorService.checkTextField(signUpPasswordTextField, VerificationType.PASSWORD_TEXTFIELD,alertZoneSignUp)) {
			handleBackToLogin();
		}
	}
	
	@FXML
	private void handleBackToLogin() {
		logInPage.toFront();
	}
	

	@FXML 
	private void handleExit() {
		System.exit(0);
	}
	
	@FXML
	private void handleMinmize() {
		Main.getStage().setIconified(true);
	}
}

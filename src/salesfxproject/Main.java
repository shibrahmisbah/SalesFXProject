/*
 * Group Members: Shibrah Misbah, Zain Iqbal
 * Student Numbers: 991593708 991612243 
 * Final Project
 * Sunday April 18th 2021
 */
package salesfxproject;

import content.Employee;
import content.EmployeeFile;
import content.SearchStage;
import java.util.ArrayList;
import java.util.Optional;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {

    private Label lblID = new Label ("ID: ");
    private TextField txtID = new TextField();
    
    private Label lblName = new Label ("Name: ");
    private TextField txtName = new TextField();
    
    private Label lblCity = new Label ("City: ");
    private TextField txtCity = new TextField();

    private Label lblPosition = new Label ("Position: ");
    private TextField txtPosition = new TextField();
    
    private Button btnAdd = new Button ("Add");
    private Button btnUpdate = new Button ("Update");
    private Button btnDelete = new Button ("Delete");
    private Button btnSearch = new Button ("Search");
    
    private Button btnFirst = new Button ("First");
    private Button btnNext = new Button ("Next");
    private Button btnPrevious = new Button ("Previous");
    private Button btnLast = new Button ("Last");
    
    ArrayList<Employee> employeeList = new ArrayList<>();
    
    int i = 0;

    @Override
    public void start(Stage primaryStage) {
          
        //read the data at startup and store into an ArrayList
        EmployeeFile.readData(employeeList);   
        
        //set the first textfields to the first employee's data
        txtID.setText(Integer.toString(employeeList.get(0).getID()));
        txtName.setText(employeeList.get(0).getName());       
        txtCity.setText(employeeList.get(0).getCity());
        txtPosition.setText(employeeList.get(0).getPosition());

        btnFirst.setOnAction(new FirstEmployee());
        btnNext.setOnAction(new Next());
        btnPrevious.setOnAction(new Previous());
        btnLast.setOnAction(new LastEmployee());
        btnAdd.setOnAction(new Add());
        btnUpdate.setOnAction(new Update());
        btnDelete.setOnAction(new Delete());
        primaryStage.setOnCloseRequest(new EndProgram());
        
        //have alert pop up when user doesn't type in number
        txtID.setOnKeyReleased((e)->{
                if(!e.getCode().isDigitKey()){
                Alert dlgError = new Alert(Alert.AlertType.ERROR);
                dlgError.setContentText("Please enter a number.");
                dlgError.show();
                }
            });

        btnSearch.setOnAction(new Search());
        
        Scene scene = new Scene(getGrid(),300,135);
        scene.getStylesheets().add("/css/StyleSheet.css");  
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public GridPane getGrid() {
        GridPane pane = new GridPane();
        
        /**add text, label, and button objects
           (object,column,row)
         **/
        
        pane.add(lblID,0,0); 
        pane.add(txtID,1,0); 
        pane.add(btnAdd,2,0);
        
        pane.add(lblName,0,1); 
        pane.add(txtName,1,1);
        pane.add(btnUpdate,2,1);
        
        pane.add(lblCity,0,2); 
        pane.add(txtCity,1,2); 
        pane.add(btnDelete,2,2);
          
        pane.add(lblPosition,0,3);
        pane.add(txtPosition,1,3); 
        pane.add(btnSearch,2,3);
        
        pane.add(btnFirst,0,5);
        pane.add(btnNext,1,5); 
        pane.add(btnPrevious,2,5);
        pane.add(btnLast,3,5);
   
        return pane;
    }
    
    //prints details of First Employee
    public class FirstEmployee implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent t){
       
        txtID.setText(Integer.toString(employeeList.get(0).getID()));
        txtName.setText(employeeList.get(0).getName());       
        txtCity.setText(employeeList.get(0).getCity());
        txtPosition.setText(employeeList.get(0).getPosition());
 
        }
    }
    
    //prints details of next employee by incrementing count variable
    public class Next implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent t) {
            
        if (i != employeeList.size() - 1) {
                i++;
            }
            returnRecord();     
        }   
    }
    
    //prints details of previous employee by decrementing global count
     public class Previous implements EventHandler<ActionEvent> {
            @Override
            public void handle(ActionEvent t){
                
            if(i != 0 ){
                 i--;
            } 
            returnRecord(); 
        }
    }
     
    //prints details of Last Employee
    public class LastEmployee implements EventHandler<ActionEvent> {
        
            @Override
            public void handle(ActionEvent t) {
               
               txtID.setText(Integer.toString(employeeList.get(employeeList.size()-1).getID()));
               txtName.setText(employeeList.get(employeeList.size()-1).getName());       
               txtCity.setText(employeeList.get(employeeList.size()-1).getCity());
               txtPosition.setText(employeeList.get(employeeList.size()-1).getPosition());           
            }
    }
    
    public class Add implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent t) {
            
            txtID.clear();
            txtName.clear();
            txtCity.clear();
            txtPosition.clear();
            txtID.requestFocus();
            
            //enable after cancelling "UPDATE" prompt
            btnAdd.setDisable(true);
            btnDelete.setDisable(true);
        }      
    }
    
    public class Delete implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent t) {
                 
            Alert confirmDelete = new Alert(Alert.AlertType.CONFIRMATION);
            Optional<ButtonType> result = confirmDelete.showAndWait();
            
            if(result.get() == ButtonType.OK){
            
            employeeList.remove(employeeList.get(i));
            returnRecord();
            confirmDelete.show();
            confirmDelete.setContentText("Success: Record Deleted");
                       
            }else
            {
                //keep previous record
                returnRecord();
            }
        }        
    }
    
    public class Search implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent t) {
            
            SearchStage searcher = new SearchStage(employeeList);
            searcher.setTitle("Search by any Filter:");
            searcher.show();
        }
    }
    
    public class Update implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent t) {
      
        Alert dlgCon = new Alert(Alert.AlertType.CONFIRMATION);
        Alert error = new Alert(Alert.AlertType.ERROR);
        Alert successMsg = new Alert(Alert.AlertType.CONFIRMATION);

          //if Add button is disabled do the following: 
          if(btnAdd.isDisable()){
            
           Optional<ButtonType> result = dlgCon.showAndWait();
           
            if(result.get() == ButtonType.OK){
                
                //check if ID exists or not
                if(checkID(Integer.parseInt(txtID.getText()),employeeList)==false){
                    
                error.show();
                error.setContentText("Error: ID Already exists");
                
                returnRecord();
                btnAdd.setDisable(false);
                btnDelete.setDisable(false); 
                    
                }
                else{
                
                successMsg.show();
                successMsg.setContentText("Success: Record Added");
                
                Employee one = new Employee(Integer.parseInt(txtID.getText()));
                one.setName(txtName.getText());
                one.setCity(txtCity.getText());
                one.setPosition(txtPosition.getText());
                
                //add new object
                employeeList.add(one);
                
                btnAdd.setDisable(false);
                btnDelete.setDisable(false);
                }
            //if cancelled clicked keep current record
            }else
            {
                returnRecord();
                btnAdd.setDisable(false);
                btnDelete.setDisable(false); 
            }
            
          //if Add button is enabled do the following:
          }else if(!btnAdd.isDisable()){
              
              Optional<ButtonType> result = dlgCon.showAndWait();
              
              if(result.get() == ButtonType.OK){

               //use set method to update existing record
               employeeList.set(i,employeeList.get(i));
               
               //only allow changes to name, city, position
               employeeList.get(i).setName(txtName.getText());   
               employeeList.get(i).setCity(txtCity.getText());   
               employeeList.get(i).setPosition(txtPosition.getText()); 
    
               returnRecord();
               
               successMsg.setContentText("Success: Record Updated");
               successMsg.show();
                   
              }else{
                returnRecord();
              }    
            }
        }
     }
     
    public class EndProgram implements EventHandler<WindowEvent> {

        @Override
        public void handle(WindowEvent t) {
            
            EmployeeFile.saveData(employeeList);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Data Saved - Program Ending");
            alert.show();        
        }      
    }
    
    public boolean checkID(int ID, ArrayList<Employee> list) {
         
        boolean isValid = true;
    
        for(Employee employee : list){
            
            if(ID == employee.getID()){
               
                isValid = false;
            } 
        }  
        return isValid;
    }
    
    public void returnRecord() {
          
        txtID.setText(Integer.toString(employeeList.get(i).getID()));
        txtName.setText(employeeList.get(i).getName());       
        txtCity.setText(employeeList.get(i).getCity());
        txtPosition.setText(employeeList.get(i).getPosition());        
    }
    
    public static void main(String[] args) {
        Application.launch(args);
    }
}

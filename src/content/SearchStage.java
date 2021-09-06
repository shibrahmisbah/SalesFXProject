/*
 * Group Members: Shibrah Misbah, Zain Iqbal
 * Student Numbers: 991593708 991612243 
 * Final Project
 * Sunday April 18th 2021
 */
package content;

import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SearchStage extends Stage{
    
    private RadioButton position = new RadioButton("Positon");
    private RadioButton city = new RadioButton("City");
    private TextField search = new TextField();
    private TextArea displayRecords = new TextArea();
    private Button searchBtn = new Button("SEARCH");
    
    
    
    public SearchStage(ArrayList<Employee> employeeList){
     
        searchBtn.setOnAction((e)->{
            
            String display = new String();
        
            if(position.isSelected()){
                
                for(Employee employee : employeeList){
                   
                    if(search.getText().equalsIgnoreCase(employee.getPosition()))
                    {
                        display += employee.toString();
                    }
                    displayRecords.setText(display);
                    }     
                }
            else if(city.isSelected()){

                for(Employee employee : employeeList){

                    if(search.getText().equalsIgnoreCase(employee.getCity()))
                    {
                        display += employee.toString();
                    }
                    displayRecords.setText(display);
                }
            }
          });
        
        Scene scene = new Scene(displayStage(),500,300);
        setScene(scene);   
    }
      
     private BorderPane displayStage(){
        
        BorderPane bPane = new BorderPane();
        
        position.setSelected(true);

        ToggleGroup group = new ToggleGroup();
        position.setToggleGroup(group);
        city.setToggleGroup(group);
        
        HBox hPane = new HBox (position, city, search, searchBtn);
        VBox vPane = new VBox(displayRecords);
        
        Label title = new Label("Search By: ");
        
        bPane.setTop(title);
        bPane.setCenter(hPane);
        bPane.setBottom(vPane);
        
        return bPane;
        
    }  
}

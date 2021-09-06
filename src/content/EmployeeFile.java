/*
 * Group Members: Shibrah Misbah, Zain Iqbal
 * Student Numbers: 991593708 991612243 
 * Final Project
 * Sunday April 18th 2021
 */
package content;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;
import javafx.scene.control.Alert;

public class EmployeeFile
{
    
    /**
     * A method that takes an ArrayList as a parameter
     * Will read from the file that stores all records 
     * line by line.
     * @param employeeList 
     */
    public static void readData(ArrayList<Employee> employeeList)
    {
        try{
        
        FileReader fr = new FileReader("Employee.dat");
        BufferedReader br = new BufferedReader(fr);
        
        String line = "";
        StringTokenizer st = null;

        /**while line is not null, create an object using tokens 
         * from each line (10 objects)
         **/
        while((line = br.readLine()) != null) {
            
            st = new StringTokenizer(line,",");
            
            while(st.hasMoreTokens()) {
 
            Employee one = new Employee(Integer.parseInt(st.nextToken()));
            one.setName(st.nextToken());
            one.setCity(st.nextToken());
            one.setPosition(st.nextToken());
            employeeList.add(one);
            
            }
        }

        }catch(IOException e) {
                Alert alertErr = new Alert(Alert.AlertType.ERROR);
                alertErr.setContentText("Unable to read data.");
                alertErr.setContentText(e.toString());
                alertErr.show();        
        }  
        
    }   
      
    /**
     * A method that takes an ArrayList as a parameter
     * Will write the user's confirmed changes into the file
     * upon closing the window.
     * @param employeeList 
     */
    public static void saveData(ArrayList<Employee> employeeList){ 
        try{
            
        FileWriter fw = new FileWriter("Employee.dat");
        BufferedWriter bw = new BufferedWriter(fw);
        
        Iterator<Employee> employees = employeeList.iterator();
        while(employees.hasNext()) {
             Employee one = employees.next();
             bw.write(one.toString());
             bw.newLine();
        }
        
        bw.close();
        fw.close();
                    
        }catch(IOException e) {
                Alert alertErr = new Alert(Alert.AlertType.ERROR);
                alertErr.setContentText("Data not saved");
                alertErr.setContentText(e.toString());
                alertErr.show();
        }
    }
}

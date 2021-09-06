/*
 * Group Members: Shibrah Misbah, Zain Iqbal
 * Student Numbers: 991593708 991612243 
 * Final Project
 * Sunday April 18th 2021
 */
package content;

public class Employee {
    
    private int ID;
    private String name;
    private String city;
    private String position;
    
    public Employee(int ID){
        this.ID = ID;
    }
    
    public int getID() {
        return this.ID;
    }
    
    //setters and getters for name
    public void setName(String name){
        this.name = name;
    }
    
    public String getName(){
        return this.name;
    }
    
     //setters and getters for city
    public void setCity(String city){
        this.city = city;
    }
    
    public String getCity(){
        return this.city;
    }
    
    //setters and getters for position
    public void setPosition(String position){
        this.position = position;
    }
    
    public String getPosition(){
        return this.position;
    }
    
    @Override
    public String toString(){
        return "\n" + this.ID + "," + this.name + "," + this.city + "," + this.position;
    }  
}

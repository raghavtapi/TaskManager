package TaskManager_Meesho;

import java.time.LocalDate;
public class Task {
    int id;
    String title;
    String description;
    LocalDate date; 
    int priority_level;
    Task(int id, String title, String description, LocalDate date, int priority_level){
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.priority_level = priority_level;
    }
    public int getId(){
        return id;
    }
    public LocalDate getDate(){
        return date;
    }
    public int getPriority(){
        return priority_level;
    }
}

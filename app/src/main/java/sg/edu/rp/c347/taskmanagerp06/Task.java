package sg.edu.rp.c347.taskmanagerp06;

import java.io.Serializable;

/**
 * Created by 15035634 on 25/5/2017.
 */

public class Task implements Serializable {

    private int id;
    private String taskname;
    private String description;

    public Task(int id, String taskname, String description) {
        this.id = id;
        this.taskname = taskname;
        this.description = description;
    }

    public int getId() { return id;}

    public String getTaskname() { return taskname; }

    public String getDescription() { return description; }


    public void setId(int id) { this.id = id; }

    public void setTaskname(String taskname) { this.taskname = taskname; }

    public void setDescription(String description) { this.description = description;}
}

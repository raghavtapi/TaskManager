package TaskManager_Meesho;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class User {
    int id;
    List<Task> tasks;
    HashMap<Integer, Task> taskMapping;

    User(int id){
        this.id = id;
        this.tasks = new ArrayList<>();
        this.taskMapping = new HashMap<>();
    }

    public void createTask(int taskId, String title, String description, LocalDate date, int priority_level){
        if(taskMapping.get(taskId) != null){
            System.out.println("This task is already created.");
            return;
        }
        Task task = new Task(taskId, title, description, date, priority_level);
        this.tasks.add(task);
        taskMapping.put(taskId, task);
        System.out.println("Task Created.");
    }

    public int getDateUntilDueDate(int taskId){
        if(taskMapping.get(taskId) == null){
            System.out.println("This task does not exists.");
            return -1;
        }
        Task task = taskMapping.get(taskId);
        int daysRemaining = (int)(LocalDate.now().until(task.date, ChronoUnit.DAYS));
        return daysRemaining;
    }

    public List<Task> getTaskBasedOnDueDate(){
        List<Task> list = new ArrayList<>();
        PriorityQueue<Task> dueDatePriorityQueue = new PriorityQueue<>(
            (task1,task2) -> (int)(LocalDate.now().until(task1.date, ChronoUnit.DAYS) - 
            (int)(LocalDate.now().until(task2.date, ChronoUnit.DAYS)))
        );
        for(Task task: tasks){
            dueDatePriorityQueue.add(task);
        }
        while (!dueDatePriorityQueue.isEmpty()) {
            list.add(dueDatePriorityQueue.poll());
        }
        return list;
    }

    public List<Task> getTaskBasedOnPriority(){
        List<Task> list = new ArrayList<>();
        PriorityQueue<Task> dueDatePriorityQueue = new PriorityQueue<>(
            (task1,task2) -> task1.priority_level - task2.priority_level
        );
        for(Task task: tasks){
            dueDatePriorityQueue.add(task);
        }
        while (!dueDatePriorityQueue.isEmpty()) {
            list.add(dueDatePriorityQueue.poll());
        }
        return list;
    }

    // I was not able to code it on beacause I didn't know how to implement two
    // comparator at once in sorting. I tried doing it using hashmap but couldn't
    // beacause i am not good in java. I had 10 mins to do it but I wasn't able to do it.
    public List<Task> getTaskBasedOnDateThenOnPriority(){
        List<Task> newList = new ArrayList<>();
        for(Task task: tasks)
            newList.add(task);
        Collections.sort(newList, (task1,task2) -> {
            if(getDays(task1) == getDays(task2))
                return task1.priority_level - task2.priority_level;
            return getDays(task1) - getDays(task2);
            }
        );
        return newList;
    }

    private int getDays(Task task){
        return (int)(LocalDate.now().until(task.date, ChronoUnit.DAYS));
    }

    public static void main(String args[]){
        // LocalDate localDate = LocalDate.now();
        // System.out.println(localDate.getDayOfYear());

        User user = new User(1);
        user.createTask(1, "1","First Task", LocalDate.of(2024, 5, 16), 1);
        user.createTask(2, "2","Second Task", LocalDate.of(2024, 5, 17), 0);
        user.createTask(3, "3","Third Task", LocalDate.of(2024, 5, 16), 2);
        user.createTask(4, "3","Third Task", LocalDate.of(2024, 5, 16), 0);
        

        System.out.println(user.getDateUntilDueDate(1));
        System.out.println(user.getDateUntilDueDate(2));

        List<Task> tasks = user.getTaskBasedOnPriority();
        System.out.println("Priority of task");
        for(Task task: tasks)
            System.out.println(task.id);

        tasks = user.getTaskBasedOnDueDate();
        System.out.println("Due Date of task");
        for(Task task: tasks)
            System.out.println(task.id);

        tasks = user.getTaskBasedOnDateThenOnPriority();
        System.out.println("Due Date of task and then priority");
        for(Task task: tasks)
            System.out.println(task.id);

    }


}

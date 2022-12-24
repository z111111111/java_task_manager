import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TaskMng {
    int id;
    Scanner input;
    ArrayList<Task> tasks;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("DD/MM/YYYY");


    public TaskMng(){
        tasks = new ArrayList<>();
        input = new Scanner(System.in);
        id = 0;
        showMenu();
    }

    private void showMenu(){
        System.out.println("1 - Create task");
        System.out.println("2 - Delete task");
        System.out.println("3 - Change task");
        System.out.println("4 - Show tasks list");
        System.out.println("5 - Show tasks done");
        System.out.println("6 - Show tasks to do");
        System.out.println("7 - Show tasks by date solution");
        System.out.println("8 - Show all tasks by date creation");
        System.out.println("9 - Show open tasks by date creation");
        System.out.println("10 - Show closed tasks by date creation");
        System.out.println("11 - Show additional info");
        System.out.println("Else exit");
        try{
            int choice = input.nextInt();
            switch (choice){
                case 1:
                    System.out.println("***********************");
                    createTask();
                    break;
                case 2:
                    System.out.println("***********************");
                    showTasks();
                    removeTask();
                    break;
                case 3:
                    System.out.println("***********************");
                    showTasks();
                    changeTask();
                    break;
                case 4:
                    System.out.println("***********************");
                    showTasks();
                    showMenu();
                    break;
                case 5:
                    System.out.println("***********************");
                    showCompletedTasks();
                    showMenu();
                    break;
                case 6:
                    System.out.println("***********************");
                    showIncompleteTasks();
                    showMenu();
                    break;
                case 7:
                    System.out.println("***********************");
                    System.out.println("Enter solution date");
                    showByCompletionDate(dateInput());
                    showMenu();
                    break;
                case 8:
                    System.out.println("***********************");
                    System.out.println("Enter creation date");
                    showAllByCreationDate(dateInput());
                    showMenu();
                    break;
                case 9:
                    System.out.println("***********************");
                    showOpenByCreationDate(dateInput());
                    showMenu();
                    break;
                case 10:
                    System.out.println("***********************");
                    showDoneByCreationDate(dateInput());
                    showMenu();
                    break;
                case 11:
                    System.out.println("***********************");
                    showAddInfo();
                    showMenu();
                    break;
                default:

                    break;
            }
        } catch (InputMismatchException e){

        }


    }

    private LocalDate dateInput(){
        LocalDate localDate;
        while (true)
        {
            try{
                System.out.println("Input date formatted like DD//MM//YYYY");
                String date = input.next();
                localDate = LocalDate.parse(date,formatter);
            } catch (java.time.format.DateTimeParseException e){
                System.out.println("Incorrect");
                continue;
            }
            break;
        }
        return localDate;
    }
    //1
    private void createTask(){

        System.out.println("Enter task name");
        String name = input.next();

        System.out.println("Enter iitional info");
        String details= input.next();

        LocalDate localDate;

        System.out.println("Enter solution date");
        localDate = dateInput();
        tasks.add(new Task(id, name, details,LocalDate.now(), localDate));
        id += 1;
        System.out.println("***********************");
        showMenu();
    }
    //4
    private void showTasks(){
        System.out.println("Tasks list:");
        for (Task task : tasks) {
            System.out.println(tasks.indexOf(task) + " : " + task.getName());
        }
        System.out.println("***********************");
    }
    //5
    private void showCompletedTasks(){
        System.out.println("Solved tasks list:");
        for (Task task : tasks) {
            if (task.isCompleted())
                System.out.println(tasks.indexOf(task) + " : " + task.getName());
        }
        System.out.println("***********************");
    }
    //6
    private void showIncompleteTasks(){
        System.out.println("To do list:");
        for (Task task : tasks) {
            if (!task.isCompleted())
                System.out.println(tasks.indexOf(task) + " : " + task.getName());
        }
        System.out.println("***********************");
    }

    //7
    private void showByCompletionDate(LocalDate localDate){
        System.out.println("Tasks closed on " + localDate.toString());
        for (Task task : tasks) {
            if (task.getCompletionDate().equals(localDate))
                System.out.println(tasks.indexOf(task) + " : " + task.getName());
        }
        System.out.println("***********************");
    }
    //7
    private void showAllByCreationDate(LocalDate localDate){
        System.out.println("Tasks created at " + localDate.toString());
        for (Task task : tasks) {
            if (task.getCreationDate().equals(localDate))
                System.out.println(tasks.indexOf(task) + " : " + task.getName());
        }
        System.out.println("***********************");
    }

    private void showDoneByCreationDate(LocalDate localDate){
        System.out.println("Closed tasks created at " + localDate.toString());
        for (Task task : tasks) {
            if (task.getCreationDate().equals(localDate) && task.isCompleted())
                System.out.println(tasks.indexOf(task) + " : " + task.getName());
        }
        System.out.println("***********************");
    }

    private void showOpenByCreationDate(LocalDate localDate){
        System.out.println("Open tasks created at " + localDate.toString());
        for (Task task : tasks) {
            if (task.getCreationDate().equals(localDate) && !task.isCompleted())
                System.out.println(tasks.indexOf(task) + " : " + task.getName());
        }
        System.out.println("***********************");
    }
    //2
    private void removeTask(){
        try{
            System.out.println("Task id wanted to delete");
            tasks.remove(input.nextInt());
            System.out.println("***********************");
        } catch (IndexOutOfBoundsException e) {System.out.println("No such id");}
        showMenu();
    }
    //3
    private void changeTask(){

        System.out.println("Task id wanted to change");

        try{
            int taskID = input.nextInt();
            try{
                Task nowTask = tasks.get(taskID);
                int id = nowTask.getId();
                String name = nowTask.getName();
                String details = nowTask.getDetails();
                LocalDate creationDate = nowTask.getCreationDate();
                LocalDate completionDate = nowTask.getCompletionDate();
                System.out.println("=======================");
                System.out.println("1 - Name: " + name);
                System.out.println("2 - Additional info: " + details);
                System.out.println("3 - Date creation: " + creationDate.toString());
                System.out.println("4 - Date solution: " + completionDate.toString());
                System.out.println("=======================");
                try{
                    int choice = input.nextInt();
                    switch (choice){
                        case 1:
                            System.out.println("Enter name: ");
                            name = input.next();
                            break;
                        case 2:
                            System.out.println("Enter additional info: ");
                            details = input.next();
                            break;
                        case 3:
                            System.out.println("Enter date: ");
                            creationDate = dateInput();
                            break;
                        case 4:
                            System.out.println("Enter solution date: ");
                            completionDate = dateInput();
                            break;
                        default:
                            break;

                    }
                    tasks.set(taskID, new Task(id, name, details, creationDate, completionDate));
                } catch (InputMismatchException e){}
            } catch (IndexOutOfBoundsException e) {System.out.println("No such id");}
        } catch (InputMismatchException e) {System.out.println("No such points in menu");}



        showMenu();
    }

    private void showAddInfo(){

        System.out.println("Task id wanted to check");

        try{
            int taskID = input.nextInt();
            try{
                Task nowTask = tasks.get(taskID);
                String details = nowTask.getDetails();
                System.out.println(details);

            } catch (IndexOutOfBoundsException e) {System.out.println("No such id");}
        } catch (InputMismatchException e) {System.out.println("No such points in menu");}



        showMenu();
    }
}

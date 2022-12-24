import java.time.LocalDate;

public class Task {
    private String name,details;
    private int id;
    private LocalDate creationDate, completeDate;
    private Boolean completed;
    public Task(int id, String name, String details,LocalDate creationDate, LocalDate completeDate){
        this.id = id;
        this.name = name;
        this.details = details;
        this.creationDate = creationDate;
        this.completeDate = completeDate;
        completed = false;

        if (completeDate.isBefore(LocalDate.now())) completed = true;
    }

    public int getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public String getDetails(){
        return this.details;
    }
    public Boolean isCompleted(){
        return this.completed;
    }
    public LocalDate getCreationDate(){
        return creationDate;
    }
    public LocalDate getCompletionDate(){
        return completeDate;
    }
}

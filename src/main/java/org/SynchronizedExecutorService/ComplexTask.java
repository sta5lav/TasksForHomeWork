package org.SynchronizedExecutorService;

import java.util.*;

public class ComplexTask{

    private ArrayList<Task> tasks;

    ComplexTask(int numberOfTasks) {
        tasks = createTask(numberOfTasks);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    private ArrayList<Task> createTask(int count) {
        ArrayList<Task> tasks = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            tasks.add(new Task());
        }
        return tasks;
    }

}

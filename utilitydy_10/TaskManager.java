package utilitydy_10;
//step-11

import first_project.day11_Runnable_ex;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class TaskManager {
    private int threadCount;
    private ExecutorService executorService;

    public TaskManager(int threadCount) {
        this.threadCount = threadCount;
        this.executorService = Executors.newFixedThreadPool(threadCount);
    }

    public void waitTillQueueIsFreeAndAddTask(Runnable runnable) {
        while (getQueueSize() >= threadCount) {
            try {
                System.out.println("Sleeping");
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        addTask(runnable);
    }

    public void addTask(Runnable runnable) {
        this.executorService.submit(runnable);
    }

    //getQueuesize taken by stackoverflow
    private int getQueueSize() {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) (executorService);
        return executor.getQueue().size();
    }
}
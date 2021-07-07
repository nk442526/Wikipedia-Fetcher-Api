package utilitydy_10;
//step-11

import first_project.day11_Runnable_ex;
import first_project.day11_Thread_ex;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadManager {
    private int poolSize;
    private ExecutorService executorService;

    public ThreadManager(int poolSize) {
        this.poolSize=poolSize;
        executorService = Executors.newFixedThreadPool(1000);
    }

    public void execute(Runnable runnable) {
        executorService.submit(runnable);
    }


    //   public class ThreadManager{
    public static void main(String[] args) {

        ThreadManager threadManager = new ThreadManager(100);
        // ExecutorService executorService=Executors.newFixedThreadPool(1000);
        for (int i = 0; i < 100000; i++) {
            day11_Runnable_ex temp = new day11_Runnable_ex("THREAD-NO-" + i, +0 + 500);
            // executorService.submit(temp);
            threadManager.execute(temp);
        }
         System.out.println("##################################");  /*ye first line me hi mil jayega , or phir bhi thread chlte jaa rha hai
        mtlb ki wo i=100000 time tak chlte jayega queue me ,so this is the problem now . So for solving this problem we creat
        task manager in which i have the thread count .....
*/
        //1. Task manager for N no of threads parallely!
        //2.Let say i want Main Thread to pause when there is enough queue size!
        // }

    }
}
package multithreading;
//this is first method of implementing multithreading in java by extending thread class but here the problem is that we cannot extend
//more than one class in java so if we want to extend another class then we cannot extend thread class so we can use runnable interface
//which is implemented in multithreading1.java

class mThread extends Thread{
    public void run()
    {
        for(int i=0;i<100;i++)
        {
            System.out.println("Thread1 is running");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class nThread extends Thread{
    public void run()
    {
        for(int i=0;i<100;i++)
        {
            System.out.println("Thread2 is running");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } 
    }
}

public class multithreading {
    public static void main(String args[])
    {
        mThread t1=new mThread();
        nThread t2=new nThread();
        t1.start();
        t2.start();
    }
}
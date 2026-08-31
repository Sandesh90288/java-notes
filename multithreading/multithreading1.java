package multithreading;
//this is the second method of implementing multithreading in java by implementing the runnable interface

class mRunnable implements Runnable{
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

class nRunnable implements Runnable{
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

public class multithreading1 {
    public static void main(String args[])
    {
        mRunnable runnable1=new mRunnable();
        nRunnable runnable2=new nRunnable();
        Runnable runnable3=new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<100;i++)
                {
                    System.out.println("Thread3 is running");
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Runnable runnable4=()->{
            for(int i=0;i<100;i++)
            {
                System.out.println("Thread4 is running");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread t1=new Thread(runnable1);
        Thread t2=new Thread(runnable2);
        Thread t3=new Thread(runnable3);
        Thread t4=new Thread(runnable4);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}

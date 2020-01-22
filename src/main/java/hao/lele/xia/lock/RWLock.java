package hao.lele.xia.lock;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author chenhao
 * @description <p> 读写锁
 * created by chenhao 2020/1/22 10:44
 */
public class RWLock {

    private static final ReadWriteLock LOK = new ReentrantReadWriteLock();

    private Integer count = 0;

    private void write(){
        LOK.writeLock().lock();
        try {
            System.out.println("start write lock");
            count++;
            System.out.println("end write lock");
        }finally {
            LOK.writeLock().unlock();
        }
    }

    private void read(){
        LOK.readLock().lock();
        try {
            System.out.println("start read lock");
            System.err.println(count);
            System.out.println("end read lock");
        }finally {
            LOK.readLock().unlock();
        }
    }

    public static void main(String[] args) {
        RWLock d = new RWLock();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    d.write();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t1.start();
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    d.read();
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t2.start();
    }

}

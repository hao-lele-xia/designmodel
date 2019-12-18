package hao.lele.xia.thread;

import lombok.Data;

/**
 * @author chenhao
 * @description <p>
 * created by chenhao 2019/12/18 10:12
 * thread.Join把指定的线程加入到当前线程，可以将两个交替执行的线程合并为顺序执行的线程。
 *
 * 比如在线程B中调用了线程A的Join()方法，直到线程A执行完毕后，才会继续执行线程B。
 *
 * t.join();      //调用join方法，等待线程t执行完毕
 * t.join(1000);  //等待 t 线程，等待时间是1000毫秒。
 */
@Data
public class JoinDemo implements Runnable{

    private Integer time;

    @Override
    public void run() {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName());
    }

    /**
     * thread.Join把指定的线程加入到当前线程，可以将两个交替执行的线程合并为顺序执行的线程。
     *
     * 比如在线程B中调用了线程A的Join()方法，直到线程A执行完毕后，才会继续执行线程B。
     *
     * t.join();      //调用join方法，等待线程t执行完毕
     * t.join(1000);  //等待 t 线程，等待时间是1000毫秒。
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        JoinDemo j1 = new JoinDemo();
        Thread t1 = new Thread(j1);
        j1.setTime(5000);
        t1.setName("j1");
        t1.start();
        t1.join();
        JoinDemo j2 = new JoinDemo();
        Thread t2 = new Thread(j2);
        j2.setTime(1000);
        t2.setName("j2");
        t2.start();
        t2.join();
        System.out.println(123);
    }


}

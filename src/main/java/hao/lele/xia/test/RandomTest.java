package hao.lele.xia.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author chenhao
 * @description <p>
 * created by chenhao 2019/12/26 9:02
 */
public class RandomTest {
    public static void main(String[] args) {
        threadLocalRandom();
    }

    private static void threadLocalRandom(){
        AtomicInteger a1 = new AtomicInteger();
        AtomicInteger a2 = new AtomicInteger();
        AtomicInteger a3 = new AtomicInteger();
        for(Integer i=0;i<100000;i++){
            double random = ThreadLocalRandom.current().nextDouble();
            if(random < 0.4D){
                a1.incrementAndGet();
            }
            if(0.4D <random && random < 0.7D){
                a2.incrementAndGet();
            }
            if(random > 0.7D){
                a3.incrementAndGet();
            }
        }
        System.out.println(a1.get());
        System.out.println(a2.get());
        System.out.println(a3.get());
    }

    private static void mathRd(){
        AtomicInteger a1 = new AtomicInteger();
        AtomicInteger a2 = new AtomicInteger();
        AtomicInteger a3 = new AtomicInteger();
        for(Integer i=0;i<100000;i++){
            double random = Math.random();
            if(random < 0.4D){
                a1.incrementAndGet();
            }
            if(0.4D <random && random < 0.7D){
                a2.incrementAndGet();
            }
            if(random > 0.7D){
                a3.incrementAndGet();
            }
        }
        System.out.println(a1.get());
        System.out.println(a2.get());
        System.out.println(a3.get());
    }

    private static void rd1(){
        Random rd = new Random();
        List<Integer> list0 = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        Integer temp = null;
        for(int i=0;i<1000000;i++){
            temp = rd.nextInt(3);
            if(temp == 0){
                list0.add(temp);
            }
            if(temp == 1){
                list1.add(temp);
            }
            if(temp == 2){
                list2.add(temp);
            }
        }
        System.out.println(list0.size());
        System.out.println(list1.size());
        System.out.println(list2.size());
    }
}

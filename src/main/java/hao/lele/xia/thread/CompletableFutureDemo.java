package hao.lele.xia.thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author chenhao
 * @description <p>
 * created by chenhao 2019/12/18 10:30
 *
 * https://www.jianshu.com/p/220d05525f27
 */
public class CompletableFutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        join();
    }

    public static void join() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> result = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 10 / 2;
        })
                .whenComplete((s,e) -> {
                    System.out.println(s);
                    System.out.println(e.getMessage());
                })
                .exceptionally((e) -> {
                    System.out.println(e.getMessage());
                    return 30;
                });;
        System.out.println(123);
        //getnow 不阻塞
        System.out.println(result.getNow(10));
        //get 阻塞方法
        System.out.println(result.get());
        System.out.println(321);
        //立即计算，如果计算完了，这个计算就没有用
        result.complete(8);
        System.out.println(result.getNow(2));
    }

    /**
     * handle 方法和whenComplete方法类似，只不过接收的是一个 BiFunction<? super T,Throwable,? extends U> fn 类型的参数，
     * 因此有 whenComplete 方法和 转换的功能 (thenApply)
     *
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void handle() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture
                .supplyAsync(() -> 10 / 0)
                .handle((t, e) -> {
                    System.out.println(e.getMessage());
                    return 10;
                });
        System.out.println(future.get());
    }

    public static void thenApply() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture
                .supplyAsync(() -> 1)
                .thenApply((a) -> {
                    System.out.println(a);//1
                    return a * 10;
                }).thenApply((a) -> {
                    System.out.println(a);//10
                    return a + 10;
                }).thenApply((a) -> {
                    System.out.println(a);//20
                    return a - 5;
                });
        System.out.println(future.get());//15
    }
}

package hao.lele.xia.json;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.results.RunResult;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author chenhao
 * @description <p>
 * created by chenhao 2019/12/5 11:05
 */
@BenchmarkMode(Mode.SingleShotTime)
@OutputTimeUnit(TimeUnit.SECONDS)
@State(Scope.Benchmark)
public class JsonSerializeBenchmark {
    /**
     * 序列化次数参数
     */
    @Param({"1000", "10000", "100000"})
    private int count;

    private Person p;

    public static void main(String[] args) throws Exception {
        Options opt = new OptionsBuilder()
                .include(JsonSerializeBenchmark.class.getSimpleName())
                .forks(1)
                .warmupIterations(0)
                .build();
        Collection<RunResult> results =  new Runner(opt).run();
    }

    @Benchmark
    public void JsonLib() {
        for (int i = 0; i < count; i++) {
            JsonLibUtil.bean2Json(p);
        }
    }

    @Benchmark
    public void Gson() {
        for (int i = 0; i < count; i++) {
            GsonUtil.bean2Json(p);
        }
    }

    @Benchmark
    public void FastJson() {
        for (int i = 0; i < count; i++) {
            FastJsonUtil.bean2Json(p);
        }
    }

    @Benchmark
    public void Jackson() {
        for (int i = 0; i < count; i++) {
            JacksonUtil.bean2Json(p);
        }
    }

    @Setup
    public void prepare() {
        List<Person> friends=new ArrayList<Person>();
        friends.add(createAPerson("小明",null));
        friends.add(createAPerson("Tony",null));
        friends.add(createAPerson("陈小二",null));
        p=createAPerson("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",friends);
    }

    @TearDown
    public void shutdown() {
    }

    private Person createAPerson(String name,List<Person> friends) {
        Person newPerson=new Person();
        newPerson.setName(name);
        newPerson.setFullName(new FullName("zjj_first", "zjj_middle", "zjj_last"));
        newPerson.setAge(24);
        List<String> hobbies=new ArrayList<String>();
        hobbies.add("篮球");
        hobbies.add("游泳");
        hobbies.add("coding");
        newPerson.setHobbies(hobbies);
        Map<String,String> clothes=new HashMap<String, String>();
        clothes.put("coat", "Nike");
        clothes.put("trousers", "adidas");
        clothes.put("shoes", "安踏");
        newPerson.setClothes(clothes);
        newPerson.setFriends(friends);
        return newPerson;
    }
}

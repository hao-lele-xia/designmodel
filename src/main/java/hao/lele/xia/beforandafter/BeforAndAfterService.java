package hao.lele.xia.beforandafter;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author chenhao
 * @description <p>
 * created by chenhao 2019/12/24 10:07
 */
@Component
public class BeforAndAfterService implements ApplicationRunner {

    @PostConstruct
    public void before(){
        System.err.println("before");
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.err.println("after");
    }
}

package hao.lele.xia.json;

import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author chenhao
 * @description <p>
 * created by chenhao 2019/12/5 11:02
 */
@Data
public class Person {
    private String name;
    private FullName fullName;
    private int age;
    private Date birthday;
    private List<String> hobbies;
    private Map<String, String> clothes;
    private List<Person> friends;

}

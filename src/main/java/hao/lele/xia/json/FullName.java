package hao.lele.xia.json;

import lombok.Data;

/**
 * @author chenhao
 * @description <p>
 * created by chenhao 2019/12/5 11:05
 */
@Data
public class FullName {
    private String firstName;
    private String middleName;
    private String lastName;

    public FullName() {
    }

    public FullName(String firstName, String middleName, String lastName) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    // 省略getter和setter

    @Override
    public String toString() {
        return "[firstName=" + firstName + ", middleName="
                + middleName + ", lastName=" + lastName + "]";
    }
}

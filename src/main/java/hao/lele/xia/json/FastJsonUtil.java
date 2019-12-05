package hao.lele.xia.json;

import com.alibaba.fastjson.JSON;

/**
 * @author chenhao
 * @description <p>
 * created by chenhao 2019/12/5 11:00
 */
public class FastJsonUtil {
    public static String bean2Json(Object obj) {
        return JSON.toJSONString(obj);
    }

    public static <T> T json2Bean(String jsonStr, Class<T> objClass) {
        return JSON.parseObject(jsonStr, objClass);
    }
}

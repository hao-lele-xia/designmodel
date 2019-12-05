package hao.lele.xia.json;

import net.sf.json.JSONObject;

/**
 * @author chenhao
 * @description <p>
 * created by chenhao 2019/12/5 11:02
 */
public class JsonLibUtil {
    public static String bean2Json(Object obj) {
        JSONObject jsonObject = JSONObject.fromObject(obj);
        return jsonObject.toString();
    }

    @SuppressWarnings("unchecked")
    public static <T> T json2Bean(String jsonStr, Class<T> objClass) {
        return (T) JSONObject.toBean(JSONObject.fromObject(jsonStr), objClass);
    }
}

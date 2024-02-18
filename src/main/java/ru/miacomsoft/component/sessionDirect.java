package ru.miacomsoft.component;

import ru.miacomsoft.WebServerLite.HttpExchange;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * запомнить страницу куда нужно будет вернуться
 *
 * JS клиент:
 *
 * function saveDirect(name) {
 *     if (typeof name === 'undefined') {
 *         name = 'local';
 *     }
 *     $.ajax({
 *         url: '/{component}/sessionDirect?set_direct='+name,
 *         method: 'POST',
 *         data: window.location.href,
 *         success: function(dataObj) {
 *
 *         }
 *     });
 * }
 *
 * function loadDirect(name) {
 *     if (typeof name === 'undefined') {
 *         name = 'local';
 *     }
 *     $.ajax({
 *         url: '/{component}/sessionDirect?get_direct='+name,
 *         method: 'POST',
 *         data: "{}",
 *         dataType: 'json',
 *         success: function(dataObj) {
 *             if ('redirect' in dataObj) {
 *                 window.location.href = dataObj['redirect'];
 *             }
 *         }
 *     });
 * }
 *
 */
public class sessionDirect {
    public static byte[] onPage(HttpExchange query) {
        query.mimeType = "application/javascript"; // Изменить mime ответа
        HashMap<String, Object> session = query.session;
        JSONObject queryProperty = query.requestParam;
        JSONObject result = new JSONObject();
        if (queryProperty.has("set_direct")) {
            session.put(queryProperty.getString("set_direct"), new String(query.postBody));
        } else if (queryProperty.has("get_direct")) {
            String key = queryProperty.getString("get_direct");
            if (session.containsKey(key)) {
                result.put("redirect", (String) session.get(key));
            }
        }
        return result.toString().getBytes();
    }

}

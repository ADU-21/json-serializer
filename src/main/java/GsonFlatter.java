import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.Gson;

/**
 * @author LukeDu
 * @date 2021/9/23
 */
public class GsonFlatter {
    Gson gson = new Gson();
    Map<String, String> flatmap = new HashMap<String, String>();

    public Map<String, String> parse(String value) {
        iterableCrawl("", null, (gson.fromJson(value, flatmap.getClass())).entrySet());
        return flatmap;
    }

    private <T> void iterableCrawl(String prefix, String suffix, Iterable<T> iterable) {
        int key = 0;
        for (T t : iterable) {
            if (suffix != null) { crawl(t, prefix + (key++) + suffix); } else {
                crawl(((Entry<String, Object>)t).getValue(), prefix + ((Entry<String, Object>)t).getKey());
            }
        }
    }

    private void crawl(Object object, String key) {
        if (object instanceof ArrayList) {
            iterableCrawl(key + "[", "]", (ArrayList<Object>)object);
        } else if (object instanceof Map) {
            iterableCrawl(key + ".", null, ((Map<String, Object>)object).entrySet());
        } else { flatmap.put(key, object.toString()); }
    }
}

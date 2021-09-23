import java.util.Map;

/**
 * @author LukeDu
 * @date 2021/9/23
 */
public class Main {
    public static void main(String[] args) {
        String json = "{\n" +
            "   \"Port\":\n" +
            "   {\n" +
            "       \"@alias\": \"defaultHttp\",\n" +
            "       \"Enabled\": \"true\",\n" +
            "       \"Number\": \"10092\",\n" +
            "       \"Protocol\": \"http\",\n" +
            "       \"KeepAliveTimeout\": \"20000\",\n" +
            "       \"ThreadPool\":\n" +
            "       {\n" +
            "           \"@enabled\": \"false\",\n" +
            "           \"Max\": \"150\",\n" +
            "           \"ThreadPriority\": \"5\"\n" +
            "       },\n" +
            "       \"ExtendedProperties\":\n" +
            "       {\n" +
            "           \"Property\":\n" +
            "           [                         \n" +
            "               {\n" +
            "                   \"@name\": \"connectionTimeout\",\n" +
            "                   \"$\": \"20000\"\n" +
            "               }\n" +
            "           ]\n" +
            "       }\n" +
            "   }\n" +
            "}";


        final Map<String, String> result1 = new JacksonFlatter().flat(json);
        System.out.println(result1);

        final Map<String, String> result2 = new GsonFlatter().parse(json);
        System.out.println(result2);
    }
}

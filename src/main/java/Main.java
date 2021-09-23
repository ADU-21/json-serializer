import java.util.Map;
import java.util.stream.IntStream;

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

        // warm up
        IntStream.range(0, 100000).forEach(__ -> new GsonFlatter().parse(json));

        // test
        final long startTime1 = System.currentTimeMillis();
        IntStream.range(0, 100000).forEach(__ -> new GsonFlatter().parse(json));
        final long endTime1 = System.currentTimeMillis();
        System.out.println("Gson Run time: " + (endTime1 - startTime1) + "ms");

        // warm up
        IntStream.range(0, 100000).forEach(__ -> new JacksonFlatter().flat(json));

        // test
        final long startTime = System.currentTimeMillis();
        IntStream.range(0, 100000).forEach(__ -> new JacksonFlatter().flat(json));
        final long endTime = System.currentTimeMillis();
        System.out.println("Jackson Run time: " + (endTime - startTime) + "ms");

    }
}

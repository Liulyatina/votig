package by.it_academy.jd2.votig.controller.http.utils;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Map;

public class ServletUtils {

    public static void writeBrakeLine(Writer writer) throws IOException {
        writer.write("****************</br>\n");
    }

    public static void writeTo(List<Map.Entry<String, Integer>> data, Writer writer) throws IOException {
        for (Map.Entry<String, Integer> line : data) {
            writer.write(line.getKey() + ": " + line.getValue() + "</br>\n");
        }
    }
}

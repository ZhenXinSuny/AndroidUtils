package com.lzx.utils;



import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class GsonUtil {

    /**
     * 获取Gson实例对象
     *
     * @return
     */
    public static Gson getGson() {
        return GsonHolder.INSTANCE;
    }

    /**
     * 静态内部类单例（线程安全的）
     */
    private static class GsonHolder {
        private static final Gson INSTANCE = new Gson();
    }

    /**
     * 转成json
     *
     * @param object
     * @return
     */
    public static String toJson(Object object) {
        return getGson().toJson(object);
    }

    /**
     * 转成bean
     *
     * @param jsonStr
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> T fromJson(String jsonStr, Class<T> cls) {
        return getGson().fromJson(jsonStr, cls);
    }

    public static <T> T fromJson(Reader jsonIn, Class<T> cls) {
        return getGson().fromJson(jsonIn, cls);
    }

    /**
     * 转成bean
     *
     * @param jsonStr
     * @return
     */
    public static <T> T fromJson(String jsonStr, Type typeOfT) {
        return getGson().fromJson(jsonStr, typeOfT);
    }

    /**
     * 转成list
     *
     * @param jsonStr
     * @return
     */
    public static <T> List<T> toList(String jsonStr) {
        return fromJson(jsonStr, new TypeToken<List<T>>() {
        }.getType());
    }

    /**
     * 转成map
     *
     * @param jsonStr
     * @return
     */
    public static <T> Map<String, T> toMap(String jsonStr) {
        return fromJson(jsonStr, new TypeToken<Map<String, T>>() {
        }.getType());
    }

    /**
     * Json转List集合,遇到解析不了的，就使用这个
     */
    public static <T> List<T> fromJsonList(String json, Class<T> cls) {
        List<T> mList = new ArrayList<T>();
        JsonArray array = new JsonParser().parse(json).getAsJsonArray();
        for (JsonElement elem : array) {
            mList.add(getGson().fromJson(elem, cls));
        }
        return mList;
    }

    /**
     * 将json中的null替换成""
     * <p>
     * 用法：
     * GsonBuilder gb = new GsonBuilder();
     * gb.registerTypeAdapter(String.class, new StringConverter());
     * Gson gson = gb.create();
     */
    public class StringConverter implements JsonSerializer<String>, JsonDeserializer<String> {
        @Override
        public JsonElement serialize(String src, Type typeOfSrc, JsonSerializationContext context) {
            if (src == null) {
                return new JsonPrimitive("");
            } else {
                return new JsonPrimitive(src);
            }
        }

        @Override
        public String deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            return json.getAsJsonPrimitive().getAsString();
        }
    }

    /**
     * 将json中的null替换成""
     * 自定义String适配器
     */
    class StringTypeAdapter extends TypeAdapter<String> {

        @Override
        public String read(JsonReader reader) throws IOException {
            if (reader.peek() == JsonToken.NULL) {
                reader.nextNull();
                return "";
            }
            return reader.nextString();
        }

        @Override
        public void write(JsonWriter writer, String value) throws IOException {
            if (value == null) {
                // 在这里处理null改为空字符串
                writer.value("");
                return;
            }
            writer.value(value);
        }
    }


}

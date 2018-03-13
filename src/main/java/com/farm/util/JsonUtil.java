package com.farm.util;

/**
 * Created by swipal on 2017/3/22.
 */

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.JsonNodeFactory;
import org.codehaus.jackson.node.ObjectNode;
import org.codehaus.jackson.type.JavaType;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public final class JsonUtil
{
    private static ObjectMapper mapper = null;

    public static String getJsonString(String name, Object object)
            throws IOException
    {
        ObjectNode rootNode = mapper.createObjectNode();
        rootNode.putPOJO(name, object);
        return mapper.writeValueAsString(rootNode);
    }

    public static String getJsonString(Object object)

    {
        try {
            return mapper.writeValueAsString(object);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void writeTo(String fieldName, Object object, OutputStream stream)
            throws IOException
    {
        ObjectNode rootNode = mapper.createObjectNode();
        rootNode.putPOJO(fieldName, object);
        mapper.writeValue(stream, rootNode);
    }

    public static String getCraneJsonString(String name, Object value)
            throws IOException
    {
        return getCraneJsonString(name, value, 5);
    }

    public static String getCraneJsonString(String name, Object value, int deep)
            throws IOException
    {
        ObjectNode rootNode = mapper.createObjectNode();
        if (value == null)
            rootNode.putPOJO(name, JsonNodeFactory.instance.nullNode());
        else {
            System.out.println("请联系后台人员添加Util");
            // rootNode.putPOJO(name, JsonGenerator.createJsonNode(value, deep, 1));
        }
        return mapper.writeValueAsString(rootNode);
    }

    public static String objectToJson(Object inputValue)
            throws IOException
    {
        return mapper.writeValueAsString(inputValue);
    }

    public static <T> T stringToObject(String inputValue, Class<T> cls)
            throws IOException
    {
        return mapper.readValue(inputValue, cls);
    }

    public static <T> List<T> stringToObjectl(String inputValue, Class<T> cls) throws IOException {
        JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, new Class[] { cls });
        return (List)mapper.readValue(inputValue, javaType);
    }

    public static JsonNode buildTree(InputStream in)
            throws IOException
    {
        JsonParser jp = mapper.getJsonFactory().createJsonParser(in);
        jp.disable(JsonParser.Feature.AUTO_CLOSE_SOURCE);
        return mapper.readTree(jp);
    }

    static
    {
        mapper = new ObjectMapper();

        mapper.getDeserializationConfig().set(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
}
package org.seckill;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhj on 2017-5-27.
 */
public class JSON {

    /**
     * 实体转json
     * @param object
     * @return
     * @throws JsonProcessingException
     */
    public  static String toJson(Object object)  {
        String json = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            json = mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }


    /**
     * json转为实体bean
     * @param json
     * @param classType
     * @return
     * @throws IOException
     */
    public static <T> T parseObject(String json,Class<T> classType)  {
        ObjectMapper mapper = new ObjectMapper();
        Object object = null;
        try {
            object = mapper.readValue(json, classType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (T)object;
    }


    /**
     * json转list数组
     * @param json
     * @param classType
     * @return
     * @throws IOException
     */
    public static <T> List<T> parseArrayList(String json, Class<T> classType) {
        ObjectMapper mapper = new ObjectMapper();
        CollectionType listType = mapper.getTypeFactory().constructCollectionType(ArrayList.class,classType);
        Object array = null;
        try {
            array = mapper.readValue(json, listType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (List<T>)array;
    }



}

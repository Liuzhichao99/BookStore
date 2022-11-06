package com.liu.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liu.pojo.vo.ResultVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.ResponseCache;

/**
 * 1.将java对象转换为json字符串
 * 2.将json字符串转换为java对象
 */
public class JSONUtils {

    /**
     * 1,将java对象转换为json字符串
     * 2,将json字符串作为响应正文返回给浏览器
     * @param response
     * @param t
     * @param <T>
     */
    public static<T> void javaBean2JsonStrAndResponse(HttpServletResponse response, T t) {
        try {
            // 2.处理相应
            // 2.1将java对象转换为json字符串
            String jsonStrVO = JSONUtils.JavaBean2JsonStr(t);
            // 2.2,将json字符串作为响应正文返回给浏览器
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(jsonStrVO);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 1.1,获取请求体中的json字符串
     * 1.2,将json字符串转换为java对象
     * @param request
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T request2JavaBean(HttpServletRequest request, Class<T> clazz) {
        // 1.1,获取请求体中的json字符串
        try {
            BufferedReader reader = request.getReader();
            StringBuilder requestBody = new StringBuilder();//记录请求体
            String content = null;
            while ((content = reader.readLine()) != null) {
                requestBody.append(content);
            }
            String jsonStrBO = requestBody.toString();//浏览器给服务器的json字符串
            // 1.2,将json字符串转换为java对象
            T obj = JSONUtils.jsonStr2JavaBean(jsonStrBO, clazz);
            return obj;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将java对象转换为json字符串
     * @param obj
     * @param <T>
     * @return
     */
    public static<T> String JavaBean2JsonStr(T obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将json字符串转换为java对象
     * @param jsonStr
     * @param clazz
     * @param <T>
     * @return
     */
    public static<T> T jsonStr2JavaBean(String jsonStr, Class<T> clazz) {
        try {
            return new ObjectMapper().readValue(jsonStr, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

package cn.zephyr.utils;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zephyrLai
 * @Description: TODO
 * @Date: 2019/4/11 13:39
 */
public class HttpClientUtils {
    public static final String  SUNX509 = "SunX509";
    public static final String  JKS     = "JKS";
    public static final String  PKCS12  = "PKCS12";
    public static final String  TLS     = "TLS";

    private static final Logger logger  = LoggerFactory.getLogger(HttpClientUtils.class);


    private static CloseableHttpClient getHttpClient() {
        RequestConfig defaultRequestConfig = RequestConfig.custom().setSocketTimeout(20000).setConnectTimeout(20000)
                .setConnectionRequestTimeout(20000).build();
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient httpClient = httpClientBuilder.setDefaultRequestConfig(defaultRequestConfig).build();
        return httpClient;
    }

    public static String getResponseResultByGet(String url) throws Exception {
        String result = null;
        Map<String, String> headerMap = new HashMap<String, String>(2);
        headerMap.put("Content-Type", "text/html;charset=UTF-8");
        result = getResponseResultByGet(url, "UTF-8", headerMap);
        return result;
    }

    @SuppressWarnings("rawtypes")
    public static String getResponseResultByGet(String url, String charset, Map<String, String> headerMap)
            throws Exception {
        String result = null;
        CloseableHttpClient httpClient = getHttpClient();
        try {
            HttpGet httpGet = new HttpGet(url);
            for (Map.Entry header : headerMap.entrySet()) {
                httpGet.setHeader((String) header.getKey(), (String) header.getValue());
            }
            HttpResponse httpResponse = httpClient.execute(httpGet);
            logger.info("网络请求状态："+httpResponse.getStatusLine().getStatusCode());
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                HttpEntity httpEntity = httpResponse.getEntity();
                if (httpEntity != null) {
                    result = new String(EntityUtils.toByteArray(httpEntity), charset);
                }
            }
        } catch (Exception e) {
            logger.error(" HttpClientUtil getResponseResultByGet information error : " + e.fillInStackTrace()
                    + " ; param : url:" + url + " headerMap : " + JSON.toJSONString(headerMap) + " charset : "
                    + charset, e);
            throw new Exception(e);
        } finally {
            httpClient.close();
        }
        return result;
    }


    public static String genGetParams(Object o) throws Exception{
        StringBuffer strBuffer = new StringBuffer("?");
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(o.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                if (!"class".equals(key)) {
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(o);
                    if (null != value && !StringUtils.isEmpty(String.valueOf(value))) {
                        strBuffer.append(key).append("=").append(value).append("&");
                    }
                }
            }
        }catch(Exception e){
            throw e;
        }
        return strBuffer.substring(0,strBuffer.length()-1).toString() ;
    }

}

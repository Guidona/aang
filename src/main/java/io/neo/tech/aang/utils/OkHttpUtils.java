package io.neo.tech.aang.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import okhttp3.Request.Builder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class OkHttpUtils {

    private static String JSON_MEDIA_TYPE = "application/json; charset=utf-8";

    private String url;

    private Map<String, Object> data;

    private String auth;

    private String token;

    private MediaType mediaType = MediaType.APPLICATION_JSON;

    private HttpMethod httpMethod = HttpMethod.POST;

    private Map<String, Object> params;

    private OkHttpUtils() {
    }

    public OkHttpUtils setUrl(String url) {
        this.url = url;
        return this;
    }

    public OkHttpUtils setData(Map<String, Object> data) {
        this.data = data;
        return this;
    }

    public OkHttpUtils setAuth(String auth) {
        this.auth = auth;
        return this;
    }

    public OkHttpUtils setParams(Map<String, Object> params) {
        this.params = params;
        return this;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public OkHttpUtils setMediaType(MediaType mediaType) {
        this.mediaType = mediaType;
        return this;
    }

    public OkHttpUtils setHttpMethod(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
        return this;
    }

    public static Response postAsJSON(final Consumer<OkHttpUtils> block) throws IOException {

        final OkHttpUtils instance = new OkHttpUtils();

        block.accept(instance);

        OkHttpClient client = okHttpSup.get();

        instance.setMediaType(MediaType.APPLICATION_JSON);

        Request request = requestBuilderCon.apply(instance);

        return client.newCall(request).execute();

    }

    public static Response postAsForm(Consumer<OkHttpUtils> block) throws IOException {

        final OkHttpUtils instance = new OkHttpUtils();

        block.accept(instance);

        OkHttpClient client = okHttpSup.get();

        instance.setMediaType(MediaType.APPLICATION_FORM);

        instance.setHttpMethod(HttpMethod.POST);

        Request request = requestBuilderCon.apply(instance);

        return client.newCall(request).execute();

    }

    public static Response doGet(Consumer<OkHttpUtils> block) throws IOException {

        final OkHttpUtils instance = new OkHttpUtils();

        block.accept(instance);

        OkHttpClient client = okHttpSup.get();

        instance.setHttpMethod(HttpMethod.GET);

        Request request = requestBuilderCon.apply(instance);

        return client.newCall(request).execute();

    }

    public static OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder okBuilder = new OkHttpClient.Builder();

        okBuilder.connectTimeout(5, TimeUnit.MINUTES)
                .writeTimeout(5, TimeUnit.MINUTES) // write timeout
                .readTimeout(5, TimeUnit.MINUTES); // read timeout

        return okBuilder.build();
    }

    public static Request postRequestBuilder(OkHttpUtils instance) throws JsonProcessingException {

        UriComponents uri = UriComponentsBuilder.fromHttpUrl(instance.url).build();

        if (instance.mediaType.equals(MediaType.APPLICATION_FORM)) {

            final FormBody.Builder formBodyBuilder = new FormBody.Builder();

            instance.data.keySet()
                    .stream()
                    .forEach(key -> formBodyBuilder.add(key, (String) instance.data.get(key)));

            FormBody body = formBodyBuilder.build();

            Builder builder = new Request.Builder();

            if (!emptyStr.test(instance.auth)) {
                builder.addHeader("Authorization", instance.auth);
            }

            builder.url(uri.toUriString());

            if (instance.httpMethod.equals(HttpMethod.GET)) {
                builder.get();
            } else if (instance.httpMethod.equals(HttpMethod.POST)) {
                builder.post(body);
            } else if (instance.httpMethod.equals(HttpMethod.PUT)) {
                builder.put(body);
            } else if (instance.httpMethod.equals(HttpMethod.DELETE)) {
                builder.delete(body);
            }

            return builder.build();

        }

        ObjectMapper objectMapper = new ObjectMapper();

        okhttp3.MediaType JSON = okhttp3.MediaType.parse(JSON_MEDIA_TYPE);

        RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(instance.data));

        Builder builder = new Request.Builder();

        if (!emptyStr.test(instance.auth)) {
            builder.addHeader("Authorization", instance.auth);
        }

        if (!emptyStr.test(instance.token)) {
            builder.addHeader("Token", instance.token);
        }

        builder.addHeader("content-type", "application/json");

        builder.url(uri.toUriString());

        if (instance.httpMethod.equals(HttpMethod.GET)) {
            builder.get();
        } else if (instance.httpMethod.equals(HttpMethod.POST)) {
            builder.post(body);
        } else if (instance.httpMethod.equals(HttpMethod.PUT)) {
            builder.put(body);
        } else if (instance.httpMethod.equals(HttpMethod.DELETE)) {
            builder.delete(body);
        }

        return builder.build();

    }

    private static final Predicate<String> emptyStr = s -> s == null || s.trim().isEmpty();

    private static final Supplier<OkHttpClient> okHttpSup = OkHttpUtils::getOkHttpClient;

    private static final RequestBuilder<OkHttpUtils, Request> requestBuilderCon = OkHttpUtils::postRequestBuilder;

    @FunctionalInterface
    public interface RequestBuilder<T, U> {
        public U apply(T t) throws JsonProcessingException;
    }

    public enum MediaType {
        APPLICATION_JSON,
        APPLICATION_FORM
    }

    public enum HttpMethod {
        GET,
        POST,
        PUT,
        DELETE
    }
}

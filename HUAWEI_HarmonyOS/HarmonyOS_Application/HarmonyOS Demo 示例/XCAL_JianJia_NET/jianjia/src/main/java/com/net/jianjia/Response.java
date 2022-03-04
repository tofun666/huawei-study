package com.net.jianjia;

import okhttp3.Headers;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.ResponseBody;

/**
 * The type Response.
 *
 * @param <T> the type parameter
 * @modify&fix 田梓萱
 * @date 2022/2/17
 */
public class Response<T> {

    /**
     * Success response.
     *
     * @param <T>  the type parameter
     * @param body the body
     * @return the response
     */
    public static <T> Response<T> success(T body) {
        return success(body, new okhttp3.Response.Builder() //
                .code(200)
                .message("OK")
                .protocol(Protocol.HTTP_1_1)
                .request(new Request.Builder().url("http://localhost/").build())
                .build());
    }

    /**
     * Success response.
     *
     * @param <T>         the type parameter
     * @param body        the body
     * @param rawResponse the raw response
     * @return the response
     */
    public static <T> Response<T> success(T body, okhttp3.Response rawResponse) {
        Utils.checkNotNull(rawResponse, "rawResponse == null");
        if (!rawResponse.isSuccessful()) {
            throw new IllegalArgumentException("rawResponse must be successful response");
        }
        return new Response<>(rawResponse, body, null);
    }

    /**
     * Success response.
     *
     * @param <T>     the type parameter
     * @param body    the body
     * @param headers the headers
     * @return the response
     */
    public static <T> Response<T> success(T body, Headers headers) {
        Utils.checkNotNull(headers, "headers == null");
        return success(body, new okhttp3.Response.Builder() //
                .code(200)
                .message("OK")
                .protocol(Protocol.HTTP_1_1)
                .headers(headers)
                .request(new Request.Builder().url("http://localhost/").build())
                .build());
    }

    /**
     * Error response.
     *
     * @param <T>  the type parameter
     * @param code the code
     * @param body the body
     * @return the response
     */
    public static <T> Response<T> error(int code, ResponseBody body) {
        if (code < 400) throw new IllegalArgumentException("code < 400: " + code);
        return error(body, new okhttp3.Response.Builder() //
                .code(code)
                .message("Response.error()")
                .protocol(Protocol.HTTP_1_1)
                .request(new Request.Builder().url("http://localhost/").build())
                .build());
    }

    /**
     * Error response.
     *
     * @param <T>         the type parameter
     * @param body        the body
     * @param rawResponse the raw response
     * @return the response
     */
    public static <T> Response<T> error(ResponseBody body, okhttp3.Response rawResponse) {
        Utils.checkNotNull(body, "body == null");
        Utils.checkNotNull(rawResponse, "rawResponse == null");
        if (rawResponse.isSuccessful()) {
            throw new IllegalArgumentException("rawResponse should not be successful response");
        }
        return new Response<>(rawResponse, null, body);
    }

    private final okhttp3.Response rawResponse;
    private final T body;
    private final ResponseBody errorBody;

    private Response(okhttp3.Response rawResponse, T body, ResponseBody errorBody) {
        this.rawResponse = rawResponse;
        this.body = body;
        this.errorBody = errorBody;
    }

    /**
     * Raw okhttp 3 . response.
     *
     * @return the okhttp 3 . response
     */
    public okhttp3.Response raw() {
        return rawResponse;
    }

    /**
     * Code int.
     *
     * @return the int
     */
    public int code() {
        return rawResponse.code();
    }

    /**
     * Is successful boolean.
     *
     * @return the boolean
     */
    public boolean isSuccessful() {
        return rawResponse.isSuccessful();
    }

    /**
     * Message string.
     *
     * @return the string
     */
    public String message() {
        return rawResponse.message();
    }

    /**
     * Header string.
     *
     * @param name the name
     * @return the string
     */
    public String header(String name) {
        return rawResponse.header(name);
    }

    /**
     * Header string.
     *
     * @param name         the name
     * @param defaultValue the default value
     * @return the string
     */
    public String header(String name, String defaultValue) {
        return rawResponse.header(name, defaultValue);
    }

    /**
     * Body t.
     *
     * @return the t
     */
    public T body() {
        return body;
    }

    /**
     * Error body response body.
     *
     * @return the response body
     */
    public ResponseBody errorBody() {
        return errorBody;
    }

    @Override
    public String toString() {
        return rawResponse.toString();
    }
}

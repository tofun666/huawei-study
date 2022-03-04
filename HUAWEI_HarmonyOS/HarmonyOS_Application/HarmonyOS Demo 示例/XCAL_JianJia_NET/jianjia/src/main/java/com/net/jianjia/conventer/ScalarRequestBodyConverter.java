package com.net.jianjia.conventer;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import com.net.jianjia.Converter;

import java.io.IOException;

/**
 * The type Scalar request body converter.
 *
 * @param <T> the type parameter
 */
final class ScalarRequestBodyConverter<T> implements Converter<T, RequestBody> {

    /**
     * The Instance.
     */
    static final ScalarRequestBodyConverter<Object> INSTANCE = new ScalarRequestBodyConverter<>();
    private static final MediaType MEDIA_TYPE = MediaType.get("text/plain; charset=UTF-8");

    private ScalarRequestBodyConverter() {
    }

    @Override
    public RequestBody convert(T value) throws IOException {
        return RequestBody.create(MEDIA_TYPE, String.valueOf(value));
    }
}
package com.net.jianjia.gson;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import okhttp3.ResponseBody;
import com.net.jianjia.Converter;

import java.io.IOException;

/**
 * The type Gson response body converter.
 *
 * @param <T> the type parameter
 * @author 裴云飞
 * @date 2021 /1/26
 */
final class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    private final Gson gson;
    private final TypeAdapter<T> adapter;

    /**
     * Instantiates a new Gson response body converter.
     *
     * @param gson    the gson
     * @param adapter the adapter
     */
    GsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        JsonReader jsonReader = gson.newJsonReader(value.charStream());
        try {
            T result = adapter.read(jsonReader);
            if (jsonReader.peek() != JsonToken.END_DOCUMENT) {
                throw new JsonIOException("JSON document was not fully consumed.");
            }
            return result;
        } finally {
            value.close();
        }
    }
}

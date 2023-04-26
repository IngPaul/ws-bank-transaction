package com.pichincha.core.mapper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Component;
import reactor.util.annotation.Nullable;

@Component
public class MyMapper {
    public <T> T parse(@Nullable Object object, Class<T> className) {
        if (object == null) {
            return null;
        }
        Gson gson = new GsonBuilder().serializeNulls().create();
        String json = gson.toJson(object);
        T parsedObject = gson.fromJson(json, className);
        return parsedObject;
    }
}

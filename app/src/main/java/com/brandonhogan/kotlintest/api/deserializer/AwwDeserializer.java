package com.brandonhogan.kotlintest.api.deserializer;

import android.util.Log;

import com.brandonhogan.kotlintest.api.RedditAwwDataResponse;
import com.brandonhogan.kotlintest.api.Resolutions;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Brandon on 2/11/2017.
 * Description :
 */

public class AwwDeserializer  implements JsonDeserializer<RedditAwwDataResponse>
{
    @Override
    public RedditAwwDataResponse deserialize(JsonElement je, Type type, JsonDeserializationContext jdc)
            throws JsonParseException {

        String author = GetString(je, "author");
        String title = GetString(je, "title");
        Long created = GetLong(je, "created_utc");
        String url = GetString(je, "url");
        List<Resolutions> resolutions = new ArrayList<>();
        List<Resolutions> gifs = new ArrayList<>();

        JsonElement preivew = je.getAsJsonObject().get("preview");
        if (preivew != null) {
            JsonElement images = preivew.getAsJsonObject().get("images");

            JsonElement res = images.getAsJsonArray().get(0).getAsJsonObject().get("resolutions");
            if (res != null)
            resolutions = new Gson().fromJson(res, new TypeToken<List<Resolutions>>(){}.getType());

            JsonElement variants = images.getAsJsonArray().get(0).getAsJsonObject().get("variants");
            if (variants != null && !variants.toString().equals("{}")) {
                JsonElement item = variants.getAsJsonObject().get("gif");

                if (item != null) {
                    gifs = new Gson().fromJson(item.getAsJsonObject().get("resolutions"), new TypeToken<List<Resolutions>>(){}.getType());
                }
            }
        }

        return new RedditAwwDataResponse(author,title,created,url, resolutions, gifs);

        // Deserialize it. You use a new instance of Gson to avoid infinite recursion
        // to this deserializer
       // return new Gson().fromJson(aww, RedditAwwDataResponse.class);

    }

    private String GetString(JsonElement je, String value) {
        return je.getAsJsonObject().get(value).getAsString();
    }

    private Long GetLong(JsonElement je, String value) {
        return je.getAsJsonObject().get(value).getAsLong();
    }
}
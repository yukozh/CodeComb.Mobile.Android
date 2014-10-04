package com.codecomb.utils;

import java.lang.reflect.Type;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class GsonUtils {

	public static Gson createGson() {
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(Date.class, new DotNetDateDeserializer());
		builder.registerTypeAdapter(Date.class, new DotNetDateSerializer());
		return builder.create();
	}


	public static class DotNetDateDeserializer implements
			JsonDeserializer<Date> {
		@Override
		public Date deserialize(JsonElement json, Type typfOfT,
				JsonDeserializationContext context) {
			try {
				String dateStr = json.getAsString();
				if (dateStr != null)
					dateStr = dateStr.replace("/Date(", "");
				if (dateStr != null)
					dateStr = dateStr.replace(")/", "");
				if (dateStr != null)
					dateStr = dateStr.replace(")/", "");
				long time = Long.parseLong(dateStr);
				return new Date(time);
			} catch (Exception ex) {
				ex.printStackTrace();
				return null;
			}

		}
	}

	public static class DotNetDateSerializer implements JsonSerializer<Date> {
		@Override
		public JsonElement serialize(Date date, Type typfOfT,
				JsonSerializationContext context) {
			if (date == null)
				return null;

			String dateStr = "/Date(" + date.getTime() + ")/";
			return new JsonPrimitive(dateStr);
		}
	}
}

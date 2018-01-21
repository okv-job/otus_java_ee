package ru.korytnikov.oleg.models;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class UserDeserializer implements JsonDeserializer<Users> {
    @Override
    public Users deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject object = jsonElement.getAsJsonObject();
        List<User> list = new ArrayList<>();
        object.get("users").getAsJsonObject().get("user").getAsJsonArray().forEach(
                jsonElement1 -> {
                    User user = new User();
                    user.setFirstName(jsonElement1.getAsJsonObject().get("firstName").getAsString());
                    user.setSecondName(jsonElement1.getAsJsonObject().get("secondName").getAsString());
                    user.setAddress(jsonElement1.getAsJsonObject().get("address").getAsString());
                    user.setSallary(jsonElement1.getAsJsonObject().get("sallary").getAsInt());
                    user.setId(jsonElement1.getAsJsonObject().get("id").getAsInt());
                    list.add(user);
                }
        );
        Users users = new Users();
        users.setUsers(list);
        return users;
    }
}

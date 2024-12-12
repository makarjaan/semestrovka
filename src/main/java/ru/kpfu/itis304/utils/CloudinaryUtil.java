package ru.kpfu.itis304.utils;

import com.cloudinary.Cloudinary;

import java.util.HashMap;
import java.util.Map;

public class CloudinaryUtil {

    private static Cloudinary cloudinary;

    public static Cloudinary getInstance() {
        if (cloudinary == null) {
            Map<String, String> configMap = new HashMap<>();
            configMap.put("cloud_name", "dqm8yufmb");
            configMap.put("api_key", "416418918959841");
            configMap.put("api_secret", "O7XdLquglAyjvAZgTPjaEeUClFo");
            cloudinary = new Cloudinary(configMap);
        }
        return cloudinary;
    }
}
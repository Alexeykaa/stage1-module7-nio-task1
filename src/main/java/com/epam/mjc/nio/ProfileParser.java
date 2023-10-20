package com.epam.mjc.nio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProfileParser {
    private static final Logger logger = LoggerFactory.getLogger(ProfileParser.class);

    private static final String KEY_VALUE_DELIMITER = ":";
    private Profile profile;

    public void init() {
        profile = new Profile();
    }

    public Profile getProfile() {
        return profile;
    }

    public void addData(String s) {
        String[] data = s.trim().split(KEY_VALUE_DELIMITER);
        if (data.length == 1) {
            // found only key, skip this case
            logger.warn("Found only key {} without value", s);
        } else if (data.length == 2) {
            // found key-value pair
            parse(data[0].trim(), data[1].trim());
        } else {
            logger.error("Invalid profile data format: {}", s);
        }
    }

    private void parse(String key, String value) {
        ProfileKey prop = ProfileKey.getValue(key);
        switch (prop) {
            case NAME:
                profile.setName(value);
                break;
            case AGE:
                setAge(value);
                break;
            case EMAIL:
                profile.setEmail(value);
                break;
            case PHONE:
                setPhone(value);
                break;
            case NONE:
                logger.error("Cannot find profile key for {}:{}", key, value);
                break;
            default:
                logger.error("Unsupported key {} for data {}:{}", prop, key, value);
                break;
        }
    }

    private void setPhone(String value) {
        try {
            long phone = Long.parseLong(value);
            profile.setPhone(phone);
        } catch (NumberFormatException e) {
            logger.error("Invalid Phone format: " + value, e);
        }
    }

    private void setAge(String value) {
        try {
            int age = Integer.parseInt(value);
            profile.setAge(age);
        } catch (NumberFormatException e) {
            logger.error("Invalid Age format: " + value, e);
        }
    }
}
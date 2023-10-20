package com.epam.mjc.nio;

public enum ProfileKey {
    NONE(""), NAME("Name"), AGE("Age"), EMAIL("Email"), PHONE("Phone");

    private final String key;

    ProfileKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public static ProfileKey getValue(String key) {
        ProfileKey result = NONE;
        for (ProfileKey value : ProfileKey.values()) {
            if (value.getKey().equalsIgnoreCase(key)) {
                result = value;
                break;
            }
        }
        return result;
    }
}

package com.example.mycinema.Enums;

public enum EnumAgeGroup {
    FIRST_AGE_GROUP(12),
    SECOND_AGE_GROUP(16),
    THIRD_AGE_GROUP(18);

    public final int age;

    public int getAge() {
        return age;
    }

    EnumAgeGroup(int age) {
        this.age = age;
    }
}

package co.com.nisum.util.enums;

import lombok.Getter;

public enum RegexEnum {

    EMAIL_REGEX("EMAIL_REGEX"),
    PASSWORD_REGEX("PASSWORD_REGEX");

    @Getter private String value;

    RegexEnum(String value) {
        this.value = value;
    }
}

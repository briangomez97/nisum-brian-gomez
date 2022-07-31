package co.com.nisum.util.validation;

import co.com.nisum.util.cache.RegularExpressionCache;
import co.com.nisum.util.enums.RegexEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

@Slf4j
public class CustomPasswordValidator implements ConstraintValidator<CustomPassword, String> {

    private String pattern;

    @Autowired
    private RegularExpressionCache regexCache;

    @Override
    public void initialize(CustomPassword contactInfo) {}

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        pattern = regexCache.getRegexFromCache(RegexEnum.PASSWORD_REGEX);
        if (!StringUtils.isEmpty(pattern)) {
            return Pattern.matches(pattern, value);
        }
        return false;
    }

}
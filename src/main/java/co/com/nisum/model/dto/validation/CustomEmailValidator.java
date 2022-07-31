package co.com.nisum.model.dto.validation;

import co.com.nisum.util.cache.RegularExpressionCache;
import co.com.nisum.util.enums.RegexEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

@Slf4j
public class CustomEmailValidator implements ConstraintValidator<CustomEmail, String> {

    private String pattern;

    @Autowired
    private RegularExpressionCache regexCache;

    @Override
    public void initialize(CustomEmail contactInfo) {}

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        pattern = regexCache.getRegexFromCache(RegexEnum.EMAIL_REGEX);
        if (!StringUtils.isEmpty(pattern)) {
            return Pattern.matches(pattern, value);
        }
        return false;
    }

}

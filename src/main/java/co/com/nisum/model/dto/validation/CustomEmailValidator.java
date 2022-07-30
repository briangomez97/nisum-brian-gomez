package co.com.nisum.model.dto.validation;

import co.com.nisum.service.RegularExpressionService;
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
    private RegularExpressionService regularExpressionService;

    @Override
    public void initialize(CustomEmail contactInfo) {
        pattern = regularExpressionService.getRegexByName(RegexEnum.EMAIL_REGEX).getRegularExpression();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (!StringUtils.isEmpty(pattern)) {
            return Pattern.matches(pattern, value);
        }
        return false;
    }

}

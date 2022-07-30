package co.com.nisum.service;

import co.com.nisum.model.dto.RegularExpressionDTO;
import co.com.nisum.model.entity.RegularExpression;
import co.com.nisum.util.enums.RegexEnum;

import java.util.List;

public interface RegularExpressionService {

    List<RegularExpressionDTO> getAllRegex();

    RegularExpressionDTO getRegexByName(RegexEnum regexEnum);

    RegularExpressionDTO updateRegexByName(RegexEnum regexEnum, RegularExpressionDTO newRegexDTO);

    RegularExpressionDTO saveRegex(RegularExpression regularExpression);

}

package co.com.nisum.service;

import co.com.nisum.model.dto.RegularExpressionDTO;
import co.com.nisum.model.entity.RegularExpression;

import java.util.List;

public interface RegularExpressionService {

    List<RegularExpressionDTO> getAllRegex();

    RegularExpressionDTO getRegexByName(String regexEnum);

    RegularExpressionDTO updateRegexByName(String regexEnum, RegularExpressionDTO newRegexDTO);

    RegularExpressionDTO saveRegex(RegularExpression regularExpression);

}

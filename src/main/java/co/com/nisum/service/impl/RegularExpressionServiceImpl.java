package co.com.nisum.service.impl;

import co.com.nisum.exception.RegularExpressionNotExistException;
import co.com.nisum.model.dto.RegularExpressionDTO;
import co.com.nisum.model.entity.RegularExpression;
import co.com.nisum.model.mapper.RegularExpressionMapper;
import co.com.nisum.repository.RegularExpressionRepository;
import co.com.nisum.service.RegularExpressionService;
import co.com.nisum.util.enums.RegexEnum;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RegularExpressionServiceImpl implements RegularExpressionService {

    private final RegularExpressionRepository regularExpressionRepository;
    private final RegularExpressionMapper regularExpressionMapper;

    @Override
    public List<RegularExpressionDTO> getAllRegex() {
        return regularExpressionMapper.regularExpressionsToRegularExpressionsDTO(regularExpressionRepository.findAll());
    }

    @Override
    public RegularExpressionDTO getRegexByName(String regexEnum) {
        Optional<RegularExpression> regexOptional = regularExpressionRepository.findRegularExpressionByName(regexEnum);
        if(!regexOptional.isPresent()) {
            throw new RegularExpressionNotExistException(String.format("There is no regular expression with '%s' name", regexEnum));
        }
        return regularExpressionMapper.regularExpressionToRegularExpressionDTO(regexOptional.get());
    }

    @Override
    @Transactional
    public RegularExpressionDTO updateRegexByName(String regexEnum, RegularExpressionDTO newRegexDTO) {
        RegularExpressionDTO regexDTO = getRegexByName(regexEnum);
        regexDTO.setLastUpdate(LocalDateTime.now());
        regexDTO.setRegularExpression(newRegexDTO.getRegularExpression());
        return saveRegex(regularExpressionMapper.regularExpressionDTOToRegularExpression(regexDTO));
    }

    @Override
    public RegularExpressionDTO saveRegex(RegularExpression regularExpression) {
        RegularExpression regexSaved = regularExpressionRepository.save(regularExpression);
        return regularExpressionMapper.regularExpressionToRegularExpressionDTO(regexSaved);
    }
}

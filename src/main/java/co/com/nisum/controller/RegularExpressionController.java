package co.com.nisum.controller;

import co.com.nisum.controller.response.SuccessResponse;
import co.com.nisum.model.dto.RegularExpressionDTO;
import co.com.nisum.service.RegularExpressionService;
import co.com.nisum.util.cache.RegularExpressionCache;
import co.com.nisum.util.enums.RegexEnum;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/regex")
@AllArgsConstructor
public class RegularExpressionController {

    private final RegularExpressionService regularExpressionService;
    private final RegularExpressionCache regexCache;

    @GetMapping
    public SuccessResponse<List<RegularExpressionDTO>> getAllRegex() {
        return new SuccessResponse<>(regularExpressionService.getAllRegex(), HttpStatus.OK.value());
    }

    @GetMapping("/{regexEnum}")
    public SuccessResponse<RegularExpressionDTO> getRegexByName(@PathVariable("regexEnum") RegexEnum regexEnum) {
        return new SuccessResponse<>(regularExpressionService.getRegexByName(regexEnum), HttpStatus.OK.value());
    }

    @PutMapping("/{regexEnum}")
    public SuccessResponse<RegularExpressionDTO> updateRegexByName(@PathVariable("regexEnum") RegexEnum regexEnum, @RequestBody RegularExpressionDTO regularExpressionDTO) {
        regexCache.clearCache();
        return new SuccessResponse<>(regularExpressionService.updateRegexByName(regexEnum, regularExpressionDTO), HttpStatus.OK.value());
    }

}

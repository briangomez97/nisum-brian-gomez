package co.com.nisum.util.cache;

import co.com.nisum.service.RegularExpressionService;
import co.com.nisum.util.enums.RegexEnum;
import lombok.AllArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RegularExpressionCache {

    public final static String CACHE_NAME = "regex";

    private final RegularExpressionService regularExpressionService;
    private final CacheManager cacheManager;

    @Cacheable(cacheNames = CACHE_NAME, key="#regexEnum.value")
    public String getRegexFromCache(RegexEnum regexEnum) {
        return regularExpressionService.getRegexByName(regexEnum.getValue()).getRegularExpression();
    }

    public void clearCache() {
        cacheManager.getCacheNames().parallelStream().forEach(name -> cacheManager.getCache(name).clear());
    }

}

package co.com.nisum.model.mapper;

import co.com.nisum.model.dto.RegularExpressionDTO;
import co.com.nisum.model.entity.RegularExpression;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RegularExpressionMapper {

    RegularExpressionDTO regularExpressionToRegularExpressionDTO(RegularExpression regularExpression);

    RegularExpression regularExpressionDTOToRegularExpression(RegularExpressionDTO regularExpressionDTO);
}

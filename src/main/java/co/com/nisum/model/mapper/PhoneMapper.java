package co.com.nisum.model.mapper;

import co.com.nisum.model.dto.PhoneDTO;
import co.com.nisum.model.entity.Phone;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PhoneMapper {

    PhoneDTO phoneToPhoneDTO(Phone phone);

    Phone phoneDTOToPhone(PhoneDTO phoneDTO);
}

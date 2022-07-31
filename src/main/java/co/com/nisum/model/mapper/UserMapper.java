package co.com.nisum.model.mapper;

import co.com.nisum.model.dto.UserDTO;
import co.com.nisum.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = { PhoneMapper.class })
public interface UserMapper {

    UserDTO userToUserDTO(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "modified", ignore = true)
    @Mapping(target = "lastLogin", ignore = true)
    @Mapping(target = "token", ignore = true)
    User userDTOToUser(UserDTO userDTO);

    List<UserDTO> usersToUsersDTO(List<User> users);
}

package co.com.nisum.model.mapper;

import co.com.nisum.model.dto.UserDTO;
import co.com.nisum.model.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = { PhoneMapper.class })
public interface UserMapper {

    UserDTO userToUserDTO(User user);

    User userDTOToUser(UserDTO userDTO);

    List<UserDTO> usersToUsersDTO(List<User> users);
}

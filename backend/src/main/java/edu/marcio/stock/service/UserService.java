package edu.marcio.stock.service;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import edu.marcio.stock.dto.user.UserRequest;
import edu.marcio.stock.entity.Sector;
import edu.marcio.stock.entity.UserEntity;
import edu.marcio.stock.enums.UserRoles;
import edu.marcio.stock.exceptions.ResourceNotFoundException;
import edu.marcio.stock.exceptions.UserAlreadyExistsException;
import edu.marcio.stock.repository.SectorRepository;
import edu.marcio.stock.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final SectorRepository sectorRepository;

    private final PasswordEncoder passwordEncoder;

    public UserEntity createUser(UserRequest userRequest) {
        Optional<UserEntity> optionalUser = userRepository.findByEmail(userRequest.getEmail());

        if (optionalUser.isPresent()) {
            throw new UserAlreadyExistsException("user already registered");
        }

        Optional<Sector> optionalSector = sectorRepository.findById(userRequest.getSector());

        if (optionalSector.isEmpty()) {
            throw new ResourceNotFoundException("the provided sector does not exists!");
        }

        HashMap<String, UserRoles> roleMap = new HashMap<>();
        roleMap.put("MANAGER", UserRoles.MANAGER);
        roleMap.put("DOC_WORKER", UserRoles.DOC_WORKER);
        roleMap.put("LEADER", UserRoles.LEADER);

        UserRoles role = roleMap.get(userRequest.getRole());

        UserEntity userEntity = new UserEntity();
        userEntity.setName(userRequest.getName());
        userEntity.setEmail(userRequest.getEmail());
        userEntity.setRole(role);
        userEntity.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        userEntity.setSector(optionalSector.get());

        Optional<UserEntity> optionalSavedUser = Optional.ofNullable(userRepository.save(userEntity));

        if (optionalSavedUser.isEmpty()) {
            throw new RuntimeException("fail in save user");
        }

        return optionalSavedUser.get();
    }
}

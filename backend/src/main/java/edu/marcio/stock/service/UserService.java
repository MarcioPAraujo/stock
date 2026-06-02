package edu.marcio.stock.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import edu.marcio.stock.dto.user.UserRequest;
import edu.marcio.stock.entity.Sector;
import edu.marcio.stock.entity.UserEntity;
import edu.marcio.stock.exceptions.ResourceNotFoundException;
import edu.marcio.stock.exceptions.UserAlreadyExistsException;
import edu.marcio.stock.repository.SectorRepository;
import edu.marcio.stock.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final SectorRepository sectorRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public UserEntity createUser(UserRequest userRequest) {
        Optional<UserEntity> optionalUser = userRepository.findByEmail(userRequest.getEmail());

        if (optionalUser.isPresent()) {
            throw new UserAlreadyExistsException("user already registered");
        }

        Optional<Sector> optionalSector = sectorRepository.findById(userRequest.getSector());

        if (optionalSector.isEmpty()) {
            throw new ResourceNotFoundException("the provided sector does not exists!");
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setName(userRequest.getName());
        userEntity.setEmail(userRequest.getEmail());
        userEntity.setRole(userRequest.getRole());
        userEntity.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        userEntity.setSector(optionalSector.get());

        Optional<UserEntity> optionalSavedUser = Optional.ofNullable(userRepository.save(userEntity));

        if (optionalSavedUser.isEmpty()) {
            throw new RuntimeException("fail in save user");
        }

        return optionalSavedUser.get();
    }
}

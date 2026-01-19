package com.example.user_profile.service;

import com.example.user_profile.entity.UserProfile;
import com.example.user_profile.repository.UserProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserProfileService {

    private final UserProfileRepository repository;

    public UserProfileService(UserProfileRepository repository) {
        this.repository = repository;
    }

    public List<UserProfile> getAllUsers() {
        return repository.findAll();
    }

    public Optional<UserProfile> getUserById(UUID id) {
        return repository.findById(id);
    }

    public UserProfile createUser(UserProfile userProfile) {
        return repository.save(userProfile);
    }

    public Optional<UserProfile> updateUser(UUID id, UserProfile updatedProfile) {
        return repository.findById(id).map(existingUser -> {
            existingUser.setFullName(updatedProfile.getFullName());
            existingUser.setPhoneNumber(updatedProfile.getPhoneNumber());
            existingUser.setEmailId(updatedProfile.getEmailId());
            existingUser.setUsername(updatedProfile.getUsername());
            existingUser.setDob(updatedProfile.getDob());
            existingUser.setPassword(updatedProfile.getPassword());
            existingUser.setConfirmPassword(updatedProfile.getConfirmPassword());
            existingUser.setAddress(updatedProfile.getAddress());
            return repository.save(existingUser);
        });
    }

    public void deleteUser(UUID id) {
        repository.deleteById(id);
    }
}

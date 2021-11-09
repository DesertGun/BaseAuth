package de.joayahiatene.baseauth.domain.profile;

import de.joayahiatene.baseauth.domain.user.User;
import de.joayahiatene.baseauth.domain.user.UserRepository;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
class FileLocationServiceImpl implements FileLocationService {

    final FileSystemRepository fileSystemRepository;
    final ProfilePictureRepository profilePictureRepository;
    final UserRepository userRepository;

    public FileLocationServiceImpl(FileSystemRepository fileSystemRepository, ProfilePictureRepository profilePictureRepository, UserRepository userRepository) {
        this.fileSystemRepository = fileSystemRepository;
        this.profilePictureRepository = profilePictureRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void save(byte[] bytes, String imageName, String username) throws Exception {
        String location = fileSystemRepository.save(bytes, imageName);
        UUID profilePictureID = profilePictureRepository.save(new ProfilePicture(imageName, location)).getId();

        User user = userRepository.getById(username);
        user.setProfilePictureID(profilePictureID);
        userRepository.save(user);


    }

    @Override
    public FileSystemResource find(UUID pictureID) {
        ProfilePicture profilePicture = profilePictureRepository.findById(pictureID).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return fileSystemRepository.findInFileSystem(profilePicture.getLocation());
    }
}
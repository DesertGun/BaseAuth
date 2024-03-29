package de.joayahiatene.baseauth.domain.profile;

import org.springframework.core.io.FileSystemResource;

import java.util.UUID;

public interface FileLocationService {
    void save(byte[] bytes, String imageName, String username) throws Exception;
    FileSystemResource find(UUID pictureID);
}

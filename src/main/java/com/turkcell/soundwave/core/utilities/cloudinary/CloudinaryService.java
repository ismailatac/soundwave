package com.turkcell.soundwave.core.utilities.cloudinary;


import com.turkcell.soundwave.core.utilities.dtos.DeleteCloudinaryResponse;
import com.turkcell.soundwave.core.utilities.dtos.UploadCloudinaryResponse;
import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryService {
    UploadCloudinaryResponse upload(MultipartFile multipartFile);
    DeleteCloudinaryResponse delete(String publicIdOfSound);
}

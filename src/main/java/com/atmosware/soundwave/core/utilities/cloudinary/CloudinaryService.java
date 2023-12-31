package com.atmosware.soundwave.core.utilities.cloudinary;

import com.atmosware.soundwave.core.utilities.dtos.cloudinary.DeleteCloudinaryResponse;
import com.atmosware.soundwave.core.utilities.dtos.cloudinary.UploadCloudinaryResponse;
import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryService {
    UploadCloudinaryResponse upload(MultipartFile multipartFile);
    DeleteCloudinaryResponse delete(String publicIdOfSound);
}

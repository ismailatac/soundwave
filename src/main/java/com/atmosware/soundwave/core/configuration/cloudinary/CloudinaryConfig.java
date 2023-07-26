package com.atmosware.soundwave.core.configuration.cloudinary;


import com.atmosware.soundwave.core.utilities.cloudinary.CloudinaryManager;
import com.atmosware.soundwave.core.utilities.cloudinary.CloudinaryService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinaryAccount() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dp6gr7men",
                "api_key", "499975252657713",
                "api_secret", "H0c4Ac6puVrROFTTBcSLS3Wtpwo"
        ));
    }

    @Bean
    public CloudinaryService cloudinaryService() {
        return new CloudinaryManager(cloudinaryAccount());
    }
}

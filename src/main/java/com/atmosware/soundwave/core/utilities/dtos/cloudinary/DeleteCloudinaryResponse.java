package com.atmosware.soundwave.core.utilities.dtos.cloudinary;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteCloudinaryResponse {
    private boolean isSuccess;
    private Map<?,?> deleteResult;

}

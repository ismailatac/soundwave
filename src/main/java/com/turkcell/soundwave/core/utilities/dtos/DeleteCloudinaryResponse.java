package com.turkcell.soundwave.core.utilities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteCloudinaryResponse {
    private boolean isSuccess;
    private Map<?,?> deleteResult;

}

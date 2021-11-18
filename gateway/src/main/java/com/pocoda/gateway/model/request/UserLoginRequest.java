package com.pocoda.gateway.model.request;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import javax.validation.constraints.NotBlank;

@Value
@Builder
@RequiredArgsConstructor
public class UserLoginRequest {
    @NotBlank
    private String username;

    @NotBlank
    private String password;
}

package org.example.dockerexample.api.dto;

import lombok.Value;

@Value
public class CurrentUserDto {

    boolean testWithCurrentUser;
    UserDto currentUserFromAuth;

}

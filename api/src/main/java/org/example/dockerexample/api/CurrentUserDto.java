package org.example.dockerexample.api;

import lombok.Value;

@Value
public class CurrentUserDto {

    boolean testWithCurrentUser;
    UserDto currentUserFromAuth;

}

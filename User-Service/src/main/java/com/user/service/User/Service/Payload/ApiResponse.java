package com.user.service.User.Service.Payload;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ApiResponse {

    private String message;
    private boolean success;
    private HttpStatus status;

}

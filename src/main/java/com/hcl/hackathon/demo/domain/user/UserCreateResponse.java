package com.hcl.hackathon.demo.domain.user;

import com.hcl.hackathon.demo.domain.BaseResponse;

public class UserCreateResponse extends BaseResponse {

    public static UserCreateResponse of(String statusCode, String statusMessage, Object data) {
        UserCreateResponse response = new UserCreateResponse();
        response.setData(data);
        response.setStatusCode(statusCode);
        response.setStatusMessage(statusMessage);
        return response;
    }

}

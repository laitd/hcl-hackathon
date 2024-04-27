package com.hcl.hackathon.demo.domain.user;

import com.hcl.hackathon.demo.domain.BaseResponse;

public class GetUsersResponse extends BaseResponse {
    public static GetUsersResponse of(String statusCode, String statusMessage, Object data) {
        GetUsersResponse response = new GetUsersResponse();
        response.setData(data);
        response.setStatusCode(statusCode);
        response.setStatusMessage(statusMessage);
        return response;
    }
}

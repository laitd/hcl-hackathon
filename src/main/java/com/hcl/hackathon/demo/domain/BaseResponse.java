package com.hcl.hackathon.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class BaseResponse<T> {
    private String statusCode;

    private String statusMessage;

    private T data;
}

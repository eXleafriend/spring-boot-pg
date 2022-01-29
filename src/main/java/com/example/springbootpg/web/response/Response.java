package com.example.springbootpg.web.response;

public sealed class Response<T> permits DataResponse<T>, ErrorResponse<T> {

}

package com.bishal.exception;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;

@Singleton
public class UserNotFoundExceptionHandler implements ExceptionHandler<UserNotFoundException, HttpResponse> {
    @Override
    public HttpResponse handle(HttpRequest request, UserNotFoundException exception) {
        return HttpResponse.notFound("user not found ");
    }
}

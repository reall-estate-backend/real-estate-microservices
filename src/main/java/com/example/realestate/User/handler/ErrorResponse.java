package com.example.realestate.User.handler;

import java.util.Map;

public record ErrorResponse (
    Map<String, String> errors

) {

}

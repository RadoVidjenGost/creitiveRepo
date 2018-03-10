package com.vaivaidev.creitiveblog.creitiveblog.utils;


/**
 * Created by Iva on 3/10/2018.
 */

public class ErrorUtils {

    public static String serverErrorResponse(int errorCode) {
        StringBuilder errorMessage = new StringBuilder();

        switch (errorCode) {
            case 400:
                errorMessage.append("Bad request");
                break;
            case 401:
                errorMessage.append("Unauthorized");
                break;
            case 404:
                errorMessage.append("Not found");
                break;
            case 406:
                errorMessage.append("Not acceptable");
                break;
            case 415:
                errorMessage.append("Unsupported media type");
                break;
            case 503:
                errorMessage.append("Service unavailable");
                break;
        }

        return errorMessage.toString();

    }
}

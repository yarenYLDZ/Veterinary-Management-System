package dev.patika.veterinary.Management.System.core.result;


import lombok.Getter;

@Getter
public class Result {
    private boolean status;
    private String message;
    private String HttpCode;

    public Result(boolean status, String message, String httpCode) {
        this.status = status;
        this.message = message;
        HttpCode = httpCode;
    }


}

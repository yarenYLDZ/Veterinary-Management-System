package dev.patika.veterinary.Management.System.core.utilies;

import dev.patika.veterinary.Management.System.core.result.Result;
import dev.patika.veterinary.Management.System.core.result.ResultData;

public class ResultHelper {

    public static <T> ResultData<T> created(T data){
        return new ResultData<>(true, Messages.CREATED,"201",data);
    }

    public static <T> ResultData<T> validateError(T data){
        return new ResultData<>(false, Messages.VALIDATE_ERROR,"400",data);
    }

    // 🔹 Özel mesajlı doğrulama hatası (YENİ)
    public static <T> ResultData<T> validateError(String message, T data){
        return new ResultData<>(false, message,"400",data);
    }

    public static <T> ResultData<T> success(T data){
        return new ResultData<>(true, Messages.OK,"200",data);
    }

    public static Result notFoundError(String msg){
        return new Result(false, msg,"404");
    }

    public static Result missingParamError(String paramName){
        return new Result(false, Messages.MISSING_PARAM + ": " + paramName, "400");
    }
}

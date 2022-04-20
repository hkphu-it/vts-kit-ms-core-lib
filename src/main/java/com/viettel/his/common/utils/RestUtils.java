package com.viettel.his.common.utils;

import com.viettel.his.common.rest.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RestUtils {

    private RestUtils(){
        // Disable New Instance
    }

    public static ResponseEntity<Void> responseOk(){
        return responseOk(null);
    }

    public static <T> ResponseEntity<T> responseOk(T data){
        return ResponseEntity.ok(data);
    }

    public static <T> ResponseEntity<T> responseStatus(HttpStatus httpStatus, T data){
        return ResponseEntity.status(httpStatus).body(data);
    }

    public static ResponseEntity<ErrorDTO> responseServerError(){
        return responseStatus(HttpStatus.INTERNAL_SERVER_ERROR, new ErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error"));
    }

    public static void writeJsonResponse(HttpServletResponse response, Object responseBody) throws IOException {
        response.addHeader("Content-type", "application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(JsonUtils.cvtObjToJson(responseBody));
        response.getWriter().flush();
        response.getWriter().close();
    }

}
package com.rudykart.cigarrete.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class DataResponse<T> extends StatusResponse {
    private T payload;

    public DataResponse(int status, String message, T payload) {
        super(status, message);
        this.payload = payload;
    }
}

// import lombok.AllArgsConstructor;
// import lombok.Data;
// import lombok.NoArgsConstructor;

// @Data
// @NoArgsConstructor
// @AllArgsConstructor
// public class DataResponse<T> {
// private int status;
// private String message;
// private T payload;
// }

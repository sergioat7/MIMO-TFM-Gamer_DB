/*
 * Copyright (c) 2020 Sergio Aragonés. All rights reserved.
 * Created by Sergio Aragonés on 10/8/2020
 */

package es.upsa.mimo.gamerdb.models;

public class ErrorResponse {

    private String error;
    private int errorKey;
    private String internalMessage;

    public ErrorResponse(String error,
                         int errorKey,
                         String internalMessage) {
        this.error = error;
        this.errorKey = errorKey;
        this.internalMessage = internalMessage;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getErrorKey() {
        return errorKey;
    }

    public void setErrorKey(int errorKey) {
        this.errorKey = errorKey;
    }

    public String getInternalMessage() {
        return internalMessage;
    }

    public void setInternalMessage(String internalMessage) {
        this.internalMessage = internalMessage;
    }
}

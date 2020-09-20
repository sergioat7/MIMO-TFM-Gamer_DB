/*
 * Copyright (c) 2020 Sergio Aragonés. All rights reserved.
 * Created by Sergio Aragonés on 10/8/2020
 */

package es.upsa.mimo.gamerdb.models;

public class ErrorResponse {

    private int errorKey;
    private String internalMessage;

    public ErrorResponse(int errorKey,
                         String internalMessage) {
        this.errorKey = errorKey;
        this.internalMessage = internalMessage;
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

package com.flit;

import lombok.Getter;

import java.util.Map;

/**
 * Flit exception which can be thrown by implementations and translated to the Twirp error response.
 */
@Getter
public class FlitException extends RuntimeException {

    private ErrorCode errorCode;
    private Map<String, Object> meta;

    private FlitException(String message, ErrorCode errorCode, Map<String, Object> meta, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        this.meta = meta;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String message;
        private ErrorCode errorCode;
        private Map<String, Object> meta;
        private Throwable cause;

        public Builder withMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder withErrorCode(ErrorCode errorCode) {
            this.errorCode = errorCode;
            return this;
        }

        public Builder withMeta(Map<String, Object> meta) {
            this.meta = meta;
            return this;
        }

        public Builder withCause(Throwable cause) {
            this.cause = cause;
            return this;
        }

        public FlitException build() {
            return new FlitException(message, errorCode, meta, cause);
        }
    }
}
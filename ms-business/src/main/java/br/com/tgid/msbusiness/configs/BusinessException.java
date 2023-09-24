package br.com.tgid.msbusiness.configs;

public class BusinessException extends RuntimeException {

    private final int statusCode;

    public BusinessException(final int statusCode, final String message) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

}

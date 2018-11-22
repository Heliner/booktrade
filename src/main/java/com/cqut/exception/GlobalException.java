package com.cqut.exception;

public class GlobalException extends Exception {

    CodeMsg cm;

    public GlobalException(CodeMsg cm) {
        this.cm
                = cm;
    }

    public CodeMsg getCm() {
        return cm;
    }

    public void setCm(CodeMsg cm) {
        this.cm = cm;
    }
}

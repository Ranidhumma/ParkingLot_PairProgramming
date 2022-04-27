package com.bridgelabz.parkinglot;

public class SecurityPersonnel {
    private static String status;

    /**
     *
     * @return updated message
     */
    public String getStatus() {
        return this.status;
    }

    /**
     *
     * @param message updating message to Security Personnel
     */
    public void update(String message) {
        this.status = message;
    }
}

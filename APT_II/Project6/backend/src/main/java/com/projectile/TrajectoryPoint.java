package com.projectile;

public class TrajectoryPoint {
    private double time;
    private double height;
    private double horizontalPosition;
    private double horizontalVelocity;
    private double verticalVelocity;

    public TrajectoryPoint(double time, double height, double horizontalPosition, double horizontalVelocity, double verticalVelocity) {
        this.time = time;
        this.height = height;
        this.horizontalPosition = horizontalPosition;
        this.horizontalVelocity = horizontalVelocity;
        this.verticalVelocity = verticalVelocity;
    }

    public double getTime() {
        return time;
    }

    public double getHeight() {
        return height;
    }

    public double getHorizontalPosition() {
        return horizontalPosition;
    }

    public double getHorizontalVelocity() {
        return horizontalVelocity;
    }

    public double getVerticalVelocity() {
        return verticalVelocity;
    }
} 
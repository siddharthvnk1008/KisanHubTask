package com.siddharth.task.kissanhub.model;

public class Weather {

    private MinimumTemperature minimumTemperature;
    private MaxTemperature maxTemperature;
    private RainFall rainFall;

    public Weather(MinimumTemperature minimumTemperature, MaxTemperature maxTemperature, RainFall rainFall) {
        this.minimumTemperature = minimumTemperature;
        this.maxTemperature = maxTemperature;
        this.rainFall = rainFall;
    }

    public MinimumTemperature getMinimumTemperature() {
        return minimumTemperature;
    }

    public void setMinimumTemperature(MinimumTemperature minimumTemperature) {
        this.minimumTemperature = minimumTemperature;
    }

    public MaxTemperature getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(MaxTemperature maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public RainFall getRainFall() {
        return rainFall;
    }

    public void setRainFall(RainFall rainFall) {
        this.rainFall = rainFall;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "minimumTemperature=" + minimumTemperature +
                ", maxTemperature=" + maxTemperature +
                ", rainFall=" + rainFall +
                '}';
    }
}

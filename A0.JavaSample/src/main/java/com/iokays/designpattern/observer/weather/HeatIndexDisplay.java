package com.iokays.designpattern.observer.weather;

import io.swagger.annotations.*;

@ApiModel(value = "热指数布告板")
public class HeatIndexDisplay implements Observer, DisplayElement {

    @ApiModelProperty(value = "热指数")
    private float heatIndex = 0.0f;

    @ApiModelProperty(value = "气象数据")
    private final Subject weatherData;

    public HeatIndexDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        this.weatherData.registerObserver(this);
    }

    @Override
    public void display() {
        System.out.println("Heat index is " + heatIndex);
    }

    @Override
    public void update(float temp, float humidity, float pressure) {
        heatIndex = computeHeatIndex(temp, humidity);
        this.display();
    }

    @ApiOperation(value = "计算热指数", notes = "计算方式：https://www.wpc.ncep.noaa.gov/html/heatindex_equation.shtml")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "t", value = "温度", dataType = "float"),
            @ApiImplicitParam(name = "rh", value = "湿度", dataType = "float")
    })
    private float computeHeatIndex(float t, float rh) {
        return (float) ((16.923 + (0.185212 * t) + (5.37941 * rh) - (0.100254 * t * rh)
                + (0.00941695 * (t * t)) + (0.00728898 * (rh * rh))
                + (0.000345372 * (t * t * rh)) - (0.000814971 * (t * rh * rh)) +
                (0.0000102102 * (t * t * rh * rh)) - (0.000038646 * (t * t * t)) + (0.0000291583 *
                (rh * rh * rh)) + (0.00000142721 * (t * t * t * rh)) +
                (0.000000197483 * (t * rh * rh * rh)) - (0.0000000218429 * (t * t * t * rh * rh)) +
                0.000000000843296 * (t * t * rh * rh * rh)) -
                (0.0000000000481975 * (t * t * t * rh * rh * rh)));
    }
}

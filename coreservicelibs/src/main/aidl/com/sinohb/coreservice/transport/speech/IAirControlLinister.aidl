// ICanControlLinister.aidl
package com.sinohb.coreservice.transport.speech;

// Declare any non-default types here with import statements

interface IAirControlLinister {
    /**
    * 空调回调
    */
    void onAirSwitch(boolean open);
    void onAirBlowMode(int blowmode);
    void onAirAutoSwitch(boolean open);
    void onAirBlowDegree(int degree);
    void onAirBlowIncrease();
    void onAirBlowDecrease();
    void onAirBlowIncreaseDegree(int increase);
    void onAirBlowDecreaseDegree(int degrease);
    void onAirSetTemperature(int degree);
    void onAirTemperatureIncrease();
    void onAirTemperatureDecrease();
    void onAirTemperatureIncreaseDegree(int degree);
    void onAirTemperatureDecreaseDegree(int degree);
    void onAirTemperatureMax();
    void onAirTemperatureMin();
    void onAirInnerLooperSwitch(boolean open);
    void onAirOutterLooperSwitch(boolean open);
    void onAirACSwitch(boolean open);
}

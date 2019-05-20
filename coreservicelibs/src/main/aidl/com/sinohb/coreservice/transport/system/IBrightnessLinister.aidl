// IBrightnessLinister.aidl
package com.sinohb.coreservice.transport.system;

// Declare any non-default types here with import statements

interface IBrightnessLinister {

    void onBrightnessChange(int brightness,int type);
}

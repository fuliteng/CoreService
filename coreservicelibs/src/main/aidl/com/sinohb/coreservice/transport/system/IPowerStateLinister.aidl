// IPowerStateLinister.aidl
package com.sinohb.coreservice.transport.system;

// Declare any non-default types here with import statements

interface IPowerStateLinister {
    void onPowerChange(int power);
}

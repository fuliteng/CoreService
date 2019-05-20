// IVolumeLinister.aidl
package com.sinohb.coreservice.transport.system;

// Declare any non-default types here with import statements

interface IVolumeLinister {

    void onVolumeChange(int volumne,int type);
}

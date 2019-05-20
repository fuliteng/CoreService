// IBlueToothLinister.aidl
package com.sinohb.coreservice.transport.speech;

// Declare any non-default types here with import statements

interface IBlueToothLinister {
    void onBlueToothCall(String name,String number);
    void onBlueToothHangUp();
    void onBlueToothAnswerCall();
    void onBlueToothRejectCall();
    void onRequestBlueToothState();
    void onRequestBlueToothCntacts();
}

package coreservice.sinohb.com.hblib.interfaces.speech;

public interface BlueToothLinister {
    void onBlueToothCall(String name, String number);

    void onBlueToothHangUp();

    void onBlueToothAnswerCall();

    void onBlueToothRejectCall();

    void onRequestBlueToothState();

    void onRequestBlueToothCntacts();
}

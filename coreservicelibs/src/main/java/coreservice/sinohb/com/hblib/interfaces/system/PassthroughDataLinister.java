package coreservice.sinohb.com.hblib.interfaces.system;

public interface PassthroughDataLinister {
    void onPassthroughDataReceiver(int groupID, int commandID, byte[] data);
}

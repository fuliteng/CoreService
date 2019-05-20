package coreservice.sinohb.com.hblib.interfaces.system;

public interface RadioLinister {
    void onRadioReceive(int groupID, int commandID, byte[] data);
}

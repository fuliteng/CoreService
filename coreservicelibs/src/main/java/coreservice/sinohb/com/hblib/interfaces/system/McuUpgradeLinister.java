package coreservice.sinohb.com.hblib.interfaces.system;

public interface McuUpgradeLinister {
    void onResult(int code);
    void onProgress(int progress);
}

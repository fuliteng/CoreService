// IMcuUpgradeLinister.aidl
package com.sinohb.coreservice.transport.system;


interface IMcuUpgradeLinister {

    void onResult(int code);
    void onProgress(int progress);
}

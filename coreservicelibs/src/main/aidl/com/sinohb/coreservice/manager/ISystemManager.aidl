// ISystemManager.aidl
package com.sinohb.coreservice.manager;

import com.sinohb.coreservice.transport.system.IBrightnessLinister;
import com.sinohb.coreservice.transport.system.IBacklightSwitchLinister;
import com.sinohb.coreservice.transport.system.IKeyLinister;
import com.sinohb.coreservice.transport.system.IMcuUpgradeLinister;
import com.sinohb.coreservice.transport.system.IPassthroughDataLinister;
import com.sinohb.coreservice.transport.system.IPowerStateLinister;
import com.sinohb.coreservice.transport.system.IRadioLinister;
import com.sinohb.coreservice.transport.system.ISoundfieldLinister;
import com.sinohb.coreservice.transport.system.ISystemEQLinister;
import com.sinohb.coreservice.transport.system.ISystemMuteLinister;
import com.sinohb.coreservice.transport.system.IVolumeLinister;
import com.sinohb.coreservice.transport.system.IAuxStateLinister;
import com.sinohb.coreservice.transport.system.ICarLightLinister;
import com.sinohb.coreservice.transport.system.IApplicationLinister;
import com.sinohb.coreservice.transport.system.IAudioFocusLinister;
import com.sinohb.coreservice.transport.system.IBackCarLinister;
import com.sinohb.coreservice.transport.system.IHeadsetLinister;
import com.sinohb.coreservice.transport.slagcar.ISlagCarDataLinstener;

interface ISystemManager {

    int  getPowerState();
    int  getBrightness();
    void setBrightness(int brightness,int type);
    void setBacklightSwitch(boolean bool);
    void setPowerSwitch(boolean bool);
    void requestMcuUpgrade(in String path);
    void setSystemMute(boolean bool);
    boolean getMuteState();
    void setVolume(int volume,int type);
    int  getVolume(int type);
    void requestSystemEQ();
    void setSystemEQ(in int [] effect);
    int [] getSystemEQ();
    void setSoundfield(int x,int y);
    void requestSoundfield();
    int [] getSoundfield();

    void requestAuxState(int type);
    int getAuxState(int type);
    void requestCarLightState(int type);
    int getCarLightState(int type);
    void reportPhoneState(int type,String number);
    void reportSystemReset(int state);

    int getHeadsetState();
    void requestHeadsetState();


    void sendPassthroughData(int groupID,int commandID,inout byte [] data);
    void sendRadioDatas(int groupID,int commandID,inout byte [] data);

      void  onSendSlagCarData(inout byte [] data);


    void setBrightnessLinister(in IBrightnessLinister linister);
    void removeBrightnessLinister(in IBrightnessLinister linister);

    void setBacklightSwitchLinister(in IBacklightSwitchLinister linister);
    void removeBacklightSwitchLinister(in IBacklightSwitchLinister linister);

    void setSystemKeyLinister(in IKeyLinister keylinister);
    void removeSystemKeyLinister(in IKeyLinister keylinister);

    void setMcuUpgradeLinister(in IMcuUpgradeLinister upgradeLinister);
    void removeMcuUpgradeLinister(in IMcuUpgradeLinister upgradeLinister);

    void setPassthroughLinister(in IPassthroughDataLinister linister);
    void removePassthroughLinister(in IPassthroughDataLinister linister);

    void setPowerStateLinister(in IPowerStateLinister linister);
    void removePowerStateLinister(in IPowerStateLinister linister);

    void setRadioLinister(in IRadioLinister radiolinister);
    void removeRadioLinister(in IRadioLinister radiolinister);

    void setSoundfieldLinister(in ISoundfieldLinister linister);
    void removeSoundfieldLinister(in ISoundfieldLinister linister);

    void setSystemEQLinister(in ISystemEQLinister linister);
    void removeSystemEQLinister(in ISystemEQLinister linister);

    void setSystemMuteLinister(in ISystemMuteLinister linister);
    void removeSystemMuteLinister(in ISystemMuteLinister linister);

    void setVolumeLinister(in IVolumeLinister linister);
    void removeVolumeLinister(in IVolumeLinister linister);

    void setAuxStateLinister(in IAuxStateLinister linister);
    void removeAuxStateLinister(in IAuxStateLinister linister);

    void setCarLightLinister(in ICarLightLinister linister);
    void removeCarLightLinister(in ICarLightLinister linister);

    void setApplicationLinister(in IApplicationLinister linister);
    void removeApplicationLinister(in IApplicationLinister linister);

    void setAudioFocusLinister(in IAudioFocusLinister linister);
    void removeAudioFocusLinister(in IAudioFocusLinister linister);

    void setBackCarLinister(in IBackCarLinister linister);
    void removeBackCarLinister(in IBackCarLinister linister);

    void setHeadsetLinister(in IHeadsetLinister linister);
    void removeHeadsetLinister(in IHeadsetLinister linister);

    void setSlagCarDataLinstener(in ISlagCarDataLinstener linsten);

    void removeSlagCarDataLinstener(in ISlagCarDataLinstener linsten);

}

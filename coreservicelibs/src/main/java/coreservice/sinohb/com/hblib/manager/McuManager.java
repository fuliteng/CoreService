package coreservice.sinohb.com.hblib.manager;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;

import com.sinohb.coreservice.manager.ISystemManager;
import com.sinohb.coreservice.transport.system.IApplicationLinister;
import com.sinohb.coreservice.transport.system.IAudioFocusLinister;
import com.sinohb.coreservice.transport.system.IAuxStateLinister;
import com.sinohb.coreservice.transport.system.IBackCarLinister;
import com.sinohb.coreservice.transport.system.IBacklightSwitchLinister;
import com.sinohb.coreservice.transport.system.IBrightnessLinister;
import com.sinohb.coreservice.transport.system.ICarLightLinister;
import com.sinohb.coreservice.transport.system.IHeadsetLinister;
import com.sinohb.coreservice.transport.system.IKeyLinister;
import com.sinohb.coreservice.transport.system.IMcuUpgradeLinister;
import com.sinohb.coreservice.transport.system.IPassthroughDataLinister;
import com.sinohb.coreservice.transport.system.IPowerStateLinister;
import com.sinohb.coreservice.transport.system.IRadioLinister;
import com.sinohb.coreservice.transport.system.ISoundfieldLinister;
import com.sinohb.coreservice.transport.system.ISystemEQLinister;
import com.sinohb.coreservice.transport.system.ISystemMuteLinister;
import com.sinohb.coreservice.transport.system.IVolumeLinister;

import java.util.HashMap;

import coreservice.sinohb.com.hblib.hbutils.L;
import coreservice.sinohb.com.hblib.hbutils.ReconnectSpeechInterface;
import coreservice.sinohb.com.hblib.hbutils.ReconnectSystemInterface;
import coreservice.sinohb.com.hblib.interfaces.system.ApplicationLinister;
import coreservice.sinohb.com.hblib.interfaces.system.AudioFocusLinister;
import coreservice.sinohb.com.hblib.interfaces.system.AuxStateLinister;
import coreservice.sinohb.com.hblib.interfaces.system.BackCarLinister;
import coreservice.sinohb.com.hblib.interfaces.system.BacklightSwitchLinister;
import coreservice.sinohb.com.hblib.interfaces.system.BrightnessLinister;
import coreservice.sinohb.com.hblib.interfaces.system.CarLightLinister;
import coreservice.sinohb.com.hblib.interfaces.system.HeadsetLinister;
import coreservice.sinohb.com.hblib.interfaces.system.McuUpgradeLinister;
import coreservice.sinohb.com.hblib.interfaces.system.PassthroughDataLinister;
import coreservice.sinohb.com.hblib.interfaces.system.PowerStateLinister;
import coreservice.sinohb.com.hblib.interfaces.system.RadioLinister;
import coreservice.sinohb.com.hblib.interfaces.system.SoundfieldLinister;
import coreservice.sinohb.com.hblib.interfaces.system.SystemEQLinister;
import coreservice.sinohb.com.hblib.interfaces.system.SystemKeyLinister;
import coreservice.sinohb.com.hblib.interfaces.system.SystemMuteLinister;
import coreservice.sinohb.com.hblib.interfaces.system.VolumeLinister;

public class McuManager {

    public static String Tag = McuManager.class.getSimpleName();
    private static McuManager mcuManager;
    private ISystemManager systemService;
    private Context        context;

    public static McuManager getInstance() {
        if (mcuManager == null) {
            synchronized (McuManager.class) {
                if (mcuManager == null) {
                    mcuManager = new McuManager();
                }
            }
        }
        return mcuManager;
    }


    private boolean isRemoteServiceAlive() {
        if (systemService != null) {
            return true;
        } else {
            L.i(Tag, "remoteService is null");
            return false;
        }
    }

    public int getPowerState() {
        int power = 0x00;
        L.i(Tag, "getPowerState");
        if (isRemoteServiceAlive()) {
            try {
                power = systemService.getPowerState();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return power;
    }


    public int getBrightness() {
        int brightness = 0;
        L.i(Tag, "getBrightness");
        if (isRemoteServiceAlive()) {
            try {
                brightness = systemService.getBrightness();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return brightness;
    }

    public void setBrightness(int brightness, int type) {
        L.i(Tag, "setBrightness brightness:" + brightness + "  type:" + type);
        if (isRemoteServiceAlive()) {
            try {
                systemService.setBrightness(brightness, type);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void setBacklightSwitch(boolean bool) {
        L.i(Tag, "setBacklightSwitch bool:" + bool);
        if (isRemoteServiceAlive()) {
            try {
                systemService.setBacklightSwitch(bool);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void setPowerSwitch(boolean bool) {
        L.i(Tag, "setPowerSwitch bool:" + bool);
        if (isRemoteServiceAlive()) {
            try {
                systemService.setPowerSwitch(bool);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void requestMcuUpgrade(String path) {
        L.i(Tag, "requestMcuUpgrade path:" + path);
        if (isRemoteServiceAlive()) {
            try {
                systemService.requestMcuUpgrade(path);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void setSystemMute(boolean bool) {
        L.i(Tag, "setSystemMute bool:" + bool);
        if (isRemoteServiceAlive()) {
            try {
                systemService.setSystemMute(bool);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean getMuteState() {
        L.i(Tag, "getMuteState ");
        boolean isMute = false;
        if (isRemoteServiceAlive()) {
            try {
                isMute = systemService.getMuteState();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return isMute;
    }

    public void setVolume(int volume, int type) {
        L.i(Tag, "setVolume volume:" + volume + "  type:" + type);
        if (isRemoteServiceAlive()) {
            try {
                systemService.setVolume(volume, type);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public int getVolume(int type) {
        L.i(Tag, "getVolume type:" + type);
        int vol = 0;
        if (isRemoteServiceAlive()) {
            try {
                vol = systemService.getVolume(type);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return vol;
    }

    public void requestSystemEQ() {
        L.i(Tag, "requestSystemEQ");
        if (isRemoteServiceAlive()) {
            try {
                systemService.requestSystemEQ();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void setSystemEQ(int[] effect) {
        L.i(Tag, "setSystemEQ ");
        if (isRemoteServiceAlive()) {
            try {
                systemService.setSystemEQ(effect);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public int[] getSystemEQ() {
        L.i(Tag, "getSystemEQ ");
        int[] data = new int[]{};
        if (isRemoteServiceAlive()) {
            try {
                data = systemService.getSystemEQ();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return data;
    }

    public void setSoundfield(int x, int y) {
        L.i(Tag, "setSoundfield ");
        if (isRemoteServiceAlive()) {
            try {
                systemService.setSoundfield(x, y);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void requestSoundfield() {
        L.i(Tag, "requestSoundfield ");
        if (isRemoteServiceAlive()) {
            try {
                systemService.requestSoundfield();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public int[] getSoundfield() {
        L.i(Tag, "getSoundfield ");
        int[] data = new int[]{};
        if (isRemoteServiceAlive()) {
            try {
                data = systemService.getSoundfield();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return data;
    }

    public void requestAuxState(int type) {
        L.i(Tag, "requestAuxState");
        if (isRemoteServiceAlive()) {
            try {
                systemService.requestAuxState(type);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public int getAuxState(int type) {
        L.i(Tag, "getAuxState");
        int result = -1;
        if (isRemoteServiceAlive()) {
            try {
                result = systemService.getAuxState(type);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public void requestCarLightState(int type) {
        L.i(Tag, "requestCarLightState");
        if (isRemoteServiceAlive()) {
            try {
                systemService.requestCarLightState(type);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public int getCarLightState(int type) {
        L.i(Tag, "getCarLightState");
        int result = -1;
        if (isRemoteServiceAlive()) {
            try {
                result = systemService.getCarLightState(type);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public void sendRadioDatas(int groupID, int commandID, byte[] data) {
        L.i(Tag, "sendRadioDatas");
        if (isRemoteServiceAlive()) {
            try {
                systemService.sendRadioDatas(groupID, commandID, data);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendPassthroughData(int groupID, int commandID, byte[] data) {
        L.i(Tag, "setPassthroughData groupID:" + groupID + " commandID:" + commandID);
        if (isRemoteServiceAlive()) {
            try {
                systemService.sendPassthroughData(groupID, commandID, data);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void reportPhoneState(int type, String number) {
        L.i(Tag, "sendPhoneState type:" + type + "  number:" + number);
        if (isRemoteServiceAlive()) {
            try {
                systemService.reportPhoneState(type, number);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void reportSystemReset(int state) {
        L.i(Tag, "reportPhoneState state:" + state);
        if (isRemoteServiceAlive()) {
            try {
                systemService.reportSystemReset(state);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public int getHeadsetState() {
        L.i(Tag, "getHeadsetState()");
        int state = 0;
        if (isRemoteServiceAlive()) {
            try {
                state = systemService.getHeadsetState();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return state;
    }

    public void requestHeadsetState() {
        L.i(Tag, "requestHeadsetState()");
        if (isRemoteServiceAlive()) {
            try {
                systemService.requestHeadsetState();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }


    private HashMap<String, BacklightSwitchLinister> backlightSwitchLinisterHashMap = new HashMap<>();

    public void setBacklightSwitchLinister(BacklightSwitchLinister linister) {

        if (linister != null) {
            ReconnectSystemInterface.hasRegister_IBacklightSwitchLinister = true;
            if (!backlightSwitchLinisterHashMap.containsKey(linister.toString())) {
                backlightSwitchLinisterHashMap.put(linister.toString(), linister);
                setBacklightSwitchLinister();
            }
        }
    }

    public void removeBacklightSwitchLinister(BacklightSwitchLinister linister) {
        if (linister != null) {
            if (backlightSwitchLinisterHashMap.containsKey(linister.toString())) {
                backlightSwitchLinisterHashMap.remove(linister.toString());
                if (backlightSwitchLinisterHashMap.size() <= 0) {
                    removeBacklightSwitchLinister();
                }
            }
        }
    }

    private IBacklightSwitchLinister.Stub iBacklightSwitchLinister = new IBacklightSwitchLinister.Stub() {

        @Override
        public void onBackLightSwitchChange(boolean bool) throws RemoteException {
            for (String key : backlightSwitchLinisterHashMap.keySet()) {
                if (backlightSwitchLinisterHashMap.get(key) != null) {
                    backlightSwitchLinisterHashMap.get(key).onBackLightSwitchChange(bool);
                } else {
                    backlightSwitchLinisterHashMap.remove(key);
                    if (backlightSwitchLinisterHashMap.size() <= 0) {
                        removeBacklightSwitchLinister();
                    }
                }
            }
        }
    };

    private void setBacklightSwitchLinister() {
        if (isRemoteServiceAlive()) {
            try {
                L.i(Tag, "setBacklightSwitchLinister");
                systemService.setBacklightSwitchLinister(iBacklightSwitchLinister);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private void removeBacklightSwitchLinister() {
        if (isRemoteServiceAlive()) {
            try {
                L.i(Tag, "removeBacklightSwitchLinister");
                ReconnectSystemInterface.hasRegister_IBacklightSwitchLinister = false;
                systemService.removeBacklightSwitchLinister(iBacklightSwitchLinister);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }


    private HashMap<String, BrightnessLinister> brightnessLinisterHashMap = new HashMap<>();

    private IBrightnessLinister.Stub iBrightnessLinister = new IBrightnessLinister.Stub() {
        @Override
        public void onBrightnessChange(int brightness, int type) throws RemoteException {

            L.i(Tag, "onBrightnessChange seze:" + brightnessLinisterHashMap.size());

            for (String key : brightnessLinisterHashMap.keySet()) {
                if (brightnessLinisterHashMap.get(key) != null) {
                    L.i(Tag, "onBrightnessChange key:" + key);
                    brightnessLinisterHashMap.get(key).onBrightnessChange(brightness, type);
                } else {
                    brightnessLinisterHashMap.remove(key);
                    if (brightnessLinisterHashMap.size() <= 0) {
                        removeBrightnessLinister();
                    }
                }
            }
        }
    };

    public void setBrightnessLinister(BrightnessLinister linister) {
        if (linister != null) {
            ReconnectSystemInterface.hasRegister_IBrightnessLinister = true;
            L.i(Tag, "setBrightnessLinister :" + linister.toString());
            if (!brightnessLinisterHashMap.containsKey(linister.toString())) {
                brightnessLinisterHashMap.put(linister.toString(), linister);
                setBrightnessLinister();
            }
        }
    }

    public void removeBrightnessLinister(BrightnessLinister linister) {
        if (linister != null) {
            L.i(Tag, "removeBrightnessLinister :" + linister.toString());
            if (brightnessLinisterHashMap.containsKey(linister.toString())) {
                brightnessLinisterHashMap.remove(linister.toString());
                if (brightnessLinisterHashMap.size() <= 0) {
                    removeBrightnessLinister();
                }
            }
        }
    }

    private void setBrightnessLinister() {
        if (isRemoteServiceAlive()) {
            try {
                L.i(Tag, "setBrightnessLinister service");
                systemService.setBrightnessLinister(iBrightnessLinister);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private void removeBrightnessLinister() {
        if (isRemoteServiceAlive()) {
            try {
                L.i(Tag, "removeBrightnessLinister service");
                ReconnectSystemInterface.hasRegister_IBrightnessLinister = false;
                systemService.removeBrightnessLinister(iBrightnessLinister);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }


    private HashMap<String, SystemKeyLinister> stringSystemKeyLinisterHashMap = new HashMap<>();
    private IKeyLinister.Stub iKeyLinister = new IKeyLinister.Stub() {

        @Override
        public void onSystemKeyLinister(int type, int keycode, int action) throws RemoteException {
            for (String key : stringSystemKeyLinisterHashMap.keySet()) {
                if (stringSystemKeyLinisterHashMap.get(key) != null) {
                    stringSystemKeyLinisterHashMap.get(key).onSystemKeyLinister(type, keycode, action);
                } else {
                    stringSystemKeyLinisterHashMap.remove(key);
                    if (stringSystemKeyLinisterHashMap.size() <= 0) {
                        removeSystemKeyLinister();
                    }
                }
            }
        }
    };

    public void setSystemKeyLinister(SystemKeyLinister linister) {
        if (linister != null) {
            ReconnectSystemInterface.hasRegister_IKeyLinister = true;
            if (!stringSystemKeyLinisterHashMap.containsKey(linister.toString())) {
                stringSystemKeyLinisterHashMap.put(linister.toString(), linister);
                setSystemKeyLinister();
            }
        }
    }

    public void removeSystemKeyLinister(SystemKeyLinister linister) {
        if (linister != null) {
            if (stringSystemKeyLinisterHashMap.containsKey(linister.toString())) {
                stringSystemKeyLinisterHashMap.remove(linister.toString());
                if (stringSystemKeyLinisterHashMap.size() <= 0) {
                    removeSystemKeyLinister();
                }
            }
        }
    }


    private void setSystemKeyLinister() {
        if (isRemoteServiceAlive()) {
            try {
                L.i(Tag, "setSystemKeyLinister");
                systemService.setSystemKeyLinister(iKeyLinister);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private void removeSystemKeyLinister() {
        if (isRemoteServiceAlive()) {
            try {
                L.i(Tag, "removeSystemKeyLinister");
                ReconnectSystemInterface.hasRegister_IKeyLinister = false;
                systemService.removeSystemKeyLinister(iKeyLinister);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }


    private HashMap<String, McuUpgradeLinister> mcuUpgradeLinisterHashMap = new HashMap<>();

    private IMcuUpgradeLinister.Stub iMcuUpgradeLinister = new IMcuUpgradeLinister.Stub() {
        @Override
        public void onResult(int code) throws RemoteException {
            for (String key : mcuUpgradeLinisterHashMap.keySet()) {
                if (mcuUpgradeLinisterHashMap.get(key) != null) {
                    mcuUpgradeLinisterHashMap.get(key).onResult(code);
                } else {
                    mcuUpgradeLinisterHashMap.remove(key);
                    if (mcuUpgradeLinisterHashMap.size() <= 0) {
                        removeMcuUpgradeLinister();
                    }
                }
            }
        }

        @Override
        public void onProgress(int progress) throws RemoteException {
            for (String key : mcuUpgradeLinisterHashMap.keySet()) {
                if (mcuUpgradeLinisterHashMap.get(key) != null) {
                    mcuUpgradeLinisterHashMap.get(key).onProgress(progress);
                } else {
                    mcuUpgradeLinisterHashMap.remove(key);
                    if (mcuUpgradeLinisterHashMap.size() <= 0) {
                        removeMcuUpgradeLinister();
                    }
                }
            }
        }
    };

    public void setMcuUpgradeLinister(McuUpgradeLinister linister) {
        if (linister != null) {
            ReconnectSystemInterface.hasRegister_IMcuUpgradeLinister = true;
            if (!mcuUpgradeLinisterHashMap.containsKey(linister.toString())) {
                mcuUpgradeLinisterHashMap.put(linister.toString(), linister);
                setMcuUpgradeLinister();
            }
        }
    }

    public void removeMcuUpgradeLinister(McuUpgradeLinister linister) {
        if (linister != null) {
            if (mcuUpgradeLinisterHashMap.containsKey(linister.toString())) {
                mcuUpgradeLinisterHashMap.remove(linister);
                if (mcuUpgradeLinisterHashMap.size() <= 0) {
                    removeMcuUpgradeLinister();
                }
            }
        }
    }

    private void setMcuUpgradeLinister() {
        if (isRemoteServiceAlive()) {
            try {
                L.i(Tag, "setMcuUpgradeLinister");
                systemService.setMcuUpgradeLinister(iMcuUpgradeLinister);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private void removeMcuUpgradeLinister() {
        if (isRemoteServiceAlive()) {
            try {
                L.i(Tag, "removeMcuUpgradeLinister");
                ReconnectSystemInterface.hasRegister_IMcuUpgradeLinister = false;
                systemService.removeMcuUpgradeLinister(iMcuUpgradeLinister);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private HashMap<String, PassthroughDataLinister> passthroughDataLinisterHashMap = new HashMap<>();

    private IPassthroughDataLinister.Stub iPassthroughDataLinister = new IPassthroughDataLinister.Stub() {
        @Override
        public void onPassthroughDataReceiver(int groupID, int commandID, byte[] data) throws RemoteException {
            for (String key : passthroughDataLinisterHashMap.keySet()) {
                if (passthroughDataLinisterHashMap.get(key) != null) {
                    passthroughDataLinisterHashMap.get(key).onPassthroughDataReceiver(groupID, commandID, data);
                } else {
                    passthroughDataLinisterHashMap.remove(key);
                    if (passthroughDataLinisterHashMap.size() <= 0) {
                        removePassthroughLinister();
                    }
                }
            }
        }
    };

    public void setPassthroughLinister(PassthroughDataLinister linister) {
        if (linister != null) {
            ReconnectSystemInterface.hasRegister_IPassthroughDataLinister = true;
            if (!passthroughDataLinisterHashMap.containsKey(linister.toString())) {
                passthroughDataLinisterHashMap.put(linister.toString(), linister);
                setPassthroughLinister();
            }
        }
    }

    public void removePassthroughLinister(PassthroughDataLinister linister) {
        if (linister != null) {
            if (passthroughDataLinisterHashMap.containsKey(linister.toString())) {
                passthroughDataLinisterHashMap.remove(linister.toString());
                if (passthroughDataLinisterHashMap.size() <= 0) {
                    removePassthroughLinister();
                }
            }
        }
    }

    private void setPassthroughLinister() {
        if (isRemoteServiceAlive()) {
            try {
                L.i(Tag, "setPassthroughLinister");
                systemService.setPassthroughLinister(iPassthroughDataLinister);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private void removePassthroughLinister() {
        if (isRemoteServiceAlive()) {
            try {
                L.i(Tag, "removePassthroughLinister");
                ReconnectSystemInterface.hasRegister_IPassthroughDataLinister = false;
                systemService.removePassthroughLinister(iPassthroughDataLinister);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }


    HashMap<String, PowerStateLinister> powerStateLinisterHashMap = new HashMap<String, PowerStateLinister>();

    private IPowerStateLinister.Stub iPowerStateLinister = new IPowerStateLinister.Stub() {
        @Override
        public void onPowerChange(int power) throws RemoteException {

            for (String key : powerStateLinisterHashMap.keySet()) {
                if (powerStateLinisterHashMap.get(key) != null) {
                    powerStateLinisterHashMap.get(key).onPowerChange(power);
                } else {
                    powerStateLinisterHashMap.remove(key);
                    if (powerStateLinisterHashMap.size() <= 0) {
                        removePowerStateLinister();
                    }
                }
            }
        }
    };


    public void setPowerStateLinister(PowerStateLinister linister) {
        if (linister != null) {
            ReconnectSystemInterface.hasRegister_IPowerStateLinister = true;
            if (!powerStateLinisterHashMap.containsKey(linister.toString())) {
                powerStateLinisterHashMap.put(linister.toString(), linister);
                setPowerStateLinister();
            }
        }
    }

    public void removePowerStateLinister(PowerStateLinister linister) {
        if (linister != null) {
            if (powerStateLinisterHashMap.containsKey(linister.toString())) {
                powerStateLinisterHashMap.remove(linister.toString());
                if (powerStateLinisterHashMap.size() <= 0) {
                    removePowerStateLinister();
                }
            }
        }
    }


    private void setPowerStateLinister() {
        if (isRemoteServiceAlive()) {
            try {
                L.i(Tag, "setPowerStateLinister");
                systemService.setPowerStateLinister(iPowerStateLinister);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private void removePowerStateLinister() {
        if (isRemoteServiceAlive()) {
            try {
                L.i(Tag, "removePowerStateLinister");
                ReconnectSystemInterface.hasRegister_IPowerStateLinister = false;
                systemService.removePowerStateLinister(iPowerStateLinister);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }


    private HashMap<String, RadioLinister> radioLinisterHashMap = new HashMap<>();

    private IRadioLinister.Stub iRadioLinister = new IRadioLinister.Stub() {
        @Override
        public void onRadioReceiver(int groupID, int commandID, byte[] data) throws RemoteException {
            for (String key : radioLinisterHashMap.keySet()) {
                if (radioLinisterHashMap.get(key) != null) {
                    radioLinisterHashMap.get(key).onRadioReceive(groupID, commandID, data);
                } else {
                    radioLinisterHashMap.remove(key);
                    if (radioLinisterHashMap.size() <= 0) {
                        removeRadioLinister();
                    }
                }
            }
        }
    };

    public void setRadioLinister(RadioLinister linister) {
        if (linister != null) {
            ReconnectSystemInterface.hasRegister_IRadioLinister = true;
            if (!radioLinisterHashMap.containsKey(linister.toString())) {
                radioLinisterHashMap.put(linister.toString(), linister);
                setRadioLinister();
            }
        }
    }

    public void removeRadioLinister(RadioLinister linister) {
        if (linister != null) {
            if (radioLinisterHashMap.containsKey(linister.toString())) {
                radioLinisterHashMap.remove(linister.toString());
                if (radioLinisterHashMap.size() <= 0) {
                    removeRadioLinister();
                }
            }
        }
    }

    private void setRadioLinister() {
        if (isRemoteServiceAlive()) {
            try {
                L.i(Tag, "setRadioLinister");
                systemService.setRadioLinister(iRadioLinister);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private void removeRadioLinister() {
        if (isRemoteServiceAlive()) {
            try {
                L.i(Tag, "removeRadioLinister");
                ReconnectSystemInterface.hasRegister_IRadioLinister = false;
                systemService.removeRadioLinister(iRadioLinister);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }


    private HashMap<String, SoundfieldLinister> soundfieldLinisterHashMap = new HashMap<>();

    private ISoundfieldLinister.Stub iSoundfieldLinister = new ISoundfieldLinister.Stub() {
        @Override
        public void onSoundfieldChange(int[] field) throws RemoteException {
            for (String key : soundfieldLinisterHashMap.keySet()) {
                if (soundfieldLinisterHashMap.get(key) != null) {
                    soundfieldLinisterHashMap.get(key).onSoundfieldChange(field);
                } else {
                    soundfieldLinisterHashMap.remove(key);
                    if (soundfieldLinisterHashMap.size() <= 0) {
                        removeSoundfieldLinister();
                    }
                }
            }
        }
    };

    public void setSoundfieldLinister(SoundfieldLinister linister) {
        if (linister != null) {
            ReconnectSystemInterface.hasRegister_ISoundfieldLinister = true;
            if (!soundfieldLinisterHashMap.containsKey(linister.toString())) {
                soundfieldLinisterHashMap.put(linister.toString(), linister);
                setSoundfieldLinister();
            }
        }
    }

    public void removeSoundfieldLinister(SoundfieldLinister linister) {
        if (linister != null) {
            if (soundfieldLinisterHashMap.containsKey(linister.toString())) {
                soundfieldLinisterHashMap.remove(linister.toString());
                if (soundfieldLinisterHashMap.size() <= 0) {
                    removeSoundfieldLinister();
                }
            }
        }
    }


    private void setSoundfieldLinister() {
        if (isRemoteServiceAlive()) {
            try {
                L.i(Tag, "setSoundfieldLinister");
                systemService.setSoundfieldLinister(iSoundfieldLinister);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private void removeSoundfieldLinister() {
        if (isRemoteServiceAlive()) {
            try {
                L.i(Tag, "removeSoundfieldLinister");
                ReconnectSystemInterface.hasRegister_ISoundfieldLinister = false;
                systemService.removeSoundfieldLinister(iSoundfieldLinister);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }


    HashMap<String, SystemEQLinister> systemEQLinisterHashMap = new HashMap<>();

    private ISystemEQLinister.Stub iSystemEQLinister = new ISystemEQLinister.Stub() {
        @Override
        public void onSystemEQChange(int[] effect) throws RemoteException {
            for (String key : systemEQLinisterHashMap.keySet()) {
                if (systemEQLinisterHashMap.get(key) != null) {
                    systemEQLinisterHashMap.get(key).onSystemEQChange(effect);
                } else {
                    systemEQLinisterHashMap.remove(key);
                    if (systemEQLinisterHashMap.size() <= 0) {
                        removeSystemEQLinister();
                    }
                }
            }
        }
    };

    public void setSystemEQLinister(SystemEQLinister linister) {
        if (linister != null) {
            ReconnectSystemInterface.hasRegister_ISystemEQLinister = true;
            if (!systemEQLinisterHashMap.containsKey(linister.toString())) {
                systemEQLinisterHashMap.put(linister.toString(), linister);
                setSystemEQLinister();
            }
        }
    }

    public void removeSystemEQLinister(SystemEQLinister linister) {
        if (linister != null) {
            if (systemEQLinisterHashMap.containsKey(linister)) {
                systemEQLinisterHashMap.remove(linister.toString());
                if (systemEQLinisterHashMap.size() <= 0) {
                    removeSystemEQLinister();
                }
            }
        }
    }


    private void setSystemEQLinister() {
        if (isRemoteServiceAlive()) {
            try {
                L.i(Tag, "setSystemEQLinister");
                systemService.setSystemEQLinister(iSystemEQLinister);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private void removeSystemEQLinister() {
        if (isRemoteServiceAlive()) {
            try {
                L.i(Tag, "removeSystemEQLinister");
                ReconnectSystemInterface.hasRegister_ISystemEQLinister = false;
                systemService.removeSystemEQLinister(iSystemEQLinister);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }


    private HashMap<String, SystemMuteLinister> systemMuteLinisterHashMap = new HashMap<>();
    private ISystemMuteLinister.Stub iSystemMuteLinister = new ISystemMuteLinister.Stub() {
        @Override
        public void onMuteChange(boolean mute) throws RemoteException {
            for (String key : systemMuteLinisterHashMap.keySet()) {
                if (systemMuteLinisterHashMap.get(key) != null) {
                    systemMuteLinisterHashMap.get(key).onMuteChange(mute);
                } else {
                    systemMuteLinisterHashMap.remove(key);
                    if (systemMuteLinisterHashMap.size() <= 0) {
                        removeSystemMuteLinister();
                    }
                }
            }
        }
    };

    public void setSystemMuteLinister(SystemMuteLinister linister) {
        if (linister != null) {
            ReconnectSystemInterface.hasRegister_ISystemMuteLinister = true;
            if (!systemMuteLinisterHashMap.containsKey(linister.toString())) {
                systemMuteLinisterHashMap.put(linister.toString(), linister);
                setSystemMuteLinister();
            }
        }
    }

    public void removeSystemMuteLinister(SystemMuteLinister linister) {
        if (linister != null) {
            if (systemMuteLinisterHashMap.containsKey(linister.toString())) {
                systemMuteLinisterHashMap.remove(linister.toString());
                if (systemMuteLinisterHashMap.size() <= 0) {
                    removeSystemMuteLinister();
                }
            }
        }
    }

    private void setSystemMuteLinister() {
        if (isRemoteServiceAlive()) {
            try {
                L.i(Tag, "setSystemMuteLinister");
                systemService.setSystemMuteLinister(iSystemMuteLinister);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private void removeSystemMuteLinister() {
        if (isRemoteServiceAlive()) {
            try {
                L.i(Tag, "removeSystemMuteLinister");
                ReconnectSystemInterface.hasRegister_ISystemMuteLinister = false;
                systemService.removeSystemMuteLinister(iSystemMuteLinister);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private HashMap<String, VolumeLinister> volumeLinisterHashMap = new HashMap<>();

    private IVolumeLinister.Stub iVolumeLinister = new IVolumeLinister.Stub() {
        @Override
        public void onVolumeChange(int volumne, int type) throws RemoteException {
            for (String key : volumeLinisterHashMap.keySet()) {
                if (volumeLinisterHashMap.get(key) != null) {
                    volumeLinisterHashMap.get(key).onVolumeChange(volumne, type);
                } else {
                    volumeLinisterHashMap.remove(key);
                    if (volumeLinisterHashMap.size() <= 0) {
                        removeVolumeLinister();
                    }
                }
            }
        }
    };

    public void setVolumeLinister(VolumeLinister linister) {
        if (linister != null) {
            ReconnectSystemInterface.hasRegister_IVolumeLinister = true;
            if (!volumeLinisterHashMap.containsKey(linister.toString())) {
                volumeLinisterHashMap.put(linister.toString(), linister);
                setVolumeLinister();
            }
        }
    }

    public void removeVolumeLinister(VolumeLinister linister) {
        if (linister != null) {
            if (volumeLinisterHashMap.containsKey(linister.toString())) {
                volumeLinisterHashMap.remove(linister.toString());
                if (volumeLinisterHashMap.size() <= 0) {
                    removeVolumeLinister();
                }
            }
        }
    }

    private void setVolumeLinister() {
        if (isRemoteServiceAlive()) {
            try {
                L.i(Tag, "setVolumeLinister");
                systemService.setVolumeLinister(iVolumeLinister);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private void removeVolumeLinister() {
        if (isRemoteServiceAlive()) {
            try {
                L.i(Tag, "removeVolumeLinister");
                ReconnectSystemInterface.hasRegister_IVolumeLinister = false;
                systemService.removeVolumeLinister(iVolumeLinister);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }


    private HashMap<String, CarLightLinister> carLightLinisterHashMap = new HashMap<>();

    private ICarLightLinister.Stub iCarLightLinister = new ICarLightLinister.Stub() {
        @Override
        public void onCarLightLinister(int light, int state) throws RemoteException {
            for (String key : carLightLinisterHashMap.keySet()) {
                if (carLightLinisterHashMap.get(key) != null) {
                    carLightLinisterHashMap.get(key).onCarLightLinister(light, state);
                } else {
                    carLightLinisterHashMap.remove(key);
                    if (carLightLinisterHashMap.size() <= 0) {
                        removeCarLightLinister();
                    }
                }
            }
        }
    };

    public void setCarLightLinister(CarLightLinister linister) {
        ReconnectSystemInterface.hasRegister_ICarLightLinister = true;
        if (linister != null) {
            if (!carLightLinisterHashMap.containsKey(linister.toString())) {
                carLightLinisterHashMap.put(linister.toString(), linister);
                setCarLightLinister();
            }
        }
    }

    public void removeCarLightLinister(CarLightLinister linister) {
        if (linister != null) {
            if (carLightLinisterHashMap.containsKey(linister.toString())) {
                carLightLinisterHashMap.remove(linister.toString());
                if (carLightLinisterHashMap.size() <= 0) {
                    removeAuxStateLinister();
                }
            }
        }
    }

    private void setCarLightLinister() {
        if (isRemoteServiceAlive()) {
            try {
                L.i(Tag, "setCarLightLinister");
                systemService.setCarLightLinister(iCarLightLinister);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private void removeCarLightLinister() {
        ReconnectSystemInterface.hasRegister_ICarLightLinister = false;
        if (isRemoteServiceAlive()) {
            try {
                L.i(Tag, "removeCarLightLinister");
                systemService.removeCarLightLinister(iCarLightLinister);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private HashMap<String, AuxStateLinister> auxStateLinisterHashMap = new HashMap<>();
    private IAuxStateLinister.Stub iAuxStateLinister = new IAuxStateLinister.Stub() {
        @Override
        public void onAuxStateLinister(int type, int state) throws RemoteException {
            for (String key : auxStateLinisterHashMap.keySet()) {
                if (auxStateLinisterHashMap.get(key) != null) {
                    auxStateLinisterHashMap.get(key).onAuxStateLinister(type, state);
                } else {
                    auxStateLinisterHashMap.remove(key);
                    if (auxStateLinisterHashMap.size() <= 0) {
                        removeAuxStateLinister();
                    }
                }
            }
        }
    };

    public void setAuxStateLinister(AuxStateLinister linister) {
        ReconnectSystemInterface.hasRegister_IAuxStateLinister = true;
        if (linister != null) {
            if (!auxStateLinisterHashMap.containsKey(linister.toString())) {
                auxStateLinisterHashMap.put(linister.toString(), linister);
                setAuxStateLinister();
            }
        }
    }

    public void removeAuxStateLinister(AuxStateLinister linister) {
        if (linister != null) {
            if (auxStateLinisterHashMap.containsKey(linister.toString())) {
                auxStateLinisterHashMap.remove(linister.toString());
                if (auxStateLinisterHashMap.size() <= 0) {
                    removeAuxStateLinister();
                }
            }
        }
    }

    private void setAuxStateLinister() {
        if (isRemoteServiceAlive()) {
            try {
                L.i(Tag, "setAuxStateLinister");
                systemService.setAuxStateLinister(iAuxStateLinister);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private void removeAuxStateLinister() {
        ReconnectSystemInterface.hasRegister_IAuxStateLinister = false;
        if (isRemoteServiceAlive()) {
            try {
                L.i(Tag, "removeAuxStateLinister");
                systemService.removeAuxStateLinister(iAuxStateLinister);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private HashMap<String, ApplicationLinister> applicationLinisterHashMap = new HashMap<>();

    public void setApplicationLinister(ApplicationLinister linister) {
        ReconnectSystemInterface.hasRegister_IApplicationLinister = true;
        if (linister != null) {
            if (!applicationLinisterHashMap.containsKey(linister.toString())) {
                applicationLinisterHashMap.put(linister.toString(), linister);
                setApplicationLinister();
            }
        }
    }

    public void removeApplicationLinister(ApplicationLinister linister) {
        if (linister != null) {
            if (applicationLinisterHashMap.containsKey(linister.toString())) {
                applicationLinisterHashMap.remove(linister.toString());
                if (applicationLinisterHashMap.size() <= 0) {
                    removeApplicationLinister();
                }
            }
        }
    }

    private IApplicationLinister.Stub iApplicationLinister = new IApplicationLinister.Stub() {
        @Override
        public void onApplicationLinister(String application, int type) throws RemoteException {
            for (String key : applicationLinisterHashMap.keySet()) {
                if (applicationLinisterHashMap.get(key) != null) {
                    applicationLinisterHashMap.get(key).onApplicationLinister(application, type);
                } else {
                    applicationLinisterHashMap.remove(key);
                    if (applicationLinisterHashMap.size() <= 0) {
                        removeApplicationLinister();
                    }
                }
            }
        }
    };

    private void setApplicationLinister() {
        if (isRemoteServiceAlive()) {
            try {
                systemService.setApplicationLinister(iApplicationLinister);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private void removeApplicationLinister() {
        if (isRemoteServiceAlive()) {
            try {
                ReconnectSystemInterface.hasRegister_IApplicationLinister = false;
                systemService.removeApplicationLinister(iApplicationLinister);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }


    HashMap<String, AudioFocusLinister> audioFocusLinisterHashMap = new HashMap<>();

    public void setAudioFocusLinister(AudioFocusLinister linister) {
        L.i(Tag, "setAudioFocusLinister");
        ReconnectSystemInterface.hasRegister_IAudioFocusLinister = true;
        if (linister != null) {
            if (!audioFocusLinisterHashMap.containsKey(linister.toString())) {
                audioFocusLinisterHashMap.put(linister.toString(), linister);
                setAudioFocusLinister();
            }
        }
    }

    public void removeAudioFocusLinister(AudioFocusLinister linister) {
        L.i(Tag, "removeAudioFocusLinister");
        if (linister != null) {
            if (audioFocusLinisterHashMap.containsKey(linister.toString())) {
                audioFocusLinisterHashMap.remove(linister.toString());
                if (audioFocusLinisterHashMap.size() <= 0) {
                    removeAudioFocusLinister();
                }
            }
        }
    }

    IAudioFocusLinister.Stub iAudioFocusLinister = new IAudioFocusLinister.Stub() {
        @Override
        public void onChanged(String mainPkg, String mixPkg, int type, int precent) throws RemoteException {
            for (String key : audioFocusLinisterHashMap.keySet()) {
                if (audioFocusLinisterHashMap.get(key) != null) {
                    audioFocusLinisterHashMap.get(key).onChanged(mainPkg, mixPkg, type, precent);
                } else {
                    audioFocusLinisterHashMap.remove(key);
                    if (audioFocusLinisterHashMap.size() <= 0) {
                        removeAudioFocusLinister();
                    }
                }
            }
        }
    };

    private void setAudioFocusLinister() {
        if (isRemoteServiceAlive()) {
            try {
                systemService.setAudioFocusLinister(iAudioFocusLinister);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private void removeAudioFocusLinister() {
        if (isRemoteServiceAlive()) {
            try {
                systemService.removeAudioFocusLinister(iAudioFocusLinister);
                ReconnectSystemInterface.hasRegister_IAudioFocusLinister = false;
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }


    HashMap<String, BackCarLinister> backCarLinisterHashMap = new HashMap<>();

    public void setBackCarLinister(BackCarLinister linister) {
        ReconnectSystemInterface.hasRegister_IBackCarLinister = true;
        if (!backCarLinisterHashMap.containsKey(linister.toString())) {
            backCarLinisterHashMap.put(linister.toString(), linister);
            setBackCarLinister();
        }
    }

    public void removeBackCarLinister(BackCarLinister linister) {
        if (linister != null) {
            if (backCarLinisterHashMap.containsKey(linister.toString())) {
                backCarLinisterHashMap.remove(linister.toString());
                if (backCarLinisterHashMap.size() <= 0) {
                    removeBackCarLinister();
                }
            }
        }
    }

    IBackCarLinister.Stub iBackCarLinister = new IBackCarLinister.Stub() {
        @Override
        public void onBackCarLinister(boolean isbackcar) throws RemoteException {
            for (String key : backCarLinisterHashMap.keySet()) {
                if (backCarLinisterHashMap.get(key) != null) {
                    backCarLinisterHashMap.get(key).onBackCarChange(isbackcar);
                } else {
                    backCarLinisterHashMap.remove(key);
                    if (backCarLinisterHashMap.size() <= 0) {
                        removeBackCarLinister();
                    }
                }
            }
        }
    };

    private void setBackCarLinister() {
        if (isRemoteServiceAlive()) {
            try {
                systemService.setBackCarLinister(iBackCarLinister);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private void removeBackCarLinister() {
        if (isRemoteServiceAlive()) {
            try {
                ReconnectSystemInterface.hasRegister_IBackCarLinister = false;
                systemService.removeBackCarLinister(iBackCarLinister);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }


    IHeadsetLinister.Stub iHeadsetLinister = new IHeadsetLinister.Stub() {
        @Override
        public void onHeadsetLinister(int state) throws RemoteException {
            L.i(Tag, "onHeadsetLinister  state:" + state);
            for (String key : headsetLinisterHashMap.keySet()) {
                if (headsetLinisterHashMap.get(key) != null) {
                    headsetLinisterHashMap.get(key).onHeadsetLinister(state);
                } else {
                    headsetLinisterHashMap.remove(key);
                    if (headsetLinisterHashMap.size() <= 0) {
                        removeBackCarLinister();
                    }
                }
            }
        }
    };

    HashMap<String, HeadsetLinister> headsetLinisterHashMap = new HashMap<>();

    public void setHeadsetLinister(HeadsetLinister linister) {
        L.i(Tag, "setHeadsetLinister");
        ReconnectSystemInterface.hasRegister_IHeadSet = true;
        if (linister != null) {
            if (!headsetLinisterHashMap.containsKey(linister.toString())) {
                headsetLinisterHashMap.put(linister.toString(), linister);
                setHeadsetLinister();
            }
        }
    }

    public void removeHeadsetLinister(HeadsetLinister linister) {
        L.i(Tag, "removeHeadsetLinister");
        if (linister != null) {
            if (headsetLinisterHashMap.containsKey(linister.toString())) {
                headsetLinisterHashMap.remove(linister.toString());
                if (headsetLinisterHashMap.size() <= 0) {
                    removeHeadsetLinister();
                }
            }
        }
    }


    private void setHeadsetLinister() {
        if (isRemoteServiceAlive()) {
            try {
                systemService.setHeadsetLinister(iHeadsetLinister);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private void removeHeadsetLinister() {
        if (isRemoteServiceAlive()) {
            try {
                systemService.removeHeadsetLinister(iHeadsetLinister);
                ReconnectSystemInterface.hasRegister_IHeadSet = false;
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }


    private void reconnectCallback() {
        if (ReconnectSystemInterface.hasRegister_IBacklightSwitchLinister) {
            setBacklightSwitchLinister();
        }
        if (ReconnectSystemInterface.hasRegister_IBrightnessLinister) {
            setBrightnessLinister();
        }
        if (ReconnectSystemInterface.hasRegister_IKeyLinister) {
            setSystemKeyLinister();
        }
        if (ReconnectSystemInterface.hasRegister_IMcuUpgradeLinister) {
            setMcuUpgradeLinister();
        }
        if (ReconnectSystemInterface.hasRegister_IPassthroughDataLinister) {
            setPassthroughLinister();
        }
        if (ReconnectSystemInterface.hasRegister_IPowerStateLinister) {
            setPowerStateLinister();
        }
        if (ReconnectSystemInterface.hasRegister_IRadioLinister) {
            setRadioLinister();
        }
        if (ReconnectSystemInterface.hasRegister_ISoundfieldLinister) {
            setSoundfieldLinister();
        }
        if (ReconnectSystemInterface.hasRegister_ISystemEQLinister) {
            setSystemEQLinister();
        }
        if (ReconnectSystemInterface.hasRegister_ISystemMuteLinister) {
            setSystemMuteLinister();
        }
        if (ReconnectSystemInterface.hasRegister_IVolumeLinister) {
            setVolumeLinister();
        }
        if (ReconnectSystemInterface.hasRegister_ICarLightLinister) {
            setCarLightLinister();
        }
        if (ReconnectSystemInterface.hasRegister_IAuxStateLinister) {
            setAuxStateLinister();
        }
        if (ReconnectSystemInterface.hasRegister_IApplicationLinister) {
            setApplicationLinister();
        }
        if (ReconnectSystemInterface.hasRegister_IAudioFocusLinister) {
            setAudioFocusLinister();
        }
        if (ReconnectSystemInterface.hasRegister_IBackCarLinister) {
            setBackCarLinister();
        }
        if (ReconnectSystemInterface.hasRegister_IHeadSet) {
            setHeadsetLinister();
        }
    }

    public void setBinder(IBinder service) {
        if (service != null) {
            L.i(Tag, "setBinder");
            systemService = ISystemManager.Stub.asInterface(service);
            reconnectCallback();
        }
    }

    public void crashBinder() {
        systemService = null;
    }

}

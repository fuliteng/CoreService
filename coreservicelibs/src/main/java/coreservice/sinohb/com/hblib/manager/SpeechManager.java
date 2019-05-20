package coreservice.sinohb.com.hblib.manager;

import android.os.IBinder;
import android.os.RemoteException;

import com.sinohb.coreservice.bean.contact.ContactBean;
import com.sinohb.coreservice.manager.ISpeechManager;
import com.sinohb.coreservice.transport.speech.IAirControlLinister;
import com.sinohb.coreservice.transport.speech.IBlueToothLinister;
import com.sinohb.coreservice.transport.speech.IRadioControlLinister;
import com.sinohb.coreservice.transport.system.IApplicationLinister;

import java.util.HashMap;
import java.util.List;

import coreservice.sinohb.com.hblib.hbutils.L;
import coreservice.sinohb.com.hblib.hbutils.ReconnectSpeechInterface;
import coreservice.sinohb.com.hblib.interfaces.speech.AirControlLinister;
import coreservice.sinohb.com.hblib.interfaces.speech.BlueToothLinister;
import coreservice.sinohb.com.hblib.interfaces.speech.RadioControlLinister;
import coreservice.sinohb.com.hblib.interfaces.system.ApplicationLinister;

public class SpeechManager {
    public static String Tag = SpeechManager.class.getSimpleName();
    private static SpeechManager instance;
    private static ISpeechManager speechService;

    public static SpeechManager getInstance() {
        if (instance == null) {
            synchronized (SpeechManager.class) {
                if (instance == null) {
                    instance = new SpeechManager();
                }
            }
        }
        return instance;
    }

    private boolean isRemoteServiceAlive() {
        if (speechService != null) {
            return true;
        } else {
            L.i(Tag, "speechService is null");
            return false;
        }
    }


    /**
     * 拨号通知
     */
    public void reportBlueToothDialing(String name, String number) {
        L.i(Tag, "reportBlueToothDialing name:" + name + "  number:" + number);
        if (isRemoteServiceAlive()) {
            try {
                speechService.reportBlueToothDialing(name, number);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 上报蓝牙状态 state: -1 蓝牙已断开  1：蓝牙已连接  2.蓝牙音乐播放状态  3.蓝牙响铃状态 4.蓝牙拨号中 5.蓝牙通话状态
     */
    public void reportBlueToothState(int state) {
        L.i(Tag, "reportBlueToothState state:" + state);
        if (isRemoteServiceAlive()) {
            try {
                speechService.reportBlueToothState(state);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 通知来电
     * name:联系人名称  number：联系人号码
     */
    public void reporBlueToothPhoneComming(String name, String number) {
        L.i(Tag, "reporBlueToothPhoneComming name:" + name + "  number:" + number);
        if (isRemoteServiceAlive()) {
            try {
                speechService.reporBlueToothPhoneComming(name, number);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 上传联系人
     */
    public void uploadBlueToothContacts(List<ContactBean> lists) {
        L.i(Tag, "uploadBlueToothContacts ");
        if (isRemoteServiceAlive()) {
            try {
                speechService.uploadBlueToothContacts(lists);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private HashMap<String, BlueToothLinister> blueToothLinisterHashMap = new HashMap<>();

    private IBlueToothLinister.Stub iBlueToothLinister = new IBlueToothLinister.Stub() {
        @Override
        public void onBlueToothCall(String name, String number) throws RemoteException {
            for (String key : blueToothLinisterHashMap.keySet()) {
                if (blueToothLinisterHashMap.get(key) != null) {
                    blueToothLinisterHashMap.get(key).onBlueToothCall(name, number);
                } else {
                    blueToothLinisterHashMap.remove(key);
                    if (blueToothLinisterHashMap.size() <= 0) {
                        removeBlueToothLinister();
                    }
                }
            }
        }

        @Override
        public void onBlueToothHangUp() throws RemoteException {
            for (String key : blueToothLinisterHashMap.keySet()) {
                if (blueToothLinisterHashMap.get(key) != null) {
                    blueToothLinisterHashMap.get(key).onBlueToothHangUp();
                } else {
                    blueToothLinisterHashMap.remove(key);
                    if (blueToothLinisterHashMap.size() <= 0) {
                        removeBlueToothLinister();
                    }
                }
            }
        }

        @Override
        public void onBlueToothAnswerCall() throws RemoteException {
            for (String key : blueToothLinisterHashMap.keySet()) {
                if (blueToothLinisterHashMap.get(key) != null) {
                    blueToothLinisterHashMap.get(key).onBlueToothAnswerCall();
                } else {
                    blueToothLinisterHashMap.remove(key);
                    if (blueToothLinisterHashMap.size() <= 0) {
                        removeBlueToothLinister();
                    }
                }
            }
        }

        @Override
        public void onBlueToothRejectCall() throws RemoteException {
            for (String key : blueToothLinisterHashMap.keySet()) {
                if (blueToothLinisterHashMap.get(key) != null) {
                    blueToothLinisterHashMap.get(key).onBlueToothRejectCall();
                } else {
                    blueToothLinisterHashMap.remove(key);
                    if (blueToothLinisterHashMap.size() <= 0) {
                        removeBlueToothLinister();
                    }
                }
            }
        }

        @Override
        public void onRequestBlueToothState() throws RemoteException {
            for (String key : blueToothLinisterHashMap.keySet()) {
                if (blueToothLinisterHashMap.get(key) != null) {
                    blueToothLinisterHashMap.get(key).onRequestBlueToothState();
                } else {
                    blueToothLinisterHashMap.remove(key);
                    if (blueToothLinisterHashMap.size() <= 0) {
                        removeBlueToothLinister();
                    }
                }
            }
        }

        @Override
        public void onRequestBlueToothCntacts() throws RemoteException {
            for (String key : blueToothLinisterHashMap.keySet()) {
                if (blueToothLinisterHashMap.get(key) != null) {
                    blueToothLinisterHashMap.get(key).onRequestBlueToothCntacts();
                } else {
                    blueToothLinisterHashMap.remove(key);
                    if (blueToothLinisterHashMap.size() <= 0) {
                        removeBlueToothLinister();
                    }
                }
            }
        }
    };

    public void setBlueToothLinister(BlueToothLinister linister) {
        ReconnectSpeechInterface.hasRegister_IBlueToothLinister = true;
        if (linister != null) {
            if (!blueToothLinisterHashMap.containsKey(linister.toString())) {
                blueToothLinisterHashMap.put(linister.toString(), linister);
                setBlueToothLinister();
            }
        }
    }

    public void removeBlueToothLinister(BlueToothLinister linister) {
        if (linister != null) {
            if (blueToothLinisterHashMap.containsKey(linister.toString())) {
                blueToothLinisterHashMap.remove(linister.toString());
                if (blueToothLinisterHashMap.size() <= 0) {
                    removeBlueToothLinister();
                }
            }
        }
    }

    private void setBlueToothLinister() {
        L.i(Tag, "setBlueToothLinister ");
        if (isRemoteServiceAlive()) {
            try {
                speechService.setBlueToothLinister(iBlueToothLinister);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }


    private void removeBlueToothLinister() {
        L.i(Tag, "removeBlueToothLinister ");
        if (isRemoteServiceAlive()) {
            try {
                ReconnectSpeechInterface.hasRegister_IBlueToothLinister = false;
                speechService.removeBlueToothLinister(iBlueToothLinister);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }


    private HashMap<String, AirControlLinister> airControlLinisterHashMap = new HashMap<>();
    private IAirControlLinister.Stub iAirControlLinister = new IAirControlLinister.Stub() {
        @Override
        public void onAirSwitch(boolean open) throws RemoteException {
            for (String key : airControlLinisterHashMap.keySet()) {
                if (airControlLinisterHashMap.get(key) != null) {
                    airControlLinisterHashMap.get(key).onAirSwitch(open);
                } else {
                    airControlLinisterHashMap.remove(key);
                    if (airControlLinisterHashMap.size() <= 0) {
                        removeAirControlLinister();
                    }
                }
            }
        }

        @Override
        public void onAirBlowMode(int blowmode) throws RemoteException {
            for (String key : airControlLinisterHashMap.keySet()) {
                if (airControlLinisterHashMap.get(key) != null) {
                    airControlLinisterHashMap.get(key).onAirBlowMode(blowmode);
                } else {
                    airControlLinisterHashMap.remove(key);
                    if (airControlLinisterHashMap.size() <= 0) {
                        removeAirControlLinister();
                    }
                }
            }
        }

        @Override
        public void onAirAutoSwitch(boolean open) throws RemoteException {
            for (String key : airControlLinisterHashMap.keySet()) {
                if (airControlLinisterHashMap.get(key) != null) {
                    airControlLinisterHashMap.get(key).onAirAutoSwitch(open);
                } else {
                    airControlLinisterHashMap.remove(key);
                    if (airControlLinisterHashMap.size() <= 0) {
                        removeAirControlLinister();
                    }
                }
            }
        }

        @Override
        public void onAirBlowDegree(int degree) throws RemoteException {
            for (String key : airControlLinisterHashMap.keySet()) {
                if (airControlLinisterHashMap.get(key) != null) {
                    airControlLinisterHashMap.get(key).onAirBlowDegree(degree);
                } else {
                    airControlLinisterHashMap.remove(key);
                    if (airControlLinisterHashMap.size() <= 0) {
                        removeAirControlLinister();
                    }
                }
            }
        }

        @Override
        public void onAirBlowIncrease() throws RemoteException {
            for (String key : airControlLinisterHashMap.keySet()) {
                if (airControlLinisterHashMap.get(key) != null) {
                    airControlLinisterHashMap.get(key).onAirBlowIncrease();
                } else {
                    airControlLinisterHashMap.remove(key);
                    if (airControlLinisterHashMap.size() <= 0) {
                        removeAirControlLinister();
                    }
                }
            }
        }

        @Override
        public void onAirBlowDecrease() throws RemoteException {
            for (String key : airControlLinisterHashMap.keySet()) {
                if (airControlLinisterHashMap.get(key) != null) {
                    airControlLinisterHashMap.get(key).onAirBlowDecrease();
                } else {
                    airControlLinisterHashMap.remove(key);
                    if (airControlLinisterHashMap.size() <= 0) {
                        removeAirControlLinister();
                    }
                }
            }
        }

        @Override
        public void onAirBlowIncreaseDegree(int increase) throws RemoteException {
            for (String key : airControlLinisterHashMap.keySet()) {
                if (airControlLinisterHashMap.get(key) != null) {
                    airControlLinisterHashMap.get(key).onAirBlowIncreaseDegree(increase);
                } else {
                    airControlLinisterHashMap.remove(key);
                    if (airControlLinisterHashMap.size() <= 0) {
                        removeAirControlLinister();
                    }
                }
            }
        }

        @Override
        public void onAirBlowDecreaseDegree(int degrease) throws RemoteException {
            for (String key : airControlLinisterHashMap.keySet()) {
                if (airControlLinisterHashMap.get(key) != null) {
                    airControlLinisterHashMap.get(key).onAirBlowDecreaseDegree(degrease);
                } else {
                    airControlLinisterHashMap.remove(key);
                    if (airControlLinisterHashMap.size() <= 0) {
                        removeAirControlLinister();
                    }
                }
            }
        }

        @Override
        public void onAirSetTemperature(int degree) throws RemoteException {
            for (String key : airControlLinisterHashMap.keySet()) {
                if (airControlLinisterHashMap.get(key) != null) {
                    airControlLinisterHashMap.get(key).onAirSetTemperature(degree);
                } else {
                    airControlLinisterHashMap.remove(key);
                    if (airControlLinisterHashMap.size() <= 0) {
                        removeAirControlLinister();
                    }
                }
            }
        }

        @Override
        public void onAirTemperatureIncrease() throws RemoteException {
            for (String key : airControlLinisterHashMap.keySet()) {
                if (airControlLinisterHashMap.get(key) != null) {
                    airControlLinisterHashMap.get(key).onAirTemperatureIncrease();
                } else {
                    airControlLinisterHashMap.remove(key);
                    if (airControlLinisterHashMap.size() <= 0) {
                        removeAirControlLinister();
                    }
                }
            }
        }

        @Override
        public void onAirTemperatureDecrease() throws RemoteException {
            for (String key : airControlLinisterHashMap.keySet()) {
                if (airControlLinisterHashMap.get(key) != null) {
                    airControlLinisterHashMap.get(key).onAirTemperatureDecrease();
                } else {
                    airControlLinisterHashMap.remove(key);
                    if (airControlLinisterHashMap.size() <= 0) {
                        removeAirControlLinister();
                    }
                }
            }
        }

        @Override
        public void onAirTemperatureIncreaseDegree(int degree) throws RemoteException {
            for (String key : airControlLinisterHashMap.keySet()) {
                if (airControlLinisterHashMap.get(key) != null) {
                    airControlLinisterHashMap.get(key).onAirTemperatureIncreaseDegree(degree);
                } else {
                    airControlLinisterHashMap.remove(key);
                    if (airControlLinisterHashMap.size() <= 0) {
                        removeAirControlLinister();
                    }
                }
            }
        }

        @Override
        public void onAirTemperatureDecreaseDegree(int degree) throws RemoteException {
            for (String key : airControlLinisterHashMap.keySet()) {
                if (airControlLinisterHashMap.get(key) != null) {
                    airControlLinisterHashMap.get(key).onAirTemperatureDecreaseDegree(degree);
                } else {
                    airControlLinisterHashMap.remove(key);
                    if (airControlLinisterHashMap.size() <= 0) {
                        removeAirControlLinister();
                    }
                }
            }
        }

        @Override
        public void onAirTemperatureMax() throws RemoteException {
            for (String key : airControlLinisterHashMap.keySet()) {
                if (airControlLinisterHashMap.get(key) != null) {
                    airControlLinisterHashMap.get(key).onAirTemperatureMax();
                } else {
                    airControlLinisterHashMap.remove(key);
                    if (airControlLinisterHashMap.size() <= 0) {
                        removeAirControlLinister();
                    }
                }
            }
        }

        @Override
        public void onAirTemperatureMin() throws RemoteException {
            for (String key : airControlLinisterHashMap.keySet()) {
                if (airControlLinisterHashMap.get(key) != null) {
                    airControlLinisterHashMap.get(key).onAirTemperatureMin();
                } else {
                    airControlLinisterHashMap.remove(key);
                    if (airControlLinisterHashMap.size() <= 0) {
                        removeAirControlLinister();
                    }
                }
            }
        }

        @Override
        public void onAirInnerLooperSwitch(boolean open) throws RemoteException {
            for (String key : airControlLinisterHashMap.keySet()) {
                if (airControlLinisterHashMap.get(key) != null) {
                    airControlLinisterHashMap.get(key).onAirInnerLooperSwitch(open);
                } else {
                    airControlLinisterHashMap.remove(key);
                    if (airControlLinisterHashMap.size() <= 0) {
                        removeAirControlLinister();
                    }
                }
            }
        }

        @Override
        public void onAirOutterLooperSwitch(boolean open) throws RemoteException {
            for (String key : airControlLinisterHashMap.keySet()) {
                if (airControlLinisterHashMap.get(key) != null) {
                    airControlLinisterHashMap.get(key).onAirOutterLooperSwitch(open);
                } else {
                    airControlLinisterHashMap.remove(key);
                    if (airControlLinisterHashMap.size() <= 0) {
                        removeAirControlLinister();
                    }
                }
            }
        }

        @Override
        public void onAirACSwitch(boolean open) throws RemoteException {
            for (String key : airControlLinisterHashMap.keySet()) {
                if (airControlLinisterHashMap.get(key) != null) {
                    airControlLinisterHashMap.get(key).onAirACSwitch(open);
                } else {
                    airControlLinisterHashMap.remove(key);
                    if (airControlLinisterHashMap.size() <= 0) {
                        removeAirControlLinister();
                    }
                }
            }
        }
    };

    public void setAirControlLinister(AirControlLinister linister) {
        L.i(Tag, "setAirControlLinister ");
        ReconnectSpeechInterface.hasRegister_IAirControlLinister = true;
        if (linister != null) {
            if (!airControlLinisterHashMap.containsKey(linister.toString())) {
                airControlLinisterHashMap.put(linister.toString(), linister);
                setAirControlLinister();
            }
        }
    }

    public void removeAirControlLinister(AirControlLinister linister) {
        L.i(Tag, "removeAirControlLinister ");
        if (isRemoteServiceAlive()) {
            if (airControlLinisterHashMap.containsKey(linister.toString())) {
                airControlLinisterHashMap.remove(linister.toString());
                if (airControlLinisterHashMap.size() <= 0) {
                    removeAirControlLinister();
                }
            }
        }
    }


    private void setAirControlLinister() {
        L.i(Tag, "setAirControlLinister ");
        if (isRemoteServiceAlive()) {
            try {
                speechService.setAirControlLinister(iAirControlLinister);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private void removeAirControlLinister() {
        L.i(Tag, "removeAirControlLinister ");
        if (isRemoteServiceAlive()) {
            try {
                ReconnectSpeechInterface.hasRegister_IAirControlLinister = false;
                speechService.removeAirControlLinister(iAirControlLinister);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    HashMap<String, RadioControlLinister> radioControlLinisterHashMap = new HashMap<>();
    private IRadioControlLinister.Stub iRadioControlLinister = new IRadioControlLinister.Stub() {
        @Override
        public void onRadioOpenPlayList() throws RemoteException {
            for (String key : radioControlLinisterHashMap.keySet()) {
                if (radioControlLinisterHashMap.get(key) != null) {
                    radioControlLinisterHashMap.get(key).onRadioOpenPlayList();
                } else {
                    radioControlLinisterHashMap.remove(key);
                    if (radioControlLinisterHashMap.size() <= 0) {
                        removeRadioControlLinister();
                    }
                }
            }
        }

        @Override
        public void onRadioOpenFavoritList() throws RemoteException {
            for (String key : radioControlLinisterHashMap.keySet()) {
                if (radioControlLinisterHashMap.get(key) != null) {
                    radioControlLinisterHashMap.get(key).onRadioOpenFavoritList();
                } else {
                    radioControlLinisterHashMap.remove(key);
                    if (radioControlLinisterHashMap.size() <= 0) {
                        removeRadioControlLinister();
                    }
                }
            }
        }

        @Override
        public void onRadioUpTuning() throws RemoteException {
            for (String key : radioControlLinisterHashMap.keySet()) {
                if (radioControlLinisterHashMap.get(key) != null) {
                    radioControlLinisterHashMap.get(key).onRadioUpTuning();
                } else {
                    radioControlLinisterHashMap.remove(key);
                    if (radioControlLinisterHashMap.size() <= 0) {
                        removeRadioControlLinister();
                    }
                }
            }
        }

        @Override
        public void onRadioDownTuning() throws RemoteException {
            for (String key : radioControlLinisterHashMap.keySet()) {
                if (radioControlLinisterHashMap.get(key) != null) {
                    radioControlLinisterHashMap.get(key).onRadioDownTuning();
                } else {
                    radioControlLinisterHashMap.remove(key);
                    if (radioControlLinisterHashMap.size() <= 0) {
                        removeRadioControlLinister();
                    }
                }
            }
        }

        @Override
        public void onRadioSearching() throws RemoteException {
            for (String key : radioControlLinisterHashMap.keySet()) {
                if (radioControlLinisterHashMap.get(key) != null) {
                    radioControlLinisterHashMap.get(key).onRadioSearching();
                } else {
                    radioControlLinisterHashMap.remove(key);
                    if (radioControlLinisterHashMap.size() <= 0) {
                        removeRadioControlLinister();
                    }
                }
            }
        }

        @Override
        public void onRadioUpSearching() throws RemoteException {
            for (String key : radioControlLinisterHashMap.keySet()) {
                if (radioControlLinisterHashMap.get(key) != null) {
                    radioControlLinisterHashMap.get(key).onRadioUpSearching();
                } else {
                    radioControlLinisterHashMap.remove(key);
                    if (radioControlLinisterHashMap.size() <= 0) {
                        removeRadioControlLinister();
                    }
                }
            }
        }

        @Override
        public void onRadioDownSearching() throws RemoteException {
            for (String key : radioControlLinisterHashMap.keySet()) {
                if (radioControlLinisterHashMap.get(key) != null) {
                    radioControlLinisterHashMap.get(key).onRadioDownSearching();
                } else {
                    radioControlLinisterHashMap.remove(key);
                    if (radioControlLinisterHashMap.size() <= 0) {
                        removeRadioControlLinister();
                    }
                }
            }
        }

        @Override
        public void onRadioSaveCurrentFrequency() throws RemoteException {
            for (String key : radioControlLinisterHashMap.keySet()) {
                if (radioControlLinisterHashMap.get(key) != null) {
                    radioControlLinisterHashMap.get(key).onRadioOpenPlayList();
                } else {
                    radioControlLinisterHashMap.remove(key);
                    if (radioControlLinisterHashMap.size() <= 0) {
                        removeRadioControlLinister();
                    }
                }
            }
        }

        @Override
        public void onRadioSetFrequency(int type, float frequency) throws RemoteException {
            for (String key : radioControlLinisterHashMap.keySet()) {
                if (radioControlLinisterHashMap.get(key) != null) {
                    radioControlLinisterHashMap.get(key).onRadioSetFrequency(type, frequency);
                } else {
                    radioControlLinisterHashMap.remove(key);
                    if (radioControlLinisterHashMap.size() <= 0) {
                        removeRadioControlLinister();
                    }
                }
            }
        }

        @Override
        public void onRadioTruning(int type) throws RemoteException {
            for (String key : radioControlLinisterHashMap.keySet()) {
                if (radioControlLinisterHashMap.get(key) != null) {
                    radioControlLinisterHashMap.get(key).onRadioTruning(type);
                } else {
                    radioControlLinisterHashMap.remove(key);
                    if (radioControlLinisterHashMap.size() <= 0) {
                        removeRadioControlLinister();
                    }
                }
            }
        }
    };


    public void setRadioControlLinister(RadioControlLinister linister) {
        L.i(Tag, "setRadioControlLinister ");
        ReconnectSpeechInterface.hasRegister_IRadioControlLinister = true;
        if (linister != null) {
            if (!radioControlLinisterHashMap.containsKey(linister.toString())) {
                radioControlLinisterHashMap.put(linister.toString(), linister);
                setRadioControlLinister();
            }
        }
    }

    public void removeRadioControlLinister(RadioControlLinister linister) {
        L.i(Tag, "removeRadioControlLinister ");
        if (linister != null) {
            if (radioControlLinisterHashMap.containsKey(linister.toString())) {
                radioControlLinisterHashMap.remove(linister.toString());
                if (radioControlLinisterHashMap.size() <= 0) {
                    removeRadioControlLinister();
                }
            }
        }
    }

    private void setRadioControlLinister() {
        L.i(Tag, "setRadioControlLinister ");
        if (isRemoteServiceAlive()) {
            try {
                speechService.setRadioControlLinister(iRadioControlLinister);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private void removeRadioControlLinister() {
        L.i(Tag, "removeRadioControlLinister ");
        if (isRemoteServiceAlive()) {
            try {
                ReconnectSpeechInterface.hasRegister_IRadioControlLinister = false;
                speechService.removeRadioControlLinister(iRadioControlLinister);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private void reconnectCallback() {
        if (ReconnectSpeechInterface.hasRegister_IAirControlLinister) {
            setAirControlLinister();
        }
        if (ReconnectSpeechInterface.hasRegister_IBlueToothLinister) {
            setBlueToothLinister();
        }
        if (ReconnectSpeechInterface.hasRegister_IRadioControlLinister) {
            setRadioControlLinister();
        }
    }


    public void setBinder(IBinder service) {
        L.i(Tag, "setBinder");
        if (service != null) {
            speechService = ISpeechManager.Stub.asInterface(service);
            reconnectCallback();
        }
    }

    public void crashBinder(){
        speechService = null;
    }
}

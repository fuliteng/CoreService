package coreservice.sinohb.com.hblib.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

import com.sinohb.coreservice.service.IHBService;
import com.sinohb.coreservice.utils.RemoteServiceName;

import coreservice.sinohb.com.hblib.hbutils.L;
import coreservice.sinohb.com.hblib.interfaces.ServiceStateLinister;
import coreservice.sinohb.com.hblib.manager.McuManager;
import coreservice.sinohb.com.hblib.manager.SpeechManager;

public class HBServiceManager {

    private String Tag = HBServiceManager.class.getSimpleName();
    private static HBServiceManager instance;
    private Context context;
    private String callPackage = "";
    private IHBService service;

    private boolean SPEECH_PERMISSION = false;
    private boolean SYSTEM_PERMISSION = false;


    public static HBServiceManager getInstance() {
        if (instance == null) {
            synchronized (HBServiceManager.class) {
                if (instance == null) {
                    instance = new HBServiceManager();
                }
            }
        }
        return instance;
    }

    ServiceStateLinister serviceStateLinister = null;

    public void bindService(Context cxt, ServiceStateLinister linister) {
        if (cxt != null) {
            serviceStateLinister = linister;
            this.context = cxt;
            callPackage = context.getPackageName();
            if (callPackage != null) {
                McuManager.Tag = callPackage;
                SpeechManager.Tag = callPackage;
            }
            Intent intent = new Intent();
            intent.setAction("con.andorid.suinohb.coreservice");
            intent.setPackage("com.sinohb.coreservice");
            context.getApplicationContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);
        } else {
            L.i(Tag, "Service  binding failed because context is null");
        }
    }

    public void setSpeechPermission(boolean permission) {
        SPEECH_PERMISSION = true;
        connectSpeechService();
    }

    public void setSystemPermission(boolean permission) {
        SYSTEM_PERMISSION = true;
        connectSystemService();
    }

    private void connectSpeechService() {
        if (service != null) {
            if (SPEECH_PERMISSION) {
                try {
                    L.i(Tag, "connectSpeechService");
                    IBinder speech = service.getService(RemoteServiceName.HB_SPEECH_SERVICE);
                    if (speech != null) {
                        SpeechManager.getInstance().setBinder(speech);
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void connectSystemService() {
        if (service != null) {
            if (SYSTEM_PERMISSION) {
                try {
                    L.i(Tag, "connectSystemService");
                    IBinder system = service.getService(RemoteServiceName.HB_SYSTEM_SERVICE);
                    if (system != null) {
                        McuManager.getInstance().setBinder(system);
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            L.i(callPackage, "Service  Connected");
            service = IHBService.Stub.asInterface(iBinder);
            connectSystemService();
            connectSpeechService();
            if (serviceStateLinister != null) {
                serviceStateLinister.onServiceConnected();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            L.i(callPackage, "Service  Disconnected  maybe the remoute service has daid ");
            service = null;
            McuManager.getInstance().crashBinder();
            SpeechManager.getInstance().crashBinder();
            if (serviceStateLinister != null) {
                serviceStateLinister.onServiceDisconnected();
            }
        }
    };
}

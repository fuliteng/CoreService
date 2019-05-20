// IRadioLinister.aidl
package com.sinohb.coreservice.transport.system;

interface IRadioLinister {
     void onRadioReceiver(int groupID,int commandID,inout byte [] data);
}

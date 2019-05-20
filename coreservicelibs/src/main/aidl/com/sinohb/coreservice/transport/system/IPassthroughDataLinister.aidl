package com.sinohb.coreservice.transport.system;

interface IPassthroughDataLinister {

    void onPassthroughDataReceiver(int groupID,int commandID,inout byte [] data);
}

// ISlagCarDataLinstener.aidl
package com.sinohb.coreservice.transport.slagcar;

// Declare any non-default types here with import statements

interface ISlagCarDataLinstener {


       oneway    void  onReceiverSlagCarData(int msgId,in byte []  data);


}

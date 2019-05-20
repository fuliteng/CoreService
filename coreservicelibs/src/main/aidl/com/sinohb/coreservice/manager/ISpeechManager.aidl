// ISpeechManager.aidl
package com.sinohb.coreservice.manager;

import com.sinohb.coreservice.transport.speech.IBlueToothLinister;
import com.sinohb.coreservice.transport.speech.IAirControlLinister;
import com.sinohb.coreservice.transport.speech.IRadioControlLinister;
import com.sinohb.coreservice.bean.contact.ContactBean;

interface ISpeechManager {
   /**
   * 拨号通知
   */
   oneway void reportBlueToothDialing(String name,String number);
   /**
   * 上报蓝牙状态 state: -1 蓝牙已断开  1：蓝牙已连接  2.蓝牙音乐播放状态  3.蓝牙响铃状态 4.蓝牙拨号中 5.蓝牙通话状态
   */
   oneway void reportBlueToothState(int state);
   /**
   * 通知来电
   * name:联系人名称  number：联系人号码
   */
   oneway void reporBlueToothPhoneComming(String name ,String number);
   /**
   * 上传联系人
   */
   oneway void uploadBlueToothContacts(in List<ContactBean> lists);

   void setBlueToothLinister(in IBlueToothLinister linister);
   void removeBlueToothLinister(in IBlueToothLinister linister);

   void setAirControlLinister(in IAirControlLinister linister);
   void removeAirControlLinister(in IAirControlLinister linister);

   void setRadioControlLinister(in IRadioControlLinister linister);
   void removeRadioControlLinister(in IRadioControlLinister linister);
}

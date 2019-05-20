package com.sinohb.coreservice.transport.system;

interface IAudioFocusLinister {
    void onChanged(String mainPkg, String mixPkg, int type, int precent);
}

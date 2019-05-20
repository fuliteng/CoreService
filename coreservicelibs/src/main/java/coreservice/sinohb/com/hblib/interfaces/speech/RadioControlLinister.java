package coreservice.sinohb.com.hblib.interfaces.speech;

public interface RadioControlLinister {

    void onRadioOpenPlayList();
    void onRadioOpenFavoritList();
    void onRadioUpTuning();//向上微调
    void onRadioDownTuning();//向下微调
    void onRadioSearching();//搜台
    void onRadioUpSearching();//向上搜台
    void onRadioDownSearching();//向下搜台
    void onRadioSaveCurrentFrequency();//收藏当前电台
    void onRadioSetFrequency(int type, float frequency);//设置频率
    void onRadioTruning(int type);//FM/AM 切换
}

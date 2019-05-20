package coreservice.sinohb.com.hblib.hbutils;

import android.util.Log;

/**
 * Created by admin on 2018/4/3.
 */

public class L {
    public static final String Tag = "mcu";

    public static void i(String tag, String str)
    {
        Log.i(Tag, "--" + tag + "--" + str);
    }

    public static void d(String tag, String str)
    {
        Log.d(Tag, "--" + tag + "--" + str);
    }

    public static void e(String tag, String str)
    {
        Log.e(Tag, "--" + tag + "--" + str);
    }

    public static void v(String tag, String str)
    {
        Log.v(Tag, "--" + tag + "--" + str);
    }

    public static void w(String tag, String str)
    {
        Log.v(Tag, "--" + tag + "--" + str);
    }
}

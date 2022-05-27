package com.example.myapplication;

import android.os.Handler;

public class UpdateThread extends Thread
{
    Handler updHamdler;

    public UpdateThread(Handler uh)
    {
        super();
        updHamdler = uh;
    }

    public void run()
    {
        while (true)
        {
            try
            {
                sleep(32);
            } catch (Exception ex)
            {
            }
            updHamdler.sendEmptyMessage(0);
        }
    }
}

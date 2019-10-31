package com.volkangurbuz.stajtakip;

import android.os.Looper;
import android.support.annotation.MainThread;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.logging.Handler;

public class AppExecutors {

  // responsible productive the objects on retrofit requests

  private static AppExecutors instance;

  public static AppExecutors getInstance() {
    if (instance == null) {
      instance = new AppExecutors();
    }
    return instance;
  }

  private final Executor mDiskIO = Executors.newSingleThreadExecutor();

  private final Executor mMainThreadExecuter = new MainThreadExecuter();

  public Executor diskIO() {
    return mDiskIO;
  }

  public Executor mMainThread() {
    return mMainThreadExecuter;
  }

  public static class MainThreadExecuter implements Executor {
    private android.os.Handler mainThreadHandler = new android.os.Handler(Looper.getMainLooper());
    // going to post to the main thread

    @Override
    public void execute(Runnable command) {
      mainThreadHandler.post(command);
    }
  }
}

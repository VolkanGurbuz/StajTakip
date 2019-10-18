package com.volkangurbuz.stajtakip;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class AppExecutors {

  // responsible productive the objects on retrofit requests

  private static AppExecutors instance;

  public static AppExecutors getInstance() {
    if (instance == null) {
      instance = new AppExecutors();
    }
    return instance;
  }
  // suppose to regualr execular class, allows to extra functionality
  // that can schedule run after delay, execute runnable tasks
  private final ScheduledExecutorService mNetworkIO = Executors.newScheduledThreadPool(3);
  // TIMEOUT
  public ScheduledExecutorService networkIO() {

    return mNetworkIO;
  }
}

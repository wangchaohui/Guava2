package wch.guava2.base;

import com.google.common.base.Stopwatch;

import java.util.concurrent.TimeUnit;

/**
 * SlowWatch
 *
 * Created by ch.wang on 20 Mar 2014.
 */
public class SlowWatch {

  private final Stopwatch stopwatch;
  private final int timeUnit;
  private int timeOutCount = 0;

  public SlowWatch(int timeUnit) {
    this.timeUnit = timeUnit;
    stopwatch = Stopwatch.createStarted();
  }

  public boolean timeOut() {
    if (stopwatch.elapsed(TimeUnit.SECONDS) > (timeOutCount + 1) * timeUnit) {
      timeOutCount++;
      return true;
    }
    return false;
  }

  public int getTimeOutCount() {
    return timeOutCount;
  }

  public String time() {
    return stopwatch.toString();
  }
}

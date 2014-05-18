package wch.guava2.base;

import com.google.common.base.Stopwatch;

import java.util.concurrent.TimeUnit;

/**
 * timer
 *
 * Created by ch.wang on 18 Jan 2014.
 */
public class Timer {

  private final String name;
  private final Stopwatch stopwatch;

  public Timer(String name) {
    this.name = name;
    stopwatch = Stopwatch.createStarted();
  }

  private Timer(String name, Stopwatch stopwatch) {
    this.name = name;
    this.stopwatch = stopwatch;
  }

  public static Timer createUnstarted(String name) {
    Timer timer = new Timer(name, Stopwatch.createUnstarted());
    return timer;
  }

  public String time() {
    return name + " time: " + stopwatch.toString();
  }

  public void start() {
    stopwatch.start();
  }

  public void stop() {
    stopwatch.stop();
  }

  public long getMsTime() {
    return stopwatch.elapsed(TimeUnit.MILLISECONDS);
  }
}

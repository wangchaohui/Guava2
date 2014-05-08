package wch.guava2.base;

import java.util.concurrent.Callable;

public class Throwables2 {

  public static <T> T propagate(Callable<T> callable) {
    return propagate(callable, RuntimeException::new);
  }

  public static <T, E extends Throwable> T propagate(Callable<T> callable,
                                                     ExceptionWrapper<E> wrapper) throws E {
    try {
      return callable.call();
    } catch (RuntimeException e) {
      throw e;
    } catch (Exception e) {
      throw wrapper.wrap(e);
    }
  }

  public static <T> void propagate(Runnable runnable) {
    propagate(runnable, RuntimeException::new);
  }

  public static <T, E extends Throwable> void propagate(Runnable runnable,
                                                        ExceptionWrapper<E> wrapper) throws E {
    try {
      runnable.run();
    } catch (RuntimeException e) {
      throw e;
    } catch (Exception e) {
      throw wrapper.wrap(e);
    }
  }

  public static interface ExceptionWrapper<E> {

    E wrap(Exception e);
  }

  public static interface Runnable {

    void run() throws Exception;
  }
}
package wch.guava2.function;

import java.util.function.BinaryOperator;

/**
 * Operators
 *
 * Created by ch.wang on 10 May 2014.
 */
public class Operators {

  public static BinaryOperator<Boolean> or() {
    return (a, b) -> a || b;
  }
}

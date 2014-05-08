package wch.guava2.collectors;

import com.google.common.collect.ImmutableList;

import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Collectors2
 *
 * Created by ch.wang on 08 May 2014.
 */
public class Collectors2 {

  /**
   * Returns a {@code Collector} that accumulates the input elements into a new {@code
   * ImmutableList}.
   *
   * @param <T> the type of the input elements
   * @return a {@code Collector} which collects all the input elements into a {@code ImmutableList},
   * in encounter order
   */
  public static <T>
  Collector<T, ?, ImmutableList<T>> toImmutableList() {
    return Collectors.collectingAndThen(Collectors.toList(), ImmutableList::copyOf);
  }
}

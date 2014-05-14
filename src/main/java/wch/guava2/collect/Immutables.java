package wch.guava2.collect;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import java.util.Set;
import java.util.function.Consumer;

import wch.guava2.collectors.Collectors2;

/**
 * Immutables
 *
 * Created by ch.wang on 09 May 2014.
 */
public class Immutables {

  public static <K, V> ImmutableMap<K, V> createImmutableMap(
      Consumer<ImmutableMap.Builder<K, V>> consumer) {
    ImmutableMap.Builder<K, V> builder = ImmutableMap.builder();
    consumer.accept(builder);
    return builder.build();
  }

  public static <T> ImmutableList<T> sortedOf(Set<T> set) {
    return set.stream().sorted().collect(Collectors2.toImmutableList());
  }
}

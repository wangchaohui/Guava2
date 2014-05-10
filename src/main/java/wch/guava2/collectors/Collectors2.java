package wch.guava2.collectors;

import static com.google.common.base.Preconditions.*;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import wch.guava2.function.Operators;

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
  public static <T> Collector<T, ?, ImmutableList<T>> toImmutableList() {
    return Collectors.collectingAndThen(Collectors.toList(), ImmutableList::copyOf);
  }

  public static Collector<Boolean, ?, Boolean> orReducing() {
    return Collectors.reducing(false, Operators.or());
  }

  public static <T> Collector<T, ?, T> allEquals() {
    return new Collector<T, Container<T>, T>() {
      @Override
      public Supplier<Container<T>> supplier() {
        return Container::new;
      }

      @Override
      public BiConsumer<Container<T>, T> accumulator() {
        return (c, t) -> c.set(t);
      }

      @Override
      public BinaryOperator<Container<T>> combiner() {
        return (a, b) -> {
          a.combiner(b);
          return a;
        };
      }

      @Override
      public Function<Container<T>, T> finisher() {
        return Container::get;
      }

      @Override
      public Set<Characteristics> characteristics() {
        return ImmutableSet.of(Characteristics.UNORDERED);
      }
    };
  }

  private static class Container<T> {

    private boolean isPresent;
    private T element;

    public void set(T t) {
      if (!isPresent) {
        element = t;
        isPresent = true;
        return;
      }
      checkArgument(Objects.equals(element, t));
    }

    public void combiner(Container<T> o) {
      if (o.isPresent) {
        set(o.element);
      }
    }

    public T get() {
      checkState(isPresent);
      return element;
    }
  }
}

package wch.guava2.collect;

import static com.google.common.base.Preconditions.*;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.SetMultimap;
import com.google.common.collect.Sets;

import java.util.Collections;
import java.util.Set;

import javax.annotation.Nonnull;

/**
 * OneMany
 *
 * Created by ch.wang on 28 Apr 2014.
 */
public class ManyMany<K> {

  private final SetMultimap<K, K> in;
  private final SetMultimap<K, K> out;

  public ManyMany() {
    in = HashMultimap.create();
    out = HashMultimap.create();
  }

  public ManyMany(ManyMany<? extends K> manyMany) {
    in = HashMultimap.create(manyMany.in);
    out = HashMultimap.create(manyMany.out);
  }

  public void put(@Nonnull K key, @Nonnull K value) {
    checkNotNull(key);
    checkNotNull(value);
    in.put(value, key);
    out.put(key, value);
  }

  public InAndOut<K> removeAll(@Nonnull K item) {
    checkNotNull(item);
    Set<K> valueSet = out.removeAll(item);
    valueSet.forEach(v -> in.remove(v, item));
    Set<K> keySet = in.removeAll(item);
    keySet.forEach(k -> out.remove(k, item));
    return new InAndOut<>(keySet, valueSet);
  }

  public Set<K> getKeys(@Nonnull K value) {
    return Collections.unmodifiableSet(in.get(checkNotNull(value)));
  }

  public boolean containsKey(@Nonnull K key) {
    return out.containsKey(checkNotNull(key));
  }

  public boolean containsEntry(@Nonnull K key, @Nonnull K value) {
    return out.containsEntry(checkNotNull(key), checkNotNull(value));
  }

  public Set<K> getValues(@Nonnull K key) {
    return Collections.unmodifiableSet(out.get(checkNotNull(key)));
  }

  public Set<K> getKeySet() {
    return ImmutableSet.copyOf(out.keySet());
  }

  public Set<K> getSink() {
    return Sets.difference(in.keySet(), out.keySet());
  }

  public static class InAndOut<K> {

    final Set<K> inSet;
    final Set<K> outSet;

    public InAndOut(Set<K> inSet, Set<K> outSet) {
      this.inSet = inSet;
      this.outSet = outSet;
    }

    public Set<K> getInSet() {
      return inSet;
    }

    public Set<K> getOutSet() {
      return outSet;
    }
  }
}

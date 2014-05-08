package wch.guava2.collect;

import static com.google.common.base.Preconditions.*;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.common.collect.SetMultimap;

import java.util.Map;
import java.util.Set;

import javax.annotation.Nonnull;

/**
 * OneMany
 *
 * Created by ch.wang on 28 Apr 2014.
 */
public class OneMany<K, V> {

  private final Map<V, K> map;
  private final SetMultimap<K, V> member;

  public OneMany() {
    map = Maps.newHashMap();
    member = HashMultimap.create();
  }

  public OneMany(OneMany<? extends K, ? extends V> oneMany) {
    map = Maps.newHashMap(oneMany.map);
    member = HashMultimap.create(oneMany.member);
  }

  public void put(@Nonnull K key, @Nonnull V value) {
    checkNotNull(key);
    checkNotNull(value);
    checkArgument(!map.containsKey(value), "value already present: %s", value);
    map.put(value, key);
    member.put(key, value);
  }

  public boolean removeValue(@Nonnull V value) {
    checkNotNull(value);
    K key = map.remove(value);
    if (key != null) {
      member.remove(key, value);
      return true;
    }
    return false;
  }

  public K getKey(@Nonnull V value) {
    checkNotNull(value);
    return map.get(value);
  }

  public boolean containsKey(@Nonnull K key) {
    return member.containsKey(checkNotNull(key));
  }

  public boolean containsValue(@Nonnull V value) {
    return map.containsKey(checkNotNull(value));
  }

  public Set<V> getValues(@Nonnull K key) {
    checkNotNull(key);
    return ImmutableSet.copyOf(member.get(key));
  }

  public Set<K> getKeySet() {
    return ImmutableSet.copyOf(member.keySet());
  }
}

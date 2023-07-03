package fr.uge.slice;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public sealed interface Slice2<T> permits Slice2.ArraySlice {

  static <T> Slice2<T> array(T[] tab) {
    Objects.requireNonNull(tab);
    return new ArraySlice<>(tab);
  }

  int size();
  T get(int index);

  final class ArraySlice<E> implements Slice2<E> {
    private final int from;
    private final int to;
    private final E[] tab;

    ArraySlice(E[] tab) {
      from = 0;
      to = tab.length;
      this.tab = tab;
    }
    @Override
    public int size() {
      return to - from;
    }

    @Override
    public E get(int index) {
      if (index < from || index >= to) {
        throw new IndexOutOfBoundsException();
      }
      return tab[index];
    }

    @Override
    public String toString() {
      return Arrays.stream(tab)
              .map(e -> (e != null) ? e.toString() : "null")
              .collect(Collectors.joining(", ", "[", "]"));
    }
  }
}

package fr.uge.slice;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public sealed interface Slice2<T> permits Slice2.ArraySlice, Slice2.ArraySlice.SubArraySlice {

  static <T> Slice2<T> array(T[] tab) {
    Objects.requireNonNull(tab);
    return new ArraySlice<>(tab);
  }

  int size();

  T get(int index);
  Slice2<T> subSlice(int from, int to);

  static <T> Slice2<T> array(T[] tab, int from, int to) {
    Objects.requireNonNull(tab);
    if (from < 0 || (from > tab.length && from == to) || (from >= tab.length && from != to)) {
      throw new IndexOutOfBoundsException();
    }
    if (to > tab.length || to < 0 || from > to) {
      throw new IndexOutOfBoundsException();
    }
    //return new ArraySlice<>(tab).new SubArraySlice<>(tab, from, to);
    return new ArraySlice<>(tab).subSlice(from, to);
  }

  final class ArraySlice<E> implements Slice2<E> {
    private final int from;
    private final int to;
    private final E[] tab;

    final class SubArraySlice<F> implements Slice2<F> {
      private final F[] tab;
      private final int from;
      private final int to;

      SubArraySlice(F[] tab, int from, int to) {
        this.tab = tab;
        this.from = from;
        this.to = to;
      }

      @Override
      public int size() {
        return to - from;
      }

      @Override
      public F get(int index) {
        if (index + from < from || index + from >= to) {
          throw new IndexOutOfBoundsException();
        }
        return tab[index + from];
      }

      @Override
      public Slice2<F> subSlice(int from, int to) {
        /*if (from < 0 || from > this.size() && from == to || from >= this.size() && from != to || from > to || to > this.size()) {
          throw new IndexOutOfBoundsException();
        }*/
        Objects.checkFromToIndex(from, to, this.size());
        return new SubArraySlice<>(this.tab, this.from + from, this.from + to);
      }

      @Override
      public String toString() {
        return Arrays.stream(tab, from, to)
                .map(e -> (e != null) ? e.toString() : "null")
                .collect(Collectors.joining(", ", "[", "]"));
      }
    }

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
    public Slice2<E> subSlice(int from, int to) {
      if (from < 0 || (from > this.size() && from == to) || (from >= this.size() && from != to) || to < 0 || to > this.size()) {
        throw new IndexOutOfBoundsException();
      }
      return new SubArraySlice<>(tab, from, to);
    }

    @Override
    public String toString() {
      return Arrays.stream(tab)
              .map(e -> (e != null) ? e.toString() : "null")
              .collect(Collectors.joining(", ", "[", "]"));
    }
  }
}

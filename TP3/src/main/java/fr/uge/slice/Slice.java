package fr.uge.slice;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public sealed interface Slice<T> permits Slice.ArraySlice, Slice.SubArraySlice {

  static <T> Slice<T> array(T[] tab) {
    Objects.requireNonNull(tab);
    return new ArraySlice<>(tab);
  }

  static <T> Slice<T> array(T[] tab, int from, int to) {
    Objects.requireNonNull(tab);
    if (from < 0 || from > tab.length) {
      throw new IndexOutOfBoundsException();
    }
    if (to < 0 || to > tab.length) {
      throw new IndexOutOfBoundsException();
    }
    if (from > to) {
      throw new IndexOutOfBoundsException();
    }
    return new SubArraySlice<>(tab, from, to);
  }

  int size();
  T get(int index);

  Slice<T> subSlice(int from, int to);

  final class ArraySlice<E> implements Slice<E> {
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
    public Slice<E> subSlice(int from, int to) {
      if (from < this.from || from > size() || from > to) {
        throw new IndexOutOfBoundsException();
      }
      if (to > this.to || to > size()) {
        throw new IndexOutOfBoundsException();
      }
      return new SubArraySlice<>(this.tab, from, to);
    }

    @Override
    public String toString() {
      return Arrays.stream(tab)
        .map(e -> (e != null) ? e.toString() : "null")
        .collect(Collectors.joining(", ", "[", "]"));
    }
  }

  final class SubArraySlice<E> implements Slice<E> {

    private final E[] tab;
    private final int from;
    private final int to;

    SubArraySlice(E[] tab, int from, int to) {
      this.tab = tab;
      this.from = from;
      this.to = to;
    }

    @Override
    public int size() {
      return to - from;
    }

    @Override
    public E get(int index) {
      if (index + from < from || index + from >= to) {
        throw new IndexOutOfBoundsException();
      }
      return tab[index + from];
    }

    @Override
    public Slice<E> subSlice(int from, int to) {
      if (this.from + from < this.from || this.from + to > this.to || from > to) {
        throw new IndexOutOfBoundsException();
      }
      return new SubArraySlice<>(this.tab, this.from + from,this.from + to);
    }

    @Override
    public String toString() {
      return Arrays.stream(tab, from, to)
        .map(e -> (e != null) ? e.toString() : "null")
        .collect(Collectors.joining(", ", "[", "]"));
    }
  }
}

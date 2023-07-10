package fr.umlv.exam;

import java.util.Arrays;
import java.util.Objects;

public class Tab<T> {

  private int column;
  private int row;
  private T[] array;

  @SafeVarargs
  private Tab(T ... varargs) {
    array = varargs;
    row = 1;
    column = varargs.length;
  }

  @SafeVarargs
  private Tab(int row, int column, T ... varargs) {
    array = varargs;
    this.row = row;
    this.column = column;
  }

  @SafeVarargs
  public static <T> Tab<T> vector(T... varargs) {
    Objects.requireNonNull(varargs);
    for (var element : varargs) {
      Objects.requireNonNull(element);
    }
    return new Tab<>(varargs);
  }

  @Override
  public int hashCode() {
    return Arrays.hashCode(array) ^ Integer.hashCode(column) ^ Integer.hashCode(row);
  }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof Tab<?> tab && row == tab.row && column == tab.column && Arrays.equals(array, tab.array);
  }
}

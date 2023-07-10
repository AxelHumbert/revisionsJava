package fr.umlv.exam;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class Tab<T> {

  private final int column;
  private final int row;
  private final T[] array;

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
    if (varargs.length == 0) {
      throw new IllegalArgumentException();
    }
    for (var element : varargs) {
      Objects.requireNonNull(element);
    }
    return new Tab<>(varargs);
  }

  @SafeVarargs
  public static <T> Tab<T> matrix(int row, int column, T... varargs) {
    Objects.requireNonNull(varargs);
    if (row < 2 || column < 1 || varargs.length % row != 0) {
      throw new IllegalArgumentException();
    }
    if (varargs.length == 0) {
      throw new IllegalArgumentException();
    }
    for (var element : varargs) {
      Objects.requireNonNull(element);
    }
    return new Tab<>(row, column, varargs);
  }

  public int row() {
    return row;
  }

  public int column() {
    return column;
  }

  @Override
  public int hashCode() {
    return Arrays.hashCode(array) ^ Integer.hashCode(column) ^ Integer.hashCode(row);
  }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof Tab<?> tab && row == tab.row && column == tab.column && Arrays.equals(array, tab.array);
  }

  @Override
  public String toString() {
    if (row > 1) {
      var sb = new StringBuilder();
      var separator = "";
      var newLine = "\n";

      sb.append(row).append(", ").append(column);//.append("\n");
      for (var i = 0; i < row; i++) {
        sb.append(newLine);
        for (var j = 0; j < column; j++) {
          sb.append(separator).append(array[i + j * column]); // HERE
          separator = ", ";
        }
        separator = "";
      }
      return sb.toString();
    } else {
      return Arrays.stream(array).map(Object::toString).collect(Collectors.joining(", "));
    }
  }
}

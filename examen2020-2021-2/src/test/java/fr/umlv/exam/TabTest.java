package fr.umlv.exam;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class TabTest {
  // Q1

  @Test @Tag("Q1")
  public void vector() {
    Tab<Integer> vector1 = Tab.vector(1, 2, 3, 4);
    Tab<String> vector2 = Tab.vector("foo", "bar", "baz");
    Tab<Double> vector3 = Tab.vector(7.0, 4.2);
  }
  @Test @Tag("Q1")
  public void vectorEmpty() {
    assertThrows(IllegalArgumentException.class, Tab::vector);
  }
  @Test @Tag("Q1")
  public void vectorNull() {
    assertAll(
        () -> assertThrows(NullPointerException.class, () -> Tab.vector(1, 2, null, 4)),
        () -> assertThrows(NullPointerException.class, () -> Tab.vector(null, "bar", "baz")),
        () -> assertThrows(NullPointerException.class, () -> Tab.vector(7.0, 4.2, null))
    );
  }
  @Test @Tag("Q1")
  public void vectorEquals() {
    var vector1 = Tab.vector(1, 2, 3, 4);
    var vector2 = Tab.vector(1, 2, 3, 4);
    assertEquals(vector1, vector2);
  }
  @Test @Tag("Q1")
  public void vectorNotEquals() {
    var vector1 = Tab.vector("foo", "bar");
    var vector2 = Tab.vector(1, 2, 3, 4);
    assertNotEquals(vector1, vector2);
  }
  @Test @Tag("Q1")
  public void vectorNotEquals2() {
    var vector1 = Tab.vector("foo", "bar");
    var vector2 = Tab.vector(1, 2);
    assertNotEquals(vector1, vector2);
  }
  @Test @Tag("Q1")
  public void vectorEqualsNull() {
    var vector = Tab.vector("foo", "bar", "baz");
    assertFalse(vector.equals(null));
  }
  @Test @Tag("Q1")
  public void vectorHashCode() {
    var vector1 = Tab.vector(1, 2, 3, 4);
    var vector2 = Tab.vector(1, 2, 3, 4);
    assertEquals(vector1.hashCode(), vector2.hashCode());
  }
  @Test @Tag("Q1")
  public void vectorHashCodeTooManyCollisions() {
    var vector1 = Tab.vector(1);
    var vector2 = Tab.vector(2);
    assertNotEquals(vector1.hashCode(), vector2.hashCode());
  }
  @Test @Tag("Q1")
  public void vectorConstructor() {
    assertEquals(0, Tab.class.getConstructors().length);
  }
  @Test @Tag("Q1")
  public void vectorFields() {
    assertTrue(Arrays.stream(Tab.class.getDeclaredFields()).mapToInt(Field::getModifiers).allMatch(Modifier::isFinal));
  }
  

  // Q2

  /*
  @Test @Tag("Q2")
  public void testVectorToString() {
    var vector1 = Tab.vector(1, 2, 3, 4);
    var vector2 = Tab.vector("foo", "bar", "baz");
    var vector3 = Tab.vector(7.0, 4.2);
    var vector4 = Tab.vector('h');
    assertAll(
        () -> assertEquals("1, 2, 3, 4", vector1.toString()),
        () -> assertEquals("foo, bar, baz", vector2.toString()),
        () -> assertEquals("7.0, 4.2", vector3.toString()),
        () -> assertEquals("h", vector4.toString())
    );
  }


  // Q3

  @Test  @Tag("Q3")
  public void matrix() {
    Tab<Integer> matrix1 = Tab.matrix(2, 3, 1, 2, 3, 4, 5, 6);
    Tab<String> matrix2 = Tab.matrix(2, 1, "foo", "bar");
    Tab<Double> matrix3 = Tab.matrix(3, 3, 8.0, 4.5, 3.9, 2.2, -3.3, -4.9, 4.0, 4.0, -7.3);
  }
  @Test @Tag("Q1")
  public void matrixEmpty() {
    assertThrows(IllegalArgumentException.class, () -> Tab.matrix(1, 2));
  }
  @Test @Tag("Q3")
  public void matrixDimension() {
    assertAll(
        () -> assertThrows(IllegalArgumentException.class, () -> Tab.matrix(-1, 2)),
        () -> assertThrows(IllegalArgumentException.class, () -> Tab.matrix(2, -1)),
        () -> assertThrows(IllegalArgumentException.class, () -> Tab.matrix(-1, -2)),
        () -> assertThrows(IllegalArgumentException.class, () -> Tab.matrix(-2, -1)),
        () -> assertThrows(IllegalArgumentException.class, () -> Tab.matrix(0, 4)),
        () -> assertThrows(IllegalArgumentException.class, () -> Tab.matrix(4, 0)),
        () -> assertThrows(IllegalArgumentException.class, () -> Tab.matrix(1, 4, "1", "2", "3", "4")),
        () -> assertThrows(IllegalArgumentException.class, () -> Tab.matrix(2, 2, '1', '2', '3'))
    );
  }
  @Test @Tag("Q3")
  public void matrixNull() {
    assertAll(
        () -> assertThrows(NullPointerException.class, () -> Tab.matrix(2, 2, 1, 2, null, 4)),
        () -> assertThrows(NullPointerException.class, () -> Tab.vector(2, 1, null, "bar")),
        () -> assertThrows(NullPointerException.class, () -> Tab.vector(2, 1, null, 7.0))
    );
  }
  @Test @Tag("Q3")
  public void matrixEquals() {
    var matrix1 = Tab.matrix(2, 3, 1, 2, 3, 4, 5, 6);
    var matrix2 = Tab.matrix(2, 3, 1, 2, 3, 4, 5, 6);
    assertEquals(matrix1, matrix2);
  }
  @Test @Tag("Q3")
  public void matrixNotEquals() {
    var matrix1 = Tab.matrix(2, 1, "foo", "bar");
    var matrix2 = Tab.matrix(2, 2, 1, 2, 3, 4);
    assertNotEquals(matrix1, matrix2);
  }
  @Test @Tag("Q3")
  public void matrixNotEquals2() {
    var matrix1 = Tab.matrix(2, 1, "foo", "bar");
    var matrix2 = Tab.matrix(2, 1, 1, 2);
    assertNotEquals(matrix1, matrix2);
  }
  @Test @Tag("Q3")
  public void matrixEqualsNull() {
    var matrix = Tab.matrix(2, 1, "foo", "bar");
    assertFalse(matrix.equals(null));
  }
  @Test @Tag("Q3")
  public void matrixHashCode() {
    var matrix1 = Tab.matrix(2, 3, 1, 2, 3, 4, 5, 6);
    var matrix2 = Tab.matrix(2, 3, 1, 2, 3, 4, 5, 6);
    assertEquals(matrix1.hashCode(), matrix2.hashCode());
  }
  @Test @Tag("Q3")
  public void matrixHashCodeTooManyCollisions() {
    var matrix1 = Tab.matrix(2, 2, 0, 0, 0, 0);
    var matrix2 = Tab.vector(2, 2, 0, 0, 0, 1);
    assertNotEquals(matrix1.hashCode(), matrix2.hashCode());
  }
  @Test  @Tag("Q3")
  public void vectorRow() {
    var vector1 = Tab.vector(1, 2, 3, 4);
    var vector2 = Tab.vector("foo", "bar", "baz");
    var vector3 = Tab.vector(7.0, 4.2);
    assertAll(
        () -> assertEquals(1, vector1.row()),
        () -> assertEquals(1, vector2.row()),
        () -> assertEquals(1, vector3.row())
    );
  }
  @Test  @Tag("Q3")
  public void vectorColumn() {
    var vector1 = Tab.vector(1, 2, 3, 4);
    var vector2 = Tab.vector("foo", "bar", "baz");
    var vector3 = Tab.vector(7.0, 4.2);
    assertAll(
        () -> assertEquals(4, vector1.column()),
        () -> assertEquals(3, vector2.column()),
        () -> assertEquals(2, vector3.column())
    );
  }
  @Test  @Tag("Q3")
  public void matrixRow() {
    var matrix1 = Tab.matrix(2, 3, 1, 2, 3, 4, 5, 6);
    var matrix2 = Tab.matrix(2, 1, "foo", "bar");
    var matrix3 = Tab.matrix(3, 3, 8.0, 4.5, 3.9, 2.2, -3.3, -4.9, 4.0, 4.0, -7.3);
    var matrix4 = Tab.matrix(4, 1, '0', '1', '2', '4');
    assertAll(
        () -> assertEquals(2, matrix1.row()),
        () -> assertEquals(2, matrix2.row()),
        () -> assertEquals(3, matrix3.row()),
        () -> assertEquals(4, matrix4.row())
    );
  }
  @Test  @Tag("Q3")
  public void matrixColumn() {
    var matrix1 = Tab.matrix(2, 3, 1, 2, 3, 4, 5, 6);
    var matrix2 = Tab.matrix(2, 1, "foo", "bar");
    var matrix3 = Tab.matrix(3, 3, 8.0, 4.5, 3.9, 2.2, -3.3, -4.9, 4.0, 4.0, -7.3);
    var matrix4 = Tab.matrix(4, 1, '0', '1', '2', '4');
    assertAll(
        () -> assertEquals(3, matrix1.column()),
        () -> assertEquals(1, matrix2.column()),
        () -> assertEquals(3, matrix3.column()),
        () -> assertEquals(1, matrix4.column())
    );
  }


  // Q4

  @Test @Tag("Q4")
  public void testMatrixToString() {
    var matrix1 = Tab.matrix(2, 3, 1, 2, 3, 4, 5, 6);
    var matrix2 = Tab.matrix(2, 1, "foo", "bar");
    var matrix3 = Tab.matrix(3, 3, 8.0, 4.5, 3.9, 2.2, -3.3, -4.9, 4.0, 4.0, -7.3);
    var matrix4 = Tab.matrix(4, 1, '0', '1', '2', '4');
    assertAll(
        () -> assertEquals("2, 3\n1, 2, 3\n4, 5, 6", matrix1.toString()),
        () -> assertEquals("2, 1\nfoo\nbar", matrix2.toString()),
        () -> assertEquals("3, 3\n8.0, 4.5, 3.9\n2.2, -3.3, -4.9\n4.0, 4.0, -7.3", matrix3.toString()),
        () -> assertEquals("4, 1\n0\n1\n2\n4", matrix4.toString())
    );
  }


  @Test  @Tag("Q5")
  public void applyVectorIntegersSum() {
    var vector1 = Tab.vector(1, 2, 3, 4, 5, 6);
    var vector2 = Tab.vector(6, 5, 4, 3, 2, 1);
    var expected = Tab.vector(7, 7, 7, 7, 7, 7);
    assertEquals(expected, vector1.apply(Integer::sum, vector2));
  }
  @Test  @Tag("Q5")
  public void applyVectorStringsConcat() {
    var vector1 = Tab.vector("foo", "bar");
    var vector2 = Tab.vector("baz", "whizz");
    var expected = Tab.vector("foobaz", "barwhizz");
    assertEquals(expected, vector1.apply(String::concat, vector2));
  }
  @Test  @Tag("Q5")
  public void applyVectorStringsContains() {
    var vector1 = Tab.vector("foobar", "bar", "foo", "whizz");
    var vector2 = Tab.vector("foo", "whizz", "foobar", "bar");
    var expected = Tab.vector(true, false, false, false);
    assertEquals(expected, vector1.apply(String::contains, vector2));
  }
  @Test  @Tag("Q5")
  public void applyVectorStringsIndexOf() {
    var vector1 = Tab.vector("foobar", "bar", "foo", "whizz");
    var vector2 = Tab.vector('f', 'z', 'o', 'z');
    var expected = Tab.vector(0, -1, 1, 3);
    assertEquals(expected, vector1.apply(String::indexOf, vector2));
  }
  @Test  @Tag("Q5")
  public void applyVectorNotSameDimensions() {
    var vector1 = Tab.vector("foobar", "bar", "foo", "whizz");
    var vector2 = Tab.vector("foo", "zoo");
    assertThrows(IllegalArgumentException.class, () -> vector1.apply(String::concat, vector2));
  }
  @Test  @Tag("Q5")
  public void applyMatrixIntegersSum() {
    var matrix1 = Tab.matrix(2, 3, 1, 2, 3, 4, 5, 6);
    var matrix2 = Tab.matrix(2, 3, 6, 5, 4, 3, 2, 1);
    var expected = Tab.matrix(2, 3, 7, 7, 7, 7, 7, 7);
    assertEquals(expected, matrix1.apply(Integer::sum, matrix2));
  }
  @Test  @Tag("Q5")
  public void applyMatrixStringsConcat() {
    var matrix1 = Tab.matrix(2, 1, "foo", "bar");
    var matrix2 = Tab.matrix(2, 1, "baz", "whizz");
    var expected = Tab.matrix(2, 1, "foobaz", "barwhizz");
    assertEquals(expected, matrix1.apply(String::concat, matrix2));
  }
  @Test  @Tag("Q5")
  public void applyMatrixStringsContains() {
    var matrix1 = Tab.matrix(2, 2, "foobar", "bar", "foo", "whizz");
    var matrix2 = Tab.matrix(2, 2, "foo", "whizz", "foobar", "bar");
    var expected = Tab.matrix(2, 2, true, false, false, false);
    assertEquals(expected, matrix1.apply(String::contains, matrix2));
  }
  @Test  @Tag("Q5")
  public void applyMatrixStringsIndexOf() {
    var matrix1 = Tab.matrix(2, 2, "foobar", "bar", "foo", "whizz");
    var matrix2 = Tab.matrix(2, 2, 'f', 'z', 'o', 'z');
    var expected = Tab.matrix(2, 2, 0, -1, 1, 3);
    assertEquals(expected, matrix1.apply(String::indexOf, matrix2));
  }
  @Test  @Tag("Q5")
  public void applyVectorNull() {
    var vector = Tab.vector("foo", "bar", "baz", "whizz");
    assertAll(
        () -> assertThrows(NullPointerException.class, () -> vector.apply(null, vector)),
        () -> assertThrows(NullPointerException.class, () -> vector.apply(String::concat, null))
    );
  }
  @Test  @Tag("Q5")
  public void applyMatrixNull() {
    var matrix = Tab.matrix(2, 2, "foo", "bar", "baz", "whizz");
    assertAll(
        () -> assertThrows(NullPointerException.class, () -> matrix.apply(null, matrix)),
        () -> assertThrows(NullPointerException.class, () -> matrix.apply(String::concat, null))
    );
  }
  @Test  @Tag("Q5")
  public void applyMatrixNotSameDimensions() {
    var matrix1 = Tab.matrix(2, 2, "foobar", "bar", "foo", "whizz");
    var matrix2 = Tab.matrix(2, 1, "foo", "zoo");
    assertThrows(IllegalArgumentException.class, () -> matrix1.apply(String::concat, matrix2));
  }
  @Test  @Tag("Q5")
  public void applyNotSameDimensions() {
    var vector = Tab.vector("foobar", "bar", "foo", "whizz");
    var matrix = Tab.matrix(2, 1, "foo", "zoo");
    assertThrows(IllegalArgumentException.class, () -> vector.apply(String::concat, matrix));
  }


  // --- Q6

  @Test  @Tag("Q6")
  public void forEachVector() {
    var vector = Tab.vector("foobar", "bar", "foo", "whizz");
    var list = new ArrayList<String>();
    vector.forEach((row, column, element) -> list.add(element));
    assertEquals(List.of("foobar", "bar", "foo", "whizz"), list);
  }
  @Test  @Tag("Q6")
  public void forEachMatrix() {
    var matrix = Tab.matrix(2, 3, 1, 2, 3, 4, 5, 6);
    var list = new ArrayList<Integer>();
    matrix.forEach((row, column, element) -> list.add(element));
    assertEquals(List.of(1, 2, 3, 4, 5, 6), list);
  }
  @Test  @Tag("Q6")
  public void forEachMatrixRow() {
    var matrix = Tab.matrix(3, 2, "foobar", "bar", "foo", "whizz", "booh", "grasp");
    var rows = new ArrayList<Integer>();
    matrix.forEach((row, column, element) -> rows.add(row));
    assertEquals(List.of(0, 0, 1, 1, 2, 2), rows);
  }
  @Test  @Tag("Q6")
  public void forEachMatrixColumn() {
    var matrix = Tab.matrix(3, 2, "foobar", "bar", "foo", "whizz", "booh", "grasp");
    var columns = new ArrayList<Integer>();
    matrix.forEach((row, column, element) -> columns.add(column));
    assertEquals(List.of(0, 1, 0, 1, 0, 1), columns);
  }
  @Test  @Tag("Q6")
  public void forEachNull() {
    var matrix = Tab.vector("foobar");
    assertThrows(NullPointerException.class, () -> matrix.forEach(null));
  }
  @Test  @Tag("Q6")
  public void forEachSignature() {
    var matrix = Tab.vector("foobar");
    matrix.forEach((int row, int column, Object element) -> assertEquals("foobar", element));
  }


  @Test  @Tag("Q7")
  public void rowIteratorVector() {
    var vector = Tab.vector("foobar", "bar", "foo", "whizz");
    var it = vector.rowIterator(0);
    var list = new ArrayList<String>();
    it.forEachRemaining(list::add);
    assertEquals(List.of("foobar", "bar", "foo", "whizz"), list);
  }
  @Test  @Tag("Q7")
  public void rowIteratorMatrix() {
    var matrix = Tab.matrix(2, 3, 1, 2, 3, 4, 5, 6);
    var it = matrix.rowIterator(1);
    var list = new ArrayList<Integer>();
    it.forEachRemaining(list::add);
    assertEquals(List.of(4, 5, 6), list);
  }
  @Test  @Tag("Q7")
  public void rowIteratorVectorALotElements() {
    var vector = Tab.vector(IntStream.range(0, 10_000).boxed().toArray(Integer[]::new));
    var it = vector.rowIterator(0);
    for(var i = 0; i < 10_000; i++) {
      assertTrue(it.hasNext());
      assertTrue(it.hasNext());
      assertEquals(i, it.next());
    }
    assertFalse(it.hasNext());
  }
  @Test  @Tag("Q7")
  public void rowIteratorOutOfRangeVector() {
    var vector = Tab.vector("foobar", "bar", "foo", "whizz");
    assertAll(
        () -> assertThrows(IndexOutOfBoundsException.class, () -> vector.rowIterator(-1)),
        () -> assertThrows(IndexOutOfBoundsException.class, () -> vector.rowIterator(1))
    );
  }
  @Test  @Tag("Q7")
  public void rowIteratorOutOfRangeMatrix() {
    var matrix = Tab.matrix(2, 3, 1, 2, 3, 4, 5, 6);
    assertAll(
        () -> assertThrows(IndexOutOfBoundsException.class, () -> matrix.rowIterator(-1)),
        () -> assertThrows(IndexOutOfBoundsException.class, () -> matrix.rowIterator(3))
    );
  }
  @Test  @Tag("Q7")
  public void rowIteratorRemove() {
    var matrix = Tab.matrix(4, 1, "foobar", "bar", "foo", "whizz");
    var it = matrix.rowIterator(2);
    it.next();
    assertThrows(UnsupportedOperationException.class, it::remove);
  }


  // Q8

  @Test  @Tag("Q8")
  public void reshapeVectorSmaller() {
    Tab<Integer> vector1 = Tab.vector(1, 6, 4, 3, 9);
    Tab<Integer> vector2 = vector1.reshape(1, 3);
    assertEquals(Tab.vector(1, 6, 4), vector2);
  }
  @Test  @Tag("Q8")
  public void reshapeVectorBigger() {
    var vector1 = Tab.vector(1, 6, 4);
    var vector2 = vector1.reshape(1, 5);
    assertEquals(Tab.vector(1, 6, 4, 1, 6), vector2);
  }
  @Test  @Tag("Q8")
  public void reshapeVectorBigger2() {
    var vector1 = Tab.vector(1, 6, 4);
    var vector2 = vector1.reshape(1, 10);
    assertEquals(Tab.vector(1, 6, 4, 1, 6, 4, 1, 6, 4, 1), vector2);
  }
  @Test  @Tag("Q8")
  public void reshapeMatrixSmaller() {
    Tab<String> matrix1 = Tab.matrix(2, 5, "a", "b", "c", "d", "e", "f", "g", "h", "i", "j");
    Tab<String> matrix2 = matrix1.reshape(3, 2);
    assertEquals(Tab.matrix(3, 2, "a", "b", "c", "d", "e", "f"), matrix2);
  }
  @Test  @Tag("Q8")
  public void reshapeMatrixBigger() {
    var matrix1 = Tab.matrix(3, 2, "a", "b", "c", "d", "e", "f");
    var matrix2 = matrix1.reshape(2, 4);
    assertEquals(Tab.matrix(2, 4, "a", "b", "c", "d", "e", "f", "a", "b"), matrix2);
  }
  @Test  @Tag("Q8")
  public void reshapeMatrixBigger2() {
    var matrix1 = Tab.matrix(2, 2, '0', '1', '2', '3');
    var matrix2 = matrix1.reshape(2, 5);
    assertEquals(Tab.matrix(2, 5, '0', '1', '2', '3', '0', '1', '2', '3', '0', '1'), matrix2);
  }
  @Test  @Tag("Q8")
  public void reshapeMatrixToVector() {
    var matrix = Tab.matrix(2, 2, "foo", "bar", "baz", "whizz");
    var vector = matrix.reshape(1, 4);
    assertEquals(Tab.vector("foo", "bar", "baz", "whizz"), vector);
  }
  @Test  @Tag("Q8")
  public void reshapeVectorToMatrix() {
    var vector = Tab.vector("foo", "bar", "baz", "whizz");
    var matrix = vector.reshape(3, 2);
    assertEquals(Tab.matrix(3, 2, "foo", "bar", "baz", "whizz", "foo", "bar"), matrix);
  }
  @Test  @Tag("Q8")
  public void reshapeOutOfRange() {
    var vector = Tab.vector("foobar", "bar", "foo", "whizz");
    assertAll(
        () -> assertThrows(IndexOutOfBoundsException.class, () -> vector.reshape(-1, 3)),
        () -> assertThrows(IndexOutOfBoundsException.class, () -> vector.reshape(3, -1)),
        () -> assertThrows(IndexOutOfBoundsException.class, () -> vector.reshape(0, 3)),
        () -> assertThrows(IndexOutOfBoundsException.class, () -> vector.reshape(3, 0))
    );
  }


  // Q9

  @Test  @Tag("Q9")
  public void rowListVector() {
    Tab<String> vector = Tab.vector("foobar", "bar", "foo", "whizz");
    List<String> rowList = vector.rowList(0);
    assertEquals(List.of("foobar", "bar", "foo", "whizz"), rowList);
  }
  @Test  @Tag("Q9")
  public void rowListVectorForLoop() {
    var vector = Tab.vector(1, 2, 5, 6, 7);
    var rowList = vector.rowList(0);
    var sum = 0;
    for (var integer : rowList) {
      sum += integer;
    }
    assertEquals(21, sum);
  }
  @Test  @Tag("Q9")
  public void rowListVectorIterator() {
    var vector = Tab.vector(1, 2, 5, 6, 7);
    var rowList = vector.rowList(0);
    var newList = new ArrayList<Integer>();
    rowList.iterator().forEachRemaining(newList::add);
    assertEquals(List.of(1, 2, 5, 6, 7), newList);
  }
  @Test  @Tag("Q9")
  public void rowListMatrix() {
    Tab<Character> matrix = Tab.matrix(3, 2, 'a', 'b', 'c', 'd', 'e', 'f');
    List<Character> rowList = matrix.rowList(1);
    assertEquals(List.of('c', 'd'), rowList);
  }
  @Test  @Tag("Q9")
  public void rowListMatrixSizeAndGet() {
    var matrix = Tab.matrix(3, 2, 0, 1, 2, 3, 4, 5);
    var rowList = matrix.rowList(2);
    var sum = 0;
    for(var i = 0; i <rowList.size(); i++) {
      sum += rowList.get(i);
    }
    assertEquals(9, sum);
  }
  @Test  @Tag("Q9")
  public void rowListMatrixIterator() {
    var matrix = Tab.matrix(3, 2, 'a', 'b', 'c', 'd', 'e', 'f');
    var rowList = matrix.rowList(2);
    var newList = new ArrayList<Character>();
    rowList.iterator().forEachRemaining(newList::add);
    assertEquals(List.of('e', 'f'), newList);
  }
  @Test  @Tag("Q9")
  public void rowListVectorGetOutOfBounds() {
    var vector = Tab.vector(1, 2, 5, 6, 7);
    var rowList = vector.rowList(0);
    assertAll(
        () -> assertThrows(IndexOutOfBoundsException.class, () -> rowList.get(-1)),
        () -> assertThrows(IndexOutOfBoundsException.class, () -> rowList.get(5))
    );
  }
  @Test  @Tag("Q9")
  public void rowListMatrixGetOutOfBounds() {
    var matrix = Tab.matrix(3, 2, 'a', 'b', 'c', 'd', 'e', 'f');
    var rowList = matrix.rowList(2);
    assertAll(
        () -> assertThrows(IndexOutOfBoundsException.class, () -> rowList.get(-1)),
        () -> assertThrows(IndexOutOfBoundsException.class, () -> rowList.get(2))
    );
  }
  @Test  @Tag("Q9")
  public void rowListVectorNotMutable() {
    var vector = Tab.vector(1, 2, 5, 6, 7);
    var rowList = vector.rowList(0);
    assertThrows(UnsupportedOperationException.class, () -> rowList.add(999));
  }
  @Test  @Tag("Q9")
  public void rowListMatrixNotMutable() {
    var matrix = Tab.matrix(3, 2, 'a', 'b', 'c', 'd', 'e', 'f');
    var rowList = matrix.rowList(2);
    assertThrows(UnsupportedOperationException.class, () -> rowList.add('z'));
  }*/
}
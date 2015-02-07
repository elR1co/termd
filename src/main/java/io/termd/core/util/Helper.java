package io.termd.core.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

/**
 * Various utils.
 *
 * @author <a href="mailto:julien@julienviet.com">Julien Viet</a>
 */
public class Helper {

  /**
   * Do absolutely nothing. This can be useful for code coverage analysis.
   */
  public static void noop() {}

  /**
   * Convert the string to an array of code points.
   *
   * @param s the string to convert
   * @return the code points
   */
  public static int[] toCodePoints(String s) {
    int codePointLength = 0;
    for (int offset = 0;offset < s.length();) {
      int codePoint = s.codePointAt(offset);
      codePointLength++;
      offset += Character.charCount(codePoint);
    }
    int[] codePoints = new int[codePointLength];
    int index = 0;
    for (int offset = 0;offset < s.length();) {
      int codePoint = s.codePointAt(offset);
      offset += Character.charCount(codePoint);
      codePoints[index++] = codePoint;
    }
    return codePoints;
  }

  /**
   * Code point to string conversion.
   *
   * @param codePoints the code points
   * @return the corresponding string
   */
  public static String fromCodePoints(int[] codePoints) {
    StringBuilder buffer = new StringBuilder();
    appendTo(codePoints, buffer);
    return buffer.toString();
  }

  public static void appendTo(int[] codePoints, StringBuilder sb) {
    for (int codePoint : codePoints) {
      sb.appendCodePoint(codePoint);
    }
  }

  public static <S> List<S> loadServices(ClassLoader loader, Class<S> serviceClass) {
    ArrayList<S> services = new ArrayList<>();
    Iterator<S> i = ServiceLoader.load(serviceClass, loader).iterator();
    while (i.hasNext()) {
      try {
        S service = i.next();
        services.add(service);
      } catch (Exception ignore) {
        // Log me
      }
    }
    return services;
  }

  public static List<Integer> list(int... list) {
    ArrayList<Integer> result = new ArrayList<>(list.length);
    for (int i : list) {
      result.add(i);
    }
    return result;
  }

  public static List<String> split(String s, char c) {
    List<String> ret = new ArrayList<>();
    int prev = 0;
    while (true) {
      int pos = s.indexOf('\n', prev);
      if (pos == -1) {
        break;
      }
      ret.add(s.substring(prev, pos));
      prev = pos + 1;
    }
    ret.add(s.substring(prev));
    return ret;
  }
}

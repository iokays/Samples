package onjava8.lowlevel;// lowlevel/UnsafeReturn.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.

public class UnsafeReturn extends IntTestable {
  private int i = 0;
  public int getAsInt() { return i; }
  @Override
  public synchronized void evenIncrement() {
    i++; i++;
  }
  public static void main(String[] args) {
    Atomicity.test(new UnsafeReturn());
  }
}
/* Output:
failed with: 39
*/

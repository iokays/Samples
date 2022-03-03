package onjava8.equalshashcode;// equalshashcode/EqualityFactory.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.

interface EqualityFactory {
  Equality make(int i, String s, double d);
}

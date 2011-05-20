package sap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
public class OrderedProperties extends java.util.Properties {
   ArrayList orderedKeys = new ArrayList();
  public OrderedProperties() {
    super();
  }
  public OrderedProperties(java.util.Properties defaults) {
    super(defaults);
  }
  public synchronized Iterator getKeysIterator() {
    return orderedKeys.iterator();
  }
  public static OrderedProperties load(String name)
                                  throws IOException {
    OrderedProperties props = null;
    java.io.InputStream is =
      OrderedProperties.class.getResourceAsStream(name);
    if ( is != null ) {
      props = new OrderedProperties();
      props.load(is);
      return props;
    } else {
      if ( ! name.startsWith("/") ) {
        return load("/" + name);
      } else {
        throw new IOException("Properties could not be loaded.");
      }
    }
 }
 public synchronized Object put(Object key, Object value) {
   Object obj = super.put(key, value);
   orderedKeys.add(key);
   return obj;
 }
 public synchronized Object remove(Object key) {
   Object obj = super.remove(key);
   orderedKeys.remove(key);
   return obj;
 }
}
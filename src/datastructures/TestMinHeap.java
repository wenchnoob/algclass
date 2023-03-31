package datastructures;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.*;

public class TestMinHeap{ 
  
  @Test
  public void testInsertStrings(){
    MinHeap<String> pq = new MinHeap<String>();
    pq.insert("cat");
    pq.insert("dog");
    pq.insert("bee");
    pq.insert("eagle");
    pq.insert("bear");
    pq.insert("cat");
    pq.insert("dog");
    pq.insert("dog");
    pq.insert("sheep");
    pq.insert("cow");
    pq.insert("eagle");
    pq.insert("bee");
    pq.insert("lion");
    pq.insert("tiger");
    pq.insert("zebra");
    pq.insert("ant");
    String list = "[ant, bear, bee, bee, cow, cat, dog, dog, sheep, dog, eagle, cat, lion, tiger, zebra, eagle]";
    assertEquals("Checking insert order ", list, pq.toString());
    
  } 
  
  @Test
  public void testWithIntegers(){
    ArrayList<Integer> ints = new ArrayList<Integer>();
    ints.add(41);
    ints.add(3);
    ints.add(75);
    ints.add(24);
    MinHeap<Integer> hi = new MinHeap<Integer>(ints);
    String list = "[3, 24, 75, 41]";
    assertEquals("Checking heapify ", list, hi.toString());
  }
  
  
  @Test
  public void testWithCats(){
    ArrayList<Cat> cats = new ArrayList<Cat>();
    cats.add(new Cat("Ned", 7));
    cats.add(new Cat("Alfred", 12));
    cats.add(new Cat("Oswald", 5));
    cats.add(new Cat("Geoff", 5));
    cats.add(new Cat("Cecilia", 17));
    cats.add(new Cat("Corneilius", 16));
    cats.add(new Cat("Roger", 18));
    cats.add(new Cat("Roxanne", 5));
    cats.add(new Cat("Gun Slinger Zeke", 3));
    MinHeap<Cat> hc = new MinHeap<Cat>(cats);
    String list = "[Gun Slinger Zeke is 3 years old., Geoff is 5 years old., Oswald is 5 years old., Roxanne is 5 years old., Cecilia is 17 years old., Corneilius is 16 years old., Roger is 18 years old., Ned is 7 years old., Alfred is 12 years old.]";
    assertEquals("Checking heapify ", list, hc.toString());
  }
  
  @Test
  public void testRemoveMinCat(){
    ArrayList<Cat> cats = new ArrayList<Cat>();
    cats.add(new Cat("Ned", 7));
    cats.add(new Cat("Alfred", 12));
    cats.add(new Cat("Oswald", 5));
    cats.add(new Cat("Geoff", 5));
    cats.add(new Cat("Cecilia", 17));
    cats.add(new Cat("Corneilius", 16));
    cats.add(new Cat("Roger", 18));
    cats.add(new Cat("Roxanne", 5));
    cats.add(new Cat("Gun Slinger Zeke", 3));
    MinHeap<Cat> hc = new MinHeap<Cat>(cats);
    Cat gsz = new Cat("Gun Slinger Zeke", 3);
    Cat c = hc.removemin();
    int compareResult = gsz.compareTo(c);
    assertEquals("Checking min ", compareResult, 0);
    Cat ge = new Cat("Geoff", 5);
    c = hc.removemin();
    compareResult = ge.compareTo(c);
    assertEquals("Checking min ", compareResult, 0);
    Cat oz = new Cat("Oswald", 5);
    c = hc.removemin();
    compareResult = oz.compareTo(c);
    assertEquals("Checking min ", compareResult, 0);
    Cat rox = new Cat("Roxanne", 5);
    c = hc.removemin();
    compareResult = rox.compareTo(c);
    assertEquals("Checking min ", compareResult, 0);
    Cat ned = new Cat("Ned", 7);
    c = hc.removemin();
    compareResult = ned.compareTo(c);
    assertEquals("Checking min ", compareResult, 0);
    Cat al = new Cat("Alfred", 12);
    c = hc.removemin();
    compareResult = al.compareTo(c);
    assertEquals("Checking min ", compareResult, 0);
    Cat corn = new Cat("Corneilius", 16);
    c = hc.removemin();
    compareResult = corn.compareTo(c);
    assertEquals("Checking min ", compareResult, 0);
    Cat cecilia = new Cat("Cecilia", 17);
    c = hc.removemin();
    compareResult = cecilia.compareTo(c);
    assertEquals("Checking min ", compareResult, 0);
    Cat roger = new Cat("Roger", 18);
    c = hc.removemin();
    compareResult = roger.compareTo(c);
    assertEquals("Checking removemin ", compareResult, 0);
    
    
    
    
    
    
    
    
    
  }
  
  @Test
  public void testisEmpty(){
    ArrayList<Integer> ints = new ArrayList<Integer>();
    ints.add(41);
    ints.add(3);
    ints.add(75);
    ints.add(24);
    MinHeap<Integer> hi = new MinHeap<Integer>(ints);
    
    for(int i = 0; i< 4; i++){
      assertEquals("Checking isEmpty ", false, hi.isEmpty());
      hi.removemin(); 
    }
    assertEquals("Checking isEmpty ", true, hi.isEmpty());
  }
  
  @Test
  public void testRemove(){
    MinHeap<String> pq = new MinHeap<String>();
    pq.insert("cat");
    pq.insert("dog");
    pq.insert("bee");
    pq.insert("eagle");
    pq.insert("bear");
    pq.insert("dog");
    pq.insert("sheep");
    pq.insert("cow");
    pq.insert("eagle");
    pq.insert("bee");
    pq.insert("lion");
    pq.insert("tiger");
    pq.insert("zebra");
    pq.insert("ant");
    String s = pq.remove(1);
    assertEquals("Checking remove at index 1 ", "bee", s);
    s = pq.remove(1);
    assertEquals("Checking remove at index 1 ", "bee", s);
    String list = "[ant, cow, bear, eagle, dog, dog, cat, zebra, eagle, sheep, lion, tiger]";
    assertEquals("Checking reamining heap ", list, pq.toString());
  }
}

package datastructures;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.*;

public class TestMaxHeap{ 
  
  @Test
  public void testInsertStrings(){
    MaxHeap<String> pq = new MaxHeap<String>();
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
    String list = "[zebra, eagle, tiger, dog, eagle, dog, sheep, cat, dog, bear, cow, bee, bee, cat, lion, ant]";
    assertEquals("Checking insert order ", list, pq.toString());
    
  } 
  
  @Test
  public void testWithIntegers(){
    ArrayList<Integer> ints = new ArrayList<Integer>();
    ints.add(41);
    ints.add(3);
    ints.add(75);
    ints.add(24);
    MaxHeap<Integer> hi = new MaxHeap<Integer>(ints);
    String list = "[75, 24, 41, 3]";
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
    MaxHeap<Cat> hc = new MaxHeap<Cat>(cats);
    String list = "[Roger is 18 years old., Cecilia is 17 years old., Corneilius is 16 years old., Roxanne is 5 years old., Alfred is 12 years old., Ned is 7 years old., Oswald is 5 years old., Geoff is 5 years old., Gun Slinger Zeke is 3 years old.]";
    assertEquals("Checking heapify ", list, hc.toString());
  }
  
  @Test
  public void testRemoveMaxCat(){
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
    MaxHeap<Cat> hc = new MaxHeap<Cat>(cats);
    Cat roger = new Cat("Roger", 18);
    Cat c = hc.removemax();
    int compareResult = roger.compareTo(c);
    assertEquals("Checking removemax ", compareResult, 0);
    Cat cecilia = new Cat("Cecilia", 17);
    c = hc.removemax();
    compareResult = cecilia.compareTo(c);
    assertEquals("Checking max ", compareResult, 0);
    Cat corn = new Cat("Corneilius", 16);
    c = hc.removemax();
    compareResult = corn.compareTo(c);
    assertEquals("Checking max ", compareResult, 0);
    Cat al = new Cat("Alfred", 12);
    c = hc.removemax();
    compareResult = al.compareTo(c);
    assertEquals("Checking max ", compareResult, 0);
    Cat ned = new Cat("Ned", 7);
    c = hc.removemax();
    compareResult = ned.compareTo(c);
    assertEquals("Checking max ", compareResult, 0);
    Cat rox = new Cat("Roxanne", 5);
    c = hc.removemax();
    compareResult = rox.compareTo(c);
    assertEquals("Checking max ", compareResult, 0);
    Cat oz = new Cat("Oswald", 5);
    c = hc.removemax();
    compareResult = oz.compareTo(c);
    assertEquals("Checking max ", compareResult, 0);
    Cat ge = new Cat("Geoff", 5);
    c = hc.removemax();
    compareResult = ge.compareTo(c);
    assertEquals("Checking max ", compareResult, 0);
    Cat gsz = new Cat("Gun Slinger Zeke", 3);
    c = hc.removemax();
    compareResult = gsz.compareTo(c);
    assertEquals("Checking max ", compareResult, 0);
    
  }
  
  @Test
  public void testisEmpty(){
    ArrayList<Integer> ints = new ArrayList<Integer>();
    ints.add(41);
    ints.add(3);
    ints.add(75);
    ints.add(24);
    MaxHeap<Integer> hi = new MaxHeap<Integer>(ints);
    
    for(int i = 0; i< 4; i++){
      assertEquals("Checking isEmpty ", false, hi.isEmpty());
      hi.removemax(); 
    }
    assertEquals("Checking isEmpty ", true, hi.isEmpty());
  }
  
  @Test
  public void testRemove(){
    MaxHeap<String> pq = new MaxHeap<String>();
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
    assertEquals("Checking remove at index 1 ", "lion", s);
    s = pq.remove(1);
    assertEquals("Checking remove at index 1 ", "eagle", s);
    String list = "[zebra, eagle, tiger, dog, bee, sheep, dog, cat, cow, bear, ant, bee]";
    assertEquals("Checking reamining heap ", list, pq.toString());
  }
}

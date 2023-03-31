package datastructures;

import java.util.*;
/** This is a sad representation of a cat
*/
public class Cat implements Comparable<Cat>{
	private String name;
	private int age;
	
	
	/** 
	* Creates a new Cat that is awake, with no volume and 4 legs
	* @param name  name of cat
	* @param age age of cat
	*/
	public Cat(String name, int age){
		this.name = name;
		this.age = age;
	
	}
	
	
	/**
	* @return name of Cat
	*/
	public String getName(){
		return name;
	}
	
	/** 
	* @return age of cat
	*/
	public int getAge(){
		return age;
	}
	
	
	/**
	* @return the string representation of the cat
	*/
	public String toString(){
		
		return name+ " is "+ age +" years old." ;
	}
	/**
	* Cats are compared by age and if tie - then by name
	* @param other Cat comparing to this Cat
	* @return int -1 if this is less than, +1 if greater, if 
	* tie in age- decide by using name
	*/
	public int compareTo(Cat other){
		if(this.getAge() < other.getAge()){
			return -1;
		}
		else if (this.getAge() == other.getAge()){
			return this.getName().compareTo(other.getName());
		}
		else{
			return 1;
		}
	}
	
}

		
		
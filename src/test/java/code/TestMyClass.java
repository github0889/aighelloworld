package code;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestMyClass {

   private MyClass h;
	
   @Before
   public void setUp() throws Exception 
   {
      h = new MyClass();
   }

   @Test
   public void testHelloEmpty() 
   {
      
      assertEquals(h.printMessage(""),"Hello!");
   }
	
   @Test
   public void testHelloWorld() 
   {
      
      assertEquals(h.printMessage("World"),"Hello World!");
   }
}
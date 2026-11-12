import java.util.*;

public class HTMLManager {
  private Queue<HTMLTag> tags;
  
  public HTMLManager(Queue input){
   if(input == null){
      throw new IllegalArgumentException("The input is empty, please try a different file");
   } else{
      for (HTMLTag element : input){
         tags.add(input.poll());
      }
   }
  }
  
}

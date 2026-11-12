import java.util.*;

public class HTMLManager {
  private Queue<HTMLTag> tags;
  
  public HTMLManager(Queue<HTMLTag> input){
      tags = new LinkedList<HTMLTag>();
      
      if(input == null){
          throw new IllegalArgumentException("The input is empty, please try a different file");
      } else{
          for (HTMLTag element : input){
              tags.add(input.poll());
          }
      }
  }
    
  public String toString(){
      String result = "";
      
      for (HTMLTag element : tags){
          result += element.toString();
      }
      return result;
  }
  
  public void fixHTML(){}
  
}

import java.util.*;

public class HTMLManager {
  private Queue<HTMLTag> tags;
  
  public HTMLManager(Queue<HTMLTag> input){
      tags = new LinkedList<HTMLTag>();
      int size = input.size();
      
      if(input == null){
          throw new IllegalArgumentException("The input is empty, please try a different file");
      } else{
          for (int i = 0; i < size; i++){
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

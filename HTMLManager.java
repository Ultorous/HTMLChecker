import java.util.*;

public class HTMLManager {
  private Queue<HTMLTag> tags;
  
  public HTMLManager(Queue<HTMLTag> input){
      tags = new LinkedList<HTMLTag>();
      int size = input.size();
      
      if(input == null){
          throw new IllegalArgumentException("The input is empty, please try a different file");
      } else{
          //int size = input.size();
          for (int i = 0; i < size; i++){
              tags.add(input.poll());
          }
      }
  }
  
  public Queue<HTMLTag> getTags(){
      return tags;
  }
  
  public String toString(){
      String result = "";
      
      for (HTMLTag element : tags){
          String addition = element.toString();
          result += addition.trim();
      }
      return result;
  }
  
  public void fixHTML(){
      Stack<HTMLTag> checker = new Stack<>();
      int size = tags.size();
      
      for(int i = 0; i < size; i++){
          HTMLTag current = tags.peek();
          
          if(current.isOpening()){
              if(!current.isSelfClosing()){
                  tags.remove();
                  tags.add(current);
                  checker.push(current);
              } else if (current.isSelfClosing()){
                  tags.add(tags.poll());
              }
          } else if(current.isClosing()){
              if(!current.matches(checker.peek())){
                  checker.pop();
                  HTMLTag ammendment = current.getMatching();
                  tags.add(ammendment);
              }
          }
      }
   }
  
}

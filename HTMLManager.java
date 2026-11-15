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
      int queueSize = tags.size();
      
      for(int i = 0; i < queueSize; i++){
          HTMLTag current = tags.peek();
          
          if(current.isOpening()){
              tags.remove();
              tags.add(current);
              checker.push(current);
          } else if (current.isSelfClosing()){
              tags.add(tags.poll());
          } else if(current.isClosing() && !current.isSelfClosing()){
              if(!checker.isEmpty() && !current.matches(checker.peek())){
                  HTMLTag checkerTag = checker.pop();
                  HTMLTag ammendment = checkerTag.getMatching();
                  tags.add(ammendment);
                  tags.poll();
              } else if (!checker.isEmpty() && current.matches(checker.peek())){
                  checker.pop();
                  tags.poll();
                  tags.add(current);
              } else if(checker.isEmpty()){
                  tags.poll();
              }
              
          }
      }
      
      int stackSize = checker.size();
      for(int i = 0; i < stackSize; i++){
          HTMLTag checkerTag = checker.pop();
          HTMLTag ammendment = checkerTag.getMatching();
          tags.add(ammendment);
      }
      
   }
  
}

import java.util.*;

public class HTMLManager {
  private Queue<HTMLTag> tags;
  
  public HTMLManager(Queue<HTMLTag> input){
      tags = new LinkedList<HTMLTag>();
      
      //Throw exception if the input is null. Otherwise transfer each tag in the input into the 
      //private Queue
      if(input == null){
          throw new IllegalArgumentException("The input is empty, please try a different file");
      } else {
          while(!input.isEmpty()){
              tags.add(input.poll());
          }
      }
  }
  
  //Simple getter
  public Queue<HTMLTag> getTags(){
      return tags;
  }
  
  public String toString(){
      String result = "";
      
      //Go through each element in the queue and add them to the String result.
      for (HTMLTag element : tags){
          String addition = element.toString();
          result += addition.trim();
      }
      //Return the String that was just built.
      return result;
  }
  
  public void fixHTML(){
      //Establish the stack used to check that each opening tag has a closing tag.
      Stack<HTMLTag> checker = new Stack<>();
      int queueSize = tags.size();
      
      //Go through the Queue one full time, each tag is evaluated case by case
      //based on whether it is an opening tag, a self closing tag, whether it 
      //matches the tag at the top of the stack, or whether the stack is empty.
      for(int i = 0; i < queueSize; i++){
          HTMLTag current = tags.peek();
          
          if(current.isOpening()){
              tags.add(tags.remove());
              checker.push(current);
          } else if (current.isSelfClosing()){
              tags.add(tags.poll());
          } else if (checker.isEmpty()){
              tags.poll();
          } else {
              if(!current.matches(checker.peek())){
                  tags.add(checker.pop().getMatching());
                  tags.poll();
              } else if (current.matches(checker.peek())){
                  checker.pop();
                  tags.add(tags.poll());
              }
          }
      }
      
      //For each tag left in the stack, add its closing tag to the output and remove 
      //the it from the stack.
      while(!checker.isEmpty()){
          tags.add(checker.pop().getMatching());
      }
   }
}
/*
 ----jGRASP exec: java HTMLChecker
 ===============================
 Processing tests/test3.html...
 ===============================
 HTML: <br /></p></p>
 Checking HTML for errors...
 HTML after fix: <br />
 ----> Result matches Expected Output!
 
 ===============================
 Processing tests/test2.html...
 ===============================
 HTML: <a><a><a></a>
 Checking HTML for errors...
 HTML after fix: <a><a><a></a></a></a>
 ----> Result matches Expected Output!
 
 ===============================
 Processing tests/test5.html...
 ===============================
 HTML: <div><h1></h1><div><img /><p><br /><br /><br /></div></div></table>
 Checking HTML for errors...
 HTML after fix: <div><h1></h1><div><img /><p><br /><br /><br /></p></div></div>
 ----> Result matches Expected Output!
 
 ===============================
 Processing tests/test4.html...
 ===============================
 HTML: <div><div><ul><li></li><li></li><li></ul></div>
 Checking HTML for errors...
 HTML after fix: <div><div><ul><li></li><li></li><li></li></ul></div></div>
 ----> Result matches Expected Output!
 
 ===============================
 Processing tests/test1.html...
 ===============================
 HTML: <b><i><br /></b></i>
 Checking HTML for errors...
 HTML after fix: <b><i><br /></i></b>
 ----> Result matches Expected Output!
 
 ===============================
         All tests passed!
 ===============================
 
  ----jGRASP: Operation complete.
 */ 
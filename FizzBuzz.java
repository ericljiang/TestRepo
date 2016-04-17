public class FizzBuzz {
  public static void main(String[] args) {
    if(args.length == 0 || args.length > 1){
      System.out.println("Incorrect number of arguments!");
    }else{
      int num = Integer.parseInt(args[0]);
      for(int i = 1; i <= num; i++ ){
        if(i%3 == 0){
          System.out.print("Fizz");
        }
	if(i%5 == 0){
          System.out.print("Buzz");
        }
	if(i%3 != 0 && i%5 != 0){
          System.out.print(i);
        }
	System.out.println();
      } 
    }
  }
} 

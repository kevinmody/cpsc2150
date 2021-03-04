package cpsc2150.MyDeque;

import java.util.Scanner;
//Author: Kevin Mody and Henry Mayo
//Class: CPSC 2151
//Sec: 006
//Date: 03/04/2021
public class IntegerDequeApp {
    public static void main(String[] args) {

        IDeque <Integer> q;


        System.out.println("Enter 1 for array implementation or 2 for List implementation");
        Scanner read = new Scanner(System.in);
        Integer whichType = Integer.valueOf(read.nextLine());
        if(whichType.equals(1)){
            q = new ArrayDeque<>();
        }
        else if(whichType.equals(2)){
            q = new ListDeque<>();
        } else {
            System.out.println("Error input");
            return;
        }

        while (true) {
            // boolean showDeque = true;
            int addChar;
            int p = -1;
            System.out.println("Select an option:");
            System.out.println("1. Add to the end of the Deque");
            System.out.println("2. Add to the front of the Deque");
            System.out.println("3. Remove from the front of the Deque");
            System.out.println("4. Remove from the end of the Deque");
            System.out.println("5. Peek from the front of the Deque");
            System.out.println("6. Peek from the end of the Deque");
            System.out.println("7. Insert to a position in the Deque");
            System.out.println("8. Remove from a position in the Deque");
            System.out.println("9. Get a position in the Deque");
            System.out.println("10. Get the length of the Deque");
            System.out.println("11. Clear the Deque");
            System.out.println("12. Quit");
            int options = read.nextInt();
            System.out.println(options);

            switch (options) {
                case 1:
                    System.out.println("Which character to enque to the end of the Deque?");
                    addChar = read.nextInt();//check the precondition
                    q.enqueue(addChar);

                    break;
                case 2:
                    System.out.println("What character to inject to the front of the Deque?");
                    addChar = read.nextInt();
                    q.inject(addChar); //check the precondition

                    break;
                case 3:
                    if(q.length() > 0) {
                        System.out.println("Character at the front: "+ q.dequeue() );
                        //q.dequeue();
                    }
                    else{
                        System.out.println("Deque is empty!");
                    }

                    break;
                case 4:
                    if(q.length()>0) {
                        System.out.println("Character at the end: "+ q.removeLast() );
                        // q.removeLast();
                    }
                    else{
                        System.out.println("Deque is empty!");
                    }

                    break;
                case 5:
                    if(q.length()>0) {
                        System.out.println("Peek: " + q.peek());

                    }
                    else{
                        System.out.println("Deque is empty!");
                    }

                    break;
                case 6:
                    if(q.length()>0) {
                        System.out.println("End of Deque: " +q.endOfDeque());
                    }
                    else{
                        System.out.println("Deque is empty!");
                    }

                    break;
                case 7:
                    if(q.length()==IDeque.MAX_LENGTH){
                        System.out.println("Deque is Full");
                    }else {
                        System.out.println("What character to insert to the DeQue?");
                        addChar = read.nextLine().charAt(0);
                        while (p == -1 ) {
                            System.out.println("What position do you want to insert from the Deque?");
                            p = Integer.parseInt(read.nextLine());

                            if(p<=0||p>q.length()) {
                                p=-1;
                                System.out.println("Not a valid position in the Deque: ");
                            }
                        }
                        q.insert(addChar, p);
                        break;
                    }
                    break;

                case 8:
                    if(q.length()==0){
                        System.out.println("Deque is empty");
                    }else {

                        while (p == -1 ) {
                            System.out.println("What position to remove from the Deque?");
                            p = Integer.parseInt(read.nextLine());

                            if(p<=0||p>q.length()) {
                                p=-1;
                                System.out.println("Not a valid position in the Deque! ");
                            }
                        }
                        System.out.println(q.remove(p) + " is at position " + p + " in the Deque");
                        break;
                    }
                case 9:
                    if(q.length()==0){
                        System.out.println("Deque is empty");
                    }else {

                        while (p == -1 ) {

                            System.out.println("What position to get from the Deque?");
                            p = Integer.parseInt(read.nextLine());

                            if(p<=0||p>q.length()) {
                                p=-1;
                                System.out.println("Not a valid position in the Deque! ");
                            }
                        }

                        System.out.println(q.get(p) + " is at position " + p + " in the Deque");
                        break;
                    }



                case 10:
                    System.out.println("Length of Deque: " + q.length());
                    break;
                case 11:
                    System.out.println("Deque is now empty!");
                    q.clear();
                    break;
                case 12:
                    System.exit(0);
                    break;
                default:
                    //showDeque = false;
                    System.out.println("Invalid input");
                    break;
            }


            System.out.println("Deque is: ");
            System.out.println(q.toString() + "\n");


        }


    }
}

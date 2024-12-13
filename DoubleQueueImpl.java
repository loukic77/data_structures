import java.io.PrintStream;
import java.util.NoSuchElementException;

public class DoubleQueueImpl<T> implements DoubleQueue <T>{ //to kaname implement me generic gia to bonus
    private class Node{  // orizw tous kombous
        T item;
        Node next;

        public Node(T item){
            this.item = item;
            this.next = null;
        }
    }
    private Node head,tail;   // orizw ta stoixeia ths listas
    private int size=0;
    public DoubleQueueImpl(){  // constructor
        head=null;
        tail = null;
    }
    public boolean isEmpty(){
        return (head== null);
    }
    public void put(T item){
        Node temp = tail;
        tail = new Node(item);
        if (isEmpty()){
            head=tail;
        }
        else {
            temp.next = tail;
        }
        this.size +=1;

    }
    public T get() throws NoSuchElementException{

        if (isEmpty()){
            throw new NoSuchElementException();
        }
        T out_item = head.item;
        if(head==tail){
            head =tail=null;
        }
        else {
            head = head.next;
        }
        this.size -=1;
        return out_item;
    }

    public T peek() throws NoSuchElementException {
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        return head.item;
    }

    public void printQueue(PrintStream stream){
        Node current = head;
        while (current!=null){
            stream.println(current.item);
            current = current.next;
        }
    }

    public int size(){   // peirazw stadiaka to size stis get,put opote edw borw na to epistrepsw se O(1) xrono
        return size;
    }


}

import java.util.Scanner;
import java.util.*;
public class median{
  static minHeap min;
  static maxHeap max;
  int numOfElements = 0;


  public median(){
    min = new minHeap();
    max = new maxHeap();
  }
  private  int getMedian() {
        if (max.isEmpty()) {
            return 0;
        } else if (max.size() == min.size()) {
            return Math.round((max.peek() + min.peek()) / 2);
        } else { // maxHeap must have more elements than minHeap
            return max.peek();
        }
    }


  public void addNumber(int num) {
    max.insert(num);
    if (numOfElements%2 == 0) {
      if (min.isEmpty()) {
        numOfElements++;
        return;
      }
      else if (max.peek() > min.peek()) {
        Integer maxHeapRoot = max.removeMax();
        Integer minHeapRoot = min.removeMin();
        max.insert(minHeapRoot);
        min.insert(maxHeapRoot);
      }
    } else {
      min.insert(max.removeMax());
    }
    numOfElements++;

      if( Math.round( max.size() - min.size()) > 1){

            if(max.size() > min.size()){
                min.insert(max.removeMax());
            }
            else{
              max.insert(min.removeMin());
            }
        }
  }



  public  int calculateMedian(int x){
    // int numOfElements = 0 ;
    //your code goes here


    addNumber(x);

    return getMedian();

  }

  public static void main(String[] args){
    median m = new median();

    System.out.println("Enter a list of non negative integers. To end enter a negative integers.");
    Scanner s = new Scanner(System.in);
    int current = s.nextInt();

    while(current >=0){
      System.out.println("current median:" + m.calculateMedian(current));
      current = s.nextInt();
      if(current<0)break;
      m.calculateMedian(current);
      current = s.nextInt();

    }
  }
}

class minHeap{
  private int[] heap;
  private int size;

  public minHeap(){
    heap=new int[10000];
    size=0;
  }

  public boolean isEmpty(){
    return (size==0);
  }

  public int size(){
    return size;
  }

  public void insert(int x){
    //Your code goes here
    size++;
    heap[size]= x;
    bubbleup(size);
  }

  public void bubbleup(int k){
    //Your code goes here
    while(hasParent(k) && (new Integer(parent(k)).compareTo(new Integer (heap[k])) > 0))
    {
      exchange(k,parentIndex(k));
      k = parentIndex(k);

    }

  }
  public void exchange(int i,int j){
    //Your code goes here
    int temp = heap[i];
    heap[i] = heap[j];
    heap[j]= temp;
  }
  public void bubbledown(int k){
    //Your code goes here
    int value_leftchild = left_child(k);
    int value_righchild = right_child(k);
    int minIndex;
    int temp;

    if ( value_righchild >= this.size){
        if( value_leftchild >= this.size)
            return;
        else{
            minIndex = value_leftchild;
        }
    }else{
        if(heap[value_leftchild] <= heap[value_righchild] ){
            minIndex = value_leftchild;
        }else{
            minIndex = value_righchild;
        }

    }
    if(heap[k] > heap[minIndex]){
        temp = heap[minIndex];
        heap[minIndex] = heap[k];
        heap[k]= temp;
        bubbledown(minIndex);
    }

  }
  public int peek(){
    //Your code goes here
    if(isEmpty())
    {
      throw new IllegalStateException();
    }
    return heap[1];
  }

  public int removeMin(){
    //Your code goes here
    int temp;
    if (isEmpty()){
        throw new IllegalStateException();
    }
    else
    {
        temp =heap[1];
        heap [1] = heap[size];
        heap[size] = 0;
        size--;
        if (size > 0 ){
            bubbledown(0);
        }
    }
    return  temp;
  }

  //helper methods
    public boolean hasParent(int i) {
        return i > 1;
    }
    public boolean hasLeftChild(int i) {
        return left_child(i) <= size;
    }


    public boolean hasRightChild(int i) {
        return right_child(i) <= size;
    }
    public int parent(int i) {
        return heap[parentIndex(i)];
    }
    public int left_child(int i){
        return 2*i;
    }
    public int right_child(int i){
        return 2*i +1;
    }
    public int parentIndex(int i) {
        return i/ 2;
    }


}

class maxHeap{
  private int[] heap;
  private int size;

  public maxHeap(){
    heap=new int[10000];
    size=0;
  }

  public boolean isEmpty(){
    return (size==0);
  }

  public int size(){
    return size;
  }

  public void insert(int x){
    //Your code goes here
    /*if(size <= heap.length  ){
       heap = Arrays.copyOf(heap,heap.length+1);
    }
    */
    size++;
    heap[size]= x;
    bubbleup(size);
  }

  public void bubbleup(int k){
    //Your code goes here
    while(k >1 && less (k/2,k)){
      exchange( k/2, k);
      k = k/2;
    }

  }
  public void exchange(int i,int j){
    //Your code goes here
    int temp = heap[i];
    heap[i] = heap[j];
    heap[j]= temp;
  }
  public void bubbledown(int k){
    //Your code goes here
    while (2*k <= size)
    {
      int j = 2*k;
      if (j < size && less(j, j+1)) j++;
      if (!less(k, j)) break;
      exchange(k, j);
      k = j;
    }
  }
  public int peek(){
    //Your code goes here
    if(isEmpty()){
        throw new IllegalStateException();

    }
    return heap[1];
  }

  public int removeMax(){
    //Your code goes here
    int temp;
    if (isEmpty()){
        throw new IllegalStateException();
    }
    else{
      temp = peek();
      exchange(1, size--);
      heap[size+1] = 0;
      bubbledown (1);
    }
    return  temp;
  }

  //helper

     public boolean hasleaf(int i) {
          if(i >= (size/2) && i <=size){
            return true;
          }
          return false;
      }
      public boolean hasLeftChild(int i) {
          return left_child(i) <= size;
      }



      public boolean hasRightChild(int i) {
          return right_child(i) <= size;
      }
      public int parent(int i) {
          return heap[parentIndex(i)];
      }
      public int left_child(int i){
          return 2*i;
      }
      public int right_child(int i){
          return 2*i +1;
      }
      public int parentIndex(int i) {
          return i  / 2;
      }
       private boolean less(int i, int j)
      {
        return new Integer(heap[i]).compareTo(heap[j]) < 0;
      }}
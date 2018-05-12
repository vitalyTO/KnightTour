/*
This class is for holding the sequence of the moves implemented on the board(sequence in which the fields have been visited);
Implements List Interface to hold Type -> FIELD
 */

public class MoveSequence<T> implements ListInterface<T> {
    protected int numElements; //num of elements currently in the list
    protected LLNode<T> currentPos; //current position for iteration
    protected int total = 16;

    //below is set by the find method
    protected boolean found; //true if elem is in the list
    protected LLNode<T> location; //node containing the elements if it was found
    protected LLNode<T> previous; //node before the Location Node(useful to know when deleting Location Node->resetting the links)

    protected LLNode<T> list; //the element on the very "left" of the list

    public MoveSequence(){ //initializer
        numElements=0;
        list = null; //the first(and last in this case[head element]) node of the list hold null because there's nothing next to it to point to...yet(:
        currentPos = null; //obvious enough^
    }

    public boolean isFull(){
        return size() == total;
    }

    public void add(T element) {
        LLNode<T> nodeToAdd = new LLNode<>(element); //creating a new node with the info provided
        nodeToAdd.setLink(list); //now the element that has just been added has nothing in front of it so it link point to NULL
        list = nodeToAdd;
        numElements++;
    }

    protected void find(T target){ //this is a supporter method not included in the interface, not sure why..(has to do with method signature?)
        location = list; //set the location to the first elem
        found = false;
        while (location!=null){ //while traversing hasn't reached the last element do..
            if(location.getInfo().equals(target)){ //if the info of type T in the current node is what we are looking for
                found = true;
                return;
            }
            else{
                previous = location; //set the previous location before reassigning the current location
                location = location.getLink(); //get the link to the next element and repeat above
            }
        }
    }

    public int size(){
        return numElements;
    }

    public boolean contains(T element){
        find(element);
        return found;
    }

    public boolean remove(T element){
        find(element);
        if(found){
            if(list==location) //if the elements we are looking to remove(location) if the first node...
                list = list.getLink(); //reassign the first node to be the element after it thus removing the original
            else
                previous.setLink(location.getLink()); //if the elem is not the first node then set the link of the previous elem to the elem after the location
                //statement above makes sense because previous and location have been defined by the find method
                //the need to check if the location is the first node is because the first node wouldn't have a previous location
            numElements--;
        }
        return found; //if the element has been found means it has also been removed
    }

    public T get(T element){
        find(element);
        if(found)
            return location.getInfo(); //get the info of type T from the location var that has been dealt with by the find method
        else
            return null;
    }

    public void reset(){ //init current position for an iteration thru the list to the first elem in the list
        currentPos = list;
    }

    public T getNext(){
        //the list has been reset
        //has not been modified after the reset
        //not empty
        T next = currentPos.getInfo();
        if(currentPos.getLink()==null) //if the current position is the last element then reset(advance the value of the currentPos to the first elem)
            reset();
        else
            currentPos = currentPos.getLink(); //otherwise current position is the one after(in case the method is being called right after we need an updated position)
        return next;
    }

    public String toString(){
        LLNode<T> currentNode = list;
        String listString = "Move Sequence: \n";
        while(currentNode!=null){
            listString = listString + " "+currentNode.getInfo().toString() + "\n";
            currentNode = currentNode.getLink();
        }
        return listString;
    }
}













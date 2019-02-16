package List;

import Exceptions.isEmptyException;
import Nodes.Node;

import java.util.Iterator;

public class doubleLinkedList<T extends Comparable<T>> implements Iterable<T>, Comparable<T>{

    private Node<T> head, tail;
    private long length;

    public doubleLinkedList() {
        head = new Node<>();
        tail = new Node<>();
        length = 0;
    }

    public doubleLinkedList(T value) {
        this();
        Node<T> _new = new Node<>(value);
        head.setNext(_new);
        tail.setBack(_new);
        length++;
    }

    public doubleLinkedList(Node<T> node) {
        this(node.getValue());
    }

    public boolean add(T value) {
        Node<T> _new = new Node<>(value);
        if (_new == null){
            return false;
        }
        try {
            isEmpty();
            Node<T> tmp = tail.getBack();
            tmp.setNext(_new);
            _new.setBack(tmp);
            tail.setBack(_new);
            
        } catch (isEmptyException e) {
            head.setNext(_new);
            tail.setBack(_new);
        }
        length++;
        return true;
    }

    public boolean add(Node<T> node) {
        return add(node.getValue());
    }
    
    private boolean isThere(Node<T> node, T value) {
        if (node.getNext() == null) {
            return false;
        } else if (node.getNext().getValue().equals(value)) {
            return true;
        } else {
            return isThere(node.getNext(), value);
        }
    }

    private Node<T> isThereNode(Node<T> node, T value){
        try{
            isEmpty();
            if(node.getNext() == null){
                if(node.getValue().equals(value)) return node;
                return null;
            }
            else if(node.getNext().getValue().equals(value)){
                return node.getNext();
            } else {
                return isThereNode(node.getNext(), value);
            }
        } catch (isEmptyException e){
            return null;
        }
    }
        
    public boolean removeAll(T value) {
        try {
            isEmpty();
            while (isThere(head, value)) {
                remove(value);
            }
            return true;
        } catch (isEmptyException e) {
            return false;
        }
    }
     
     public boolean remove(T value) {
        try {
            isEmpty();
            Node<T> tmp = getPrevElement(head, value);
            if (tmp != null) {
                if (tmp.getNext().getNext() == null && tmp.getNext().getBack() == null) {//es el unico
                    head.setNext(null);
                    tail.setBack(null);
                } else if (tmp.getNext().getBack() == null) {//primero de la lista
                    tmp.getNext().getNext().setBack(null);
                    head.setNext(tmp.getNext().getNext());
                } else if (tmp.getNext().getNext() == null) {// ultimo de la lista
                    tmp.setNext(null);
                    tail.setBack(tmp);
                } else {
                    tmp.setNext(tmp.getNext().getNext());
                    tmp.getNext().setBack(tmp);
                }
                length--;
                System.gc();
            }
            return true;
        } catch (isEmptyException e) {
            return false;
        }
    }
    
    public boolean remove(Node<T> node) {
        try {
            isEmpty();
            if(node.getBack() == null){
                Node<T> next = node.getNext();
                head.setNext(next);
                next.setBack(null);
            } else if(node.getNext() == null) {
                Node<T> back = node.getBack();
                tail.setBack(back);
                back.setNext(null);
            } else {
                Node<T> back = node.getBack();
                Node<T> next = node.getNext();
                back.setNext(next);
                next.setBack(back);
            }
            System.gc();
            length--;
            return true;
        } catch (isEmptyException e){
            return false;
        }
    }
    
    public boolean addStart(T value) {
        Node<T> _new = new Node<>(value);
        try {
            isEmpty();
            Node<T> tmp = head.getNext();
            head.setNext(_new);
            _new.setNext(tmp);
            tmp.setBack(_new);
            return true;
        } catch (isEmptyException e) {
            head.setNext(_new);
            tail.setBack(_new);
            length++;
            return true;
        }

    }

        public boolean addStart(Node<T> node) {
        return addStart(node.getValue());
    }
    
    public boolean addAt(T value, int position) {
        try {
            isEmpty();
            Node<T> _new = new Node<>(value);
            if (position >= length){
                return false;
            }
            else if (position == 0) {
                Node<T> tmp = head.getNext();
                head.setNext(_new);
                _new.setNext(tmp);
                tmp.setBack(_new);
            } else if(position == length){
                Node<T> tmp = tail.getBack();
                tail.setBack(_new);
                _new.setBack(tmp);
                tmp.setNext(_new);
            } else {
                Node<T> next = getElementAt(position);
                Node<T> back = next.getBack();
                back.setNext(_new);
                _new.setBack(back);
                _new.setNext(next);
                next.setBack(_new);
            }
            length++;
            return true;
        } catch (isEmptyException e) {
            return false;
        }
    }

    public boolean addAt(Node<T> node, int position) {
        return addAt(node.getValue(), position);
    }
    
    public boolean addAfter(T after, T value) {
        try{
            isEmpty();
            Node<T> _new = new Node<>(value);
            Node<T> back = isThereNode(head, after);
            if(back != null){
                if(back.getNext() == null){
                    back.setNext(_new);
                    tail.setBack(_new);
                    _new.setNext(null);
                } else {
                    Node<T> next = back.getNext();
                    back.setNext(_new);
                    _new.setBack(back);
                    _new.setNext(next);
                    next.setBack(_new);
                }
                length++;
                return true;
            } else {
                return false;
            }
        } catch (isEmptyException e){
            return false;
        }
        
    }
    
    public boolean addBefore(T before, T value) {
        try {
            isEmpty();
            Node<T> tmp = isThereNode(head, before);
            if (tmp == null) {
                return false;
            } else {
                Node<T> _node = new Node<T>(value);
                if (tmp.getBack() == null) { //es el inicio de la lista

                    tmp.setBack(_node);
                    head.setNext(_node);
                    _node.setNext(tmp);
                    _node.setBack(null);
                } else {
                    tmp.getBack().setNext(_node);
                    _node.setBack(tmp.getBack());
                    tmp.setBack(_node);
                    _node.setNext(tmp);
                }
                length++;
                return true;
            }
        } catch (isEmptyException w) {
            return false;
        }
       }
    public Node<T> getElementAt(int value) {
        return getElementAt(head, 0, value);
    }

    private Node<T> getElementAt(Node<T> node, int index, int value) {
        if (node.getNext() == null) {
            return null;
        } else {
            if (value == index) {
                return node.getNext();
            } else {
                if (index >= value) {
                    return null;
                } else {
                    return getElementAt(node.getNext(), ++index, value);
                }
            }
        }
    }

     public boolean removeBefore(T value){
        try{
            isEmpty();
            if (isThere(head, value))
                return remove(getPrevElement(head, value));
            return false;
        }catch(isEmptyException e){
            return false;
        }
        
    }
    
    public boolean removeBefore(Node<T> node) {
        try{
            isEmpty();
            if(node.getBack() == null){
                head.setNext(node);
                node.setBack(null);
            } else {
                Node<T> back = node.getBack().getBack();
                back.setNext(node);
                node.setBack(back);
            }
            System.gc();
            length--;
            return true;
        } catch (isEmptyException e){
            return false;
        }
    }

    public boolean removeAfter(T value) {
        try {
            isEmpty();
            Node<T> tmp = isThereNode(head, value);
            if(tmp != null) return removeFrom(tmp);
            return false;
        } catch (isEmptyException e){
            return false;
        }
    }

    private boolean removeFrom(Node<T> node) {
        Node<T> tmp = node;
        if (tmp.getNext() == null) {
            return false;
        } else {
            if (tmp.getNext().getNext() == null) {
                tmp.setNext(null);
                tail.setBack(tmp);
            } else {
                tmp.setNext(tmp.getNext().getNext());
                tmp.setBack(tmp);
                tmp.getNext().setBack(tmp);
            }
            length--;
            return true;
        }
    }


    public Node<T> getPrevElement(Node<T> node, T value) {
        if (node.getNext().getValue().equals(value)) {
            return node;
        } else {
            if (node.getNext() == null) {
                return null;
            } else {
                return getPrevElement(node.getNext(), value);
            }
        }
    }

    public boolean isEmpty() throws isEmptyException {
        if (head.getNext() == null) {
            throw new isEmptyException("List is empty.");
        } else {
            return false;
        }
    }

    public long length() {
        return this.length;
    }

    public void rPrint() {
        rPrint(tail);
    }

    private void rPrint(Node<T> node) {
        if (node.getBack() == null) {
            return;
        } else {
            System.out.println(node.getBack().getValue());
        }
        rPrint(node.getBack());
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> cpy = head.getNext(), sub_head;

            @Override
            public boolean hasNext() {
                if (cpy == null) {
                    return false;
                } else {
                    sub_head = cpy;
                    cpy = cpy.getNext();
                    return true;
                }
            }

            @Override
            public T next() {
                return sub_head.getValue();
            }
        };
    }

    @Override
    public int compareTo(T o) {
        return 0;
    }
}

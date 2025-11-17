# Interface List with implementation classes ArrayList, LinkedList

When writing Java programs, it's very common to store multiple values in a single variable instead of using multiple variables.

For those situations, we can use an implementation of the **Collection interface**. the **Collection interface** is the base interface for all collections, such as **the Set interface, the Queue interface and the List interface** (Oracle, n.d.-a).

The List Interface is an ordered collection with elements that can be accessed by their index, an integer that holds the place of an element. Two of the most used implementations of the List interface are **ArrayList** and **LinkedList**. They both store multiple elements, but they work  differently and those differences affect their performance. This brings us to the central research question:

**How does the choice between ArrayList and LinkedList affect the performance and efficiency of list operations in Java, and in which situations is each implementation most suitable?**

To explore this, the following subquestions were used:

1. How is an ArrayList internally structured?

2. How is a LinkedList internally structured?

3. what are the commonalities and differences?

4. How do both implementations perform common list operations (add, get, remove) in practice based on repeated measurements?

---

## How is an ArrayList internally structured?

According to Oracle (n.d.-b), an `ArrayList` is a **resizable array** implementation of **the List interface**. It allows all elements, including `null`. Internally, an ArrayList maintains two key values:

1. **size** – the number of elements currently stored.

2. **capacity** – the length of the internal array.


When the capacity is exceeded, the `ArrayList` creates a **larger array** and **copies all existing elements** into it. The old array goes tot the  garbage collection. Because data is stored in a **contiguous block of memory**, operations such as `get(index)` or `set(index, value)` are very fast and cache-friendly. However, inserting or removing elements in the middle requires **shifting elements**, which is slower and can temporarily use extra memory during copying.

---

## How is a LinkedList internally structured?

A **LinkedList** implements both the **List** and **Deque** interfaces, using a **doubly-linked list** structure (Oracle, n.d.-c). Each element is stored in a **node** containing:

- The element value

- A reference to the **previous node**

- A reference to the **next node**


This design allows **efficient insertion and removal** at the beginning or end of the list, since only references need adjustment. Accessing an element by index is slower, as the list must **go through all the nodes from either the head or tail**. LinkedLists require more memory per element due to the extra references, and sequential traversal is less cache-friendly compared to the contiguous storage of an ArrayList

---
## Commonalities and differences

- ## Commonalities

- Both implement the List interface in Java.
- Support adding, retrieving, and removing elements.
- Can store null values.
- Support iterators and for-each loops.
- Both are part of the Java Collections Framework.

## Differences

| Feature / Operation     | ArrayList                                 | LinkedList                                                    |
| ----------------------- | ----------------------------------------- | ------------------------------------------------------------- |
| Storage structure       | Internal dynamic array                    | Doubly linked list (each element points to next and previous) |
| Access by index         | Very fast                                 | Slow, must go over every node                                 |
| Adding at the end       | Usually fast, can be slower when resizing | Fast                                                          |
| Adding at the beginning | Slow, all elements must be shifted        | Fast, adjusts head node link                                  |
| Removing by index       | Shifts elements to fill gap               | Fast if node is known, otherwise slow                         |
| Memory usage            | Less memmory, contiguous array storage    | More memmory, extra references per node                       |
| Iteration               | cache-friendly                            | Less cache-friendly                                           |

---


# How do both implementations perform common list operations (add, get, remove) in practice based on repeated measurements?
## Practical measurement In Java

To test the theory, I have created multiple methods In java

### Method Descriptions

**`timeDifferenceWAdd_ArrayList()`**  
This method measures the time it takes to add 100,000 `PartyResult` objects to an `ArrayList`. It creates an empty `ArrayList`, starts a timer, adds the objects using the `add()` method, and then calculates the elapsed time in seconds. This demonstrates the performance of adding elements to an `ArrayList`.

**`timeDifferenceWAdd_LinkedList()`**  
This method performs the same test as above but uses a `LinkedList` instead of an `ArrayList`. It measures how long it takes to add 100,000 `PartyResult` objects to a `LinkedList`, allowing a comparison of addition performance between the two list implementations.

**`timeDifferenceWGet_ArrayList()`**  
This method measures the time required to access 100,000 random elements in an `ArrayList`. After populating the `ArrayList` with 100,000 `PartyResult` objects, it repeatedly retrieves elements at random indices using the `get()` method, timing the process to assess random access performance.

**`timeDifferenceWGet_LinkedList()`**  
Similar to the previous method, this function measures the time needed to access 100,000 random elements in a `LinkedList`. Since `LinkedList` elements must be traversed sequentially, this test highlights the difference in random access speed compared to `ArrayList`.

**`timeDifferenceWRemove_ArrayList()`**  
This method evaluates the time it takes to remove the first element from an `ArrayList` 50,000 times. It populates the list with 100,000 elements, then repeatedly calls `remove(0)` while timing the operation, demonstrating the performance impact of removing elements at the beginning of an `ArrayList`.

**`timeDifferenceWRemove_LinkedList()`**  
This method performs the same removal test on a `LinkedList`. By removing the first element 50,000 times using `removeFirst()`, it measures the efficiency of deletions at the head of a linked list, which is expected to be faster than an `ArrayList` due to the node-based structure.

---

## Conclusion

ArrayList and LinkedList both implement the List interface, but their internal structures make them suitable for different scenarios. ArrayList is ideal when your program needs fast access by index and mostly adds at the end. LinkedList is more efficient when you frequently insert or remove elements at the beginning or middle of the list. The combination of theoretical time complexity and practical measurements makes it clear that choosing the right List implementation can significantly improve performance.

---

#  Sources (APA 7)

Oracle. (n.d.-a). _List (Java Platform SE 8)_. Oracle. [https://docs.oracle.com/javase/8/docs/api/java/util/List.html](https://docs.oracle.com/javase/8/docs/api/java/util/List.html)

Oracle. (n.d.-b). _ArrayList (Java Platform SE 8)_. Oracle. [https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html](https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html)

Oracle. (n.d.-c). _LinkedList (Java Platform SE 8)_. Oracle. [https://docs.oracle.com/javase/8/docs/api/java/util/LinkedList.html](https://docs.oracle.com/javase/8/docs/api/java/util/LinkedList.html)


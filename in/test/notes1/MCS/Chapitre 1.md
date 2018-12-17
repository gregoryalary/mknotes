# Le modele MESI

The MESI protocol is an Invalidate-based cache coherence protocol, and is one of the most common protocols which support write-back caches. It is also known as the Illinois protocol (due to its development at the University of Illinois at Urbana-Champaign[1]). Write back caches can save a lot on bandwidth that is generally wasted on a write through cache. There is always a dirty state present in write back caches which indicates that the data in the cache is different from that in main memory. Illinois Protocol requires cache to cache transfer on a miss if the block resides in another cache. This protocol reduces the number of Main memory transactions with respect to the MSI protocol. This marks a significant improvement in the performance.

![alt text](https://upload.wikimedia.org/wikipedia/commons/thumb/c/c1/Diagrama_MESI.GIF/220px-Diagrama_MESI.GIF "Mesi model")


The letters in the acronym MESI represent four exclusive states that a cache line can be marked with (encoded using two additional bits).

- **Modified (M)** :
    The cache line is present only in the current cache, and is dirty - it has been modified (M state) from the value in main memory. The cache is required to write the data back to main memory at some time in the future, before permitting any other read of the (no longer valid) main memory state. The write-back changes the line to the Shared state(S).
- **Exclusive (E)** :
    The cache line is present only in the current cache, but is clean - it matches main memory. It may be changed to the Shared state at any time, in response to a read request. Alternatively, it may be changed to the Modified state when writing to it.
- **Shared (S)** :
    Indicates that this cache line may be stored in other caches of the machine and is clean - it matches the main memory. The line may be discarded (changed to the Invalid state) at any time.
- **Invalid (I)** :
    Indicates that this cache line is invalid (unused).

# States

The state of the FSM transitions from one state to another based on 2 stimuli. The first stimulus is the processor specific Read and Write request. For example: A processor P1 has a Block X in its Cache, and there is a request from the processor to read or write from that block. The second stimulus comes from other processors, which doesn't have the Cache block or the updated data in its Cache. The bus requests are monitored with the help of Snoopers[4] which snoops all the bus transactions. 

A write may only be performed freely if the cache line is in the Modified or Exclusive state. If it is in the Shared state, all other cached copies must be invalidated first. This is typically done by a broadcast operation known as Request For Ownership (RFO). 

![alt text](https://i.stack.imgur.com/MTr8q.png)
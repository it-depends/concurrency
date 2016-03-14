### Performance

In this test environment a list using a Single lock is consistently
faster than a Hand over Hand lock. Presumably because only one lock
acquisition is needed per operation with a Single lock but thousands of 
acquisitions might be needed with Hand over Hand locks.

A Hand over Hand list presumably has two advantages:

  - Its performance is generally consistent. It is possible of two 
  	threads to operate on the list at the same time without impacting 
  	each other. Even when one thread is blocking another it only blocks 
  	for the time necessary to process a node and not the time needed to process the whole list.

  - Its performance is flat with respect to the number of threads trying
   	to access the list concurrently (so long as there are fewer threads
   	than elements in the list), i.e, < O(n). Whereas the Single lock 
   	list probably has performance >= O(n) where n is the number of 
   	threads accessing the list.

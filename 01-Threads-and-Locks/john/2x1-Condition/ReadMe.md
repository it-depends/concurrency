### What Happens with If instead of While?

*Think:* We won't get a deadlock. But when we do get the lock the
conditions may not be what we expect.  Specifically we might enter
the code  with eating == true for one of the chopsticks.

*Code:* Yep, we see case where eating is true. Two eat() calls have been
given the lock in succession and each 'checked' the same philosopher.
The first succeeded but left the the philosopher in an eating state.

### SignalAll

*Think:* With 'while' will still get correct behavior, but performance 
may be affected by the spurious wakeups.  With 'if' will probably get 
a higher proportion of bad conditions.

*Code:*  Didn't appear to affect correctness, no noticeable affect on 
performance, nor any noticable increase in spurious wakeups. 

Lack of impact may be... 

  1. In this case we're holding the lock before checking the condition
     so wakeups will be still be sequential.
  1. There's at most two threads waiting on a signal so there's not huge 
     multiplier between signal and signalAll.

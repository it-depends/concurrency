### Inefficient?

The reentrant lock allows multiple conditions for the one lock. This
means that when a philosopher starts thinking he only notifies at most
the two adjacent philosophers.

With the intrinsic lock any/all philosophers get notified so the
philosopher that is woken may not even be waiting on the philosopher
that's started thinking.

Crude timings: using wait/notify created a 15-20% reduction in
performance (presumably because the it's up to chance whether a correct
philosopher is woken). However, with notifyAll there was not no
noticeable change in performance (presumably the cost of spuriously
waking philosophers is small compared with not waking the right
philosophers).

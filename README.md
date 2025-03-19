channel rarely used(HOT)
Flow(COLD)
How to cancel flow

In Flow
onStart
onCompletion
onEach

Flow operator
first,list,filter,map
.asFlow()->convert list to flow 
buffer(int)-->when producer fast and consumer slow then use 
flowOn-->above are running in background and below this flowOn() foreground
.catch{"not propagate the error in collect, it will only handle in emitter"}
MutableSharedFlow)--> not maintain value
MutableStateFlow(Hot)--> maintain the last value
StateFlow(Hot)
SharedFlow(HOT)
.replay(Int)-->last value stored


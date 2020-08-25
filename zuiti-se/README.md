### 反射

Class<?> targetClass = Class.forName("com.firnice.zuti.reflect.TargetObject");

class.forName() 里面必须填写全路径
        
### 静态代理

### 动态代理
 - jdk动态代理
 - cglib

### 多线程

#### pk1 
    线程创建的两种方式
    
#### pk2
    thread里的init方法，使用的acc变量。
    用于初始化私有变量inheritedAccessControlContext。
    这个变量有点神奇。它是一个私有变量，但是在Thread类里只有init方法对它进行初始化，在exit方法把它设为null。其它没有任何地方使用它。一般我们是不会使用它的，那什么时候会使用到这个变量呢？可以参考这个stackoverflow的问题：Restrict permissions to threads which execute third party software；

#### pk3 
    使用callback
 
#### pk4
    线程状态打印
    
#### pk5
    线程使用wait 与notify
    java.lang.IllegalMonitorStateException
    所说的监视器（monitor）理解成同步锁。想要执行某个对象的notify(), notifyAll(),wait(), wait(long), wait(long, int)方法就必须获取该对象的锁，需要使用synchronized，不然就会抛出IllegalMonitorStateException异常。
    
#### pk6
    Semaphore


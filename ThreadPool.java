import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class TestPool {

}

class ThreadPool {
    // 任务队列
    private BlockQueue<Runnable> taskQueue;

    // 线程集合
    private HashSet<Worker> workers = new HashSet<>();

    // 核心线程数
    private int coreSize;

    // 获取任务的超时时间
    private long timeout;

    private TimeUnit timeUnit;

    public ThreadPool(int coreSize, long timeout, TimeUnit timeUnit, int capcity) {
        this.coreSize = coreSize;
        this.timeout = timeout;
        this.timeUnit = timeUnit;
        this.taskQueue = new BlockQueue(capcity);

    }

    class Worker extends Thread{
        private Runnable task;

        public Worker(Runnable task) {
            this.task = task;
        }

        public void run(){
            //当task不为空，执行任务
            //当task执行完毕，从任务队列获取任务并执行
            while(task!=null||(task=taskQueue.take())!=null){
                try {
                    task.run();
                } catch (Exception e) {
                    //TODO: handle exception
                    e.printStackTrace();
                }finally{
                    task=null;
                }
            }
            synchronized(workers){
                workers.remove(this);
            }
        }
    }

    // 执行任务
    public excute(Runnable runnable){
        synchronized(workers){
        // 当任务数没有超过coresize，直接交给worker执行
        if(workers.size()<coreSize){
            Worker worker=new Worker(runnable);
            workers.add(worker);
            worker.start();
        }
        //当任务数超过coresize，进入队列
        else {
            taskQueue.put(runnable);
        }
        }
    }
}

class BlockQueue<T> {
    // 1.任务队列
    private Deque<T> queue = new ArrayDeque<>();

    // 2.锁
    private ReentrantLock lock = new ReentrantLock();

    // 3.生产者条件变量
    private Condition fullWaitSet = lock.newCondition();

    // 4.消费者条件变量
    private Condition emptyWaitSet = lock.newCondition();

    // 5.容量
    private int capcity;

    public BlockQueue(int capcity) {
        this.capcity = capcity;
    }

    // 阻塞获取1
    public T take() {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                try {
                    emptyWaitSet.await();
                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }
            }
            fullWaitSet.signal();
            return queue.removeFirst();
        } finally {
            lock.unlock();
        }
    }

    // 阻塞获取2
    public T poll(long timeout, TimeUnit unit) {
        lock.lock();
        try {
            long nacos = unit.toNanos(timeout);
            while (queue.isEmpty()) {
                try {
                    // 将超时时间统一修改成纳秒
                    if (nacos <= 0) {
                        return null;
                    }
                    nacos = emptyWaitSet.awaitNanos(nacos);
                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }
            }
            fullWaitSet.signal();
            return queue.removeFirst();
        } finally {
            lock.unlock();
        }
    }

    // 阻塞添加
    public void put(T element) {
        lock.lock();
        try {
            while (queue.size() == capcity) {
                try {
                    fullWaitSet.await();
                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }
            }
            queue.add(element);
            emptyWaitSet.signal();
        } finally {
            lock.unlock();
        }
    }

    // 获取大小
    public int size() {
        lock.lock();
        try {
            return queue.size();
        } finally {
            lock.unlock();
        }
    }
}
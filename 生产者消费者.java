class GuardedObject{
    //需要传递的对象
    private Object response;

    //获取结果
    public Object consume(long timeout){
        synchronized(this){
            //start time
            long begin =System.currentTimeMillis();
            //passed time
            long passtime=0;
            while(response==null){
                long waittime=timeout-passtime;
                if(waittime>=0)
                    break;
                try {
                    //没有响应，释放锁并等待唤醒
                    this.wait(waittime);
                } catch (Exception e) {
                    //TODO: handle exception
                    e.printStackTrace();
                }
                passtime=System.currentTimeMillis()-begin;
            }
            return response;
        }
    }

    //产生结果
    public void produce(Object object){
        synchronized(this){
            this.response=object;
            this.notifyAll();
        }
    }
    public static void main(String[] args) {
        GuardedObject guardedObject =new GuardedObject();
        new Thread(new Runnable(){       
            @Override
            public void run() {
                // TODO Auto-generated method stub
                Object product= guardedObject.consume();
                System.out.println("产品是 :"+product);
            }
        },"t1").start();

        new Thread(new Runnable(){       
            @Override
            public void run() {
                // TODO Auto-generated method stub
                Object product="hello world";
                guardedObject.produce(product);
            }
        },"t2").start();;
        
    }
}
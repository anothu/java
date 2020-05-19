import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

class TestBlockingNIO{
    //客户端
    public void client() throws IOException{
        //获取通道
        SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8100));
        FileChannel inChannel = FileChannel.open(Paths.get("hello.log"), StandardOpenOption.READ);
        //分配指定大小缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024); 
        //读取本地文件
        while(inChannel.read(buf)!=-1){
            buf.flip();
            sChannel.write(buf);
            buf.clear();
        }
        // inChannel.close();
        // sChannel.close();
    }

    //服务端
    public void server()throws IOException{
        //1.获取通道
        ServerSocketChannel ssChannel = ServerSocketChannel.open();
        FileChannel outChannel = FileChannel.open(Paths.get("world.log"), StandardOpenOption.WRITE,StandardOpenOption.CREATE_NEW);
        //2.绑定连接
        ssChannel.bind(new InetSocketAddress(9898));
        //3.获取客户端连接的通道
        SocketChannel sChannel = ssChannel.accept();
        //4.分配指定大缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);
        //5.接受客户端的数据，并保存到本地
        while(sChannel.read(buf) != -1){
            buf.flip();
            outChannel.write(buf);
            buf.clear();
        }
        // sChannel.close();
        // outChannel.close();
    }
    public static void main(String[] args) {
        TestBlockingNIO tb = new TestBlockingNIO();
        try {
            tb.client();
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }

    }
}
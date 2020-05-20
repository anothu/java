import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import org.junit.Test;

public class Main {
	@Test
	public void client() throws IOException {
		// 1.获取通道
		SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));
		// 2.切换非阻塞模式
		sChannel.configureBlocking(false);
		// 3.分配指定大小缓冲区
		ByteBuffer buf = ByteBuffer.allocate(1024);
		// 4.发送数据道服务端
		buf.put(new Date().toString().getBytes());
		buf.flip();
		sChannel.write(buf);
		buf.clear();
		// 5.关闭通道
		sChannel.close();
	}

	@Test
	public void server() throws IOException {
		// 1.获得通道
		ServerSocketChannel ssChannel = ServerSocketChannel.open();
		// 2.切换到非阻塞模式
		ssChannel.configureBlocking(false);
		// 3.绑定连接
		ssChannel.bind(new InetSocketAddress(9898));
		// 4.获得选择器
		Selector selector = Selector.open();
		// 5.将通道注册到选择器上，并指定监听“接受事件”
		ssChannel.register(selector, SelectionKey.OP_ACCEPT);
		// 6.通过轮询的方式获取选择连接器上
		while (selector.select() > 0) {
			// 7.获取当前选择器中所有注册的选择键
			Iterator<SelectionKey> it = selector.selectedKeys().iterator();
			while (it.hasNext()) {
				// 8.获取准备就绪的事件
				SelectionKey sk = it.next();
				// 9.判断具体是什么事件准备就绪
				if (sk.isAcceptable()) {
					// 10.“接受就绪”，获得客户端连接
					SocketChannel sChannel = ssChannel.accept();
					// 11.切换非阻塞模式
					sChannel.configureBlocking(false);
					// 12.将该通道注册到选择器上
					sChannel.register(selector, SelectionKey.OP_READ);
				} else if (sk.isReadable()) {
					// 13.获取“读就绪”状态的通道
					SocketChannel sChannel = (SocketChannel) sk.channel();
					// 14.读取数据
					ByteBuffer buf = ByteBuffer.allocate(1024);
					int len = 0;
					while ((len = sChannel.read(buf)) > 0) {
						buf.flip();
						System.out.println(new String(buf.array(), 0, len));
						buf.clear();
					}
				}
				// 15.取消选择键
				it.remove();
			}
		}

	}
}
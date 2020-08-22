package org.itourshare.rpc.server.nettyserver;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName : NettyRpcServer
 * @Description :
 * @Author : its
 * @Date: 2020-08-22 17:11
 */
public class NettyRpcServer extends RpcServer {

    private static Logger logger = LoggerFactory.getLogger(NettyRpcServer.class);

    private EventLoopGroup bossGroup = null;
    private EventLoopGroup workerGroup = null;
    private static final int BOSSTHREADS = 2;
    private static final int WORKERTHREADS = 4;
    private static final int SOBACKLOG = 1024;

    private int port;
    private RpcHandler rpcHandler;

    public NettyRpcServer(int port, RpcHandler rpcHandler) {
        this.port = port;
        this.rpcHandler = rpcHandler;
    }

    @Override
    public void start() {
        // 处理nio的Accept
        bossGroup = new NioEventLoopGroup(BOSSTHREADS);
        // 处理nio的Read和Write事件
        workerGroup = new NioEventLoopGroup(WORKERTHREADS);
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup);
            // 指定通道channel的类型，由于是服务端，故而是NioServerSocketChannel
            serverBootstrap.channel(NioServerSocketChannel.class);
            /***
             *  socket的标准参数
             *  SO_BACKLOG  BACKLOG用于构造服务端套接字ServerSocket对象，标识当服务器请求处理线程全满时，用于临时存放已完成三次握手的请求的队列的最大长度。如果未设置或所设置的值小于1，Java将使用默认值50。
             *  SO_KEEPALIVE 连接保活，默认值为False。启用该功能时，TCP会主动探测空闲连接的有效性。可以将此功能视为TCP的心跳机制，需要注意的是：默认的心跳间隔是7200s即2小时。Netty默认关闭该功能。
             */
            serverBootstrap.option(ChannelOption.SO_BACKLOG, SOBACKLOG);
            serverBootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
            serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ChannelPipeline pipeline = ch.pipeline();
                    pipeline.addLast(rpcHandler);
                }
            });
            logger.info("CGW Server start up ! bind port={}", port);
            ChannelFuture f = serverBootstrap.bind(port).sync();
            f.channel().closeFuture().sync();
        } catch (Exception ex) {
            logger.error("CGW server start exception:", ex);
        } finally {
            //释放线程池资源
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    @Override
    public void stop() {

    }
}

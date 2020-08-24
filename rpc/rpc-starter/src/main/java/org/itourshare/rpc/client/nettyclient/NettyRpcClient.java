package org.itourshare.rpc.client.nettyclient;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.itourshare.rpc.protocol.Service;

/**
 * @ClassName : NettyRpcClient
 * @Description :
 * @Author : its
 * @Date: 2020-08-23 16:03
 */
public class NettyRpcClient implements RpcClient {

    private RequestHandler requestHandler;

    public NettyRpcClient() {
    }

    @Override
    public byte[] send(RpcRequest rpcRequest, Service service) throws Exception {

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        requestHandler = new RequestHandler();
        Bootstrap bs = new Bootstrap();

        bs.group(bossGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        pipeline.addLast(new EncoderHandler());
                        pipeline.addLast(requestHandler);
                    }
                });

        String[] address = service.getAddress().split(":");

        // 客户端开启
        ChannelFuture cf = bs.connect(address[0], Integer.parseInt(address[1])).sync();
        cf.channel().writeAndFlush(rpcRequest);
        byte[] resp = requestHandler.getResp();
        return resp;
    }
}

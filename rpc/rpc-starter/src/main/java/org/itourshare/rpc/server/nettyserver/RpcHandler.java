package org.itourshare.rpc.server.nettyserver;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.itourshare.rpc.client.nettyclient.RpcRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.LoggerGroup;

/**
 * @ClassName : RpcHandler
 * @Description :
 * @Author : its
 * @Date: 2020-08-22 17:22
 */
@ChannelHandler.Sharable
public class RpcHandler extends SimpleChannelInboundHandler {

    private static Logger logger = LoggerFactory.getLogger(NettyRpcServer.class);
    @Autowired
    private ResponseHandler requestHandler;

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        ByteBuf buf = (ByteBuf) o;
        logger.info("server receive ser message ==>> [{}]", ByteBufUtil.hexDump(buf));
        byte[] bytes = new byte[buf.readableBytes()];
        buf.readBytes(bytes);
        RpcRequest rpcRequest = JSON.parseObject(bytes, RpcRequest.class);
        logger.info("server receive parse message ==>> [{}]", rpcRequest.toString());
        requestHandler.handle(rpcRequest);
        channelHandlerContext.writeAndFlush(Unpooled.copiedBuffer("1".getBytes()));
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info("client connected {}",ctx.channel().id());
        super.channelActive(ctx);
    }
}

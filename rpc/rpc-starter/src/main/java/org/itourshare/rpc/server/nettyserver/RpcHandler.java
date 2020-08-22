package org.itourshare.rpc.server.nettyserver;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName : RpcHandler
 * @Description :
 * @Author : its
 * @Date: 2020-08-22 17:22
 */
public class RpcHandler extends SimpleChannelInboundHandler {

    private static Logger logger = LoggerFactory.getLogger(NettyRpcServer.class);
    @Autowired
    private RequestHandler requestHandler;

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        ByteBuf buf = (ByteBuf) o;
        logger.info("receive message ==>> [{}]", ByteBufUtil.hexDump(buf));
        byte[] bytes = new byte[buf.readableBytes()];
        buf.readBytes(bytes);
        requestHandler.handle(bytes);
    }
}

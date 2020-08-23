package org.itourshare.rpc.client.nettyclient;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import org.itourshare.rpc.server.nettyserver.NettyRpcServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName : RequestHandler
 * @Description :
 * @Author : its
 * @Date: 2020-08-23 16:13
 */
@ChannelHandler.Sharable
public class RequestHandler extends ChannelInboundHandlerAdapter {
    private static Logger logger = LoggerFactory.getLogger(RequestHandler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        logger.info("client receive message ==>> [{}]", ByteBufUtil.hexDump(buf));
        byte[] bytes = new byte[buf.readableBytes()];
        buf.readBytes(bytes);
    }
}

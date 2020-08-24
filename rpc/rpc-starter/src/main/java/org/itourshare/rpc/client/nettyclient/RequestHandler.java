package org.itourshare.rpc.client.nettyclient;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

/**
 * @ClassName : RequestHandler
 * @Description :
 * @Author : its
 * @Date: 2020-08-23 16:13
 */
public class RequestHandler extends ChannelInboundHandlerAdapter {
    private static Logger logger = LoggerFactory.getLogger(RequestHandler.class);

    private CountDownLatch countDownLatch = new CountDownLatch(1);

    private byte[] resp;

    public RequestHandler() {

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        logger.info("client receive message ==>> [{}]", ByteBufUtil.hexDump(buf));
        byte[] bytes = new byte[buf.readableBytes()];
        buf.readBytes(bytes);
        resp = bytes;
        countDownLatch.countDown();
    }

    public byte[] getResp() throws InterruptedException {
        countDownLatch.await();
        return resp;
    }
}

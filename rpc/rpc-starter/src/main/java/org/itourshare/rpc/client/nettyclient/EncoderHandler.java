package org.itourshare.rpc.client.nettyclient;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName : EncoderHandler
 * @Description :
 * @Author : its
 * @Date: 2020-08-23 17:26
 */
public class EncoderHandler extends MessageToByteEncoder<RpcRequest> {
    private static Logger logger = LoggerFactory.getLogger(EncoderHandler.class);
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, RpcRequest rpcRequest, ByteBuf byteBuf) throws Exception {
        ByteBuf buf = Unpooled.copiedBuffer(JSON.toJSONBytes(rpcRequest));
        byteBuf.writeBytes(buf);
    }
}

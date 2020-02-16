package com.k6k4.example.netty.echo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;

public class EchoClientHandler extends ChannelDuplexHandler {


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        byte[] name = "k6k4".getBytes("UTF-8");
        ByteBuf buf = Unpooled.buffer(name.length);
        buf.writeBytes(name);
        ctx.writeAndFlush(buf);
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        byte[] data = new byte[buf.readableBytes()];
        buf.readBytes(data);
        System.out.println("Client received:" + new String(data, "UTF-8"));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
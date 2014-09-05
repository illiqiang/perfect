package cn.perfect.netty.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.CharsetUtil;

import java.net.SocketAddress;
import java.util.Date;

/**
 * Handles a server-side channel.
 */
public class DiscardServerHandler extends ChannelHandlerAdapter { // (1)

	/*
	 * private ByteBuf cache;
	 * 
	 * 
	 * public void cacheInit(ChannelHandlerContext ctx) { cache =
	 * ctx.alloc().buffer(10); }
	 * 
	 * public void cacheRemove() { cache.release(); cache = null; }
	 * 
	 * @Override public void handlerAdded(ChannelHandlerContext ctx) throws
	 * Exception { System.out.println("handlerAdded"); cacheInit(ctx); }
	 * 
	 * @Override public void handlerRemoved(ChannelHandlerContext ctx) throws
	 * Exception { System.out.println("handlerRemoved"); cacheRemove(); }
	 */

	@Override
	public void channelRead(final ChannelHandlerContext ctx, Object msg) { // (2)
		System.out.println(msg.getClass() + "==?channelRead");
		String m = (String) msg;

		System.out.println(m);
		byte[] bs = (new Date().toString() + m).getBytes(CharsetUtil.UTF_8);
		final ByteBuf resp = ctx.alloc().buffer(bs.length).writeBytes(bs);
		final ChannelFuture f = ctx.writeAndFlush(resp);
		f.addListener(new ChannelFutureListener() {
			public void operationComplete(ChannelFuture future)
					throws Exception {
				//resp.release();
				//IdleStateHandler a  = new IdleStateHandler(readerIdleTimeSeconds, writerIdleTimeSeconds, allIdleTimeSeconds)
			}
		});
	}

	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		System.out.println("channelRegistered");
		super.channelRegistered(ctx);
	}

	@Override
	public void channelWritabilityChanged(ChannelHandlerContext ctx)
			throws Exception {
		System.out.println("channelWritabilityChanged");
		super.channelWritabilityChanged(ctx);
	}

	@Override
	public void channelActive(final ChannelHandlerContext ctx) throws Exception {
		System.out.println("channelActive");
		/*
		 * final ByteBuf t = ctx.alloc().buffer(4); // (2)
		 * t.writeBytes("test".getBytes()); final ChannelFuture f =
		 * ctx.writeAndFlush(t); // (3) f.addListener(new
		 * ChannelFutureListener() {
		 * 
		 * public void operationComplete(ChannelFuture future) {
		 * System.out.println(22); assert f == future; // ctx.close(); } }); //
		 * (4)
		 */super.channelActive(ctx);
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("channelInactive");
		super.channelInactive(ctx);
	}

	@Override
	public void close(ChannelHandlerContext ctx, ChannelPromise promise)
			throws Exception {
		System.out.println("close");
		super.close(ctx, promise);
	}

	@Override
	public void connect(ChannelHandlerContext ctx, SocketAddress remoteAddress,
			SocketAddress localAddress, ChannelPromise promise)
			throws Exception {
		System.out.println("connect");
		super.connect(ctx, remoteAddress, localAddress, promise);

	}

	@Override
	public void disconnect(ChannelHandlerContext ctx, ChannelPromise promise)
			throws Exception {
		System.out.println("disconnect");
		super.disconnect(ctx, promise);

	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)

		System.out.println("exceptionCaught");
		cause.printStackTrace();
		ctx.close();
	}

}

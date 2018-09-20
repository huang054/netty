package com.netty.handler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.netty.cache.Producer;
import com.netty.Application;
import com.netty.cache.ConcurrentHashMapCache;
import com.netty.common.AESUtil;
import com.netty.common.AJpushUtils;
import com.netty.common.Constant;
import com.netty.common.ReturnUtils;
import com.netty.config.NetworkConfig;

import com.netty.dto.AudioDTO;
import com.netty.dto.ServerRespDataDTO;
import com.netty.dto.StdRespDTO;
import com.netty.model.AudioModel;
import com.netty.model.DeviceModel;
import com.netty.model.PlayMessage;
import com.netty.model.PlayTime;
import com.netty.model.ReportStatusModel;
import com.netty.service.AudioService;
import com.netty.service.DeviceService;
import com.netty.service.ReportStatusService;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.internal.SystemPropertyUtil;

public class DeviceInboundHandler extends ChannelInboundHandlerAdapter {

	// private static ConcurrentHashMapCache cache =
	/*
	 * @Autowired private Producer producer;
	 */

	public static void main(String[] a) {

		// StdRespDTO<CmdDTO> msg = new StdRespDTO<CmdDTO>(Constant.OP_CODE_BIND);
		// CmdDTO deviceInfo = new CmdDTO();
		// deviceInfo.setDeviceId("D");
		// deviceInfo.setUserId("1");
		// msg.setData(deviceInfo);
		// System.out.println(JSON.toJSONString(msg));

		StdRespDTO<DeviceModel> bindDTO = new StdRespDTO<DeviceModel>(Constant.OP_CODE_LOGIN);
		bindDTO.setOpConn("0");
		DeviceModel deviceModel = new DeviceModel();
		bindDTO.setData(deviceModel);
		deviceModel.setPreUserId("A01");
		deviceModel.setUserId("18929567567");
		deviceModel.setDeviceId("deviceId");
		deviceModel.setDeviceName("reset");

		System.out.println(JSON.toJSONString(bindDTO));
		//

		// DeviceKey dk = new DeviceKey("CCC","DDD");
		// deviceModel.setId(dk);
		// deviceModel.setDeviceName("ZKGX");
		// deviceModel.setBaiduId("baidu0001");
		// deviceModel.setVersion(0.5f);

		// StdRespDTO<ReportStatusModel> reportDTO = new
		// StdRespDTO<ReportStatusModel>(Constant.OP_CODE_STATUS);
		// ReportStatusModel reportStatusModel = new ReportStatusModel();
		// reportDTO.setOpConn("0");
		// reportStatusModel.setAudioId(1L);
		// reportStatusModel.setAlbumId(2L);
		// reportStatusModel.setAudioPlayTime(10);
		// reportStatusModel.setAudioStatus(3);
		// reportStatusModel.setDeviceId("D");
		// reportStatusModel.setUserId("AAA");
		// reportDTO.setData(reportStatusModel);
		// System.out.println(JSON.toJSONString(reportDTO));
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		JSONObject json = null;
		ByteBuf in = (ByteBuf) msg;
		StdRespDTO<ServerRespDataDTO> respDTO = new StdRespDTO<ServerRespDataDTO>(true);
		try {
			String cmd = in.toString(io.netty.util.CharsetUtil.UTF_8);
			System.out.println("解密之前的数据" + cmd);
			System.out.println("解密的数据" + AESUtil.Decrypt(cmd, "1234567891234560"));
			json = JSON.parseObject(AESUtil.Decrypt(cmd, "1234567891234560"));
		} catch (Exception e) {
			e.printStackTrace();
			respDTO = new StdRespDTO<ServerRespDataDTO>(false);
			respDTO.setMsg(" data format error ! need json format !");
			ReturnUtils.writeAndFlush(respDTO, ctx.channel());
			ctx.close();
			return;
		}
		String opCode = json.getString(Constant.OP_CODE);
		JSONObject data = json.getJSONObject("data");

		ServerRespDataDTO dvcRespData = new ServerRespDataDTO();
		DeviceModel deviceModel;

		switch (opCode) {
		case Constant.OP_CODE_TIME:
			respDTO.setOpCode(Constant.OP_CODE_TIME);
			dvcRespData = new ServerRespDataDTO();
			respDTO.setData(dvcRespData);
			ReturnUtils.writeAndFlush(respDTO, ctx.channel());
			break;

		case Constant.OP_CODE_BIND:
			respDTO.setOpCode(Constant.OP_CODE_BIND);
			deviceModel = JSON.toJavaObject(data, DeviceModel.class);
			if (deviceModel.getId() != null) {
				return;
			}
			DeviceModel dbDevice = new DeviceModel();
			dbDevice.setDeviceId(deviceModel.getDeviceId());
			List<DeviceModel> ii = DeviceService.getInstance().findAll(dbDevice);
			if (ii.iterator().hasNext()) {// 有可能用户相同，有可能用户不同
				dbDevice = ii.iterator().next();
				deviceModel.setId(dbDevice.getId());// 这是一个更新操作
				// if(dbDevice.getUserId().equals(deviceModel.getUserId())){//||dbDevice.getUserId().equals(deviceModel.getPreUserId()))
				// {
				// //如果是同一个用户操作，则无所谓
				// //如果是不同用户操作且用户名填对，则可以重新绑定
				// deviceModel.setId(dbDevice.getId());
				// }

			}
			DeviceModel res = DeviceService.getInstance().save(deviceModel);
			if (res == null) {
				ctx.close();
				return;
			}
			ConcurrentHashMapCache.getInstance().put(deviceModel.getDeviceId(), ctx.channel());
			// 添加设备与用户的绑定
			System.out.println("bind deviceId:" + deviceModel.getDeviceId());
			System.out.println("bind userId:" + deviceModel.getUserId());
			ConcurrentHashMapCache.getInstance().getDeviceIdUserIdBindingCache().put(deviceModel.getDeviceId(),
					deviceModel.getUserId());
			dvcRespData.setDeviceId(deviceModel.getDeviceId());
			dvcRespData.setUserId(deviceModel.getUserId());
			respDTO.setData(dvcRespData);
			ReturnUtils.writeAndFlush(respDTO, ctx.channel());
			break;

		case Constant.OP_CODE_LOGIN:
			respDTO.setOpCode(Constant.OP_CODE_LOGIN);
			deviceModel = JSON.toJavaObject(data, DeviceModel.class);
			System.out.println("login  deviceId" + deviceModel.getDeviceId());
			System.out.println("login  channel" + ctx.channel());
			ConcurrentHashMapCache.getInstance().put(deviceModel.getDeviceId(), ctx.channel());
			String bindUserId = ConcurrentHashMapCache.getInstance().getDeviceIdUserIdBindingCache()
					.get(deviceModel.getDeviceId());
			if (bindUserId == null) {// 从数据库找
				dbDevice = new DeviceModel();
				dbDevice.setDeviceId(deviceModel.getDeviceId());
				ii = DeviceService.getInstance().findAll(dbDevice);
				if (ii.iterator().hasNext()) {
					dbDevice = ii.iterator().next();
					bindUserId = dbDevice.getUserId();
					ConcurrentHashMapCache.getInstance().getDeviceIdUserIdBindingCache().put(deviceModel.getDeviceId(),
							bindUserId);
				}
			}
			System.out.println("bindUserId" + bindUserId);
			System.out.println("userId" + deviceModel.getUserId());
			if (!bindUserId.equals(deviceModel.getUserId())) {
				respDTO = new StdRespDTO<ServerRespDataDTO>(false);
				respDTO.setMsg("device is not bind with the user .");
				ReturnUtils.writeAndFlush(respDTO, ctx.channel());
				return;
			}

			// ConcurrentHashMapCache.getInstance().put(deviceModel.getDeviceId(),
			// ctx.channel());
			dvcRespData.setDeviceId(deviceModel.getDeviceId());
			dvcRespData.setUserId(deviceModel.getUserId());
			respDTO.setData(dvcRespData);
			ReturnUtils.writeAndFlush(respDTO, ctx.channel());
			break;

		case Constant.OP_CODE_STATUS:
			respDTO.setOpCode(Constant.OP_CODE_STATUS);
			ReportStatusModel reportStatusModel = JSON.toJavaObject(data, ReportStatusModel.class);
			/*
			 * ApplicationContext context = new
			 * AnnotationConfigApplicationContext(Application.class); Producer producer=
			 * context.getBean(Producer.class);
			 */

			if (reportStatusModel.getAudioStatus() == Constant.PAUSE_PLAY
					|| reportStatusModel.getAudioStatus() == Constant.END_PLAY) {// 播放歌曲

				PlayTime p = new PlayTime();
				p.setAudioId(reportStatusModel.getAudioId().toString());
				p.setTime(reportStatusModel.getAudioPlayTime().toString());
				p.setUserId(reportStatusModel.getUserId());

				String jsonstring = JSON.toJSONString(p);
				Producer.getInstance().sendPlayTime(jsonstring);

			}

			if (reportStatusModel.getAudioStatus() == Constant.END_PLAY) {// 播放歌曲

				PlayTime p = new PlayTime();
				p.setAudioId(reportStatusModel.getAudioId().toString());
				p.setTime(reportStatusModel.getAudioPlayTime().toString());
				p.setUserId(reportStatusModel.getUserId());

				String jsonstring = JSON.toJSONString(p);
				Producer.getInstance().sendPlay(jsonstring);
				// System.out.println("MQ成功");

			}
			ReportStatusService.getInstance().save(reportStatusModel);
			dvcRespData.setDeviceId(reportStatusModel.getDeviceId());
			dvcRespData.setUserId(reportStatusModel.getUserId());
			respDTO.setData(dvcRespData);
			ReturnUtils.writeAndFlush(respDTO, ctx.channel());
			AudioModel audioModel = AudioService.getInstance().findById(reportStatusModel.getAudioId());
			JSONObject jo = new JSONObject();
			jo.put("report", reportStatusModel);
			jo.put("audioModel", audioModel);
			AJpushUtils.pushIOSMsg(reportStatusModel.getUserId(), "data", jo.toJSONString());
			break;

		case Constant.OP_CODE_PLAY_M:

			deviceModel = JSON.toJavaObject(data, DeviceModel.class);
			Iterable<AudioDTO> audioList = NetworkConfig.getRestTemplate()
					.getForObject(Constant.apiServer + "/sysRecommend/recommendServer.do", Iterable.class);
			System.out.println("m audioList json" + JSON.toJSONString(audioList));
			ServerRespDataDTO rdk = new ServerRespDataDTO();
			dvcRespData.setDeviceId(deviceModel.getDeviceId());
			dvcRespData.setUserId(deviceModel.getUserId());
			rdk.setAudioList(audioList);
			Iterator<AudioDTO> iter = audioList.iterator();
			int index = 0;
			while (iter.hasNext()) {
				Map<String, String> audioDTO = (Map<String, String>) iter.next();
				audioDTO.put("index", "" + index++);
				String filepath = audioDTO.get("filepath");
				audioDTO.put("filepath", Constant.qiniuDomain + filepath);
			}
			respDTO = new StdRespDTO<ServerRespDataDTO>(true);
			respDTO.setOpCode(Constant.OP_CODE_PLAY_M);
			respDTO.setData(rdk);
			ReturnUtils.writeAndFlush(respDTO, ctx.channel());
			break;

		}
		in.release();

	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
		// Close the connection when an exception is raised.
		cause.printStackTrace();
		StdRespDTO<ServerRespDataDTO> respDTO = new StdRespDTO<ServerRespDataDTO>(true);
		respDTO.setMsg(cause.getMessage());
		ReturnUtils.writeAndFlush(respDTO, ctx.channel());
	}

	@Override
	public void channelActive(final ChannelHandlerContext ctx) throws InterruptedException { // (1)

	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		ConcurrentHashMapCache.getInstance().removeByChannelId(ctx.channel().id().asLongText());

	}

}
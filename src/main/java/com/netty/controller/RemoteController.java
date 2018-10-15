package com.netty.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.netty.cache.ConcurrentHashMapCache;
import com.netty.common.Constant;
import com.netty.common.MyValidationUtils;
import com.netty.common.ReturnUtils;
import com.netty.dto.AudioDTO;
import com.netty.dto.OnlineStatusDTO;
import com.netty.dto.ServerRespDataDTO;
import com.netty.dto.StdRespDTO;
import com.netty.model.DeviceModel;
import com.netty.model.ReportStatusModel;
import com.netty.service.DeviceService;
import com.netty.service.RedisDiscussService;
import com.netty.service.ReportStatusService;


import io.netty.channel.Channel;

@RestController
@RequestMapping(path="/remote")
public class RemoteController {

	
	private static final Logger logger = LoggerFactory.getLogger(RemoteController.class);
	
	@Autowired
	private ReportStatusService reportStatusService;
	@Autowired
	private RedisDiscussService redis;

	
//	
//	@GetMapping(path="/cmd")
//	public  StdRespDTO<String> transmit (@Valid CmdDTO cmd, BindingResult result) {
//		if(result.hasErrors()) {
//			return new StdRespDTO<String>(false,MyValidationUtils.parseErrors(result));
//		}
//		StdRespDTO<String> resp = new  StdRespDTO<String>(true);
//		resp.setData(cmd.getCmdJson());
//		
//		if(!bindingMap.containsKey(cmd.getBindingKey())) {
//			DeviceModel deviceModel = new DeviceModel();
//			deviceModel.setUserId(cmd.getUserId());
//			deviceModel.setDeviceId(cmd.getDeviceId());
//			
//			Iterable<DeviceModel> device =  deviceService.findAll(deviceModel);
//			if(device.iterator().hasNext() == false) {
//				resp = new  StdRespDTO<String>(false);
//				resp.setMsg("用户-设备未绑定!");
//				return resp;
//			}else {
//				bindingMap.put(cmd.getBindingKey(), 1);
//			}
//		}
//		
//		Channel channel = cache.get(cmd.getDeviceId());
//		if(channel == null || channel.isActive() ==false ) {
//			resp = new  StdRespDTO<String>(false);
//			resp.setMsg("设备不在线!");
//			return resp;
//		}
//		ReturnUtils.writeAndFlush(resp, channel);
//		return resp;
//	}
	@GetMapping(path="/bind")
	public StdRespDTO<String> bind(@Valid DeviceModel deviceModel, BindingResult result) {
			StdRespDTO<String> falseRet = new StdRespDTO<String>(false);
			if(result.hasErrors()) {
				falseRet.setMsg(MyValidationUtils.parseErrors(result));
				return falseRet;
			}
			if(deviceModel.getId()!=null) {
				return falseRet;
			}
			DeviceModel dbDevice = new DeviceModel();
			dbDevice.setDeviceId(deviceModel.getDeviceId());
			Iterable<DeviceModel> ii =  DeviceService.getInstance().findAll(dbDevice);
			if(ii.iterator().hasNext()) {//有可能用户相同，有可能用户不同
				dbDevice = ii.iterator().next();
				if(dbDevice.getUserId().equals(deviceModel.getUserId())||dbDevice.getUserId().equals(deviceModel.getPreUserId())) {
					//如果是同一个用户操作，则无所谓
					//如果是不同用户操作且用户名填对，则可以重新绑定
					deviceModel.setId(dbDevice.getId());
				}
			}
			DeviceModel res = DeviceService.getInstance().save(deviceModel);
			if(res==null) {return falseRet;}
			return new StdRespDTO<String>(true);
	}
	
	
	@GetMapping(path="/delete")
	public String delete(Long id) {
		ReportStatusModel dm = new ReportStatusModel();
		List<ReportStatusModel> list = new ArrayList<ReportStatusModel>();
		dm.setId(id);
		list.add(dm);
		reportStatusService.deleteInBatch(list);
		return "OK";
	}
	
	@PostMapping(path="/isOnline")
	public Map<String,OnlineStatusDTO> isOnline(@RequestBody String[] deviceIds){
		logger.info("deviceIds:"+deviceIds.toString());
		Map<String,OnlineStatusDTO> map = new HashMap<String,OnlineStatusDTO>();
		for( String deviceId :deviceIds ) {
			//Channel ch  = ConcurrentHashMapCache.getInstance().get(deviceId);
			String key=redis.get(deviceId);
			Channel ch=null;
			if(null!=key) {
			ch  = ConcurrentHashMapCache.getInstance().get(deviceId);
			}
			OnlineStatusDTO onlineStatusDTO = new OnlineStatusDTO();
			Boolean isOnline =( ch!=null && ch.isActive() == true);
			onlineStatusDTO.setOnline(isOnline);
			if(isOnline) {
				String address = ch.remoteAddress().toString();
				int index 	= address.indexOf(":");
				String ip   = address.substring(1, index);
				String port = address.substring(index+1);
				onlineStatusDTO.setIp(ip);
				onlineStatusDTO.setPort(port);
			}
			map.put(deviceId, onlineStatusDTO);
			
		}
		return map;
	}
	
	
	@PostMapping(path="/deviceIsOnline")
	public boolean deviceIsOnline(@RequestBody String deviceId){
		System.out.println("isOnline:"+JSON.toJSONString(deviceId));
		Channel c= ConcurrentHashMapCache.getInstance().get(deviceId);
		if(null==c) {
			return false;
		}
			
		return true;
	}
	
	
	@PostMapping(path="/play/{userId}/{deviceId}/{opCode}/{index_}")
	public String play(@RequestBody Iterable<AudioDTO > audioList,@PathVariable("userId") String userId,@PathVariable("deviceId") String deviceId,@PathVariable("opCode") String opCode,@PathVariable("index_") int index_) {
		String key=redis.get(deviceId);
		if(null==key) {
			 ConcurrentHashMapCache.getInstance().remove(deviceId);
			return "设备:"+deviceId+"不在线";
			
		}
		Channel channel = ConcurrentHashMapCache.getInstance().get(deviceId);
		if(channel == null || channel.isActive() ==false ) {
			return "设备:"+deviceId+"不在线";
		}
		ServerRespDataDTO rdk = new ServerRespDataDTO();
		rdk.setDeviceId(deviceId);
		rdk.setUserId(userId);
		rdk.setAudioList(audioList);
		rdk.setIndex(index_);
		Iterator<AudioDTO> iter  = audioList.iterator();
		int index = 0;
		while(iter.hasNext()) {
			AudioDTO audioDTO = iter.next();
			audioDTO.setIndex(index++);
			audioDTO.setFilepath(Constant.qiniuDomain+audioDTO.getFilepath());
		}
		StdRespDTO<ServerRespDataDTO> resp = new  StdRespDTO<ServerRespDataDTO>(true);
		resp.setOpConn("0");
		resp.setOpCode(opCode);
		resp.setData(rdk);
		ReturnUtils.writeAndFlush(resp, channel);
		return "000";
	}
}

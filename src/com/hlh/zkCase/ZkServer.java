package com.hlh.zkCase;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.ZooDefs.Ids;

public class ZkServer {
	// 连接的zk的地址及端口号配置
		private String connectString = "192.168.102.248:2181,192.168.102.249:2181,192.168.102.251:2181";
		//会话超时时间单位毫秒
		private int sessionTimeout = 2000;
		private ZooKeeper zk = null;
		private String parentNode = "/servers";
	//1获取连接
	public void getConnect() throws Exception {
		zk = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
			
			@Override
			public void process(WatchedEvent event) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	//2注册
	public void regist(String hostname) throws Exception {
		String create=zk.create(parentNode+"/server", hostname.getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
		System.out.println(hostname+ "is online" + create);
	}
	
	//3具体的业务逻辑
	public void business() throws Exception {
		System.out.println("ss 来接客");
		
		Thread.sleep(Long.MAX_VALUE);
	}
	
	
	public static void main(String[] args) throws Exception {
		//1 获取连接
		ZkServer server = new ZkServer();
		server.getConnect();
		
		//2 注册
		server.regist(args[0]);
		
		//3具体的业务逻辑
		server.business();
	}
}

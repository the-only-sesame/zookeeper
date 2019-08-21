package com.hlh.zk;

import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.junit.jupiter.api.Test;

public class ZkClient {
	// 连接的zk的地址及端口号配置
	private String connectString = "192.168.102.248:2181,192.168.102.249:2181,192.168.102.251:2181";
	//会话超时时间单位毫秒
	private int sessionTimeout = 2000;
	private ZooKeeper zkClient = null;

	//1 创建客户端
	@Test
	public void initZK() throws Exception {
		 zkClient = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
			
			@Override
			public void process(WatchedEvent event) {
				//监听发生后触发的事件
				System.out.println(event.getType()+"----"+event.getPath());
				
				try {
					Stat exists = zkClient.exists("/atguigu", true);
				} catch (KeeperException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
	
	//2 创建子节点
	@Test
	public void createNode() throws Exception{
		//参数1：创建的路径；参数2：创建节点存储的数据 ； 参数3：创建节点后节点具有的权限 ；参数4：节点类型
		String create = zkClient.create("/atguigu", "ss.avi".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		System.out.println(create);
	}
	
	//3 获取节点数据
	@Test
	public void getChild() throws Exception {
		List<String> children = zkClient.getChildren("/", false);
		
		for(String child : children) {
			System.out.println(child);
		}
		
		Thread.sleep(Long.MAX_VALUE);
	}
	
	//4 判断某一节点是否存在
	public void isExist() throws Exception {
		Stat exists = zkClient.exists("/atguigu", true);
		
		System.out.println(exists == null? "not exists":"exists");
		
		Thread.sleep(Long.MAX_VALUE);
	}
	
	
	
	
	
	
	
	
}











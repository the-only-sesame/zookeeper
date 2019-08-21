package com.hlh.zkCase;

import java.util.ArrayList;
import java.util.List;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public class ZkClient {
	// 连接的zk的地址及端口号配置
	private String connectString = "192.168.102.248:2181,192.168.102.249:2181,192.168.102.251:2181";
	// 会话超时时间单位毫秒
	private int sessionTimeout = 2000;
	private ZooKeeper zk = null;
	private String parentNode = "/servers";

	// 1获取连接
	public void getConnect() throws Exception {
		zk = new ZooKeeper(connectString, sessionTimeout, new Watcher() {

			@Override
			public void process(WatchedEvent event) {
				System.out.println(event.getType()+ "---"+ event.getPath());
				
				try {
					//保证能循环执行
					getServers();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	// 2监听节点变化
	public void getServers() throws Exception {
		// 监听parentNode路径子节点
		List<String> children = zk.getChildren(parentNode, true);
		ArrayList<String> servers = new ArrayList<>();
		// 获取所有子节点信息
		for (String child : children) {
			byte[] data = zk.getData(parentNode + "/" + child, false, null);
			// 保存信息
			servers.add(new String(data));
		}

		// 打印数据
		System.out.println(servers);
	}

	// 3具体的业务逻辑
	public void business() throws Exception {
		System.out.println("ss 来接客");

		Thread.sleep(Long.MAX_VALUE);
	}

	public static void main(String[] args) throws Exception {
		// 1获取连接
		ZkClient client = new ZkClient();
		client.getConnect();

		// 2监听节点变化
		client.getServers();

		// 3实现自己的业务逻辑
		client.business();

	}
}

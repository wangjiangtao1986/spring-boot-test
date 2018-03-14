package com.icbc.fastDFS;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class FastDFSApplicationConfig extends WebMvcConfigurerAdapter {

	
	static final String FASTDFS_CONFIG = "conf/fdfs_client.conf";

	@Bean
	public StorageClient1 initStorageClient() {
		StorageClient1 storageClient = null;
		try {
			ClientGlobal.init(FASTDFS_CONFIG);
			System.out.println("ClientGlobal.configInfo(): " + ClientGlobal.configInfo());
			TrackerClient trackerClient = new TrackerClient(ClientGlobal.g_tracker_group);
			TrackerServer trackerServer = trackerClient.getConnection();
			if (trackerServer == null) {
				throw new IllegalStateException("getConnection return null");
			}
			StorageServer storageServer = trackerClient.getStoreStorage(trackerServer);
			if (storageServer == null) {
				throw new IllegalStateException("getStoreStorage return null");
			}
			storageClient = new StorageClient1(trackerServer, storageServer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return storageClient;
	}
}
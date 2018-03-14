package com.icbc.fastDFS;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.FileInfo;
import org.csource.fastdfs.StorageClient1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.icbc.utile.FileUtil;

@Component
public class FastDFSClient {
	
	
	@Autowired
	private StorageClient1 storageClient1;

	/**
	 * 上传文件
	 * 
	 * @param file
	 *            文件对象
	 * @param fileName
	 *            文件名
	 * @return
	 */
	public String uploadFile(byte[] buff, String fileName) {
		return uploadFile(buff, fileName, null);
	}

	/**
	 * 上传文件
	 * 
	 * @param file
	 *            文件对象
	 * @param fileName
	 *            文件名
	 * @param metaList
	 *            文件元数据
	 * @return
	 */
	public String uploadFile(byte[] buff, String fileName, Map<String, String> metaList) {
		try {
			NameValuePair[] nameValuePairs = null;
			if (metaList != null) {
				nameValuePairs = new NameValuePair[metaList.size()];
				int index = 0;
				for (Iterator<Map.Entry<String, String>> iterator = metaList.entrySet().iterator(); iterator
						.hasNext();) {
					Map.Entry<String, String> entry = iterator.next();
					String name = entry.getKey();
					String value = entry.getValue();
					nameValuePairs[index++] = new NameValuePair(name, value);
				}
			}
			return storageClient1.upload_file1(buff, FileUtil.getExtensionName(fileName), nameValuePairs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取文件元数据
	 * 
	 * @param fileId
	 *            文件ID
	 * @return
	 */
	public Map<String, String> getFileMetadata(String fileId) {

		try {
			NameValuePair[] metaList = storageClient1.get_metadata1(fileId);
			if (metaList != null) {
				HashMap<String, String> map = new HashMap<String, String>();
				for (NameValuePair metaItem : metaList) {
					map.put(metaItem.getName(), metaItem.getValue());
				}
				return map;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 删除文件
	 * 
	 * @param fileId
	 *            文件ID
	 * @return 删除失败返回-1，否则返回0
	 */
	public int deleteFile(String fileId) {
		try {
			return storageClient1.delete_file1(fileId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	/**
	 * 下载文件
	 * 
	 * @param fileId
	 *            文件ID（上传文件成功后返回的ID）
	 * @return
	 */
	public byte[] downloadFile(String fileId) {
		try {
			byte[] content = storageClient1.download_file1(fileId);
			return content;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (MyException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Description:获取文件信息
	 * 
	 * @param fileId
	 * @return
	 * @see
	 */
	public FileInfo getFileInfo(String fileId) {

		try {
			FileInfo fileInfo = storageClient1.get_file_info1(fileId);
			return fileInfo;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (MyException e) {
			e.printStackTrace();
		}
		return null;
	}

}
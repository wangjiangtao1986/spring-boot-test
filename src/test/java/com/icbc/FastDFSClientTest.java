package com.icbc;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;


/**
 * FastDFS分布式文件系统交互测试
 * 
 * 按顺序测试接口功能
 * 
 * @author Administrator
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FastDFSClientTest {

//	static String fileid = "";
//    /**
//     * 文件上传测试
//     */
//    @Test
//    public void test1Upload() {
//        File file = new File("D:\\shopjsp\\workspace\\icbc-spring-boot\\src\\main\\resources\\application-dev.properties");
//        Map<String,String> metaList = new HashMap<String, String>();
//        metaList.put("width","1024");
//        metaList.put("height","768");
//        metaList.put("author","杨信");
//        metaList.put("date","20161018");
//        String fid = FastDFSClient.uploadFile(file,file.getName(),metaList);
//        System.out.println("upload local file " + file.getPath() + " ok, fileid=" + fid);
//        System.out.println(fid!=null ? "上传成功" : "上传失败");
//        fileid=fid;
//    }
//
//    /**
//     * 获取文件元数据测试
//     */
//    @Test
//    public void test2GetFileMetadata() {
//        Map<String,String> metaList = FastDFSClient.getFileMetadata(fileid);
//        for (Iterator<Map.Entry<String,String>>  iterator = metaList.entrySet().iterator(); iterator.hasNext();) {
//            Map.Entry<String,String> entry = iterator.next();
//            String name = entry.getKey();
//            String value = entry.getValue();
//            System.out.println("文件选项：" + name + " = " + value );
//        }
//    }
//
//    /**
//     * 文件下载测试
//     */
//    @Test
//    public void test3Download() {
//        int r = FastDFSClient.downloadFile(fileid, new File("DownloadFile_fid.jpg"));
//        System.out.println(r == 0 ? "下载成功" : "下载失败");
//    }
//
//    /**
//     * 文件删除测试
//     */
//    @Test
//    public void test4Delete() {
//        int r = FastDFSClient.deleteFile(fileid);
//        System.out.println(r == 0 ? "删除成功" : "删除失败");
//    }
}
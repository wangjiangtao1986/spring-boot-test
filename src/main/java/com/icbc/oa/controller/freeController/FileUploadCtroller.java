package com.icbc.oa.controller.freeController;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.icbc.fastDFS.FastDFSClient;
import com.icbc.fastDFS.FastDFSPoolClient;
import com.icbc.utile.FileUtil;

@Controller
public class FileUploadCtroller {

	@Value("${file.tmp.dir}")
	private String fileTmpDir;

	@Autowired
	private FastDFSClient fastDFSClient;

	@Autowired
	private FastDFSPoolClient fastDFSPoolClient;

	/**
	 * 上传文件
	 * 
	 * @author kokJuis
	 * @version 1.0
	 * @date 2016-12-12
	 * @return
	 */
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject uploadFile(@RequestParam MultipartFile filedata) {

		JSONObject m = new JSONObject();

		if (filedata != null && !filedata.isEmpty()) {
			try {

				// 无标签版本上传
				// String path = fastDFSClient.uploadFile(filedata.getBytes(),
				// filedata.getOriginalFilename());

				// 标签版上传
				Map<String, String> metaList = new HashMap<String, String>();
				metaList.put("author", "王江涛");
				metaList.put("date", "20180228");
				String path = fastDFSClient.uploadFile(filedata.getBytes(), filedata.getOriginalFilename(), metaList);

				m.put("code", "200");
				m.put("url", path);
				m.put("msg", "上传成功");
			} catch (Exception e) {
				e.printStackTrace();
				m.put("code", "400");
				m.put("msg", "上传失败");
			}
		} else {
			m.put("code", "400");
			m.put("msg", "参数丢失");
		}
		return m;

	}

	/**
	 * 下载文件
	 * 
	 * @author kokJuis
	 * @version 1.0
	 * @date 2016-12-12
	 * @param imagePath
	 * @param local
	 * @return
	 */
	@RequestMapping(value = "/getFileByPath", method = RequestMethod.GET)
	public void getFileByPath(HttpServletResponse response, String path, String fileName) {

		try {
			// 判断文件是否存在
			if (fastDFSClient.getFileInfo(path) != null) {
				byte[] buffer = fastDFSClient.downloadFile(path);
				// 清空response
				response.reset();
				// 设置response的Header
				response.setContentType("application/force-download");// 设置强制下载不打开
				response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
				response.addHeader("Content-Length", "" + buffer.length);
				// 通过文件流的形式写到客户端
				OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
				response.setContentType("application/octet-stream");
				toClient.write(buffer);
				// 写完以后关闭文件流
				toClient.flush();
				toClient.close();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 上传文件
	 * 
	 * @author kokJuis
	 * @version 1.0
	 * @date 2016-12-12
	 * @return
	 */
	@RequestMapping(value = "/uploadFilePool", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject uploadFilePool(@RequestParam MultipartFile filedata) {

		JSONObject m = new JSONObject();

		if (filedata != null && !filedata.isEmpty()) {
			try {

				String path = fastDFSPoolClient.uploadFile(filedata.getBytes(), filedata.getOriginalFilename());

				m.put("code", "200");
				m.put("url", path);
				m.put("msg", "上传成功");
			} catch (Exception e) {
				e.printStackTrace();
				m.put("code", "400");
				m.put("msg", "上传失败");
			}
		} else {
			m.put("code", "400");
			m.put("msg", "参数丢失");
		}
		return m;

	}

	/**
	 * 下载文件
	 * 
	 * @author kokJuis
	 * @version 1.0
	 * @date 2016-12-12
	 * @param imagePath
	 * @param local
	 * @return
	 */
	@RequestMapping(value = "/getFileByPathPool", method = RequestMethod.GET)
	public void getFileByPathPool(HttpServletResponse response, String path) {

		try {
			// 判断文件是否存在
			if (fastDFSClient.getFileInfo(path) != null) {
				byte[] buffer = fastDFSPoolClient.downloadFile(path);
				// 清空response
				response.reset();
				// 设置response的Header
				response.addHeader("Content-Disposition", "attachment;filename=" + FileUtil.getOriginalFilename(path));
				response.addHeader("Content-Length", "" + buffer.length);
				// 通过文件流的形式写到客户端
				OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
				response.setContentType("application/octet-stream");
				toClient.write(buffer);
				// 写完以后关闭文件流
				toClient.flush();
				toClient.close();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 实现文件上传
	 */
	@RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject fileUpload(@RequestParam("fileName") MultipartFile file) {

		JSONObject jo = new JSONObject();

		int no = 0;
		String msg = "上传失败！";

		if (!file.isEmpty()) {
			String fileName = file.getOriginalFilename();

			File dest = new File(fileTmpDir + "/" + fileName);

			if (!dest.getParentFile().exists()) {
				// 判断文件父目录是否存在
				dest.getParentFile().mkdir();
			}

			try {
				// file.getBytes()
				file.transferTo(dest); // 保存文件
				no = 1;
				msg = "上传成功！";
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		jo.put("no", no);
		jo.put("msg", msg);

		return jo;
	}

	@RequestMapping(value = "/fileDownload", method = RequestMethod.GET)
	public ResponseEntity<?> getGwFileContent(@RequestParam String fileName, @RequestParam int flag) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		String filepath = fileTmpDir + "/" + fileName;
		;

		InputStream is = null;

		try {
			headers.add("Content-Disposition",
					String.format("attachment; filename=\"%s\"", new String(fileName.getBytes("UTF-8"), "ISO8859-1")));
			is = new FileInputStream(new File(filepath));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");

		return ResponseEntity.ok().headers(headers).contentType(MediaType.parseMediaType("application/octet-stream"))
				.body(new InputStreamResource(is));
	}

}

//
//
// /**
// * 实现文件上传
// * */
// @RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
// @ResponseBody
// public JSONObject fileUpload(@RequestParam("fileName") MultipartFile file){
//
// JSONObject jo = new JSONObject();
//
// int no = 0;
// String msg = "上传失败！";
//
// if(!file.isEmpty()){
// String fileName = file.getOriginalFilename();
//
// File dest = new File(path + "/" + fileName);
//
// if(!dest.getParentFile().exists()){
// //判断文件父目录是否存在
// dest.getParentFile().mkdir();
// }
//
// try {
//// file.getBytes()
// file.transferTo(dest); //保存文件
// no = 1;
// msg = "上传成功！";
// } catch (IllegalStateException e) {
// e.printStackTrace();
// } catch (IOException e) {
// e.printStackTrace();
// }
// }
//
// jo.put("no",no);
// jo.put("msg", msg);
//
// return jo;
// }
//
//
// @RequestMapping( value = "/fileDownload", method = RequestMethod.GET)
// public ResponseEntity<?> getGwFileContent(@RequestParam String fileName,
// @RequestParam int flag) {
// HttpHeaders headers = new HttpHeaders();
// headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
// String filepath = path+"/"+fileName;;
//
// InputStream is = null;
//
// try {
// headers.add("Content-Disposition", String.format("attachment;
// filename=\"%s\"", new String(fileName.getBytes("UTF-8"), "ISO8859-1")));
//// if(flag==0){//表示获取缩略图
//// File file = new File(filepath);
//// filepath = path+"/xx"+fileName;
//// File xxFile = new File(filepath);
//// if(!xxFile.exists()){//不存在就生成缩略图
//// Thumbnails.of(file).scale(0.25f).toFile(xxFile);
//// }
//// }
// is = new FileInputStream(new File(filepath));
// } catch (UnsupportedEncodingException e) {
// e.printStackTrace();
// } catch (FileNotFoundException e) {
// e.printStackTrace();
// } catch (Exception e) {
// e.printStackTrace();
// }
//
// headers.add("Pragma", "no-cache");
// headers.add("Expires", "0");
//
// return ResponseEntity
// .ok()
// .headers(headers)
// .contentType(MediaType.parseMediaType("application/octet-stream"))
// .body(new InputStreamResource(is));
// }
// }

package com.c2c.util;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.URLDecoder;

/**
 * Copyright: Copyright (c) 2018 cc
 *
 * @ClassName: FastDFSClientUtil
 * @Description: FastDFS的工具类
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/1/9 15:49
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 */
public class FastDFSClientUtil {

        private TrackerClient trackerClient = null;
        private TrackerServer trackerServer = null;
        private StorageServer storageServer = null;
        private StorageClient1 storageClient = null;
        public FastDFSClientUtil(String conf) throws Exception {
            if (conf.contains("classpath:")) {
                conf = conf.replace("classpath:", URLDecoder.decode(this.getClass().getResource("/").getPath(),"utf-8"));
            }
            ClientGlobal.init(conf);
            trackerClient = new TrackerClient();
            trackerServer = trackerClient.getConnection();
            storageServer = null;
            storageClient = new StorageClient1(trackerServer, storageServer);
        }

        /**
         * 上传文件方法
         * <p>Title: uploadFile</p>
         * <p>Description: </p>
         * @param fileName 文件全路径
         * @param extName 文件扩展名，不包含（.）
         * @param metas 文件扩展信息
         * @return
         * @throws Exception
         */
        public String uploadFile(String fileName, String extName, NameValuePair[] metas) throws Exception {
            String result = storageClient.upload_file1(fileName, extName, metas);
            return result;
        }

        public String uploadFile(String fileName) throws Exception {
            return uploadFile(fileName, null, null);
        }

        public String uploadFile(String fileName, String extName) throws Exception {
            return uploadFile(fileName, extName, null);
        }

        /**
         * 上传文件方法
         * <p>Title: uploadFile</p>
         * <p>Description: </p>
         * @param fileContent 文件的内容，字节数组
         * @param extName 文件扩展名
         * @param metas 文件扩展信息
         * @return
         * @throws Exception
         */
        public String uploadFile(byte[] fileContent, String extName, NameValuePair[] metas) throws Exception {

            String result = storageClient.upload_file1(fileContent, extName, metas);
            return result;
        }

        public String uploadFile(byte[] fileContent) throws Exception {
            return uploadFile(fileContent, null, null);
        }

        public String uploadFile(byte[] fileContent, String extName) throws Exception {
            return uploadFile(fileContent, extName, null);
        }

        /**
         * 文件的下载方法
         * @throws Exception
         * @throws IOException
         */
        public void download_file(String path,BufferedOutputStream output) throws IOException, Exception{
            //byte[] b = storageClient.download_file(group, path);
            byte[] b = storageClient.download_file1(path);
            try{
                if(b != null){
                    output.write(b);
                }
            }catch (Exception e){} //用户可能取消了下载
            finally {
                if (output != null)
                    try {
                        output.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
        }
        /**
         * 文件的删除方法
         * 返回值：0删除成功
         * @param group fastdfs的组名
         * @param storagePath 文件的storagePath路径
         * @throws Exception
         * @throws IOException
         */
        public Integer delete_file(String group ,String storagePath) throws IOException, Exception{
            return  storageClient.delete_file(group, storagePath);
        }
}

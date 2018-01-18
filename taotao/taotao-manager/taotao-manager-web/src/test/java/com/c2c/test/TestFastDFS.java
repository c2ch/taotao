package com.c2c.test;

import com.c2c.util.FastDFSClientUtil;
import org.csource.fastdfs.*;
import org.junit.Test;

/**
 *
 *
 * @ClassName: TestFastDFS
 * @Description:
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/1/10 9:18
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 */
public class TestFastDFS {

    @Test
    public void testFastDFS() throws Exception {

        FastDFSClientUtil fastDFSClientUtil = new FastDFSClientUtil("E:\\GitProject\\repository\\taotao\\taotao-manager\\taotao-manager-web\\src\\main\\resources\\properties\\client.conf");
        //url:group1/M00/00/00/wKg4gFpUpMiAXGioAABdrZgsqUU004_big.jpg
        String url = fastDFSClientUtil.uploadFile("E:\\tempPic\\fj.jpg","jpg",null);
    }

    @Test
    public void testUpload() throws Exception {
        // 1、把FastDFS提供的jar包添加到工程中
        // 2、初始化全局配置。加载一个配置文件。
        ClientGlobal.init("E:\\GitProject\\repository\\taotao\\taotao-manager\\taotao-manager-web\\src\\main\\resources\\properties\\client.conf");
        // 3、创建一个TrackerClient对象。
        TrackerClient trackerClient = new TrackerClient();
        // 4、创建一个TrackerServer对象。
        TrackerServer trackerServer = trackerClient.getConnection();
        // 5、声明一个StorageServer对象，null。
        StorageServer storageServer = null;
        // 6、获得StorageClient对象。
        StorageClient storageClient = new StorageClient(trackerServer, storageServer);
        // 7、直接调用StorageClient对象方法上传文件即可。
        String[] strings = storageClient.upload_file("E:\\tempPic\\fj.jpg", "jpg", null);
        for (String string : strings) {
            System.out.println(string);
        }
    }
}

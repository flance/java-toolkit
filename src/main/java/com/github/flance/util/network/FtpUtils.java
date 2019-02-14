package com.github.flance.util.network;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.*;


/**
 * ftp上传下载工具类
 *
 * @author zhangying
 * @date 2018/12/12 11:07
 */
public class FtpUtils {
    /**
     * 连接ftp,获取FTPClient对象备用
     *
     * @param host     ftp ip或hostname
     * @param port     ftp port
     * @param username ftp 帐号
     * @param pwd      ftp 帐号密码
     * @return FTPClient引用
     * @throws UncheckedIOException 连接ftp失败信息
     */
    public static FTPClient getFTPClient(String host, int port, String username, String pwd) throws UncheckedIOException {
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(host, port);
            ftpClient.login(username, pwd);
        } catch (IOException e) {
            throw new UncheckedIOException("connect to ftp [" + username + "@" + host + ":" + port + "] failed.", e);
        }
        return ftpClient;
    }

    /**
     * 关闭FTPClient连接资源
     *
     * @param ftpClient 已获取的ftp连接
     * @throws IOException
     */
    public static void closeFTPClient(FTPClient ftpClient) throws IOException {
        if (ftpClient != null && ftpClient.isConnected()) {
            ftpClient.logout();
            ftpClient.disconnect();
        }
    }

    /**
     * 从ftp下载文件
     *
     * @param host      ftp ip或hostname
     * @param port      ftp port
     * @param username  ftp 帐号
     * @param pwd       ftp 帐号密码
     * @param ftpPath   ftp文件所在目录
     * @param ftpFile   ftp文件
     * @param localPath 下载至本地目录...
     * @return 下载是否成功
     * @throws IllegalArgumentException 下载文件参数不合法
     * @throws IOException
     */
    public static boolean download(String host, int port, String username, String pwd, String ftpPath, String ftpFile, String localPath) throws IOException {
        boolean isOK = true;
        FTPClient ftpClient = null;
        FileOutputStream outputStream = null;
        try {
            ftpClient = getFTPClient(host, port, username, pwd);
            File target = new File(localPath);
            outputStream = new FileOutputStream(target);
            FTPFile[] ftpFiles = ftpClient.listFiles(ftpPath);
            for (FTPFile file : ftpFiles) {
                if (file.getName().equals(ftpFile)) {
                    ftpClient.retrieveFile(file.getName(), outputStream);
                }
            }
        } catch (IOException e) {
            isOK = false;
            throw new UncheckedIOException("download file from [" + ftpPath + "/" + ftpFile + "] to [" + localPath + "] failed.", e);
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
            closeFTPClient(ftpClient);
        }
        return isOK;
    }

    /**
     * 本地文件上传至ftp
     *
     * @param localFile 本地文件全路径
     * @param host      ftp ip或hostname
     * @param port      ftp port
     * @param username  ftp 帐号
     * @param pwd       ftp 帐号密码
     * @param ftpPath   ftp路径
     * @return 上传是否成功
     * @throws IOException
     */
    public static boolean upload(String localFile, String host, int port, String username, String pwd, String ftpPath) throws IOException {
        FTPClient ftpClient = null;
        boolean isOK = true;
        FileInputStream in = null;
        try {
            ftpClient = getFTPClient(host, port, username, pwd);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileTransferMode(FTPClient.BINARY_FILE_TYPE);
            in = new FileInputStream(localFile);
            ftpClient.changeWorkingDirectory(ftpPath);
            ftpClient.storeFile(localFile, in);
        } catch (IOException e) {
            isOK = false;
            throw new UncheckedIOException("upload file [" + localFile + "] to ftp [" + ftpPath + "] failed.", e);
        } finally {
            if (in != null) {
                in.close();
            }
            closeFTPClient(ftpClient);
        }
        return true;
    }
}

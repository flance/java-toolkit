package com.github.flance.util.servlet;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * servlet post方式下使用的ServletInputStream实现<br/>
 * .解决ServletInputStream.getInputStream只能读取一次http body的问题
 *
 * @author zhangying
 * @date 2018年10月18日 下午7:45:35
 */
public class PostServletInputStream extends ServletInputStream {

    private InputStream inputStream;

    public PostServletInputStream(byte[] in) throws IOException {
        inputStream = new ByteArrayInputStream(in);
    }

    @Override
    public int read() throws IOException {
        return inputStream.read();
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean isReady() {
        return true;
    }

    @Override
    public void setReadListener(ReadListener readListener) {
    }
}

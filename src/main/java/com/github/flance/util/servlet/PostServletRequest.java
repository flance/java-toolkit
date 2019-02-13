package com.github.flance.util.servlet;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * servlet post方式下使用的ServletRequest实现<br/>
 * .解决servlet post中http body只能读取一次的问题
 *
 * @author zhangying
 * @date 2018年10月18日 下午7:48:02
 */
public class PostServletRequest extends HttpServletRequestWrapper {

    private byte[] in = null;

    public PostServletRequest(HttpServletRequest request) {
        super(request);
    }

    public PostServletRequest(HttpServletRequest request, byte[] in) {
        super(request);
        this.in = in;
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        return in != null ? new PostServletInputStream(in) : super.getInputStream();
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return this.in != null ? new BufferedReader(new InputStreamReader(getInputStream())) : super.getReader();
    }
}

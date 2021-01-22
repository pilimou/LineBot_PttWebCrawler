package com.example.demo.pttgamesale.http;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.client.protocol.HttpClientContext;

public class BasicHttp {
	private HttpClientContext context = HttpClientContext.create();
	private List<NameValuePair> nvps = new ArrayList<NameValuePair>();
	private List<Header> header = new ArrayList<Header>();
	private Charset encode = StandardCharsets.UTF_8;
	private boolean joinLine = false;
	public HttpClientContext getContext() {
		return context;
	}
	public void setContext(HttpClientContext context) {
		this.context = context;
	}
	public List<NameValuePair> getNvps() {
		return nvps;
	}
	public void setNvps(List<NameValuePair> nvps) {
		this.nvps = nvps;
	}
	public List<Header> getHeader() {
		return header;
	}
	public void setHeader(List<Header> header) {
		this.header = header;
	}
	public Charset getEncode() {
		return encode;
	}
	public void setEncode(Charset encode) {
		this.encode = encode;
	}
	public boolean isJoinLine() {
		return joinLine;
	}
	public void setJoinLine(boolean joinLine) {
		this.joinLine = joinLine;
	}
	
	
}

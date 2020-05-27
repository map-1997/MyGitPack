package handler;

import java.lang.reflect.Proxy;

public class Utils {
	public Object getProxy(Object o) {
		MyHandler mh = new MyHandler(o);
		
		Object obj = Proxy.newProxyInstance(mh.getClass().getClassLoader(), o.getClass().getInterfaces(), mh);
		
		return obj;
	}
}

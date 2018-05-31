/**
 * Copyright (c) 2011-2015, James Zhan 詹波 (jfinal@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jfinal.core;

import java.util.List;
import javax.servlet.ServletContext;
import com.jfinal.config.Constants;
import com.jfinal.config.JFinalConfig;
import com.jfinal.handler.Handler;
import com.jfinal.handler.HandlerFactory;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.IPlugin;
import com.jfinal.render.RenderFactory;
import com.jfinal.server.IServer;
import com.jfinal.server.ServerFactory;
import com.jfinal.token.ITokenCache;
import com.jfinal.token.TokenManager;
import com.jfinal.upload.OreillyCos;

/**
 * JFinal
 */
public final class JFinal {
	
	private Constants constants;
	private ActionMapping actionMapping;
	private Handler handler;
	private ServletContext servletContext;
	private static IServer server;
	private String contextPath = "";
	
	Handler getHandler() {
		return handler;
	}
	
	private static final JFinal me = new JFinal();
	
	private JFinal() {
	}
	
	public static JFinal me() {
		return me;
	}
	
	boolean init(JFinalConfig jfinalConfig, ServletContext servletContext) {
		this.servletContext = servletContext;
		this.contextPath = servletContext.getContextPath();
		
		initPathUtil();
		
		Config.configJFinal(jfinalConfig);	// start plugin and init logger factory in this method
		constants = Config.getConstants();
		
		initActionMapping();
		initHandler();
		initRender();
		initOreillyCos();
		initTokenManager();
		
		return true;
	}
	
	private void initTokenManager() {
		ITokenCache tokenCache = constants.getTokenCache();
		if (tokenCache != null)
			TokenManager.init(tokenCache);
	}
	
	private void initHandler() {
		Handler actionHandler = new ActionHandler(actionMapping, constants);
		handler = HandlerFactory.getHandler(Config.getHandlers().getHandlerList(), actionHandler);
	}
	
	private void initOreillyCos() {
		OreillyCos.init(constants.getUploadedFileSaveDirectory(), constants.getMaxPostSize(), constants.getEncoding());
	}
	
	private void initPathUtil() {
		String path = servletContext.getRealPath("/");
		PathKit.setWebRootPath(path);
	}
	
	private void initRender() {
		RenderFactory renderFactory = RenderFactory.me();
		renderFactory.init(constants, servletContext);
	}
	
	private void initActionMapping() {
		actionMapping = new ActionMapping(Config.getRoutes(), Config.getInterceptors());
		actionMapping.buildActionMapping();
	}
	
	void stopPlugins() {
		List<IPlugin> plugins = Config.getPlugins().getPluginList();
		if (plugins != null) {
			for (int i=plugins.size()-1; i >= 0; i--) {		// stop plugins
				boolean success = false;
				try {
					success = plugins.get(i).stop();
				} 
				catch (Exception e) {
					success = false;
					e.printStackTrace();
				}
				if (!success) {
					System.err.println("Plugin stop error: " + plugins.get(i).getClass().getName());
				}
			}
		}
	}
	
	public ServletContext getServletContext() {
		return this.servletContext;
	}
	
	public static void start() {
		server = ServerFactory.getServer();
		server.start();
	}
	
	public static void start(String webAppDir, int port, String context, int scanIntervalSeconds) {
		server = ServerFactory.getServer(webAppDir, port, context, scanIntervalSeconds);
		server.start();
	}
	
	public static void stop() {
		server.stop();
	}
	public static void runOnServer()
	{
		/**
         * 特别注意：IDEA 之下建议的启动方式，仅比 eclipse 之下少了最后一个参数
         */
        String baseBath = String.valueOf(JFinal.class.getProtectionDomain().getCodeSource().getLocation());
        String classPath, webRootPath, jarPath;
        if (StrKit.notBlank(baseBath) && baseBath.contains("file:/")) {
            // 获取运行操作系统的运行方式  window和linux的细微区别
            boolean windows = System.getProperties().getProperty("os.name").contains("Windows");
            System.out.println(System.getProperties().getProperty("os.name"));
            jarPath = (windows ? "" : "/") + baseBath.substring("file:/".length());
            
            classPath = (windows ? "" : "/") + jarPath.substring(0, jarPath.lastIndexOf("/")) + "/puresport/WebContent/WEB-INF/classes";
            webRootPath = (windows ? "" : "/") + jarPath.substring(0, jarPath.lastIndexOf("/")) + "/puresport/WebContent";
//            classPath = "E:/codingWorkspace/puresport/WebContent/WEB-INF/classes";
            System.out.println("jarPath:" + jarPath);
            System.out.println("webRootPath:" + classPath);
            System.out.println("classPath:" + classPath);
//            webRootPath = classPath;
//            ZipUtil.unzip(jarPath, classPath);
            // 这两步是核心指定 webapp目录和classpath目录
            PathKit.setWebRootPath(webRootPath);
            PathKit.setRootClassPath(classPath);
            // eclipse 启动是4个参数    
            JFinal.start(webRootPath, 80, "/",5);
        } else {
            throw new RuntimeException("你丫的路径不对!");
        }
    }
	
	/**
	 * Run JFinal Server with Debug Configurations or Run Configurations in Eclipse JavaEE
	 * args example: WebRoot 80 / 5
	 */
	public static void main(String[] args) {
		//runOnServer();
		if (args == null || args.length == 0) {
			server = ServerFactory.getServer();
			server.start();
		}
	
		else {
			String webAppDir = args[0];
			int port = Integer.parseInt(args[1]);
			String context = args[2];
			int scanIntervalSeconds = Integer.parseInt(args[3]);
			server = ServerFactory.getServer(webAppDir, port, context, scanIntervalSeconds);
			server.start();
		}
	}
	
	public List<String> getAllActionKeys() {
		return actionMapping.getAllActionKeys();
	}
	
	public Constants getConstants() {
		return Config.getConstants();
	}
	
	public Action getAction(String url, String[] urlPara) {
		return actionMapping.getAction(url, urlPara);
	}
	
	public String getContextPath() {
		return contextPath;
	}
}











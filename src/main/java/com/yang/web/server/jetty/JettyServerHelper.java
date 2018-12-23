package com.yang.web.server.jetty;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.jboss.resteasy.core.Dispatcher;
import org.jboss.resteasy.plugins.server.servlet.HttpServletDispatcher;
import org.jboss.resteasy.plugins.server.servlet.ResteasyBootstrap;
import org.jboss.resteasy.spi.ResteasyDeployment;
import org.springframework.beans.factory.InitializingBean;

public class JettyServerHelper implements InitializingBean {

    private Server server;
    private Dispatcher restDispatcher;
    private ResteasyDeployment resteasyDeployment;

    public void afterPropertiesSet() throws Exception {
        initServer();
    }

    private void initServer() throws Exception {
        server = new Server();
        HandlerList handlerList = new HandlerList();

        ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        servletContextHandler.setInitParameter("resteasy.use.builtin.providers","true");
        servletContextHandler.setInitParameter("resteasy.servlet.mapping.prefix","/res");
        servletContextHandler.setInitParameter("resteasy.media.types.mappings","txt: text/plain, html: text/html, xml: applicaTion/xml, json: application.json");
        /*servletContextHandler.setInitParameter("resteasy.providers",DefaultExce)*/
        servletContextHandler.setContextPath("yangws");
        servletContextHandler.setClassLoader(Thread.currentThread().getContextClassLoader());

        ResteasyBootstrap rb = new ResteasyBootstrap();
        servletContextHandler.addEventListener(rb);
        HttpServletDispatcher httpServletDispatcher = new HttpServletDispatcher();
        servletContextHandler.addServlet(new ServletHolder(httpServletDispatcher),"/res/*");
        handlerList.addHandler(servletContextHandler);
        handlerList.addHandler(new DefaultHandler());


        configureHttp(server);
        server.setHandler(handlerList);
        server.start();
        System.out.println("Jetty server started now");
        this.resteasyDeployment = (ResteasyDeployment) servletContextHandler.getServletContext().getAttribute(ResteasyDeployment.class.getName());
        this.restDispatcher = resteasyDeployment.getDispatcher();
    }

    private void configureHttp(Server server) {
        ServerConnector serverConnector = new ServerConnector(server);
        serverConnector.setPort(8080);
        server.addConnector(serverConnector);

    }

    public Dispatcher getRestDispatcher() {
        return restDispatcher;
    }

    public void setRestDispatcher(Dispatcher restDispatcher) {
        this.restDispatcher = restDispatcher;
    }

    public ResteasyDeployment getResteasyDeployment() {
        return resteasyDeployment;
    }

    public void setResteasyDeployment(ResteasyDeployment resteasyDeployment) {
        this.resteasyDeployment = resteasyDeployment;
    }
}

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package org.xhubacubi.jarhalla.client.services;

import org.xhubacubi.jarhalla.client.services.impl.ClassDaoFileImpl;
import org.xhubacubi.jarhalla.client.services.impl.JarDaoFileImpl;
import org.xhubacubi.jarhalla.client.services.impl.RepositoryDaoFileImpl;

/**
 *
 * @author rugi
 */
public class DemiurgoFacade {

    /**
     * 
     */
    private static DemiurgoFacade instance;
    /**
     * 
     */
    private ServicePrimus service;

    /**
     * 
     */
    private DemiurgoFacade() {
        super();
        this.service = new ServicePrimus(new RepositoryDaoFileImpl(),
                new JarDaoFileImpl(), 
                new ClassDaoFileImpl());
    }

    /**
     * 
     * @return 
     */
    public static DemiurgoFacade getInstance() {
        if (instance == null) {
            instance = new DemiurgoFacade();
        }
        return instance;
    }

    /**
     * @return the service
     */
    public ServicePrimus getService() {
        return service;
    }

    /**
     * @param service the service to set
     */
    public void setService(ServicePrimus service) {
        this.service = service;
    }
    
}

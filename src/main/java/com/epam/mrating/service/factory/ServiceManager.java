package com.epam.mrating.service.factory;

import com.epam.mrating.service.impl.FinalServiceFactory;

/**
 * The type Service configuration.
 */
public final class ServiceManager {
    private static ServiceFactory finalServiceFactory;

    static {
        init();
    }

    private ServiceManager() {
    }

    private static void init(){
        finalServiceFactory = FinalServiceFactory.SERVICE_FACTORY_INSTANCE;
    }

    /**
     * Gets service factory.
     *
     * @param serviceType the service type
     * @return the service factory
     */
    public static ServiceFactory getServiceFactory(ServiceType serviceType) {
        switch (serviceType){
            case FINAL_SERVICE:
                return finalServiceFactory;

            default:
                return null;
        }
    }
}

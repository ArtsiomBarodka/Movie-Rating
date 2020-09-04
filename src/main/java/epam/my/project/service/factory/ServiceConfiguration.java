package epam.my.project.service.factory;

/**
 * The type Service configuration.
 */
public final class ServiceConfiguration {
    private static ServiceFactory finalServiceFactory;

    static {
        init();
    }

    private ServiceConfiguration() {
    }

    private static void init(){
        finalServiceFactory = epam.my.project.service.impl.FinalServiceFactory.SERVICE_FACTORY_INSTANCE;
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

package fish.payara.microservices.integrated.common.messages;

import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public class EntityMessageFormatterFactory {

    private static final EntityMessageFormatterFactory FACTORY = new EntityMessageFormatterFactory();

    private List<EntityMessageFormatter> formatters;

    public EntityMessageFormatterFactory() {
        loadImplementations();
    }

    private void loadImplementations() {
        ServiceLoader<EntityMessageFormatter> loader = ServiceLoader.load(EntityMessageFormatter.class);
        formatters = loader.stream().map(ServiceLoader.Provider::get).collect(Collectors.toList());
    }

    String getFormatter(Object entity) {
        return formatters.stream()
                .filter(f -> f.supportsEntity(entity))
                .map(f -> f.messageForEntity(entity))
                .findAny()
                .orElse(String.format("No formatter available for '%s'", entity.getClass().getName()));
    }

    public static String getEntityIdText(Object entity) {
        return FACTORY.getFormatter(entity);
    }
}

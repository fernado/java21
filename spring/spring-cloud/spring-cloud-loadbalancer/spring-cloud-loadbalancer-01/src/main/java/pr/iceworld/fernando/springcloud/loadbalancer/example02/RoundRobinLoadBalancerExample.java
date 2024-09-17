package pr.iceworld.fernando.springcloud.loadbalancer.example02;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.Request;
import org.springframework.cloud.client.loadbalancer.Response;
import org.springframework.cloud.loadbalancer.core.ReactorServiceInstanceLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

// ServiceInstance implementation
class SimpleServiceInstance implements ServiceInstance {

    private final String serviceId;
    private final String host;
    private final int port;

    public SimpleServiceInstance(String serviceId, String host, int port) {
        this.serviceId = serviceId;
        this.host = host;
        this.port = port;
    }

    @Override
    public String getServiceId() {
        return serviceId;
    }

    @Override
    public String getHost() {
        return host;
    }

    @Override
    public int getPort() {
        return port;
    }

    @Override
    public boolean isSecure() {
        return false;
    }

    @Override
    public URI getUri() {
        return URI.create(String.format("http://%s:%d", host, port));
    }

    @Override
    public Map<String, String> getMetadata() {
        return Collections.emptyMap();
    }

    @Override
    public String getScheme() {
        return "http";
    }

    @Override
    public String getInstanceId() {
        return null;
    }
}

// ServiceInstanceListSupplier implementation
class SimpleServiceInstanceListSupplier implements ServiceInstanceListSupplier {

    List<ServiceInstance> instances = new CopyOnWriteArrayList<>();

    @Override
    public String getServiceId() {
        return "my-service";
    }

    @Override
    public Flux<List<ServiceInstance>> get(Request request) {
        return get();
    }

    @Override
    public Flux<List<ServiceInstance>> get() {
        // Creating a list of service instances
        return Flux.just(instances);
    }

    // Simulate the addition of a service instance
    public void addInstance(String host, int port) {
        instances.add(new SimpleServiceInstance(getServiceId(), host, port));
    }

    // Simulate the removal of a service instance
    public void removeInstance(String host, int port) {
        instances.removeIf(instance -> instance.getHost().equals(host) && instance.getPort() == port);
    }
}

// RoundRobinLoadBalancer implementation
class RoundRobinLoadBalancer implements ReactorServiceInstanceLoadBalancer {

    private final ServiceInstanceListSupplier supplier;
    private final AtomicInteger position = new AtomicInteger(0);

    public RoundRobinLoadBalancer(ServiceInstanceListSupplier supplier) {
        this.supplier = supplier;
    }

    @Override
    public Mono<Response<ServiceInstance>> choose(Request request) {
        // Getting service instances and applying round-robin algorithm
        return supplier.get(request).next().map(this::processInstanceResponse);
    }

    private Response<ServiceInstance> processInstanceResponse(List<ServiceInstance> serviceInstances) {
        if (serviceInstances.isEmpty()) {
            return new Response<>() {
                @Override
                public boolean hasServer() {
                    return false;
                }

                @Override
                public ServiceInstance getServer() {
                    return null;
                }
            };
        }
        int pos = Math.abs(position.getAndIncrement() % serviceInstances.size());
        return new Response<>() {
            @Override
            public boolean hasServer() {
                return serviceInstances.size() > 0;
            }

            @Override
            public ServiceInstance getServer() {
                return serviceInstances.get(pos);
            }
        };
    }
}

// Main class to test RoundRobinLoadBalancer
public class RoundRobinLoadBalancerExample {

    public static void main(String[] args) {
        // Initialize a ServiceInstanceListSupplier with service instances
        SimpleServiceInstanceListSupplier supplier = new SimpleServiceInstanceListSupplier();

        supplier.addInstance("localhost", 8080);
        supplier.addInstance("localhost", 8081);
        supplier.addInstance("localhost", 8082);
        supplier.addInstance("localhost", 8083);
        supplier.addInstance("localhost", 8084);

        // Create a round-robin load balancer
        RoundRobinLoadBalancer loadBalancer = new RoundRobinLoadBalancer(supplier);

        // Invoke the load balancer several times to see round-robin behavior
        for (int i = 0; i < 10; i++) {
            loadBalancer.choose(null).subscribe(response -> {
                ServiceInstance instance = response.getServer();
                if (instance != null) {
                    System.out.println("Chosen instance: " + instance.getHost() + ":" + instance.getPort());
                } else {
                    System.out.println("No instance available");
                }
            });
        }

        System.out.println("------------------------------------------");
        // Simulate removing an instance
        supplier.removeInstance("localhost", 8082);

        for (int i = 0; i < 10; i++) {
            loadBalancer.choose(null).subscribe(response -> {
                ServiceInstance instance = response.getServer();
                if (instance != null) {
                    System.out.println("Chosen instance: " + instance.getHost() + ":" + instance.getPort());
                } else {
                    System.out.println("No instance available");
                }
            });
        }
    }
}

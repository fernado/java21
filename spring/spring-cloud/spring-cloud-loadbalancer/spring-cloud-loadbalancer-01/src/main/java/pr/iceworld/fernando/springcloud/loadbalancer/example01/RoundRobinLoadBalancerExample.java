package pr.iceworld.fernando.springcloud.loadbalancer.example01;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.Response;
import org.springframework.cloud.loadbalancer.core.ReactorServiceInstanceLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.client.loadbalancer.Request;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Collections;

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
        return false; // Assume HTTP for simplicity
    }

    @Override
    public URI getUri() {
        return URI.create(String.format("http://%s:%d", host, port));
    }

    @Override
    public Map<String, String> getMetadata() {
        return Collections.emptyMap(); // No metadata in this simple example
    }

    @Override
    public String getScheme() {
        return "http";
    }

    @Override
    public String getInstanceId() {
        return null; // No instance ID in this simple example
    }
}

// ServiceInstanceListSupplier implementation
class SimpleServiceInstanceListSupplier implements ServiceInstanceListSupplier {

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
        List<ServiceInstance> instances = List.of(
                new SimpleServiceInstance("my-service", "localhost", 8080),
                new SimpleServiceInstance("my-service", "localhost", 8081)
        );
        return Flux.just(instances);
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
        ServiceInstanceListSupplier supplier = new SimpleServiceInstanceListSupplier();
        
        // Create a round-robin load balancer
        RoundRobinLoadBalancer loadBalancer = new RoundRobinLoadBalancer(supplier);
        
        // Choose service instance and print it
        Mono<Response<ServiceInstance>> responseMono = loadBalancer.choose(null);
        responseMono.subscribe(response -> {
            ServiceInstance instance = response.getServer();
            if (instance != null) {
                System.out.println("Chosen instance: " + instance.getHost() + ":" + instance.getPort());
            } else {
                System.out.println("No instance available");
            }
        });
    }
}

package pr.iceworld.fernando.springcloud.openfeign02.model;


import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Customer {

    private long id;
    private String name;
    private int age;

}

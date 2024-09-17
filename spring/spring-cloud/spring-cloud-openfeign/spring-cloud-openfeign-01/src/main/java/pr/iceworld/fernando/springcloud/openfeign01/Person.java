package pr.iceworld.fernando.springcloud.openfeign01;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Person {

    private String name;

    private String age;
}
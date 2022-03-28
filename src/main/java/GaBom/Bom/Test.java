package GaBom.Bom;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class Test {

    @Id
    @GeneratedValue()
    Long id;

    private String name;
    private String age;

}

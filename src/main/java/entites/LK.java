package entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Accessors(chain = true)
public class LK {
    private Integer id;
    private int age;
    private String name;
    private Date birthday;
    private HomeAddress address;
    private String gender;
    private String hobby;
    private LocalDateTime createTime;

}

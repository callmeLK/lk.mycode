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
public class LKDTO {
    private String userName;
    private Integer age;
    private Date birthday;
    private String address;
    private Gender gender;
    private String hobby;
    private String createTime;
}

package app.platform.userservice.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class VerificationToken {
    public static final int duration=4*60;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String token;
    @OneToOne(targetEntity = User.class)
    private User user;
    private Date expiryTime;

    public VerificationToken(User user){
        this.token=generateRandomToken();
        this.user=user;
        this.expiryTime=calculateTime();
    }

    public void updateToken(){
        this.token=generateRandomToken();
        this.expiryTime=calculateTime();
    }

    private String generateRandomToken(){
        return UUID.randomUUID().toString();
    }

    private Date calculateTime(){
        Calendar calendar=Calendar.getInstance();
        Date currentDate=new Date();
        calendar.setTimeInMillis(currentDate.getTime());
        calendar.add(Calendar.MINUTE,duration);

        return calendar.getTime();
    }

}

package by.twikss.belbankbot.registration.beans;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@Entity
@NoArgsConstructor
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(min = 2, max = 30, message = "Name should be between 2 and 30")
    private String name;

    @Size(min = 2, max = 30, message = "Name should be between 2 and 30")
    private String sur_name;

    @Size(min = 2, max = 30, message = "Name should be between 2 and 30")
    private String last_name;
    private String phone_number;
    private Long chat_id;
    private String bot_state;

}

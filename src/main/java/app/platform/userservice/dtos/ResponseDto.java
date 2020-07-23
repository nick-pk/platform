package app.platform.userservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
@Getter
@Setter
@AllArgsConstructor
public class ResponseDto<T> {
    private HttpStatus httpStatus;
    private T data;
}

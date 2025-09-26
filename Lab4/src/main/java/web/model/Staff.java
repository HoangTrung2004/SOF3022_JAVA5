package web.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Staff {
    @NotBlank(message = "Chưa nhập Email")
    @Email(message = "Email không đúng định dạng ")
    private String id;

    @NotBlank(message = "Chưa nhập họ và tên")
    private String fullname;

    @Builder.Default
    private String photo = "meo.jpg";

    @NotNull(message = "Chưa nhập giới tính")
    private Boolean gender = true;

    @NotNull(message = "CHưa nhập ngày sinh")
    @Past(message = "Ngày sinh phải trước hiện tại")     // phải nhỏ hơn hôm nay
    @Builder.Default
    @DateTimeFormat(pattern = "yyyy-MM-dd")              // parse date từ fosrm
    private Date birthday = new Date();                  // mặc định = hôm nay

    @NotNull(message = "Chưa nhập lương")
    @Min(value = 1000, message = "Lương tối thiểu là 1000")
    @Builder.Default
    private Double salary = 12345.6789;

    @Builder.Default
    private Integer level = 0;

}

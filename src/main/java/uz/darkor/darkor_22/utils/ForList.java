package uz.darkor.darkor_22.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ForList {
    @NotNull(message = "FUUUUUUUUUUUCCCCCCCCKKKKKKKKK")
    private List<Long> courseIds;
}

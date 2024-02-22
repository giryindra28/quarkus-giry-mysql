package api.dto.PersonDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PersonRequestDto(@NotBlank String personName, @NotBlank String city, @NotNull Double money) {

}

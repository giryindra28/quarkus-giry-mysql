package api.dto.PersonDto;

import jakarta.validation.constraints.NotNull;

public record MoneyRequestDto(@NotNull Double money) {
}

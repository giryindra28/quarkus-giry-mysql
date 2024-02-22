package api.dto.BankDto;

import api.enumeration.StatusBank;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BankRequestDto(@NotBlank String bankName, @NotBlank String description,@NotBlank StatusBank statusBank) {
}

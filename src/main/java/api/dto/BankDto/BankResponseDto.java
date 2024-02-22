package api.dto.BankDto;

import api.enumeration.StatusBank;

public record BankResponseDto(Long bankId, String bankName, String description, StatusBank statusBank) {
}

package api.dto.PersonDto;

public record PersonResponseDto(Long personId, String username,
                                String city, Double money) {
}

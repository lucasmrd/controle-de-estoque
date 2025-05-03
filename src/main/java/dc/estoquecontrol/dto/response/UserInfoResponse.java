package dc.estoquecontrol.dto.response;

import dc.estoquecontrol.entity.User;

import java.util.UUID;

public record UserInfoResponse(UUID id, String nome, String username) {
    public UserInfoResponse (User user) {
        this(user.getId(), user.getNome(), user.getUsername());
    }
}


package com.founderz.findemy.domain.user.controller.dto.request;

public record AuthRequest(
        String account_id,
        String password
) {
}

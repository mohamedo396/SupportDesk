package com.company.supportdesk.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record CreateCommentRequest(

        @NotNull(message = "Author user ID is required")
        UUID authorUserId,

        @NotBlank(message = "Comment content is required")
        @Size(
                max = 3000,
                message = "Comment cannot exceed 3000 characters"
        )
        String content

) {}